import java.util.ArrayList;

/**
 * Created by Bailey Ralston on 12/14/2016.
 */
public class Sentence {
    private ArrayList<Word> mySentence = new ArrayList<Word>();
    private String endPunctuation;

    public Sentence(String sent){
        int prevIndex = 0;
        for(int i=0; i<sent.length();i++){
            char temp = sent.charAt(i);
            if(temp != '!' && temp !='?' && temp !='.') {
                if (temp == ' ') {
                    mySentence.add(new Word(sent.substring(prevIndex, i)));
                    prevIndex = i+1;
                }
            }else{
                mySentence.add(new Word(sent.substring(prevIndex,i)));
                endPunctuation =String.valueOf(temp);
            }
        }
    }
    public ArrayList<Word> getMySentence() {
        return mySentence;
    }
    public void setMySentence(ArrayList<Word> mySentence) {
        this.mySentence = mySentence;
    }
    public String getEndPunctuation() {
        return endPunctuation;
    }
    public void setEndPunctuation(String endPunctuation) {
        this.endPunctuation = endPunctuation;
    }
    public double avgWordLength(){
        double avg=0;
        for(int i=0; i<mySentence.size(); i++){
            avg += mySentence.get(i).myLength();
        }
        return avg/mySentence.size();
    }
    public int totalNumOfWords(){return mySentence.size();}
    public int totalNumOfSyllables(){
        int syllables=0;
        for(int i=0; i<mySentence.size(); i++){
            syllables += mySentence.get(i).countSyllablesInWord();
        }
        return syllables;
    }
    public String toString(){
        String str = "";
        for(int i = 0; i< mySentence.size(); i++){
            if(i<mySentence.size()-1) {
                str += mySentence.get(i).getWord() + " ";
            }else{
                str += mySentence.get(i).getWord()+endPunctuation;
            }
        }
        return str;
    }

}
