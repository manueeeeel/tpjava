package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import Clases_utilizadas.alumno;
import Clases_utilizadas.asignaturas.asignatura;
import Clases_utilizadas.clase;
import Clases_utilizadas.inscripcion;
import Clases_utilizadas.ranking;
import controladora.controladora;

public class ReportesPanel extends JPanel {
    private JTextArea areaReporte;
    private JComboBox<String> comboAsignaturas;
    private JButton btnRanking, btnDetalleAsignatura, btnLibres;
    private controladora controladora; 

    public ReportesPanel(controladora c) { //recibimos la controladora por parámetro
        this.controladora = c;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelControles = new JPanel();
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
        panelControles.setBorder(BorderFactory.createTitledBorder("Generar Reportes"));
        panelControles.setPreferredSize(new Dimension(280, 0));

        Dimension maxDim = new Dimension(Integer.MAX_VALUE, 30);

        btnRanking = new JButton("Ranking de Presentismo");
        btnRanking.setMaximumSize(maxDim);
        panelControles.add(btnRanking);
        panelControles.add(Box.createVerticalStrut(20));

        panelControles.add(new JLabel("Seleccionar Asignatura:"));
        comboAsignaturas = new JComboBox<>();
        comboAsignaturas.setMaximumSize(maxDim);
        panelControles.add(comboAsignaturas);
        panelControles.add(Box.createVerticalStrut(5));
        
        btnDetalleAsignatura = new JButton("Detalle de Alumnos");
        btnDetalleAsignatura.setMaximumSize(maxDim);
        panelControles.add(btnDetalleAsignatura);
        panelControles.add(Box.createVerticalStrut(20));

        btnLibres = new JButton("Alumnos Libres");
        btnLibres.setMaximumSize(maxDim);
        panelControles.add(btnLibres);

        areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 13)); 
        JScrollPane scrollPane = new JScrollPane(areaReporte);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Visualización del Reporte"));

        add(panelControles, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        btnRanking.addActionListener(e -> generarRanking());
        btnDetalleAsignatura.addActionListener(e -> generarDetalleAsignatura());
        btnLibres.addActionListener(e -> generarLibres());

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) { cargarComboAsignaturas(); }
        });
    }

    private void cargarComboAsignaturas() {
        comboAsignaturas.removeAllItems();
        // usamos la controladora para obtener las asignaturas
        for (asignatura asig : controladora.getAsignaturas().values()) {
            comboAsignaturas.addItem(asig.getCodigo() + " - " + asig.getNombre());
        }
    }

    private void generarRanking() {
        // llamamos al reporte desde la controladora
        ArrayList<ranking> reporte = controladora.getReporteRankingPresentismo();
        StringBuilder sb = new StringBuilder();
        
        sb.append("============================================================\n");
        sb.append("        RANKING DE ASIGNATURAS POR PORCENTAJE DE PRESENTISMO \n");
        sb.append("============================================================\n\n");
        
        if (reporte != null && !reporte.isEmpty()) {
            for (int i = reporte.size() - 1; i >= 0; i--) { 
                sb.append(String.format("Asignatura: %-25s | Presentismo: %.2f%%\n", 
                        reporte.get(i).getAsignatura().getNombre(), reporte.get(i).getPresentismo()));
            }
        } else {
            sb.append("No hay datos cargados en el sistema para calcular el presentismo.\n");
        }

        mostrarYGuardar("Ranking_Presentismo.txt", sb.toString());
    }

    private void generarDetalleAsignatura() {
        if (comboAsignaturas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Por favor, cargue los datos y seleccione una asignatura.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String asigSeleccionada = (String) comboAsignaturas.getSelectedItem();
        int codAsig = Integer.parseInt(asigSeleccionada.split(" - ")[0]);
        String nombreAsig = asigSeleccionada.split(" - ")[1];

        ArrayList<inscripcion> reporte = controladora.getReporteAlumnosAsignatura(codAsig);
        StringBuilder sb = new StringBuilder();
        ArrayList<clase> v = reporte.get(0).getAsignatura().getListadoClases();
        System.out.println(">>>>>>>>>>>>>" + v.get(0).getCodigo());


        sb.append("============================================================\n");
        sb.append(" DETALLE DE ASISTENCIAS - ASIGNATURA: ").append(nombreAsig.toUpperCase()).append("\n");
        sb.append("============================================================\n\n");
        
        if (reporte != null && !reporte.isEmpty()) {
            for (inscripcion ins : reporte) {
                int totalClases = ins.getAsignatura().getListadoClases().size();
                double porcentaje = 0;
                
                if (totalClases > 0) {
                    porcentaje = ((double) ins.getAsistencias() / totalClases) * 100;
                }

                sb.append("Alumno: ").append(ins.getAlumno().getNombre())
                  .append(" (Matrícula: ").append(ins.getAlumno().getMatricula()).append(")\n")
                  .append(String.format("  - Modalidad de Cursado: %s\n", ins.getTipoalum() == 'R' ? "Regular" : "Libre"))
                  .append("  - Clases Dictadas:      ").append(totalClases).append("\n")
                  .append("  - Clases Presente:      ").append(ins.getAsistencias()).append("\n")
                  .append(String.format("  - Porcentaje Asistencia: %.2f%%\n", porcentaje))
                  .append("  - CONDICIÓN FINAL:       ").append(ins.ObtenerCondicion()).append("\n")
                  .append("------------------------------------------------------------\n");
            }
        } else {
            sb.append("No se registran alumnos inscriptos en esta asignatura.\n");
        }

        mostrarYGuardar("Detalle_Asignatura_" + codAsig + ".txt", sb.toString());
    }

    private void generarLibres() {
        //llamamos al reporte de libres desde la controladora
        ArrayList<alumno> reporte = controladora.getReporteLibresPorFaltas();
        StringBuilder sb = new StringBuilder();

        sb.append("============================================================\n");
        sb.append("       LISTADO DE ALUMNOS LIBRES POR INCUMPLIMIENTO DE ASISTENCIA \n");
        sb.append("============================================================\n\n");
        
        if (reporte != null && !reporte.isEmpty()) {
            sb.append(String.format("%-15s | %-30s\n", "MATRÍCULA", "APELLIDO Y NOMBRE"));
            sb.append("------------------------------------------------------------\n");
            for (alumno a : reporte) {
                sb.append(String.format("%-15d | %-30s\n", a.getMatricula(), a.getNombre()));
            }
            sb.append("\nTotal de alumnos libres detectados: ").append(reporte.size()).append("\n");
        } else {
            sb.append("No se registran alumnos en condición de 'Libre' en el sistema.\n");
        }

        mostrarYGuardar("Listado_Alumnos_Libres.txt", sb.toString());
    }

    private void mostrarYGuardar(String nombreArchivo, String contenido) {
        areaReporte.setText(contenido);
        areaReporte.setCaretPosition(0);

        try {
            File carpetaData = new File("src/data");
            if (!carpetaData.exists()) { carpetaData.mkdirs(); }
            
            File archivoTXT = new File(carpetaData, nombreArchivo);
            try (FileWriter fw = new FileWriter(archivoTXT)) { fw.write(contenido); }
            
            JOptionPane.showMessageDialog(this, 
                "Reporte generado exitosamente.\nArchivo guardado en: src/data/" + nombreArchivo, 
                "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error crítico al guardar el archivo de texto: " + ex.getMessage(), 
                "Error de E/S", JOptionPane.ERROR_MESSAGE);
        }
    }
}