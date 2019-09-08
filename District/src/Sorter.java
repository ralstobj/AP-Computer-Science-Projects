import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Bailey Ralston on 1/25/2017.
 */
public class Sorter {
    public ArrayList<Student> students2;

    public Sorter(ArrayList<Student> e) {
        students2 = new ArrayList<>();
        for(int i = 0; i<e.size(); i++){
            students2.add(e.get(i));
        }
    }

    public ArrayList<Student> getArrayList() {
        return this.students2;
    }
    public Student getStudent(int index){return students2.get(index);}

    public void sortFirst() {
        for (int i = 0; i < students2.size() - 1; i++) {
            for (int k = i + 1; k < students2.size(); k++) {
                // Compare left to right, not right to left.
                if (students2.get(i).getFirstName().compareToIgnoreCase(students2.get(k).getFirstName()) > 0) {
                    Student temp = students2.get(i);
                    students2.set(i, students2.get(k));
                    students2.set(k, temp);
                }
            }
        }
    }
    public void sortLast() {
        for (int i = 0; i < students2.size() - 1; i++) {
            for (int k = i + 1; k < students2.size(); k++) {
                // Compare left to right, not right to left.
                if (students2.get(i).getLastName().compareToIgnoreCase(students2.get(k).getLastName()) > 0) {
                    Student temp = students2.get(i);
                    students2.set(i, students2.get(k));
                    students2.set(k, temp);
                }
            }
        }
    }
    public void sortid() {
        for (int i = 0; i < students2.size() - 1; i++) {
            for (int k = i + 1; k < students2.size(); k++) {
                // Compare left to right, not right to left.
                if (students2.get(i).getIdNum()<students2.get(k).getIdNum()) {
                    Student temp = students2.get(i);
                    students2.set(i, students2.get(k));
                    students2.set(k, temp);
                }
            }
        }
    }

    public int searchFirst(String name) {
        int c=-1;
        name.trim();
        int high = students2.size()-1;
        int low = 0;
        while(low<=high){
            int md =(low+high)/2;
            if (name.compareToIgnoreCase(students2.get(md).getFirstName())==0) {
                c = md;
                return c;
            }else if(name.compareToIgnoreCase(students2.get(md).getFirstName()) > 0){
                low = md +1;

            }else{
                high = md -1;
            }
        }
        return c;
    }
    public int searchLast(String name) {
        int c=-1;
        name.trim();
        int high = students2.size()-1;
        int low = 0;
        while(low<=high){
            int md =(low+high)/2;
            if (name.compareToIgnoreCase(students2.get(md).getLastName())==0) {
                c = md;
                return c;
            }else if(name.compareToIgnoreCase(students2.get(md).getLastName()) > 0){
                low = md +1;

            }else{
                high = md -1;
            }
        }
        return c;
    }
    public int searchId(int id) {
        int c=-1;
        int high = students2.size()-1;
        int low = 0;
        while(low<=high){
            int md =(low+high)/2;
            if (id == students2.get(md).getIdNum()) {
                c = md;
                return c;
            }else if(id < students2.get(md).getIdNum()){
                low = md +1;
            }else{
                high = md -1;
            }
        }
        return c;
    }

}
