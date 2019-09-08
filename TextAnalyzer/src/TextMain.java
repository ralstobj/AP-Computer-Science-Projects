/**
 * Created by Bailey Ralston on 12/14/2016.
 */
public class TextMain {
    public static void main(String[] args){
        TextAnalyzer g = new TextAnalyzer("Gettysburg.txt");
        g.findSentences();
        System.out.println(g.toString());
        TextAnalyzer t = new TextAnalyzer("test-6th-grader.txt");
        t.findSentences();
        System.out.println(t.toString());
        TextAnalyzer x = new TextAnalyzer("test-college-grad.txt");
        x.findSentences();
        System.out.print(x.toString());
    }
}
