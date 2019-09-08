import java.awt.Graphics;
import java.awt.Font;
public class Player {

    private int myPoints;
    private String myInitals;
    
    public Player(){
    	myPoints = 0;
    	myInitals = "";
    }
    public Player(int p, String i){
    	myPoints = p;
    	myInitals = i;
    }
    public Player(Player p){
    	myPoints = p.getPoints();
    	myInitals = p.getInitals();
    }
	public int getPoints() {return myPoints;}
	public void setPoints(int myPoints) {
		this.myPoints = myPoints;
	}

	public String getInitals() {return myInitals;}
	public void setInitals(String myInitals) {
		this.myInitals = myInitals;
	}
	public String toString(){
		String str;
		str = getInitals() + " " + getPoints();
		return str;
	}
	public void draw(Graphics g, int x, int y){
		g.setFont(new Font("Fira Sans", Font.ITALIC, 50));
		g.drawString(toString(),x,y );
	}

     

}
