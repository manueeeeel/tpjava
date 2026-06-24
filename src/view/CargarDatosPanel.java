package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controladora.controladora;


public class CargarDatosPanel extends JPanel {
    private JButton btnCargarDatos;
    private JTextArea textConsola;
    private controladora c;
    private ListadoAlumnosPanel listadoAlumnosPanel;
    private ListadoClasesPanel listadoClasesPanel;
    private ListadoAsignaturasPanel listadoAsignaturasPanel;

    public CargarDatosPanel(controladora c, ListadoAlumnosPanel listadoAlumnosPanel,
            ListadoClasesPanel listadoClasesPanel, ListadoAsignaturasPanel listadoAsignaturasPanel) {
        this.c = c;
        this.listadoAlumnosPanel = listadoAlumnosPanel;
        this.listadoClasesPanel = listadoClasesPanel;
        this.listadoAsignaturasPanel = listadoAsignaturasPanel;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnCargarDatos = new JButton("Importar datos XML");
        textConsola = new JTextArea();
        textConsola.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textConsola);

        btnCargarDatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.cargarDatosXML();
                listadoAlumnosPanel.cargarDatosAlumnos();
                listadoClasesPanel.cargarDatosClases();
                listadoAsignaturasPanel.cargarDatosAsignaturas();
                textConsola.append("Datos cargados y listados actualizados correctamente.\n");
            }
        });

        add(btnCargarDatos, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
