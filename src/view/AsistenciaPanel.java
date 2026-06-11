package view;

import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

import Clases_utilizadas.asignaturas.asignatura;
import Clases_utilizadas.alumno;
import Clases_utilizadas.universidad;
import controladora.controladora;

public class AsistenciaPanel extends JPanel {
    private JComboBox<String> comboAlumnos, comboAsignaturas;
    private JButton btnRegistrarPresente;
    private controladora c;

    public AsistenciaPanel(controladora c) {
        this.c = c;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Registrar Asistencia de Alumno"));

        Dimension maxDim = new Dimension(Integer.MAX_VALUE, 30);
        
        // ComboBox Alumnos
        panelFormulario.add(new JLabel("Seleccionar Alumno:"));
        comboAlumnos = new JComboBox<>();
        comboAlumnos.setMaximumSize(maxDim);
        panelFormulario.add(comboAlumnos);
        panelFormulario.add(Box.createVerticalStrut(15));

        // ComboBox Asignaturas
        panelFormulario.add(new JLabel("Seleccionar Asignatura de la Clase:"));
        comboAsignaturas = new JComboBox<>();
        comboAsignaturas.setMaximumSize(maxDim);
        panelFormulario.add(comboAsignaturas);
        panelFormulario.add(Box.createVerticalStrut(25));

        btnRegistrarPresente = new JButton("Registrar Presente");
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnRegistrarPresente);

        add(panelFormulario, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        btnRegistrarPresente.addActionListener(e -> guardarAsistencia());

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                cargarCombos();
            }
        });
    }

    private void cargarCombos() {
        comboAlumnos.removeAllItems();
        comboAsignaturas.removeAllItems();

        TreeSet<alumno> alumnos = universidad.getInstancia().getAlumnos();
        if (alumnos != null) {
            for (alumno a : alumnos) {
                comboAlumnos.addItem(a.getMatricula() + " - " + a.getNombre());
            }
        }

        for (asignatura asig : universidad.getInstancia().getAsignaturas().values()) {
            comboAsignaturas.addItem(asig.getCodigo() + " - " + asig.getNombre());
        }
    }

    private void guardarAsistencia() {
        if (comboAlumnos.getSelectedItem() == null || comboAsignaturas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno y una asignatura.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Extraer ID del Alumno
            String alumnoSeleccionado = (String) comboAlumnos.getSelectedItem();
            int matricula = Integer.parseInt(alumnoSeleccionado.split(" - ")[0]);

            // Extraer ID de la Asignatura
            String asignaturaSeleccionada = (String) comboAsignaturas.getSelectedItem();
            int codigoAsignatura = Integer.parseInt(asignaturaSeleccionada.split(" - ")[0]);

            universidad.getInstancia().RegistraAsistencia(matricula, codigoAsignatura);
      
            this.c.serealizaInscripciones();
            JOptionPane.showMessageDialog(this, "Se ha registrado la asistencia correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar la asistencia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}