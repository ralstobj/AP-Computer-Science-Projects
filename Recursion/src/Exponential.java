/**
 * Created by Bailey Ralston on 2/13/2017.
 */
public class Exponential extends ProgramBase {

    public Exponential(){}

    @Override
    public long recursiveWay(long i, long j) {
        if(j==1){
            return i;
        }else{
            return i*recursiveWay(i,j-1);
        }
    }

    @Override
    public long iterativeWay(long i, long j) {
        long total=i;
        for(int q=1; q<j;q++){
            total *= i;
        }
        return total;
    }

}
