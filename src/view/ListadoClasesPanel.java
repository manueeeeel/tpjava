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
    private JButton btnActualizar;

    public ListadoClasesPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        String[] columnas = {"Código", "Fecha", "Horario"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaClases = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClases);
        btnActualizar = new JButton("Actualizar Listado Clases");
        add(scrollPane, BorderLayout.CENTER);
        add(btnActualizar, BorderLayout.SOUTH);
        btnActualizar.addActionListener(e -> cargarDatosClases());
    }

    private void cargarDatosClases() {
        modeloTabla.setRowCount(0);
        HashMap<String, clase> clases =
                universidad.getInstancia().getClases();
        if (clases != null && !clases.isEmpty()) {
            for (clase c : clases.values()) {
                Object[] fila = {
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