/**
 * Created by Bailey Ralston on 10/28/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PongPanel extends JPanel {

    private Ball pongBall;
    private javax.swing.Timer timer;
    private Paddle pongPaddle;
    private Player p;
    private int lose =0;

    public PongPanel(Color c, int width, int height){
        setBackground(c);
        setPreferredSize(new Dimension(width, height));
        // Circle centered in the panel with radius 25
        pongBall = new Ball(100, height/2, 25, 0, 0, Color.red);
        pongPaddle = new Paddle(width/20,height/2, 300, Color.BLACK);
        p = new Player(0," ");
        pongBall.setVelocity(5);
        pongBall.setDirection(45);
        timer = new javax.swing.Timer(5,new MoveListener());
        timer.start();
        addMouseMotionListener(new MouseListener());
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if (lose ==0){
            pongBall.fill(g);
            pongPaddle.fill(g);
            p.draw(g,(getWidth()/12)*10,(getHeight()/12));
        } else{
            Font f = new Font("Fira Sans", Font.ITALIC, 50);
            g.setFont(f);
            g.drawString("You Lose", (getHeight()/2)-50, getWidth()/2);
            timer.stop();
        }
    }
    private class MouseListener extends MouseMotionAdapter {
        public void mouseMoved(MouseEvent e){
            pongPaddle.setCenterY(e.getY());
            repaint();
        }

    }

    private class MoveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int x = pongBall.getCenterX();
            int y = pongBall.getCenterY();
            int radius = pongBall.getRadius();
            int width = getWidth();
            int height = getHeight();
            if(y-radius <= pongPaddle.getCenterY() + pongPaddle.getTotalLength()/2 && y+radius >= pongPaddle.getCenterY() - pongPaddle.getTotalLength()/2){
                if(x-radius <= pongPaddle.getCenterX()+20 && x+radius >= pongPaddle.getCenterX()-20){
                    pongBall.turn(pongBall.getDirection()+45);
                    pongBall.setVelocity(pongBall.getVelocity()+.005);
                    p.setPoints(p.getPoints()+50);
                }
            }
            if(x-radius <=0){
                lose =1;
            }
            if( x+ radius >= width) {
                pongBall.turn(45);
            }
            if(y-radius <=0 || y+radius >= height){
                pongBall.turn(45);
            }
                pongBall.move();
                repaint();

        }

    }
}
