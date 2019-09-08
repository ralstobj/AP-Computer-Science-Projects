import java.util.Random;

/**
 * Created by Bailey Ralston on 1/19/2017.
 */
public class HighStu extends Student {
    private static final int MIN_FEE = 30;
    private static final int MAX_FEE = 200;
    private static final double WEIGHT_EXAM = .15;
    private static final double WEIGHT_HOMEWORK = .10;
    private static final double WEIGHT_QUIZ = .10;
    private static final double WEIGHT_TEST = .40;
    private static final double WEIGHT_ASSIGNMENT = .25;
    private double finalExam;
    private double homeworkGrade;
    private double quizGrade;
    private double testGrades;
    private double assignmentGrade;
    public static double HighFees = 0.0;

    public HighStu(int i) {
        super(i);
        Random random = new Random();
        finalExam = random.nextInt(MAX_GRADE - MIN_GRADE + 1) + MIN_GRADE;
        homeworkGrade = random.nextInt(MAX_GRADE - MIN_GRADE + 1) + MIN_GRADE;
        quizGrade = random.nextInt(MAX_GRADE - MIN_GRADE + 1) + MIN_GRADE;
        testGrades = random.nextInt(MAX_GRADE - MIN_GRADE + 1) + MIN_GRADE;
        assignmentGrade = random.nextInt(MAX_GRADE - MIN_GRADE + 1) + MIN_GRADE;
        fees = MIN_FEE + (MAX_FEE - MIN_FEE) * random.nextDouble();
        HighFees += fees;
        totalFees +=fees;
    }
    public HighStu(String s){
        String[] parts = s.split(";");
        this.lastName = parts[0];
        this.firstName = parts[1];
        this.fees = Double.parseDouble(parts[2]);
        this.idNum = Integer.parseInt(parts[3]);
        this.homeworkGrade = Double.parseDouble(parts[4]);
        this.testGrades = Double.parseDouble(parts[5]);
        this.assignmentGrade = Double.parseDouble(parts[6]);
        this.quizGrade = Double.parseDouble(parts[7]);
        this.finalExam = Double.parseDouble(parts[8]);
        HighFees += fees;
        totalFees +=fees;
    }
    public HighStu(String last, String first, double fee, int id, double hw,
                   double test, double assign, double quiz, double finalExam){
        this.lastName = last;
        this.firstName = first;
        this.fees = fee;
        this.idNum = id;
        this.homeworkGrade = hw;
        this.testGrades = test;
        this.assignmentGrade = assign;
        this.quizGrade = quiz;
        this.finalExam = finalExam;
        HighFees += fees;
        totalFees +=fees;
    }
    @Override
    public String saveFormat(){
        return super.saveFormat() + homeworkGrade + ';' + testGrades + ';' + assignmentGrade + ';' + quizGrade+ ';' + finalExam + ';';
    }
    public double getHomeworkGrade() {
        return homeworkGrade;
    }

    public void setHomeworkGrade(double homeworkGrade) {
        this.homeworkGrade = homeworkGrade;
    }

    @Override
    public double getFees() {
        return fees;
    }

    @Override
    public double getGradeAverage() {
        return ((finalExam / 100.0) * WEIGHT_EXAM * MAX_GRADE) + ((homeworkGrade / 100.0) * WEIGHT_HOMEWORK * MAX_GRADE) +
                ((quizGrade / 100.0) * WEIGHT_QUIZ * MAX_GRADE) + ((testGrades / 100.0) * WEIGHT_TEST * MAX_GRADE) +
                ((assignmentGrade / 100.0) * WEIGHT_ASSIGNMENT * MAX_GRADE);
    }

    public double getQuizGrade() {
        return quizGrade;
    }

    public void setQuizGrade(double quizGrade) {
        this.quizGrade = quizGrade;
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

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    public String toString() {
        return super.toString();
    }

}
