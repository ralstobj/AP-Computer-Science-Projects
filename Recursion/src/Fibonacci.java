/**
 * Created by Bailey Ralston on 2/13/2017.
 */
public class Fibonacci extends ProgramBase {

    public Fibonacci(){}

    @Override
    public long recursiveWay(long i, long j) {
        if(i == 0)
            return 0;
        else if(i == 1)
            return 1;
        else
            return recursiveWay(i - 1,j) + recursiveWay(i-2,0);
    }

    @Override
    public long iterativeWay(long i, long j) {
        long prev1 = 1;
        long prev2 = 0;
        long temp;
        for(int q=0; q<i; q++){
            temp = prev1;
            prev1= prev2;
            prev2 = temp+prev2;
        }
        return prev2;
    }


}
