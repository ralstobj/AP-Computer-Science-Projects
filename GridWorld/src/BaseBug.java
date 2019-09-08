import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bailey Ralston on 4/28/2017.
 */
public class BaseBug extends Actor {
    private int turns;
    public boolean bred;
    public BaseBug() {
        this.setColor(Color.RED);
        turns = 0;
        bred = false;
    }

    public BaseBug(Color bugColor) {
        this.setColor(bugColor);
        turns = 0;
        bred = false;
    }
    public void breed() {
        Random rand = new Random();
        ArrayList<Location> empty = getGrid().getEmptyAdjacentLocations(this.getLocation());
        if(empty.size()>1) {
            bred = true;
            int places = rand.nextInt((empty.size()-1));
            for (int i = 0; i < places; i++) {
                BaseBug hi = new BaseBug();
                hi.putSelfInGrid(getGrid(), empty.get(i));
            }
        }
    }
    public void act() {
        turns++;
        Color g = this.getColor().darker();
        this.setColor(g);
        if(this.canMove()) {
            this.move();
        } else {
            this.turn();
        }
        if(!bred) {
            if (getDistance()) {
                breed();
            }
        }
        if(turns == 30){
            this.removeSelfFromGrid();
        }
    }
    public boolean getDistance(){
        Grid gr = getGrid();
        ArrayList<Location> locationsOfBugs = gr.getOccupiedLocations();
        Random rand = new Random();
        for(Location l:locationsOfBugs) {
            double x1, x2, y1, y2;
            x1 = l.getCol();
            y1 = l.getRow();
            x2 = this.getLocation().getCol();
            y2 = this.getLocation().getRow();
            double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                if(!bred) {
                    if (distance <= 2.0 && distance != 0.0) {
                        if (getGrid().get(l) instanceof BaseBug) {
                            if (turns >= 10) {
                                return true;
                            }
                        }
                    }
                }
            }
        return false;
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
                return neighbor == null || neighbor instanceof Flower;
            }
        }
    }
}
