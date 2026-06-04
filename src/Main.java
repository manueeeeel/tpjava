import view.MainFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
        System.out.println("\nHola mundo");
        controladora c = new controladora();
        c.deserializaAlumnos();
    }
}