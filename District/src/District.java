import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Bailey Ralston on 1/29/2017.
 */
public class District {
    private ArrayList<Student> students = new ArrayList<>();
    public District(int numOfRandom){
        Random random = new Random();
        for(int i=0; i<numOfRandom; i++){
            int value = random.nextInt(3)+1;
            if(value==3){
                students.add(new ElementaryStu(1));

            }else if(value==2){
                students.add(new HighStu(1));
            }else{
                students.add(new MiddleStu(1));
            }
        }
    }
    public District(File filename){
        try {
            Scanner reader = new Scanner(filename);
            while(reader.hasNext()){
                String stu = reader.nextLine();
                int cnt=0;
                for(int i = 0; i < stu.length(); i++){
                    if(stu.charAt(i)==';'){
                        cnt++;
                    }
                }
                if(cnt == 7){
                    students.add(new ElementaryStu(stu));
                }else if(cnt == 8){
                    students.add(new MiddleStu(stu));
                }else{
                    students.add(new HighStu(stu));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void saveStudents(String fileName){
        try {
            PrintWriter writer = new PrintWriter(new File(fileName));
            for(int i=0; i<students.size(); i++){
                writer.println(students.get(i).saveFormat());
            }
            writer.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public int getDistrictSize(){
        return students.size();
    }
    public void deleteStudent(int index){
        students.remove(index);
    }
    public void replaceStudent(int index, Student stu){
        students.set(index,stu);
    }
    public void addStudent(Student stu){
        students.add(stu);
    }
    public Student getStudent(int index){return students.get(index);}
    public ArrayList<Student> getStudents(){return students;}
}
