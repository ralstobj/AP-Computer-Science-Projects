import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Bailey Ralston on 11/4/2016.
 */
public class Projectile {
    private double mass;
    private double velocity;
    private VertexForm pathOfMotion;
    private double xPos;
    private double yPos;

    public Projectile(double mass, double velocity, VertexForm pathOfMotion, double xPos, double yPos) {
        this.mass = mass;
        this.velocity = velocity;
        this.pathOfMotion = pathOfMotion;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    public void setPathOfMotion(VertexForm v){
        this.pathOfMotion = v;
    }
    public double momentum(){return mass*velocity;}
    public void drawPro(Graphics g){
        g.setColor(Color.RED);
        Rectangle2D rect = new Rectangle2D.Double();
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(5));
        rect.setRect(xPos-5, -yPos -5, 10,10);
        g2.draw(rect);
    }
    public void move(){
        if(xPos < pathOfMotion.posZero()){
            xPos += .5;
            yPos = pathOfMotion.yVal(xPos);
        }
    }

}
