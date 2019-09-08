import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Bailey Ralston on 11/4/2016.
 */
public class Projectile {

    private VertexForm pathOfMotion;
    private double xPos;
    private double yPos;
    private int hit=0;
    private int timesFired=0;

    public Projectile( VertexForm pathOfMotion, double xPos, double yPos) {

        this.pathOfMotion = pathOfMotion;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public int getHit() {
        return hit;
    }
    public void setHit(int hit) {
        this.hit = hit;
    }
    public VertexForm getPathOfMotion() {
        return pathOfMotion;
    }
    public double getxPos() {
        return xPos;
    }
    public int getTimesFired(){return timesFired;}
    public void drawTimesFired(Graphics g, int x, int y){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Fira Sans", Font.BOLD, 30));
        g.drawString("Times Fired: " + timesFired,x,y);
    }
    public void setTimesFired(int i ){timesFired =i;}
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }
    public double getyPos() {
        return yPos;
    }
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
    public void setPathOfMotion(VertexForm v){
        this.pathOfMotion = new VertexForm(v);
    }
    public void drawPro(Graphics g){

        Rectangle2D rect = new Rectangle2D.Double();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(5));
        rect.setRect(xPos-5, yPos -5, 10,10);
        g2.fill(rect);
        g2.draw(rect);
    }
    public void move(int value, boolean forward){
        if (forward) {
            if (xPos < pathOfMotion.posZero()) {
                if (!pathOfMotion.getObstacle().contains(xPos, yPos)) {
                    xPos += value;
                    yPos = pathOfMotion.yVal(xPos);
                } else {
                    hit = 1;
                }

            } else {
                hit = 1;
            }
        } else{
            if (xPos > 0) {
                if (!pathOfMotion.getObstacle().contains(xPos, yPos)) {
                    xPos += value;
                    yPos = pathOfMotion.yVal(xPos);
                } else {
                    hit = 1;
                }

            } else {
                hit = 1;
            }
        }
    }

}
