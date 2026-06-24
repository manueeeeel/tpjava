package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import Clases_utilizadas.inscripcion;
import controladora.controladora;

/**
 * Muestra el contenido del panel Inscripciones creando el listado
 */
public class ListadoInscripcionesPanel extends JPanel {
    private JTable tablaInscripciones;
    private DefaultTableModel modeloTabla;
    private controladora c;

    /**
     * Crea el panel de listado de inscripciones
     * @param c Controladora
     */
    public ListadoInscripcionesPanel(controladora c) {
        this.c = c;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnas = {"Alumno", "Asignatura", "Modalidad", "Asistencias", "Condición"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaInscripciones = new JTable(modeloTabla);
        tablaInscripciones.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(tablaInscripciones);

        add(scrollPane, BorderLayout.CENTER);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                cargarDatosInscripciones();
            }
        });
    }

    /**
     * Carga las inscripciones del sistema en la tabla
     */
    public void cargarDatosInscripciones() {
        modeloTabla.setRowCount(0);
        ArrayList<inscripcion> inscripciones = c.getInscripciones();

        if (inscripciones != null && !inscripciones.isEmpty()) {
            for (inscripcion ins : inscripciones) {
                int totalClases = ins.getAsignatura().getListadoClases().size();
                String modalidad = switch (ins.getTipoalum()) {
                    case "R" -> "Regular";
                    case "C" -> "Condicional";
                    case "O" -> "Oyente";
                    default  -> "Desconocido";
                };
                Object[] fila = {
                        ins.getAlumno().getNombre(),
                        ins.getAsignatura().getNombre(),
                        modalidad,
                        ins.getAsistencias() + "/" + totalClases,
                        ins.ObtenerCondicion()
                };
                modeloTabla.addRow(fila);
            }
        } else {
            System.out.println("No hay inscripciones cargadas en el sistema.");
        }
    }
}