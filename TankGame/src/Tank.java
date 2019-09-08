import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

/**
 * Created by Bailey Ralston on 11/9/2016.
 */
public class Tank {
    private VertexForm projectilePath;
    private double xLocal;
    private double yLocal;
    private double barrelAngle;
    private Projectile p;
    private Area tankArea;
    public boolean hit;
    private boolean forward;
    public Tank(Area a){
        this.projectilePath = new VertexForm(500,-130,100,-120,a);
        this.xLocal = 100;
        this.yLocal = -100;
        this.barrelAngle = 180;
        this.p = new Projectile(null,100,-120);
        this.forward = true;
    }

    public Tank(VertexForm projectilePath, double xLocal, double yLocal, double barrelAngle, boolean forward) {
        this.projectilePath = projectilePath;
        this.xLocal = xLocal;
        this.yLocal = yLocal;
        this.barrelAngle = barrelAngle;
        this.p = new Projectile(null,xLocal,yLocal);
        this.tankArea = null;
        this.forward = forward;
    }

    public boolean isForward() {
        return forward;
    }
    public void setForward(boolean forward) {
        this.forward = forward;
    }
    public VertexForm getProjectilePath() {
        return projectilePath;
    }
    public void setProjectilePath(VertexForm projectilePath) {
        this.projectilePath = projectilePath;
    }
    public double getxLocal() {
        return xLocal;
    }
    public void setxLocal(double xLocal) {
        this.xLocal = xLocal;
    }
    public double getyLocal() {
        return yLocal;
    }
    public void setyLocal(double yLocal) {
        this.yLocal = yLocal;
    }
    public double getBarrelAngle() {
        return barrelAngle;
    }
    public Projectile getP() {
        return p;
    }
    public void setP(Projectile p) {
        this.p = p;
    }
    public void realSetBarrel(int degree){
        barrelAngle = degree;
    }
    public void setBarrelAngle(int flag) {
        if(flag ==1){
            if(getBarrelAngle()!= 0){
                barrelAngle -=1;
            }
        }else if(flag == 0){
            if(getBarrelAngle()!= 180){
                barrelAngle +=1;
            }
        }
    }
    public void drawTank(Graphics g ,int flag){
        Color tankColor = new Color(24,97,31);
        Color wheelColor = new Color(144,141,156);
        Graphics2D e = (Graphics2D)g;
        if(!hit) {
            p.drawPro(e);
            e.setColor(wheelColor);

            e.fillOval((int) xLocal - 40, (int) yLocal + 3, 25, 25);
            e.fillOval((int) xLocal + 20, (int) yLocal + 3, 25, 25);
            if (flag == 0) {
                e.setColor(Color.BLACK);
                projectilePath.drawPath(e, forward);
            }
            e.setColor(tankColor);
            tankArea = new Area(new Rectangle2D.Double(xLocal - 60, yLocal - 30, 120, 45));
            tankArea.add(new Area(new Rectangle2D.Double(xLocal - 30, yLocal - 40, 60, 15)));
            e.fill(tankArea);

            e.rotate(Math.toRadians(barrelAngle), xLocal, yLocal - 20);
            e.setColor(tankColor);
            Rectangle2D.Double rect1 = new Rectangle.Double(xLocal - 90, yLocal - 30, 100, 30);
            e.fill(rect1);
            tankArea.add(new Area(rect1));
            e.rotate(Math.toRadians(360 - barrelAngle), xLocal, yLocal - 20);
        }else{

        }
    }
    public void moveLeftRight(int value, int val2){
        xLocal += value;
        if(p.getxPos()+value == xLocal || p.getHit()==1){
            p.setxPos(xLocal);
        }
        projectilePath.setStartX(xLocal);
        projectilePath.setH(xLocal+ val2);
    }
    public void moveUpDown(int value, int value2){
        projectilePath.setK(projectilePath.getK() + value);
        projectilePath.setH(projectilePath.getH() + value2);
    }
    public void barrelMove(int value){
        projectilePath.setK(projectilePath.getK()+value);
    }
    public void hitTank(Projectile p){
        if(tankArea.contains(p.getxPos(),p.getyPos())){
            hit = true;
        }else{
            hit = false;
        }
    }
    public boolean getHit(){return hit;}

}
