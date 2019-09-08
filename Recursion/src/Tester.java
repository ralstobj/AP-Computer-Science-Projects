import java.util.Scanner;

/**
 * Created by Bailey Ralston on 2/13/2017.
 */
public class Tester {
    public static void main(String args[]){
        boolean test=true;
        Fibonacci f = new Fibonacci();
        Summation s = new Summation();
        Exponential e = new Exponential();
        while(test) {
            System.out.println("Enter e for exponential");
            System.out.println("Enter s for Summation");
            System.out.println("Enter f for Fibonacci");
            System.out.println("Enter g to End");
            System.out.println("-----------------");
            Scanner reader = new Scanner(System.in);
            String r = reader.nextLine();
            if(r.equals("e")){
                System.out.println("Exponential:");
                System.out.println("Enter input one");
                int numOne = reader.nextInt();
                System.out.println("Enter power");
                int numTwo = reader.nextInt();
                System.out.println("Recursive:" + e.recursiveWay(numOne,numTwo));
                System.out.println("Iterative:" + e.iterativeWay(numOne,numTwo));
                System.out.println("-----------------");
            }else if(r.equals("s")){
                System.out.println("Summation:");
                System.out.println("Enter input one");
                int numOne = reader.nextInt();
                System.out.println("Recursive:" + s.recursiveWay(numOne,0));
                System.out.println("Iterative:" + s.iterativeWay(numOne,0));
                System.out.println("-----------------");
            }else if(r.equals("f")){
                System.out.println("Fibonacci:");
                System.out.println("Enter input one");
                int numOne = reader.nextInt();
                System.out.println("Recursive:" + f.recursiveWay(numOne,0));
                System.out.println("Iterative:" + f.iterativeWay(numOne,0));
                System.out.println("-----------------");
            }else if(r.equals("g")){
                test = false;
            }

        }
    }

}
