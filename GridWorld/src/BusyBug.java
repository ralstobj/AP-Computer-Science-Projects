import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * Created by Bailey Ralston on 4/20/2017.
 */
public class BusyBug extends BaseBug {
    boolean male;

    public BusyBug(){
        super();
        male = true;
    }
    public BusyBug(boolean male){
        super();
        this.male = male;
    }
    public boolean getMale(){return male; }
    public void setMale(boolean male){this.male = male;}
    public void act() {
        if(this.canMove()) {
            this.move();
        } else {
            this.turn();
        }

    }

    public void turn() {
        this.setDirection(this.getDirection() + 45);
    }

    public void move() {
        Grid gr = this.getGrid();
        if(gr != null) {
            Location loc = this.getLocation();
            Location next = loc.getAdjacentLocation(this.getDirection());
            if(gr.isValid(next)) {
                this.moveTo(next);
            } else {
                this.removeSelfFromGrid();
            }
        }
    }

    public boolean canMove() {
        Grid gr = this.getGrid();
        if(gr == null) {
            return false;
        } else {
            Location loc = this.getLocation();
            Location next = loc.getAdjacentLocation(this.getDirection());
            if(!gr.isValid(next)) {
                return false;
            } else {
                Actor neighbor = (Actor)gr.get(next);
                return neighbor == null;
            }
        }
    }

}
