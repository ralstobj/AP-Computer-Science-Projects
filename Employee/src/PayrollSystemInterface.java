/* PayrollSystemInterface.java
1. Request employee name, type, pay rate, and hours.
2. Print employee name and pay.
3. Repeat until the name is blank.
4. Print the total pay. */


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PayrollSystemInterface {

    public static void main(String[] args) {
        ArrayList<Employee> emps = new ArrayList<>();
        ArrayList<Employee> emp2 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000 ; i++) {
            int value = random.nextInt(2 -1 + 1) + 1;
            if(value == 1){
                emps.add(new FullTimeEmployee(1));
            }else{
                emps.add(new PartTimeEmployee(1));
            }
        }
        Sorter s = new Sorter(emps);
        emp2 = s.sortAscending();
        for(int i=0; i<emp2.size(); i++){
            System.out.println(emp2.get(i).toString());
            System.out.println("-----------------");
        }
        System.out.print("Search Employee:");
        Scanner reader = new Scanner(System.in);
        String s1 = reader.nextLine();
        if(s.search(s1)!=-1){
            System.out.println(emp2.get(s.search(s1)));
        }else{
            System.out.println("Not Found");
        }
        reader.close();


    }
}   
         

