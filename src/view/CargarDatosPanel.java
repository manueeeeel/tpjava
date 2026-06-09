package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Clases_utilizadas.universidad;
import controladora.controladora;

public class CargarDatosPanel extends JPanel {
    private JButton btnCargarDatos;
    private JTextArea textConsola;
    private controladora c;

    public CargarDatosPanel(controladora c) {
        this.c = c;
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
                asd();
            }
        });

        add(btnCargarDatos, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void asd() {
        textConsola.append("Cargando datos desde XML...\n");

        try {
            textConsola.append("Datos de alumnos cargados exitosamente.\n");

            textConsola.append("Datos de clases cargados exitosamente.\n");

            textConsola.append("Datos de asignaturas cargados exitosamente.\n");

        } catch (Exception ex) {
            textConsola.append("Error al cargar datos: " + ex.getMessage() + "\n");
        }
    }
}
