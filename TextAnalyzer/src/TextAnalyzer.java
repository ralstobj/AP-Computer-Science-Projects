import java.io.File;
import java.util.Scanner;
import java.util.*;
/**
 * Created by Bailey Ralston on 12/14/2016.
 */
public class TextAnalyzer {
    private String textPiece ="";
    private String fNa="";
    private File f;
    private ArrayList<Sentence> mySentences = new ArrayList<Sentence>();

    public TextAnalyzer(String fileName){
       textPiece = fileName;
    }
    public TextAnalyzer(File fileName){
        f = fileName;
        try {

            Scanner reader = new Scanner(fileName);
            while(reader.hasNext()){
                textPiece += reader.nextLine();
            }
            reader.close();
        }catch(Exception e){
            System.out.println("Are you sure that's the right filename?");
        }
        fNa = f.getName();
    }
    public void findSentences(){
        int prev = 0;
        for(int i=0;i<textPiece.length();i++){
            char temp = textPiece.charAt(i);
            if(temp =='!'|| temp == '.' || temp =='?'){
                mySentences.add(new Sentence(textPiece.substring(prev,i+1)));
                prev = i+2;
            }
        }
    }
    public int totalSyllables(){
        int total=0;
        for(int i=0;i<mySentences.size();i++){
            total +=mySentences.get(i).totalNumOfSyllables();
        }
        return total;
    }
    public int totalSentences(){return mySentences.size();}
    public int totalWords(){
        int total=0;
        for(int i=0;i<mySentences.size();i++){
            total +=mySentences.get(i).totalNumOfWords();
        }
        return total;
    }
    public int flesch(){
        int sentences = totalSentences();
        int syllables = totalSyllables();
        int words = totalWords();
        double index;
        index = 206.835 - (84.6 * ((double) syllables /(double)words))
                - (1.015 * ((double) words /(double) sentences));
        return (int)index;
    }
    public String realVal(){
        int index = flesch();
        if(index >=91){
            return "5th grade";
        }else if(index >=81 && index <= 90){
            return  "6th grade";
        }else if(index >=71 && index <= 80){
            return  "7th grade";
        }else if(index >=61 && index <= 70){
            return  "7th & 8th grade";
        }else if(index >=51 && index <= 60){
            return  "10th to 12th grade";
        }else if(index >=31 && index <= 50){
            return  "College Student";
        }else if(index >=0 && index <= 30){
            return  "College Graduate";
        }else{
            return "Law School Graduate";
        }
    }

    public String getTextPiece() {
        return textPiece;
    }

    public String getfName() {
        return fNa;
    }

    public void setfName(String fName) {
        this.fNa = fName;
    }

    public void setTextPiece(String textPiece) {
        this.textPiece = textPiece;
    }

    public String toString(){
        String str= "Source text file:  " + fNa+ "\n"+
                    "Readability score: " + flesch() +"\n"+
                    "Educational level: " + realVal() + "\n"+
                    "Syllables:         " + totalSyllables() +"\n"+
                    "Words:             " + totalWords()+ "\n"+
                    "Sentences:         " + totalSentences()+ "\n";
        return str;
    }
}
