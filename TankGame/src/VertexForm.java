import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Line2D;

/**
 * Created by Bailey Ralston on 11/5/2016.
 */
public class VertexForm {

    private double h;
    private double k;
    private double startX;
    private double startY;
    private Area obstacle;
    public VertexForm(double h, double k, double x, double y, Area a) {
        this.h = h;
        this.k = k;
        this.startX = x;
        this.startY = y;
        this.obstacle = a;
    }
    public VertexForm(){
        this(0,0,0,0,null);
    }
    public VertexForm(VertexForm v){
        this.h = v.getH();
        this.k = v.getK();
        this.startX = v.getStartX();
        this.startY = v.getStartY();
        this.obstacle= v.getObstacle();
    }

    public double getH() {
        return h;
    }
    public Area getObstacle() {
        return obstacle;
    }
    public void setObstacle(Area obstacle) {
        this.obstacle = obstacle;
    }
    public double getStartX() {
        return startX;
    }
    public void setStartX(double startX) {
        this.startX = startX;
    }
    public double getStartY() {
        return startY;
    }
    public void setStartY(double startY) {
        this.startY = startY;
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
        double a = (startY- k) / (Math.pow((startX-h),2));
        return  a * (Math.pow((x - h),2)) + k;
    }
    public double posZero(){
        double a = (startY- k) / (Math.pow((startX-h),2));
        return Math.sqrt((-k)/a)+h;
    }
    public void drawPath(Graphics g, boolean forward){
        Line2D line = new Line2D.Double();
        Graphics2D g2 = (Graphics2D)g;
        if(forward) {
            for (double i = startX; i <= posZero(); i += 10) {
                if (obstacle.contains(i, (yVal(i)))) {
                    return;
                } else {
                    g2.setStroke(new BasicStroke(5));
                    line.setLine(i, (yVal(i)), i + .1, (yVal(i)));
                    g2.fill(line);
                    g2.draw(line);
                }
            }
        } else{
            for (double i =startX ; i>=0; i -= 10) {
                if (obstacle.contains(i, (yVal(i)))) {
                    return;
                } else {
                    g2.setStroke(new BasicStroke(5));
                    line.setLine(i, (yVal(i)), i + .1, (yVal(i)));
                    g2.fill(line);
                    g2.draw(line);
                }
            }
        }
    }
}

