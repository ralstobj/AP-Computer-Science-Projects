import java.awt.*;

/**
 * Created by Bailey Ralston on 11/3/2016.
 */
public class Paddle {
    int centerX;
    int centerY;
    int totalLength;
    Color color;
    public Paddle(){
        this(0,0,0, Color.BLACK);
    }
    public Paddle(int centerX, int centerY, int totalLength,Color c) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.totalLength = totalLength;
        this.color = c;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
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
    public int getTotalLength() {
        return totalLength;
    }
    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }
    public void draw(Graphics g){
        Color oldcolor = g.getColor();
        g.setColor(color);
        //Translates rectangle's center to edge for drawing.
        g.drawRect(getCenterX()-5, getCenterY()-getTotalLength()/2,5,getTotalLength());
        g.setColor(oldcolor);

    }
    public void fill(Graphics g){
        Color oldcolor = g.getColor();
        g.setColor(color);
        //Translates rectangle's center to edge for drawing.
        g.fillRect(getCenterX()-20, getCenterY()-getTotalLength()/2,40,getTotalLength());
        g.setColor(oldcolor);

    }
}
