package view;
import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

import Clases_utilizadas.asignaturas.asignatura;
import Clases_utilizadas.alumno;
import Clases_utilizadas.universidad;
import Clases_utilizadas.inscripcion;
import controladora.controladora;

public class InscripcionesPanel extends JPanel{
    private JComboBox<String> comboAlumnos, comboAsignaturas, comboModalidad;
    private JButton btnInscribir;
    private controladora c;

    public InscripcionesPanel(controladora c){
        this.c = c;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Inscribir Alumno en Asignatura"));

        Dimension maxDim = new Dimension(Integer.MAX_VALUE, 30);
        //ComboBox Alumnos
        panelFormulario.add(new JLabel("Seleccionar alumno:"));
        comboAlumnos = new JComboBox<>();
        comboAlumnos.setMaximumSize(maxDim);
        panelFormulario.add(comboAlumnos);
        panelFormulario.add(Box.createVerticalStrut(15));

        //ComboBox Asignaturas
        panelFormulario.add(new JLabel("Seleccionar asignatura:"));
        comboAsignaturas = new JComboBox<>();
        comboAsignaturas.setMaximumSize(maxDim);
        panelFormulario.add(comboAsignaturas);
        panelFormulario.add(Box.createVerticalStrut(15));

        //ComboBox Modalidad
        panelFormulario.add(new JLabel("Seleccionar modalidad:"));
        comboModalidad = new JComboBox<>(new String[]{"Presencial", "Virtual"});
        comboModalidad.setMaximumSize(maxDim);
        panelFormulario.add(comboModalidad);
        panelFormulario.add(Box.createVerticalStrut(25));

        //Boton Inscribir
        btnInscribir = new JButton("Inscribir");
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnInscribir);

        add(panelFormulario, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        btnInscribir.addActionListener(e -> guardarInscripcion());
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

    private void guardarInscripcion(){
        if(comboAlumnos.getSelectedItem() == null || comboAsignaturas.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno y una asignatura", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            String alumnoSeleccionado = (String) comboAlumnos.getSelectedItem();
            int matricula = Integer.parseInt(alumnoSeleccionado.split(" - ")[0]);
            alumno alu = null;
            for(alumno a : universidad.getInstancia().getAlumnos()){
                if(a.getMatricula() == matricula){
                    alu = a;
                    break;
                }
            }
            String asignaturaSeleccionada = (String) comboAsignaturas.getSelectedItem();
            int codigoAsignatura = Integer.parseInt(asignaturaSeleccionada.split(" - ")[0]);
            asignatura asig = universidad.getInstancia().getAsignaturas().get(codigoAsignatura);

            String mod = (String) comboModalidad.getSelectedItem();
            char modalidad = mod.contains("(R)") ? 'R' : 'L';

            inscripcion ins = new inscripcion();
            ins.setAlumno(alu);
            ins.setAsignatura(asig);
            ins.setTipoalum(modalidad);

            universidad.getInstancia().InsertaInscripcion(ins);

            this.c.serealizaInscripciones();
            JOptionPane.showMessageDialog(this, "Se ha inscripto al Alumno correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
           

        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Error al registrar la inscripción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
