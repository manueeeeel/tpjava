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

    public ListadoAlumnosPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        String[] columnas = {"Matrícula", "Nombre", "Fecha de Nacimiento"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaAlumnos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos);
        
        add(scrollPane, BorderLayout.CENTER);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                cargarDatosAlumnos();
            }
        });
    }

    public void cargarDatosAlumnos() {
        modeloTabla.setRowCount(0); // Vaciamos la tabla para no duplicar
        TreeSet<alumno> alumnos = universidad.getInstancia().getAlumnos();
        
        if (alumnos != null && !alumnos.isEmpty()) {
            for (alumno a : alumnos) {
                Object[] fila = {
                    a.getMatricula(),
                    a.getNombre(),
                    a.getFechanacimiento()
                };
                modeloTabla.addRow(fila);
            }
        } else {
            System.out.println("No hay alumnos cargados en el sistema.");
        }
    }
}