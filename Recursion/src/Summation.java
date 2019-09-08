/**
 * Created by Bailey Ralston on 2/13/2017.
 */
public class Summation extends ProgramBase {

    public Summation(){}

    @Override
    public long recursiveWay(long i, long j) {
        if(i==0) {
            return i;
        }
        else{
            return i + recursiveWay(i - 1, 0);
        }
    }

    @Override
    public long iterativeWay(long i, long j) {
        long totalSum=0;
        for(int q=0; q<=i;q++){
            totalSum+=q;
        }
        return totalSum;
    }


}
