import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * Created by Bailey Ralston on 12/8/2016.
 */
public class MatrixGUI extends JFrame {
    private Font defaultFont = new Font("Consolas",Font.PLAIN,30);
    private String[] operations = {"Operations","Addition","Multiply","Subtract", "Scalar"};
    private JComboBox listOfOperations = new JComboBox(operations);
    private JButton fileInput = new JButton("From a File");
    private JButton fileInput2 = new JButton("From a File");
    private String op="";
    private int hi =0;
    private int numOn =1;
    private JPanel oneMatrix = new JPanel(new GridLayout(3,1,12,6));
    private JPanel twoMatrix = new JPanel(new GridLayout(3,1,12,6));
    private JTextArea answer1 = new JTextArea();
    private JTextArea answer2 = new JTextArea();
    private JTextArea matrixP1 = new JTextArea();
    private JTextArea matrixP2 = new JTextArea();
    private JTextArea matrixP11 = new JTextArea();
    private JButton randomInput = new JButton("Random");
    private JButton manualInput = new JButton("Manual Fill");
    private JButton randomInput2 = new JButton("Random");
    private JButton manualInput2 = new JButton("Manual Fill");
    private JLabel matrix1 = new JLabel("Matrix 1", SwingConstants.CENTER);
    private JLabel matrix2 = new JLabel("Matrix 2", SwingConstants.CENTER);
    JPanel cards = new JPanel(new CardLayout());
    private Matrix m1;
    private Matrix m2;

    public MatrixGUI() throws IOException{
        setPreferredSize(new Dimension(1000,1000));
        JPanel comboPanel = new JPanel();
        listOfOperations.setFont(defaultFont);
        manualInput.setFont(defaultFont);
        randomInput.setFont(defaultFont);
        fileInput.setFont(defaultFont);
        manualInput2.setFont(defaultFont);
        randomInput2.setFont(defaultFont);
        fileInput2.setFont(defaultFont);
        matrix1.setFont(defaultFont);
        matrix2.setFont(defaultFont);
        matrixP1.setEditable(false);
        matrixP2.setEditable(false);
        matrixP11.setEditable(false);
        answer1.setEditable(false);
        answer2.setEditable(false);
        matrixP1.setFont(defaultFont);
        matrixP2.setFont(defaultFont);
        matrixP11.setFont(defaultFont);
        answer1.setFont(defaultFont);
        answer2.setFont(defaultFont);
        comboPanel.setLayout(new GridBagLayout());
        listOfOperations.setEditable(false);
        listOfOperations.addItemListener(new ItemListen());
        listOfOperations.setPreferredSize(new Dimension(300,100));
        comboPanel.add(listOfOperations);
        JPanel inputPanel = new JPanel(new GridLayout(4,1,12,6));
        JPanel inputPanel2 = new JPanel(new GridLayout(4,1,12,6));
        oneMatrix.add(matrixP11);
        oneMatrix.add(answer2);
        twoMatrix.add(matrixP1);
        twoMatrix.add(matrixP2);
        twoMatrix.add(answer1);
        cards.add(new JPanel(), "Blank");
        cards.add(oneMatrix, "One");
        cards.add(twoMatrix, "Two");
        cards.add(inputPanel, "Input");
        cards.add(inputPanel2,"Input2");
        inputPanel.add(matrix1);
        inputPanel.add(fileInput);
        inputPanel.add(randomInput);
        inputPanel.add(manualInput);
        inputPanel2.add(matrix2);
        inputPanel2.add(fileInput2);
        inputPanel2.add(randomInput2);
        inputPanel2.add(manualInput2);
        fileInput.addActionListener(new FileListener());
        manualInput.addActionListener(new ManualListener());
        randomInput.addActionListener(new RandomListener());
        fileInput2.addActionListener(new FileListener());
        manualInput2.addActionListener(new ManualListener());
        randomInput2.addActionListener(new RandomListener());
        Container container = getContentPane();
        container.add(comboPanel,BorderLayout.PAGE_START);
        container.add(cards,BorderLayout.CENTER);
    }
    private void operation(){
        try{
            if (op.equals("Scalar")) {
                String multiplier = JOptionPane.showInputDialog("Multiplier", "1");
                answer2.setText("Scalar Multiplied: " + "\n" + m1.scalar(Double.parseDouble(multiplier)).toString());
            } else if (op.equals("Multiply")) {
                answer1.setText("Multiplied: " + "\n" + m1.multiply(m2).toString());
            } else if (op.equals("Subtract")) {
                answer1.setText("Subtracted: " + "\n" + m1.sub(m2).toString());
            } else if (op.equals("Addition")) {
                answer1.setText("Added: " + "\n" + m1.add(m2).toString());
            }
        }catch(Exception e){
            answer1.setText("Invalid Dimensions");
            answer2.setText("Invalid Dimensions");
        }
    }
    private class ItemListen implements ItemListener{
        public void itemStateChanged(ItemEvent et){
            if(et.getItem() != "Operations") {
                if ((String) et.getItem() == "Scalar") {
                    hi = 1;
                } else {
                    hi = 0;
                }
                numOn = 1;
                op = (String) et.getItem();
                CardLayout c1 = (CardLayout) (cards.getLayout());
                c1.show(cards, "Input");
            }else{
                CardLayout c1 = (CardLayout) (cards.getLayout());
                c1.show(cards, "Blank");
            }
        }

    }

    private class FileListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String filename = JOptionPane.showInputDialog("Name of File", "matrixList.txt");
            if(hi ==0){
                if(numOn ==1) {
                    try {
                        m1 = new Matrix("src/" + filename);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    numOn =2;
                    CardLayout c1 = (CardLayout) (cards.getLayout());
                    c1.show(cards, "Input2");
                }else{
                    try {
                        m2 = new Matrix("src/" + filename);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    CardLayout c1 = (CardLayout)(cards.getLayout());
                    c1.show(cards, "Two");
                    matrixP1.setText(m1.toString());
                    matrixP2.setText(m2.toString());
                    operation();
                }
            }else{
                try {
                    m1 = new Matrix("src/" +filename);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                CardLayout c1 = (CardLayout)(cards.getLayout());
                c1.show(cards, "One");
                matrixP11.setText("Matrix 1:" + "\n" + m1.toString());
                operation();
            }

        }

    }
    private class ManualListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String rows = JOptionPane.showInputDialog("Number of Rows", "1");
            String columns = JOptionPane.showInputDialog("Number of Columns", "1");
            if(hi ==0){
                if(numOn ==1) {
                    m1 = new Matrix(Integer.parseInt(rows),Integer.parseInt(columns));
                    m1.manualFill();
                    numOn =2;
                    CardLayout c1 = (CardLayout) (cards.getLayout());
                    c1.show(cards, "Input2");
                }else{
                    m2 = new Matrix(Integer.parseInt(rows),Integer.parseInt(columns));
                    m2.manualFill();
                    CardLayout c1 = (CardLayout)(cards.getLayout());
                    c1.show(cards, "Two");
                    matrixP1.setText("Matrix 1:" + "\n"+m1.toString());
                    matrixP2.setText("Matrix 2:" + "\n"+m2.toString());
                    operation();
                }
            }else{
                m1 = new Matrix(Integer.parseInt(rows),Integer.parseInt(columns));
                m1.manualFill();
                CardLayout c1 = (CardLayout)(cards.getLayout());
                c1.show(cards, "One");
                matrixP11.setText("Matrix 1:" + "\n"+m1.toString());
                operation();
            }
        }

    }
    private class RandomListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String rows = JOptionPane.showInputDialog("Number of Rows", "1");
            String columns = JOptionPane.showInputDialog("Number of Columns", "1");
            String upperBounds = JOptionPane.showInputDialog("Upper Bounds", "10");
            String lowerBounds = JOptionPane.showInputDialog("Lower Bounds", "-10");
            if(hi ==0){
                if(numOn ==1) {
                    m1 = new Matrix(Integer.parseInt(rows),Integer.parseInt(columns));
                    m1.randomFill(Integer.parseInt(upperBounds),Integer.parseInt(lowerBounds));
                    numOn =2;
                    CardLayout c1 = (CardLayout) (cards.getLayout());
                    c1.show(cards, "Input2");
                }else{
                    m2 = new Matrix(Integer.parseInt(rows),Integer.parseInt(columns));
                    m2.randomFill(Integer.parseInt(upperBounds),Integer.parseInt(lowerBounds));
                    CardLayout c1 = (CardLayout)(cards.getLayout());
                    c1.show(cards, "Two");
                    matrixP1.setText("Matrix 1:" + "\n"+m1.toString());
                    matrixP2.setText("Matrix 2:" + "\n"+m2.toString());
                    operation();
                }
            }else{
                m1 = new Matrix(Integer.parseInt(rows),Integer.parseInt(columns));
                m1.randomFill(Integer.parseInt(upperBounds),Integer.parseInt(lowerBounds));
                CardLayout c1 = (CardLayout)(cards.getLayout());
                c1.show(cards, "One");
                matrixP11.setText("Matrix 1:" + "\n"+m1.toString());
                operation();
            }
        }

    }
}
