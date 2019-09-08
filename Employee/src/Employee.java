import jdk.internal.dynalink.beans.StaticClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.text.DecimalFormat;
abstract public class Employee implements Comparator<Employee> {

    public static double LOW_RATE = 6.75;
    public static double HIGH_RATE = 30.50;
    public static double LOW_HOURS = 1;
    public static double HIGH_HOURS = 60;
    public static int empCount = 1000;
    protected static double totalPay = 0;
    protected static double totalPayPart = 0;
    protected static double totalPayFull = 0;
    private static ArrayList<String> firstNames = new ArrayList<>();
    private static ArrayList<String> lastNames = new ArrayList<>();
    // Protected Instance Variables:
    protected String name;
    protected double rate;
    protected int hours;
    protected int myId;

    // Public Methods:
    public Employee() {
        name = "";
        rate = 0;
        hours = 0;
        myId = empCount++;
    }
    public Employee(String name, double rate, int hrs){
        this.name = name;
        this.rate = rate;
        this.hours = hrs;
        this.myId = empCount++;
    }
    public Employee(int i){
        Random random = new Random();
        if(firstNames.size()== 0) {
          fillFirstAndLast();
        }
            this.name = lastNames.get(random.nextInt(lastNames.size() -2 + 1) + 1); //+ ", " +
                   // firstNames.get(random.nextInt(firstNames.size() -1 + 1) + 1);
            this.rate = Math.round((LOW_RATE + (HIGH_RATE - LOW_RATE) * random.nextDouble())*100.0)/100.0;
            this.hours = (int)(LOW_HOURS + (HIGH_HOURS - LOW_RATE) * random.nextDouble());
            this.myId = empCount++;
    }
    private void fillFirstAndLast(){
        try {
            Scanner readerLast = new Scanner(new File("src/LastNames.txt"));
            while(readerLast.hasNext()){
                lastNames.add(readerLast.nextLine());
            }
            readerLast.close();
            Scanner readerFirst = new Scanner(new File("src/FirstNames.txt"));
            while(readerFirst.hasNext()){
                firstNames.add(readerFirst.nextLine());
            }
            readerFirst.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static String getNameRules() {
        return "nonblank";
    }

    //---------------------------------------

    public static String getTypeRules() {
        return "1 or 2";
    }

    public static String getRateRules() {
        return getRule(LOW_RATE, HIGH_RATE);
    }

    public static String getHoursRules() {
        return getRule(LOW_HOURS, HIGH_HOURS);
    }

    public static boolean typeOK(int type) {
        return type == 1 || type == 2;
    }
    public static double getTotalPayPart(){return totalPayPart;}
    public static double getTotalPayFull(){return totalPayFull;}
    public static double getTotalPay() {
        return totalPay;
    }

    private static String getRule(double low, double high) {
        return "between " + low + " and " + high + ", inclusive";
    }

    public int getHours() {
        return hours;
    }

    public double getRate() {
        return rate;
    }

    public boolean setName(String nm) {
        if (nm.equals(""))
            return false;
        else {
            name = nm;
            return true;
        }
    }

    public boolean setRate(double rt) {
        if (!(LOW_RATE <= rt && rt <= HIGH_RATE))
            return false;
        else {
            rate = rt;
            return true;
        }
    }

    public boolean setId(int id) {
        this.myId = id;
        return true;
    }

    public boolean setHours(int hrs) {
        if (!(LOW_HOURS <= hrs && hrs <= HIGH_HOURS))
            return false;
        else {
            hours = hrs;
            return true;
        }
    }

    public int getId() {
        return myId;
    }
    public String getName() {
        return name;
    }
    abstract public double getPay();
    public String toString(){
        DecimalFormat money = new DecimalFormat("'$'0.00");
        return "ID: " + getId()+ '\n' +
                "Name: "+ getName()+ '\n'+
                "Hours: " + getHours()+ '\n'+
                "Wage: " + money.format(getRate())+ '\n';
    }
    public static class Comparators{
        public static final Comparator<Employee> NAME = (Employee o1, Employee o2) -> o1.name.compareTo(o2.name);
    }

}

