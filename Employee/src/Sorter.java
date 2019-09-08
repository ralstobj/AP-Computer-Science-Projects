import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Bailey Ralston on 1/25/2017.
 */
public class Sorter {
    public ArrayList<Employee> employees;

    public Sorter(ArrayList<Employee> e){
        this.employees = e;
    }

    public ArrayList<Employee> getArrayList() {
        return this.employees;
    }

    public ArrayList<Employee> sortAscending() {
        Collections.sort(this.employees,Employee.Comparators.NAME );
        return this.employees;
    }

    public int search(String name) {
        int c=-1;
        name.trim();
        int high = employees.size()-1;
        int low = 0;
        while(low<=high){
            int md =(low+high)/2;
            if (name.compareToIgnoreCase(employees.get(md).getName())==0) {
                c = md;
                return c;
            }else if(name.compareToIgnoreCase(employees.get(md).getName()) > 0){
                low = md +1;

            }else{
                high = md -1;
            }
        }
        return c;
    }

}
