/**
 * Created by Bailey Ralston on 10/28/2016.
 */
import java.awt.*;

public class Ball extends Circle {
    double direction,velocity;
    public Ball(int x, int y, int r, int v, int d, Color color){
        direction = d;
        velocity = v;
        setCenterX(x);
        setCenterY(y);
        setColor(color);
        setRadius(r);
    }
    public Ball(){
        this(0,0,0,0,0,Color.WHITE);
    }

    public void setVelocity(double v){
        velocity = v;
    }
    public void setDirection(double degrees){
        direction = degrees % 360;
    }
    public void turn(double degrees){
        direction = (direction + degrees) % 360;
    }
    public void move(){
        move((int)(velocity*Math.cos(Math.toRadians(direction))),
                (int)(velocity*Math.sin(Math.toRadians(direction))));
    }
    public double getDirection() {
        return direction;
    }
    public double getVelocity() {
        return velocity;
    }
}
