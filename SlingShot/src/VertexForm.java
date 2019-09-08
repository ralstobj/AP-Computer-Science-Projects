import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Bailey Ralston on 11/5/2016.
 */
public class VertexForm {

    private double h;
    private double k;

    public VertexForm( double h, double k) {
        this.h = h;
        this.k = k;
    }
    public VertexForm(VertexForm v){
        this.h = v.getH();
        this.k = v.getK();
    }

    public double getH() {
        return h;
    }
    public void setH(double h) {
        this.h = h;
    }
    public double getK() {
        return k;
    }
    public void setK(double k) {
        this.k = k;
    }
    public double yVal(double x){
        double a = (0- k) / (Math.pow((0-h),2));
        return  a * (Math.pow((x - h),2)) + k;
    }
    public double posZero(){
        double a = (0- k) / (Math.pow((0-h),2));
        return Math.sqrt((-k)/a)+h;
    }
    public void drawPath(Graphics g){
        Line2D line = new Line2D.Double();
        Graphics2D g2 = (Graphics2D)g;
        for(double i = 0; i <= posZero(); i+=10){
            g2.setStroke(new BasicStroke(5));
            line.setLine(i,(-yVal(i)),i+.1,(-yVal(i)));
            g2.draw(line);
        }
    }
}

