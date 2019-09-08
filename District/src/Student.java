import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Bailey Ralston on 1/17/2017.
 */
abstract public class Student {
    static final int MAX_GRADE = 100;
    static final int MIN_GRADE = 50;
    public static int stuCount = 0;
    public static int totalFees = 0;
    private static ArrayList<String> firstNames = new ArrayList<>();
    private static ArrayList<String> lastNames = new ArrayList<>();
    protected String firstName;
    protected String lastName;
    protected int idNum;
    protected int currentIndex;
    protected double fees;

    public Student() {
        this.firstName = "";
        this.lastName = "";
        this.fees = 0.0;
        this.idNum = (stuCount++) + 6000;
    }
    public Student(int i) {
        Random random = new Random();
        if (firstNames.size() == 0) {
            fillFirstAndLast();
        }
        this.lastName = lastNames.get(random.nextInt(lastNames.size() - 2 + 1) + 1);
        this.firstName = firstNames.get(random.nextInt(firstNames.size() - 2 + 1) + 1);
        this.idNum = (stuCount++) + 6000;
    }

    private void fillFirstAndLast() {
        try {
            Scanner readerLast = new Scanner(new File("src/LastNames.txt"));
            while (readerLast.hasNext()) {
                lastNames.add(readerLast.nextLine());
            }
            readerLast.close();
            Scanner readerFirst = new Scanner(new File("src/FirstNames.txt"));
            while (readerFirst.hasNext()) {
                firstNames.add(readerFirst.nextLine());
            }
            readerFirst.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    abstract public double getFees();

    abstract public double getGradeAverage();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        DecimalFormat moneyFormat = new DecimalFormat("$0.00");
        DecimalFormat gradeFormat = new DecimalFormat("##.00");
        return "ID: " + Integer.toString(idNum) + '\n' +
                "Name: " + lastName + ", " + firstName + '\n' +
                "Grade: " + gradeFormat.format(getGradeAverage()) + "%" + '\n' +
                "Fees: " + moneyFormat.format(fees);
    }
    public String saveFormat(){
        DecimalFormat moneyFormat = new DecimalFormat("0.00");

        return lastName + ';' + firstName + ';' + moneyFormat.format(fees) +';' + idNum + ';';
    }


}
