import view.MainFrame;
import javax.swing.SwingUtilities;

import controladora.controladora;
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