import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.Graphics2D;

/**
 * Created by Bailey Ralston on 11/4/2016.
 */


public class QuadEquation {
    private double a;
    private double b;
    private double c;

    public QuadEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }
    public void setA(double a) {
        this.a = a;
    }
    public double getB() {
        return b;
    }
    public void setB(double b) {
        this.b = b;
    }
    public double getC() {
        return c;
    }
    public void setC(double c) {
        this.c = c;
    }
    public double posZero(){
        double zeroVal=1;
        zeroVal = (-b + (Math.sqrt(Math.pow(b,2)-(4*(a)*(c)))))/(2*a);
        if(zeroVal<=0){
            zeroVal = (-b - (Math.sqrt(Math.pow(b,2)-(4*(a)*(c)))))/(2*a);
        }
        return zeroVal;

    }
    public double calcYVal(double x){
        return a*(Math.pow(x,2)) + b*(x) + c;
    }
    public void drawPath(Graphics g){
        Line2D line = new Line2D.Double();
        Graphics2D g2 = (Graphics2D)g;
        System.out.println(posZero());
        for(double i = 0; i <= posZero(); i+=10){
            g2.setStroke(new BasicStroke(3));
            line.setLine(i,Math.abs(calcYVal(i)),i+.1,Math.abs(calcYVal(i)));
            g2.draw(line);
        }
    }
}
