package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import Clases_utilizadas.clase;
import Clases_utilizadas.universidad;

public class ListadoClasesPanel extends JPanel {
    private JTable tablaClases;
    private DefaultTableModel modeloTabla;

    public ListadoClasesPanel() {
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
        HashMap<String, clase> clases =
                universidad.getInstancia().getClases();
        if (clases != null && !clases.isEmpty()) {
            for (clase c : clases.values()) {
                Object[] fila = {
                        c.getAsignatura(),
                        c.getCodigo(),
                        c.getFecha(),
                        c.getHorario()
                };
                modeloTabla.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay clases registradas.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}