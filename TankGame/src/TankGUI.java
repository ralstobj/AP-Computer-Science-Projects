/**
 * Created by Bailey Ralston on 11/10/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TankGUI {
    public static void main(String[] args)throws IOException {
        UIManager.put("OptionPane.messageFont",new Font("Fira Sans", Font.ITALIC, 50));
        JFrame theGUI = new JFrame();
        theGUI.setLocation(350, 0);
        theGUI.setSize(1500, 1000);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = theGUI.getContentPane();
        TankPanel test = new TankPanel(Color.white);
        pane.add(test);
        theGUI.setVisible(true);
        theGUI.addKeyListener(test.getListener());


    }
}

