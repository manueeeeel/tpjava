package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import Clases_utilizadas.universidad;
import Clases_utilizadas.asignaturas.asignatura;

public class ListadoAsignaturasPanel extends JPanel {
    private JTable tablaAsignaturas;
    private DefaultTableModel modeloTabla;

    public ListadoAsignaturasPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        String[] columnas = {
                "Código",
                "Nombre",
                "Cuatrimestre",
                "Promocionable"
        };
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaAsignaturas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAsignaturas);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void cargarDatosAsignaturas() {
        modeloTabla.setRowCount(0);
        HashMap<Integer, asignatura> asignaturas = universidad.getInstancia().getAsignaturas();
        if (asignaturas != null && !asignaturas.isEmpty()) {
            for (asignatura a : asignaturas.values()) {
                Object[] fila = {
                        a.getCodigo(),
                        a.getNombre(),
                        a.getCuatrimestre(),
                        a.getPromocionable()
                };
                modeloTabla.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay asignaturas registradas.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}