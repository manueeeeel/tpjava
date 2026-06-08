package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.TreeSet;

import Clases_utilizadas.alumno;
import Clases_utilizadas.universidad;



public class ListadoAlumnosPanel extends JPanel {
    private JTable tablaAlumnos;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;

    public ListadoAlumnosPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnas = {"Matrícula", "Nombre", "Fecha de Nacimiento"};
        // Crear el modelo de la tabla
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };
        // Crear la tabla y asignarle el modelo
        tablaAlumnos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos);
        //Botón para traer los datos
        btnActualizar = new JButton("Actualizar Listado Alumnos");
        btnActualizar.addActionListener(e -> cargarDatosAlumnos());
        // Ensamblar el panel
        add(scrollPane, BorderLayout.CENTER);
        add(btnActualizar, BorderLayout.SOUTH);

    }

    private void cargarDatosAlumnos() {

        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos
        // Traer el TreeSet de alumnos desde el Singleton
        TreeSet<alumno> alumnos = universidad.getInstancia().getAlumnos();


        if(alumnos != null && !alumnos.isEmpty()) {
            for (alumno a : alumnos) {
                Object[] fila = {
                    a.getMatricula(), 
                    a.getNombre(), 
                    a.getFechanacimiento()
                };
                modeloTabla.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay alumnos registrados. Por favor, agregue algunos alumnos.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
