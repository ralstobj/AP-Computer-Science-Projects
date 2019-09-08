import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Bailey Ralston on 11/4/2016.
 */
public class SlingGUI {
    public static void main(String []args){
        JFrame theGUI = new JFrame();
        theGUI.setLocation(350,0);
        theGUI.setSize(1000,1000);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = theGUI.getContentPane();
        TestPanel test = new TestPanel(Color.white);
        pane.add(test);
        theGUI.setVisible(true);
        class KeyAdapt extends KeyAdapter {
            public void keyPressed(KeyEvent e){
                System.out.print("hello");
            }

        }
        theGUI.addKeyListener(new KeyAdapt());

    }

}
