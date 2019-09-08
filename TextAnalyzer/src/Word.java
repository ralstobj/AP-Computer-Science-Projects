/**
 * Created by Bailey Ralston on 12/12/2016.
 */
public class Word {

    private String myWord;
    private Integer mySyllables;

    public Word(){
        myWord = "";
        mySyllables = 1;
    }
    public Word(String word){
        myWord = word;
        mySyllables = countSyllablesInWord();
    }
    public int myLength(){return myWord.length();}
    public String getWord() {return myWord;}
    public void setWord(String Word) {this.myWord = Word;}
    public int getSyllables() {return mySyllables;}
    private boolean isVowel(char temp) {
        if (temp == 'a' || temp == 'e' || temp == 'i'
                || temp == 'o' || temp == 'u'
                || temp == 'y') {
            return true;
        }
        return false;
    }
    public int countSyllablesInWord() {
        int counter = 0;
        char temp;
        char next;
        for (int i = 0; i < myWord.length(); i++) {
            temp = myWord.charAt(i);
            if (i < myWord.length() - 1) {
                next = myWord.charAt(i + 1);
            } else {
                next = 0;
            }
            if (temp == 'e' && i == myWord.length() - 1) {
                continue;
            } else if (isVowel(temp) && !isVowel(next)) {
                counter += 1;
            }
        }
        if (counter == 0) {
            counter = 1 ;
        }
        return counter;
    }
    public String toString(){
            return myWord + ":" + mySyllables;
    }
}
