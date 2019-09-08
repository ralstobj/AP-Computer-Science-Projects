import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * Created by Bailey Ralston on 11/10/2016.
 */
public class TankPanel extends JPanel {
    private Tank num1;
    private Tank num2;
    private TopTen topTen;
    private int flag = 0;
    private int hit = 0;
    private int flag2 = 0;
    private int show = 0;
    private int finish = 0;
    private String firstInt;
    private String secondInt;
    private javax.swing.Timer timer;
    private javax.swing.Timer timer2;
    private QuadCurve2D.Double curve;
    private QuadCurve2D.Double curve2;
    private Area hill;

    TankPanel(Color c) {
        setBackground(c);
        firstInt = JOptionPane.showInputDialog("Enter Player 1 Initials","Bob");
        secondInt = JOptionPane.showInputDialog("Enter Player 2 Initials","Bob");
        addKeyListener(new KeyListener());
        curve = new QuadCurve2D.Double(400, 0, 500, -700, 750, 0);
        curve2 = new QuadCurve2D.Double(320, 0, 380, -300, 500, 0);
        hill = new Area(curve);
        topTen = new TopTen();
        hill.add(new Area(curve2));
        hill.add(new Area(new Rectangle2D.Double(0, -76, getWidth(), 76)));
        num1 = new Tank(hill);
        num2 = new Tank(hill);
        num2.realSetBarrel(0);
        num2.setForward(false);
        num2.setProjectilePath(new VertexForm(600, -130, 1000, -120, hill));
        num2.setxLocal(1000);
        num2.setP(new Projectile(null, 1000, num2.getyLocal()));
        timer = new javax.swing.Timer(2, new MoveListener());
        timer2 = new javax.swing.Timer(2, new MoveListener2());
    }
    public KeyAdapter getListener() {
        return new KeyListener();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.translate(0, getHeight());
        g.setColor(new Color(145, 225, 227));
        g.fillRect(0, -getHeight(), getWidth(), getHeight());
        num1.getP().drawTimesFired(g,(getWidth()/20)*15, 30);
        num1.drawTank(g, flag);
        num2.drawTank(g, flag2);
        num1.getP().drawTimesFired(g,(getWidth()/20)*16, -getHeight()+100);
        num2.getP().drawTimesFired(g,(getWidth()/20*16),-getHeight()+50);
        if(num1.hit){
            g.drawString(firstInt + " Wins!!!", getWidth()/2, -getHeight()/2 );
        }
        if(num2.hit){
            g.drawString(secondInt + " Wins!!!", getWidth()/2, -getHeight()/2 );
        }
        Graphics2D e = (Graphics2D) g;
        e.setColor(new Color(70, 137, 47));
        e.fill(hill);
        e.draw(hill);
        e.fillRect(0, -76, getWidth(), 76);
        if(show == 1){
            topTen.showTopTen(e,100,-800);
        }
    }

    private class MoveListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num2.getP().move(-5, num2.isForward());
            if (num2.getP().getHit() == 1) {
                flag2 = 0;
                timer2.stop();
                num2.getP().setxPos(num2.getxLocal());
                num2.getP().setyPos(num2.getyLocal());
                num2.getP().setHit(0);
            }
            num1.hitTank(num2.getP());
            repaint();
            if (num1.getHit()) {
                timer2.stop();
                finish =1;
                num2.getP().setxPos(num2.getxLocal());
                num2.getP().setyPos(num2.getyLocal());
                num2.getP().setHit(0);
                hit = 1;
                if(num2.getP().getTimesFired()<=topTen.getTopTenPlayer(10).getPoints()){
                    topTen.setTopTen(10,new Player(num2.getP().getTimesFired(),firstInt));
                    topTen.sortTopTen();
                    try {
                        topTen.saveTopTen();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
    private class MoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num1.getP().move(5,num1.isForward());
            if(num1.getP().getHit()==1){
                flag = 0;
                timer.stop();
                num1.getP().setxPos(num1.getxLocal());
                num1.getP().setyPos(num1.getyLocal());
                num1.getP().setHit(0);
            }
            num2.hitTank(num1.getP());
            repaint();
            if(num2.getHit()){
                timer.stop();
                finish =1;
                num1.getP().setxPos(num1.getxLocal());
                num1.getP().setyPos(num1.getyLocal());
                num1.getP().setHit(0);
                hit = 1;
                if(num1.getP().getTimesFired()<=topTen.getTopTenPlayer(10).getPoints()){
                    topTen.setTopTen(10,new Player(num1.getP().getTimesFired(),secondInt));
                    topTen.sortTopTen();
                    try {
                        topTen.saveTopTen();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        }

    }
    private class KeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            if(finish == 1){
                if(e.getKeyCode()==KeyEvent.VK_T){
                    if(show ==0) {
                        show = 1;
                    }else{
                        show = 0;
                    }
                    repaint();
                }
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    finish = 0;
                    hit = 0;
                    flag = 0;
                    show = 0;
                    flag2 = 0;
                    num1 = new Tank(hill);
                    num2 = new Tank(hill);
                    num2.realSetBarrel(0);
                    num2.setForward(false);
                    num2.setProjectilePath(new VertexForm(600, -130, 1000, -120, hill));
                    num2.setxLocal(1000);
                    num2.setP(new Projectile(null, 1000, num2.getyLocal()));
                    try {
                        topTen.saveTopTen();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    firstInt = JOptionPane.showInputDialog("Enter Player 1 Initials","Bob");
                    secondInt = JOptionPane.showInputDialog("Enter Player 2 Initials","Bob");

                }
            }
            if (!num1.getHit() || num2.getHit()) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(hit ==0) {
                        if (flag2 == 0) {
                            num2.getP().setPathOfMotion(num2.getProjectilePath());
                            num2.getP().setTimesFired(num2.getP().getTimesFired()+1);
                        }
                        flag2 = 1;
                        timer2.start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    if(hit ==0) {
                        if (flag == 0) {
                            num1.getP().setPathOfMotion(num1.getProjectilePath());
                            num1.getP().setTimesFired(num1.getP().getTimesFired()+1);
                        }
                        flag = 1;
                        timer.start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    if (num1.getBarrelAngle() >= 134) {
                        num1.setBarrelAngle(1);
                        num1.barrelMove(-5);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    if (num1.getBarrelAngle() != 180) {
                        num1.setBarrelAngle(0);
                        num1.barrelMove(5);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    if (!hill.contains(num1.getxLocal() + 120, -100)) {
                        num1.moveLeftRight(5, 400);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    if (num1.getxLocal() - 60 >= 0) {
                        num1.moveLeftRight(-5, 400);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_Q) {
                    if (num1.getProjectilePath().getK() > -385.0) {
                        num1.moveUpDown(-5, 5);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_E) {
                    if (num1.getProjectilePath().getK() < -355) {
                        num1.moveUpDown(5, -5);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (!hill.contains(num2.getxLocal() - 120, -100)) {
                        num2.moveLeftRight(-5, -400);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (num2.getxLocal() + 80 <= getWidth()) {
                        num2.moveLeftRight(5, -400);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (num2.getBarrelAngle() <= 46) {
                        num2.setBarrelAngle(0);
                        num2.barrelMove(-5);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (num2.getBarrelAngle() != 0) {
                        num2.setBarrelAngle(1);
                        num2.barrelMove(5);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_EQUALS) {
                    if (num2.getProjectilePath().getK() > -385.0) {
                        num2.moveUpDown(-5, -5);
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                    if (num2.getProjectilePath().getK() < -355.0) {
                        num2.moveUpDown(5, 5);
                    }
                }
                repaint();
            }
        }
    }

}
