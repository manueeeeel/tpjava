package view;
import Clases_utilizadas.*;
import javax.swing.JFrame; 

public class MainFrame extends JFrame {
    private universidad uni;

    public MainFrame(){
        uni = universidad.getInstancia();
        setTitle("Universidad");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}