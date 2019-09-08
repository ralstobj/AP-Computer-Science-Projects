/**
 * Created by Bailey Ralston on 10/26/2016.
 */
import java.awt.*;

public class Circle {

    private int centerX, centerY, radius;
    private Color color;
    public Circle(){this(0,0,0,Color.WHITE);}
    public Circle(int x, int y, int r, Color c){
        centerX = x;
        centerY = y;
        radius = r;
        color = c;
    }
    public void draw(Graphics g){
        Color oldcolor = g.getColor();
        g.setColor(color);
        //Translates circle's center to rectangle's origin for drawing.
        g.drawOval(centerX-radius,centerY-radius,radius*2,radius*2);
        g.setColor(oldcolor);
    }
    public void fill(Graphics g){
        Color oldcolor = g.getColor();
        g.setColor(color);
        //Translates circle's center to rectangle's origin for drawing.
        g.fillOval(centerX-radius,centerY-radius,radius*2,radius*2);
        g.setColor(oldcolor);
    }
    public boolean containsPoint(int x, int y){
        int xSquared = (x-centerX)*(x-centerX);
        int ySquared = (y-centerY)*(y-centerY);
        int radiusSquared = radius*radius;
        return xSquared + ySquared - radiusSquared <=0;
    }
    public void move(int xAmount, int yAmount){
        centerX = centerX + xAmount;
        centerY = centerY + yAmount;
    }
    public int getCenterX() {
        return centerX;
    }
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }
    public int getCenterY() {
        return centerY;
    }
    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}
