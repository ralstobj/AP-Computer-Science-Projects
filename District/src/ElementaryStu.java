import java.util.Random;

/**
 * Created by Bailey Ralston on 1/19/2017.
 */
public class ElementaryStu extends Student {
    private static final int MIN_FEE = 10;
    private static final int MAX_FEE = 50;
    private static final double WEIGHT_HOMEWORK = .25;
    private static final double WEIGHT_TEST = .30;
    private static final double WEIGHT_ASSIGNMENT = .45;
    private double homeworkGrade;
    private double testGrades;
    public static double ElementaryFees = 0.0;
    private double assignmentGrade;

    public ElementaryStu(int i) {
        super(i);
        Random random = new Random();
        homeworkGrade = random.nextInt(MAX_GRADE - MIN_GRADE + 1) + MIN_GRADE;
        testGrades = random.nextInt(MAX_GRADE - MIN_GRADE + 1) + MIN_GRADE;
        assignmentGrade = random.nextInt(MAX_GRADE - MIN_GRADE + 1) + MIN_GRADE;
        fees = MIN_FEE + (MAX_FEE - MIN_FEE) * random.nextDouble();
        totalFees += fees;
        ElementaryFees += fees;
    }
    public ElementaryStu(String last, String first, double fee, int id, double hw,
                   double test, double assign){
        this.lastName = last;
        this.firstName = first;
        this.fees = fee;
        this.idNum = id;
        this.homeworkGrade = hw;
        this.testGrades = test;
        this.assignmentGrade = assign;
        totalFees += fees;
        ElementaryFees += fees;
    }
    public ElementaryStu(String s){
        String[] parts = s.split(";");
        this.lastName = parts[0];
        this.firstName = parts[1];
        this.fees = Double.parseDouble(parts[2]);
        this.idNum = Integer.parseInt(parts[3]);
        this.homeworkGrade = Double.parseDouble(parts[4]);
        this.testGrades = Double.parseDouble(parts[5]);
        this.assignmentGrade = Double.parseDouble(parts[6]);
        totalFees += fees;
        ElementaryFees += fees;
    }
    @Override
    public double getFees() {
        return fees;
    }
    @Override
    public String saveFormat(){
        return super.saveFormat() + homeworkGrade + ';' + testGrades + ';' + assignmentGrade + ';';
    }
    @Override
    public double getGradeAverage() {
        return ((homeworkGrade / 100.0) * WEIGHT_HOMEWORK * MAX_GRADE) + ((testGrades / 100.0) * WEIGHT_TEST * MAX_GRADE) +
                ((assignmentGrade / 100.0) * WEIGHT_ASSIGNMENT * MAX_GRADE);
    }

    public String toString() {
        return super.toString();
    }

    public double getHomeworkGrade() {
        return homeworkGrade;
    }

    public void setHomeworkGrade(double homeworkGrade) {
        this.homeworkGrade = homeworkGrade;
    }

    public double getTestGrades() {
        return testGrades;
    }

    public void setTestGrades(double testGrades) {
        this.testGrades = testGrades;
    }

    public double getAssignmentGrade() {
        return assignmentGrade;
    }

    public void setAssignmentGrade(double assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
    }
}
