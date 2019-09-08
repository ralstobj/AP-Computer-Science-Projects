import java.text.DecimalFormat;

public class PartTimeEmployee extends Employee {

    public PartTimeEmployee() {
        super();
    }
    public PartTimeEmployee(String name, int rate, int hrs){
        super(name,rate,hrs);
    }
    public PartTimeEmployee(int i){
        super(i);
    }
    public PartTimeEmployee(PartTimeEmployee f){
        super(f.getName(),f.getRate(),f.getHours());
    }
    public double getPay() {
        double pay;
        pay = Math.round((rate * hours)*100.0)/100.0;
        totalPay += pay;
        totalPayPart += pay;
        return pay;
    }
    public String toString(){
        DecimalFormat money = new DecimalFormat("'$'0.00");
        return "Part Time: " + '\n' + super.toString() + "GrossPay: " + money.format(getPay());
    }

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.name.compareTo(o2.name);
    }
}

