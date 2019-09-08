/**
 * Created by Bailey Ralston on 11/27/2016.
 */
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class TopTen {

        private Player[] myTopTenList = new Player[11];
        private String myTopTenString = "";
        private String mySavedTopTenString= "";

        public TopTen(){
            for(int i = 1; i<=10; i++){
                myTopTenList[i] = new Player();
            }
            try {
                openTopTen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getSavedTopTen(){return mySavedTopTenString;}
        public String getTopTen(){return myTopTenString;}
        public Player getTopTenPlayer(int index){return myTopTenList[index];}
        public void setTopTen(int index, Player p){ myTopTenList[index] = new Player(p);}
        public void saveTopTen() throws IOException{
            PrintWriter writer = new PrintWriter(new File("src/TopTen.txt"));
                writer.println(getSavedTopTen());
            writer.close();
        }
        public void openTopTen() throws IOException{
            String strline;
            Scanner inputs = new Scanner(new File("src/TopTen.txt"));
            for(int i = 0; i<10; i++){
                strline = inputs.nextLine();
                String expressionParts[] = strline.split(";");
                myTopTenList[i].setInitals(expressionParts[0]);
                myTopTenList[i].setPoints(Integer.parseInt(expressionParts[1]));
            }
            inputs.close();
        }
        public void sortTopTen(){
            Player temp;
            for(int i =1; i<=10; i++){
                for(int j =1; j<=10; j++){
                    if(myTopTenList[i].getPoints() < myTopTenList[j].getPoints()){
                        temp = new Player(myTopTenList[j]);
                        myTopTenList[j] = new Player(myTopTenList[i]);
                        myTopTenList[i] = new Player(temp);
                    }
                }
            }

            myTopTenString = "";

            for(int i = 1; i<= 10; i++){
                myTopTenString = myTopTenString + i + "." + "  " +myTopTenList[i].getInitals() +";" + myTopTenList[i].getPoints() + "\r";
                mySavedTopTenString = mySavedTopTenString + myTopTenList[i].getInitals() + ";" + myTopTenList[i].getPoints() + "\r";
            }
        }
        public void showTopTen(Graphics g, int x, int y){
            g.fillRect(x,y,500,400);
            g.setColor(Color.BLACK);
            g.drawString("01: " + myTopTenList[1].toString(),x+5,y+40);
            g.drawString("02: " + myTopTenList[2].toString(),x+5,y +80);
            g.drawString("03: " + myTopTenList[3].toString(),x+5,y +120);
            g.drawString("04: " + myTopTenList[4].toString(),x+5,y +160);
            g.drawString("05: " + myTopTenList[5].toString(),x+5,y +200);
            g.drawString("06: " + myTopTenList[6].toString(),x+300,y+40);
            g.drawString("07: " + myTopTenList[7].toString(),x+300,y +80);
            g.drawString("08: " + myTopTenList[8].toString(),x+300,y +120);
            g.drawString("09: " + myTopTenList[9].toString(),x+300,y +160);
            g.drawString("10: " + myTopTenList[10].toString(),x+300,y +200);

        }
    }

