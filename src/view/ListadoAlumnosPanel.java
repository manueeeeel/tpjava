package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.TreeSet;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import Clases_utilizadas.alumno;
import Clases_utilizadas.universidad;
import controladora.controladora;




public class ListadoAlumnosPanel extends JPanel {
    private JTable tablaAlumnos;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;
    private JTextField txtMatricula, txtNombre;
    private JFormattedTextField txtFechaNacimiento;
    private JButton btnGuardar;

    public ListadoAlumnosPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS)); //Apilar los elementos verticalmente
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Agregar Nuevo Alumno"));
        panelFormulario.setPreferredSize(new Dimension(220, 0)); //ancho fijo para fijar el menu
        //Altura maxima fija para que los formularios no se estiren 
        Dimension maxDim = new Dimension(Integer.MAX_VALUE, 30);

        //Formulario matricula
        panelFormulario.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        txtMatricula.setMaximumSize(maxDim);

        //Limitar la entrada de numeros para que la matricula tenga 6 y se condiga con la clase controladora
        txtMatricula.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{
                if (str == null) return;
                //matches("\\d+") permite solo números y el segundo condicional limita a 6 caracteres para que se condiga con la clase controladora
                if(str.matches("\\d+") && (getLength() + str.length() <= 6)) 
                    super.insertString(offs, str, a);
            } 
        });
        panelFormulario.add(txtMatricula);
        //Espacio entre campos
        panelFormulario.add(Box.createVerticalStrut(15));

        //Formulario nombre
        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        txtNombre.setMaximumSize(maxDim);
        panelFormulario.add(txtNombre);
        panelFormulario.add(Box.createVerticalStrut(15));

        //Formulario fecha  de nacimiento
        panelFormulario.add(new JLabel("Fecha de Nacimiento (AAAA/MM/DD)):"));
        try{
            MaskFormatter mascaraFecha = new MaskFormatter("####/##/##");
            mascaraFecha.setPlaceholderCharacter('_');
            txtFechaNacimiento = new JFormattedTextField(mascaraFecha);
  
        }catch(ParseException ex){
            txtFechaNacimiento = new JFormattedTextField();
        }
        txtFechaNacimiento.setMaximumSize(maxDim);
        panelFormulario.add(txtFechaNacimiento);
        panelFormulario.add(Box.createVerticalStrut(25));

        //Botón para guardar el nuevo alumno
        btnGuardar = new JButton("Guardar Alumno");
        panelFormulario.add(btnGuardar);
        
        String[] columnas = {"Matrícula", "Nombre", "Fecha de Nacimiento"};
        // Crear el modelo de la tabla
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };
        // Crear la tabla y asignarle el modelo
        tablaAlumnos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAlumnos);
        //Botón para actualizar
        btnActualizar = new JButton("Actualizar Listado Alumnos");
        // Ensamblar todo en el panel principal
        add(panelFormulario, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(btnActualizar, BorderLayout.SOUTH);

        //Eventos de los botones
        btnActualizar.addActionListener(e -> cargarDatosAlumnos());
        btnGuardar.addActionListener(e-> guardarAlumno());
    }

    private void cargarDatosAlumnos() {

        modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar nuevos datos
        // Traer el TreeSet de alumnos desde el Singleton
        TreeSet<alumno> alumnos = universidad.getInstancia().getAlumnos();

        if(alumnos != null && !alumnos.isEmpty()) {
            for (alumno a : alumnos) {
                String fechaRaw = a.getFechanacimiento();
                String fechaFormateada = fechaRaw;
                if (fechaRaw != null && fechaRaw.length() == 8) {
                    fechaFormateada = fechaRaw.substring(0, 4) + "/" + 
                                      fechaRaw.substring(4, 6) + "/" + 
                                      fechaRaw.substring(6, 8);
                }
                Object[] fila = {
                    a.getMatricula(), 
                    a.getNombre(), 
                    fechaFormateada
                };
                modeloTabla.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay alumnos registrados. Por favor, agregue algunos alumnos.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void guardarAlumno() {
        try {
            String matriculaTexto = txtMatricula.getText().trim();
            int matricula = Integer.parseInt(matriculaTexto);
            String nombre = txtNombre.getText().trim();
            String fechaConBarras = txtFechaNacimiento.getText().trim();
            String fechaNacimiento = fechaConBarras.replace("/", "");

            if (nombre.isEmpty() || fechaNacimiento.contains("_")) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Validacion con el calendario para ingreso de fecha correcta
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd").withResolverStyle(ResolverStyle.STRICT);
                LocalDate.parse(fechaConBarras, formatter);
            }catch(DateTimeParseException ex){
                JOptionPane.showMessageDialog(this, "La fecha de nacimiento no es válida. Por favor, ingrese una fecha correcta.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            alumno nuevoAlumno = new alumno();
            nuevoAlumno.setMatricula(matricula);
            nuevoAlumno.setNombre(nombre);
            nuevoAlumno.setFechanacimiento(fechaNacimiento);

            universidad.getInstancia().InsertaAlumno(nuevoAlumno);


            controladora c = new controladora();
            c.serealizaAlumnos(); // Guardar el nuevo alumno en el XML

            JOptionPane.showMessageDialog(this, "Alumno guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            txtMatricula.setText("");
            txtNombre.setText("");
            txtFechaNacimiento.setValue(null);
            cargarDatosAlumnos(); // Actualizar la tabla después de guardar
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La matrícula debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
