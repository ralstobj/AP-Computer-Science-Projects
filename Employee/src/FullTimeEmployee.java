import java.text.DecimalFormat;

public class FullTimeEmployee extends Employee {

    public FullTimeEmployee() {
        super();
    }
    public FullTimeEmployee(String name, int rate, int hrs){
        super(name,rate,hrs);}
    public FullTimeEmployee(int i){
        super(i);
    }
    public FullTimeEmployee(FullTimeEmployee f){
        super(f.getName(),f.getRate(),f.getHours());
    }


    public double getPay() {
        double pay;

        if (hours <= 40)
            pay = Math.round((rate * hours)*100.0)/100.0;
        else
            pay = Math.round((rate * 40 + rate * 2 * (hours - 40))*100.0)/100.0;

        totalPay += pay;
        totalPayFull += pay;
        return pay;
    }
    public String toString(){
        DecimalFormat money = new DecimalFormat("'$'0.00");
        return "Full Time: " + '\n' + super.toString() + "GrossPay: " + money.format(getPay());
    }

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.name.compareTo(o2.name);
    }
}

