/**
 * Created by Bailey Ralston on 1/5/2017.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class TextAnalyzerGUI extends JFrame implements ActionListener {

    JTextArea myText, myResults;
    JButton open, analyse, clear;
    JPanel myButtons;
    File selectedFile;
    TextAnalyzer analyzer;

    public static void main(String args[]) {
        TextAnalyzerGUI GUI = new TextAnalyzerGUI();
        GUI.setSize(1500, 1500);
        GUI.setTitle("Text Analyzer");
        GUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GUI.setVisible(true);
    }

    public TextAnalyzerGUI() {
        myText = new JTextArea(20, 20);
        myResults = new JTextArea(20,20);
        myText.setLineWrap(true);
        myText.setFont(new Font("Calibri",Font.PLAIN, 30));
        myResults.setFont(new Font("Calibri",Font.PLAIN, 30));
        open = new JButton("Open File");
        open.setPreferredSize(new Dimension(200,100));
        open.setFont(new Font("Calibri",Font.PLAIN, 30));
        open.addActionListener(this);
        analyse = new JButton("Analyze");
        analyse.setPreferredSize(new Dimension(200,100));
        analyse.setFont(new Font("Calibri",Font.PLAIN, 30));
        analyse.addActionListener(this);
        clear = new JButton("Clear");
        clear.setPreferredSize(new Dimension(200,100));
        clear.setFont(new Font("Calibri",Font.PLAIN, 30));
        clear.addActionListener(this);
        myButtons = new JPanel();
        myButtons.setLayout(new FlowLayout());
        setLayout(new GridLayout(3,1,12,30));
        add(myText);
        add(myResults);
        myButtons.add(open);
        myButtons.add(analyse);
        myButtons.add(clear);
        add(myButtons, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser myChooser;
        if (e.getSource() == open) {
            myChooser = new JFileChooser();
            myChooser.setPreferredSize(new Dimension(800, 600));
            myChooser.setCurrentDirectory(new File("D:"));
            if (myChooser.showOpenDialog(this)
                    == JFileChooser.APPROVE_OPTION) {
                selectedFile = myChooser.getSelectedFile();
                analyzer = new TextAnalyzer(selectedFile);
                myText.setText(analyzer.getTextPiece());

            }
        }else if(e.getSource()==analyse){
            if(!myText.getText().equals("")) {
                if (analyzer == null) {
                    analyzer = new TextAnalyzer(myText.getText());
                    analyzer.findSentences();
                    analyzer.setfName("Manual Input");
                    myResults.setText(analyzer.toString());
                } else {
                    analyzer.findSentences();
                    myResults.setText(analyzer.toString());
                }
            }
        }else if(e.getSource()==clear){
            analyzer = null;
            myResults.setText("");
            myText.setText("");
        }
    }
}
