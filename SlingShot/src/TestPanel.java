import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.*;

/**
 * Created by Bailey Ralston on 11/4/2016.
 */
public class TestPanel extends JPanel {
    private Point prev;
    private VertexForm v;
    private javax.swing.Timer timer;
    private Projectile p;
    private int flag= 0;
    TestPanel(Color c){
        setBackground(c);
         v = new VertexForm(1,1);
        prev = new Point(getWidth(),0);
        p = new Projectile(0,0,v,0,0);
        timer = new javax.swing.Timer(5,new MoveListener());
        addMouseMotionListener(new MouseListener());
        addMouseListener(new MouseClicker());
        

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.translate(0,getHeight());
        v.drawPath(g);
        p.drawPro(g);
    }
    private class MoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            p.move();
            repaint();
        }
    }

    private class MouseListener extends MouseMotionAdapter{
        public void mouseMoved(MouseEvent e){
            if (flag == 0 ){
                if (e.getX() > prev.getX()) {
                    v.setH(v.getH() - (e.getX() / 50));
                } else if (e.getX() < prev.getX()) {
                    v.setH(v.getH() + (e.getX() / 50));
                }
                v.setK(e.getY());
                p.setPathOfMotion(v);
                prev.setLocation(e.getX(), e.getY());
                repaint();
            }
        }
    }

}
