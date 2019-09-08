import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by Bailey Ralston on 1/19/2017.
 */
public class DistrictGUI extends JFrame implements ActionListener {

    private JPanel baseInfoPanel, testPanel, optionsPanel, topPanel, bottomPanel;
    private int index = 0;
    private int type = 0;
    private Sorter sorter;
    private ImageIcon middleIcon = new ImageIcon("src/Middle_School.jpg");
    private ImageIcon highIcon = new ImageIcon("src/High_School.jpeg");
    private ImageIcon elementIcon = new ImageIcon("src/Elementary.jpeg");
    private JLabel lNameLabel, fNameLabel, idLabel, feeLabel, gradeLabel,
            hwLabel, testLabel, assignLabel, quizLabel, finalLabel,
            stuTypeLabel, stuTypeValLabel, totalStusLabel, totalStusValLabel,
            currentStuLabel, searchLabel, totalFeeLabel, totalFeeValLabel, imageLabel;
    private JTextField lastNameField, firstNameField, idField, feeField, gradeField,
            homeworkField, testField, assignmentField, quizField, finalField, searchField;
    private JButton addButton, deleteButton, prevButton, nextButton, searchButton, editButton,
            fileButton, randomButton, newButton;
    private String[] searchOptions = {"id", "Last Name", "First Name"};
    private JComboBox searchBy = new JComboBox(searchOptions);
    private Font f = new Font("Calibri", Font.PLAIN, 30);
    private File selectedFile;
    private District dst;

    public DistrictGUI() {
        lNameLabel = new JLabel("Last Name:", SwingConstants.CENTER);
        lNameLabel.setFont(f);
        fNameLabel = new JLabel("First Name:", SwingConstants.CENTER);
        fNameLabel.setFont(f);
        idLabel = new JLabel("Id:", SwingConstants.CENTER);
        idLabel.setFont(f);
        feeLabel = new JLabel("Fee:", SwingConstants.CENTER);
        feeLabel.setFont(f);
        gradeLabel = new JLabel("Grade:", SwingConstants.CENTER);
        gradeLabel.setFont(f);
        lastNameField = new JTextField(10);
        lastNameField.setFont(f);
        firstNameField = new JTextField(10);
        firstNameField.setFont(f);
        idField = new JTextField(10);
        idField.setFont(f);
        feeField = new JTextField(10);
        feeField.setFont(f);
        gradeField = new JTextField(10);
        gradeField.setFont(f);
        gradeField.setEnabled(false);
        baseInfoPanel = new JPanel(new GridLayout(5, 2));
        baseInfoPanel.add(fNameLabel);
        baseInfoPanel.add(firstNameField);
        baseInfoPanel.add(lNameLabel);
        baseInfoPanel.add(lastNameField);
        baseInfoPanel.add(idLabel);
        baseInfoPanel.add(idField);
        baseInfoPanel.add(feeLabel);
        baseInfoPanel.add(feeField);
        baseInfoPanel.add(gradeLabel);
        baseInfoPanel.add(gradeField);
        add(baseInfoPanel, BorderLayout.WEST);
        hwLabel = new JLabel("Homework Grade:", SwingConstants.CENTER);
        hwLabel.setFont(f);
        testLabel = new JLabel("Test Grades:", SwingConstants.CENTER);
        testLabel.setFont(f);
        assignLabel = new JLabel("Assignment Grades", SwingConstants.CENTER);
        assignLabel.setFont(f);
        quizLabel = new JLabel("Quiz Grade", SwingConstants.CENTER);
        quizLabel.setFont(f);
        finalLabel = new JLabel("Final Exam", SwingConstants.CENTER);
        finalLabel.setFont(f);
        homeworkField = new JTextField(10);
        homeworkField.setFont(f);
        testField = new JTextField(10);
        testField.setFont(f);
        assignmentField = new JTextField(10);
        assignmentField.setFont(f);
        quizField = new JTextField(10);
        quizField.setFont(f);
        finalField = new JTextField(10);
        finalField.setFont(f);
        testPanel = new JPanel(new GridLayout(5, 2));
        testPanel.add(hwLabel);
        testPanel.add(homeworkField);
        testPanel.add(testLabel);
        testPanel.add(testField);
        testPanel.add(assignLabel);
        testPanel.add(assignmentField);
        testPanel.add(quizLabel);
        testPanel.add(quizField);
        testPanel.add(finalLabel);
        testPanel.add(finalField);
        add(testPanel, BorderLayout.CENTER);
        topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setPreferredSize(new Dimension(1500, 200));
        stuTypeValLabel = new JLabel(" Student Type: Placeholder");
        stuTypeValLabel.setPreferredSize(new Dimension(100,200));
        stuTypeValLabel.setFont(f);
        totalFeeValLabel = new JLabel("#", SwingConstants.LEFT);
        totalFeeValLabel.setPreferredSize(new Dimension(500,200));
        totalFeeValLabel.setFont(f);
        topPanel.add(stuTypeValLabel);
        topPanel.add(totalFeeValLabel);
        add(topPanel, BorderLayout.NORTH);
        addButton = new JButton("Add");
        addButton.setEnabled(false);
        addButton.setFont(f);
        addButton.addActionListener(new AddListener());
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteListener());
        deleteButton.setFont(f);
        editButton = new JButton("Edit");
        editButton.addActionListener(new EditListener());
        editButton.setFont(f);
        fileButton = new JButton("File");
        fileButton.addActionListener(this);
        fileButton.setFont(f);
        randomButton = new JButton("Random");
        randomButton.addActionListener(new RandomListener());
        randomButton.setFont(f);
        newButton = new JButton("New");
        newButton.addActionListener(new NewListener());
        newButton.setFont(f);
        searchLabel = new JLabel("Search By:", SwingConstants.CENTER);
        searchLabel.setFont(f);
        searchBy.setFont(f);
        searchButton = new JButton("Search");
        searchButton.setFont(f);
        searchButton.addActionListener(new SearchListener());
        searchField = new JTextField(10);
        searchField.setFont(f);
        optionsPanel = new JPanel(new GridLayout(5, 2));
        optionsPanel.setPreferredSize(new Dimension(800, 1000));
        optionsPanel.add(fileButton);
        optionsPanel.add(randomButton);
        optionsPanel.add(newButton);
        optionsPanel.add(addButton);
        optionsPanel.add(editButton);
        optionsPanel.add(deleteButton);
        optionsPanel.add(searchLabel);
        optionsPanel.add(searchBy);
        optionsPanel.add(searchButton);
        optionsPanel.add(searchField);
        add(optionsPanel, BorderLayout.EAST);
        prevButton = new JButton("Previous");
        prevButton.addActionListener(new PreviousListener());
        prevButton.setFont(f);
        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextListener());
        nextButton.setFont(f);
        currentStuLabel = new JLabel("#", SwingConstants.CENTER);
        currentStuLabel.setFont(f);
        totalStusLabel = new JLabel("Total Number of Stus:");
        totalStusLabel.setFont(f);
        totalStusValLabel = new JLabel("#");
        totalStusValLabel.setFont(f);
        imageLabel = new JLabel();
        bottomPanel = new JPanel(new GridLayout(1, 6));
        bottomPanel.setPreferredSize(new Dimension(1500, 200));
        bottomPanel.add(prevButton);
        bottomPanel.add(currentStuLabel);
        bottomPanel.add(nextButton);
        bottomPanel.add(totalStusLabel);
        bottomPanel.add(totalStusValLabel);
        bottomPanel.add(imageLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String args[]) {
        DistrictGUI GUI = new DistrictGUI();
        GUI.setSize(2000, 1500);
        GUI.setTitle("District");
        GUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GUI.setVisible(true);
    }
    private void setIndeces(){
        for(int i = 0; i<dst.getDistrictSize(); i++){
            dst.getStudent(i).setCurrentIndex(i);
        }
    }

    private void getStudentInfo() {
        DecimalFormat moneyFormat = new DecimalFormat("$0.00");
        DecimalFormat gradeFormat = new DecimalFormat("##.00");
        Student s = dst.getStudent(index);
        firstNameField.setText(s.getFirstName());
        lastNameField.setText(s.getLastName());
        feeField.setText(moneyFormat.format(s.getFees()));
        idField.setText(Integer.toString(s.getIdNum()));
        gradeField.setText(gradeFormat.format(s.getGradeAverage()));
        if (s instanceof HighStu) {
            homeworkField.setText(Double.toString(((HighStu) s).getHomeworkGrade()));
            testField.setText(Double.toString(((HighStu) s).getTestGrades()));
            assignmentField.setText(Double.toString(((HighStu) s).getAssignmentGrade()));
            quizField.setText(Double.toString(((HighStu) s).getQuizGrade()));
            finalField.setText(Double.toString(((HighStu) s).getFinalExam()));
            hwLabel.setText("Homework Grade:");
            testLabel.setText("Test Grades:");
            assignLabel.setText("Assignment Grades");
            quizLabel.setText("Quiz Grade");
            finalLabel.setText("Final Exam");
            stuTypeValLabel.setText(" Student Type: High School Student");
            imageLabel.setIcon(highIcon);
            type = 0;
        } else if (s instanceof MiddleStu) {
            homeworkField.setText(Double.toString(((MiddleStu) s).getHomeworkGrade()));
            testField.setText(Double.toString(((MiddleStu) s).getTestGrades()));
            assignmentField.setText(Double.toString(((MiddleStu) s).getAssignmentGrade()));
            quizField.setText(Double.toString(((MiddleStu) s).getQuizGrade()));
            finalField.setText("");
            hwLabel.setText("Homework Grade:");
            testLabel.setText("Test Grades:");
            assignLabel.setText("Assignment Grades");
            quizLabel.setText("Quiz Grade");
            finalLabel.setText("N/A");
            stuTypeValLabel.setText(" Student Type: Middle School Student");
            imageLabel.setIcon(middleIcon);
            type = 1;
        } else {
            homeworkField.setText(Double.toString(((ElementaryStu) s).getHomeworkGrade()));
            testField.setText(Double.toString(((ElementaryStu) s).getTestGrades()));
            assignmentField.setText(Double.toString(((ElementaryStu) s).getAssignmentGrade()));
            quizField.setText("");
            finalField.setText("");
            hwLabel.setText("Homework Grade:");
            testLabel.setText("Test Grades:");
            assignLabel.setText("Assignment Grades");
            quizLabel.setText("N/A");
            finalLabel.setText("N/A");
            stuTypeValLabel.setText(" Student Type: Elementary School Student");
            imageLabel.setIcon(elementIcon);
            type = 2;
        }
        totalStusValLabel.setText(Integer.toString(dst.getDistrictSize()));
        currentStuLabel.setText(Integer.toString(index + 1));
        double totalFees=0;
        for(int i=0; i<dst.getDistrictSize(); i++){
            totalFees += dst.getStudent(i).fees;
        }
        totalFeeValLabel.setText("Fees High:  " +moneyFormat.format(HighStu.HighFees) + "    " + '\n' +
                "Fees Middle:  " + moneyFormat.format(MiddleStu.MiddleFees) + "    " + '\n'
                + "Fee Elementary:  " + moneyFormat.format(ElementaryStu.ElementaryFees));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser myChooser;
        if (e.getSource() == fileButton) {
            myChooser = new JFileChooser();
            myChooser.setPreferredSize(new Dimension(800, 600));
            myChooser.setCurrentDirectory(new File("D:"));
            if (myChooser.showOpenDialog(this)
                    == JFileChooser.APPROVE_OPTION) {
                selectedFile = myChooser.getSelectedFile();
                dst = new District(selectedFile);
                setIndeces();
                sorter = new Sorter(dst.getStudents());
                getStudentInfo();
            }
        }
    }

    private class RandomListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == randomButton) {
                int val = Integer.parseInt(JOptionPane.showInputDialog("How Many Students would you like?", "1000"));
                dst = new District(val);
                setIndeces();
                sorter = new Sorter(dst.getStudents());
                getStudentInfo();
            }
        }
    }

    protected class NextListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (dst != null) {
                if (index + 1 < dst.getDistrictSize()) {
                    index++;
                } else {
                    index = 0;
                }
                getStudentInfo();
            }
        }
    }
    private class PreviousListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (dst != null) {
                if (index - 1 >= 0) {
                    index--;
                } else {
                    index = dst.getDistrictSize() - 1;
                }
                getStudentInfo();
            }

        }
    }
    private class NewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(dst != null){
                addButton.setEnabled(true);
                firstNameField.setText("");
                lastNameField.setText("");
                feeField.setText("");
                gradeField.setText("");
                idField.setText("");
                quizField.setText("");
                assignmentField.setText("");
                testField.setText("");
                finalField.setText("");
                homeworkField.setText("");
                currentStuLabel.setText(Integer.toString(dst.getDistrictSize()+1));
                index = dst.getDistrictSize();
                String[] options = new String[] {"High School", "Middle School", "Elementary"};
                type = JOptionPane.showOptionDialog(null, "Please Choose a Type of New Student", "Type of Student",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[0]);
                if(type ==0){
                    stuTypeValLabel.setText("High School");
                    finalLabel.setText("Final Exam");
                    quizLabel.setText("Quiz Grade");
                }else if(type ==1){
                    stuTypeValLabel.setText("Middle School");
                    finalLabel.setText("N/A");
                    quizLabel.setText("Quiz Grade");
                }else{
                    stuTypeValLabel.setText("Elementary School");
                    finalLabel.setText("N/A");
                    quizLabel.setText(("N/A"));
                }
            }
        }
    }
    private class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(type ==0) {
                HighStu h = new HighStu(lastNameField.getText(),
                        firstNameField.getText(),
                        Double.parseDouble(feeField.getText()),
                        Integer.parseInt(idField.getText()),
                        Double.parseDouble(homeworkField.getText()),
                        Double.parseDouble(testField.getText()),
                        Double.parseDouble(assignmentField.getText()),
                        Double.parseDouble(quizField.getText()),
                        Double.parseDouble(finalField.getText()));
                dst.addStudent(h);
            }else if(type ==1){
                MiddleStu m = new MiddleStu(lastNameField.getText(),
                        firstNameField.getText(),
                        Double.parseDouble(feeField.getText()),
                        Integer.parseInt(idField.getText()),
                        Double.parseDouble(homeworkField.getText()),
                        Double.parseDouble(testField.getText()),
                        Double.parseDouble(assignmentField.getText()),
                        Double.parseDouble(quizField.getText()));
                dst.addStudent(m);
            }else{
                ElementaryStu el = new ElementaryStu(lastNameField.getText(),
                        firstNameField.getText(),
                        Double.parseDouble(feeField.getText()),
                        Integer.parseInt(idField.getText()),
                        Double.parseDouble(homeworkField.getText()),
                        Double.parseDouble(testField.getText()),
                        Double.parseDouble(assignmentField.getText()));
                dst.addStudent(el);
            }
            setIndeces();
            sorter = new Sorter(dst.getStudents());
            totalStusValLabel.setText(Integer.toString(dst.getDistrictSize()));

        }
    }
    private class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(dst !=null){
                if(type ==0) {
                    HighStu h = new HighStu(lastNameField.getText(),
                            firstNameField.getText(),
                            Double.parseDouble(feeField.getText()),
                            Integer.parseInt(idField.getText()),
                            Double.parseDouble(homeworkField.getText()),
                            Double.parseDouble(testField.getText()),
                            Double.parseDouble(assignmentField.getText()),
                            Double.parseDouble(quizField.getText()),
                            Double.parseDouble(finalField.getText()));
                    HighStu.HighFees -= (dst.getStudent(index).getFees());
                    Student.totalFees -=(dst.getStudent(index).getFees());
                    dst.replaceStudent(index,h);
                }else if(type ==1){
                    MiddleStu m = new MiddleStu(lastNameField.getText(),
                            firstNameField.getText(),
                            Double.parseDouble(feeField.getText()),
                            Integer.parseInt(idField.getText()),
                            Double.parseDouble(homeworkField.getText()),
                            Double.parseDouble(testField.getText()),
                            Double.parseDouble(assignmentField.getText()),
                            Double.parseDouble(quizField.getText()));
                    MiddleStu.MiddleFees -= (dst.getStudent(index).getFees());
                    Student.totalFees -=(dst.getStudent(index).getFees());
                    dst.replaceStudent(index,m);
                }else{
                    ElementaryStu el = new ElementaryStu(lastNameField.getText(),
                            firstNameField.getText(),
                            Double.parseDouble(feeField.getText()),
                            Integer.parseInt(idField.getText()),
                            Double.parseDouble(homeworkField.getText()),
                            Double.parseDouble(testField.getText()),
                            Double.parseDouble(assignmentField.getText()));
                    ElementaryStu.ElementaryFees -= (dst.getStudent(index).getFees());
                    Student.totalFees -=(dst.getStudent(index).getFees());
                    dst.replaceStudent(index,el);
                }
                setIndeces();
                sorter = new Sorter(dst.getStudents());
            }
        }
    }
    private class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(dst != null){
                Student.totalFees -=(dst.getStudent(index).getFees());
                if(dst.getStudent(index) instanceof HighStu){
                    HighStu.HighFees -= (dst.getStudent(index).getFees());
                }else if(dst.getStudent(index) instanceof MiddleStu) {
                    MiddleStu.MiddleFees -= dst.getStudent(index).getFees();
                }else{
                    ElementaryStu.ElementaryFees -=dst.getStudent(index).getFees();
                }

                dst.deleteStudent(index);
                getStudentInfo();
                setIndeces();
                sorter = new Sorter(dst.getStudents());
                totalStusValLabel.setText(Integer.toString(dst.getDistrictSize()));
            }
        }
    }
    private class SearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!searchField.getText().equals("") && dst != null) {
                try {
                    if (searchBy.getSelectedIndex() == 0) {
                        sorter.sortid();
                        int tempindex = sorter.searchId(Integer.parseInt(searchField.getText()));
                        int realIndex = sorter.getStudent(tempindex).getCurrentIndex();
                        index = realIndex;
                        getStudentInfo();
                    } else if (searchBy.getSelectedIndex() == 1) {
                        sorter.sortLast();
                        int tempindex = sorter.searchLast(searchField.getText());
                        int realIndex = sorter.getStudent(tempindex).getCurrentIndex();
                        index = realIndex;
                        getStudentInfo();
                    } else {
                        sorter.sortFirst();
                        int tempindex = sorter.searchFirst(searchField.getText());
                        int realIndex = sorter.getStudent(tempindex).getCurrentIndex();
                        index = realIndex;
                        getStudentInfo();
                    }
                }catch(Exception d){
                    System.out.print("Not Found");
            }
            }

        }
    }

}