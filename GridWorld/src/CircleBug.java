import info.gridworld.actor.Bug;

/**
 * Created by Bailey Ralston on 4/18/2017.
 */

public class CircleBug extends Bug
{
    private int steps;
    private int sideLength;

    public CircleBug(int n)
    {
        sideLength=n;
        steps=0;
    }

    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            turn();
            steps=0;
        }
    }

}
