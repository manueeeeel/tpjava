package view;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import controladora.controladora;

public class MainFrame extends JFrame {
    public MainFrame(controladora c) {
        setTitle("Sistema de Gestión de Asistencia Universitaria");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Crear contenedor de pestañas
        JTabbedPane panelPestañas = new JTabbedPane();
        //Instaciar los dos paneles 
        CargarDatosPanel cargarDatosPanel = new CargarDatosPanel(c);
        ListadoAlumnosPanel listadoAlumnosPanel = new ListadoAlumnosPanel(c);
        //Agregar las pestañas al contenedor
        panelPestañas.addTab("Cargar Datos", cargarDatosPanel);
        panelPestañas.addTab("Listado Alumnos", listadoAlumnosPanel);
        //Agregar el contenedor a la ventana principal
        add(panelPestañas);
    }
}
