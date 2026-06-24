package view;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import controladora.controladora;

/**
 * MAINFRAME DE LA GIU
 * <p>
 * Se encarga de crear la ventana principal donde se mostraran los paneles
 */
public class MainFrame extends JFrame {
    public MainFrame(controladora c) {
        setTitle("Sistema de Gestión de Asistencia Universitaria");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Crear contenedor de pestañas
        JTabbedPane panelPestañas = new JTabbedPane();
        ListadoInscripcionesPanel inscripcionesPanel = new ListadoInscripcionesPanel(c);

        AsistenciaPanel asistenciaPanel = new AsistenciaPanel(c);
        ReportesPanel reportesPanel = new ReportesPanel(c);
        panelPestañas.addTab("Listado Inscripciones", inscripcionesPanel);
        panelPestañas.addTab("Registrar Asistencia", asistenciaPanel);
        panelPestañas.addTab("Reportes", reportesPanel);
        
        //Agregar el contenedor a la ventana principal
        add(panelPestañas);
    }
}
