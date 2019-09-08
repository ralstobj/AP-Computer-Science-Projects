/**
 * Created by Bailey Ralston on 4/18/2017.
 */
public class SpiralBug extends BoxBug{

    public SpiralBug(int length) {
        super(length);
    }

    /**
     * Moves to the next location of the spiral.
     */
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
            turn();
            steps = 0;
            sideLength++;
        }
    }

}
