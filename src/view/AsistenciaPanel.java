package view;

import javax.swing.*;
import java.awt.*;
import controladora.controladora;
import Clases_utilizadas.alumno;
import Clases_utilizadas.asignaturas.asignatura;
import Clases_utilizadas.clase;

/**
 * Muestra el contenido del panel de Asistencia
 */
public class AsistenciaPanel extends JPanel {
    private JComboBox<String> comboAlumnos;
    private JComboBox<String> comboClases;
    private JButton btnRegistrar;
    private controladora controladora;

    public AsistenciaPanel(controladora c) {
        this.controladora = c;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));

        //selección de Alumno
        panelForm.add(new JLabel("Seleccionar Alumno:"));
        comboAlumnos = new JComboBox<>();
        comboAlumnos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelForm.add(comboAlumnos);
        panelForm.add(Box.createVerticalStrut(20));

        //selección de Clase
        panelForm.add(new JLabel("Seleccionar Clase a dictar presente:"));
        comboClases = new JComboBox<>();
        comboClases.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelForm.add(comboClases);

        add(panelForm, BorderLayout.CENTER);

        btnRegistrar = new JButton("Registrar Presente");
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnRegistrar);
        add(panelBoton, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(e -> registrarPresente());

        //evento para actualizar los combos al mostrar la pestaña
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                cargarCombos();
            }
        });
    }

    private void cargarCombos() {
        //recargar alumnos
        comboAlumnos.removeAllItems();
        for (alumno a : controladora.getAlumnos()) {
            comboAlumnos.addItem(a.getMatricula() + " - " + a.getNombre());
        }

        //recargar clases
        comboClases.removeAllItems();
        for (asignatura asig : controladora.getAsignaturas().values()) {
            if (asig.getListadoClases() != null) {
                for (clase c : asig.getListadoClases()) {
                    //formato amigable para el usuario
                    comboClases.addItem(c.getCodigo() + " | Mat: " + asig.getCodigo() + " - " + asig.getNombre() + " (" + c.getFecha() + ")");
                }
            }
        }
    }

    private void registrarPresente() {
        if (comboAlumnos.getSelectedItem() == null || comboClases.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno y una clase.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            //extraemos matrícula del alumno seleccionado
            String alumnoStr = (String) comboAlumnos.getSelectedItem();
            int matricula = Integer.parseInt(alumnoStr.split(" - ")[0]);

            //extraer código de asignatura de la clase seleccionada
            String codclase = (String) comboClases.getSelectedItem();
            String parteMat = codclase.split(" \\| Mat: ")[1];
            int codAsignatura = Integer.parseInt(parteMat.split(" - ")[0]);

            //invocamos al backend del Singleton para registrar la asistencia
            controladora.RegistraAsistencia(matricula,codAsignatura,codclase);

            //guardamos automáticamente los cambios en el XML de inscripciones
            controladora.guardarDatosXML();

            JOptionPane.showMessageDialog(this, "Se ha registrado la asistencia correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar asistencia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}