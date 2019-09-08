import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bailey Ralston on 4/18/2017.
 */
public class DiagonalBug extends BaseBug
{
    //Constructor overridden to cause bug to start in one of four diagonal positions
    //Initializes DiagonalBug instance, no preconditions
    public DiagonalBug()
    {
        super();
        setColor(Color.blue);
        setDirection(45 + 90*((int)(4*Math.random())));
    }
    //Turns 4 times every turn so that bug turns 180 degrees
    //Post condition: bug turned 180 degrees
    //Precondition: instance initialized
    public void turn()
    {
        super.turn();
        super.turn();
        super.turn();
        super.turn();
    }
    @Override
    public void breed(){
        Random rand = new Random();
        ArrayList<Location> empty = getGrid().getEmptyAdjacentLocations(this.getLocation());
        if(empty.size()>0) {
            bred = true;
            int places = rand.nextInt((empty.size()));
            for (int i = 0; i < places; i++) {
                BaseBug hi = new DiagonalBug();
                hi.putSelfInGrid(getGrid(), empty.get(i));
            }
        }
    }
}