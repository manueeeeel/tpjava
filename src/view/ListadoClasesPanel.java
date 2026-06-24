package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Clases_utilizadas.asignaturas.asignatura;
import Clases_utilizadas.clase;
import controladora.controladora;

public class ListadoClasesPanel extends JPanel {
    private JTable tablaClases;
    private DefaultTableModel modeloTabla;
    private controladora controladora;

    public ListadoClasesPanel(controladora c) {
        controladora = c;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        String[] columnas = {"Materia", "Código", "Fecha", "Horario"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaClases = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClases);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void cargarDatosClases() {
        modeloTabla.setRowCount(0);

        //recorremos todas las asignaturas para extraer las clases que tienen adentro
        for (asignatura asig : controladora.getAsignaturas().values()) {
            if (asig.getListadoClases() != null) {
                for (clase c : asig.getListadoClases()) {
                    Object[] fila = {
                            asig.getNombre(), //sacamos el nombre directamente de la asignatura padre
                            c.getCodigo(),
                            c.getFecha(),
                            c.getHorario()
                    };
                    modeloTabla.addRow(fila);
                }
            }
        }
        
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay clases registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}