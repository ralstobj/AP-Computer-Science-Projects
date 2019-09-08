import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Bailey Ralston on 12/1/2016.
 */
public class MatrixTester {
    public static void main(String[] args) throws IOException {
        UIManager.put("OptionPane.messageFont",new Font("Fira Sans", Font.ITALIC, 50));
        MatrixGUI theGUI = new MatrixGUI();
        theGUI.setTitle("Matrix");
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theGUI.setLocation(800,100);
        theGUI.pack();
        theGUI.setVisible(true);
    }
}
