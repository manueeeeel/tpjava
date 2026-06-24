import view.MainFrame;
import javax.swing.SwingUtilities;

import controladora.controladora;

/**
 * MAIN DEL PROGRAMA
 * <p>
 * Llama a la carga de datos del XML y muestra la GIU.
 */
public class Main {
    public static void main(String[] args) {
        controladora c = new controladora();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                c.cargarDatosXML();
                MainFrame mainFrame = new MainFrame(c);
                mainFrame.setVisible(true);
            }
        });
        System.out.println("Sistema iniciado correctamente.");
    }
}