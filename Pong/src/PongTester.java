/**
 * Created by Bailey Ralston on 11/2/2016.
 */
import javax.swing.*;
import java.awt.*;
public class PongTester {
    public static void main(String [] args){
        JFrame theGUI = new JFrame();
        theGUI.setLocation(350,0);
        theGUI.setSize(1000,1000);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = theGUI.getContentPane();
        PongPanel pPanel = new PongPanel(Color.WHITE, theGUI.getWidth(), theGUI.getHeight());

        pane.add(pPanel);
        theGUI.setVisible(true);
    }
}
