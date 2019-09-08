import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Bailey Ralston on 11/29/2016.
 */
public class Matrix {
    private int myRow, myColumn;
    private double[][] matrice;
    //Rows are horizontal --------
    //Columns are vertical ||
    //                     ||
    //                     ||
    // [row][column]
    public Matrix(){
        this(0,0);
        for (int rCnt = 0; rCnt < myRow; rCnt++) {
            for (int cCnt = 0; cCnt < myColumn; cCnt++) {

                matrice[rCnt][cCnt] = 0;
            }
        }
    }
    public Matrix(int r, int c){
        myRow = r;
        myColumn = c;
        matrice = new double[r][c];
        for (int rCnt = 0; rCnt < myRow; rCnt++) {
            for (int cCnt = 0; cCnt < myColumn; cCnt++) {
                matrice[rCnt][cCnt] = 0;
            }
        }
    }
    public Matrix(Matrix m){
        myRow = m.getNumRows();
        myColumn = m.getNumColumns();
        matrice = m.getMatrice();
    }
    public Matrix(String path)throws IOException {
        ArrayList<String> matrixRows = new ArrayList<>();
        Scanner reader = new Scanner(new File(path));
        while(reader.hasNext()){
            matrixRows.add(reader.nextLine());
        }
        myRow = matrixRows.size();
        for(int i = 0; i<matrixRows.get(0).length(); i++){
            if(String.valueOf(matrixRows.get(0).charAt(i)).equals(" ")){
                myColumn ++;
            }
        }
        myColumn ++;
        matrice = new double[myRow][myColumn];
        for(int r=0; r< myRow; r++){
            String[] individual = matrixRows.get(r).split(" ");
            for(int c=0; c< myColumn; c++){
                matrice[r][c] = Double.parseDouble(individual[c]);
            }
        }
        reader.close();
    }
    public void manualFill(){
        for (int r = 0; r < myRow; r++) {
            for (int c = 0; c < myColumn; c++) {
                String num = JOptionPane.showInputDialog("Enter Value for Row: " + (r+1)+ " Column: "+(c+1) ,"0");
                matrice[r][c] = Double.parseDouble(num);
            }
        }

    }
    public void setMatrices(int r,int c){
        myColumn = c;
        myRow = r;
        matrice = new double[r][c];
    }
    public int getNumRows(){return myRow;}
    public int getNumColumns(){return myColumn;}
    public double[][] getMatrice(){return matrice;}
    public void randomFill(int high, int low){
        Random rand = new Random();
        for (int r = 0; r < myRow; r++) {
            for (int c = 0; c < myColumn; c++) {
                matrice[r][c] = rand.nextInt(high-low+1)+low;
            }
        }
    }
    public void setValue(double n, int r, int c){matrice[r][c] = n;}
    public double getValue(int r, int c){return matrice[r][c];}
    public Matrix add(Matrix m) {
        if(m.getNumColumns()==myColumn&& m.getNumRows()==myRow) {
            Matrix holdMatrix = new Matrix(myRow, myColumn);
            for (int r = 0; r < m.getNumRows(); r++) {
                for (int c = 0; c < m.getNumColumns(); c++) {
                    holdMatrix.setValue(m.getValue(r,c)+matrice[r][c],r,c);
                }
            }
            return holdMatrix;
        }else{
            return null;
        }
    }
    public Matrix sub(Matrix m){
        if(m.getNumColumns()==myColumn&& m.getNumRows()==myRow) {
            Matrix holdMatrix = new Matrix(myRow, myColumn);
            for (int r = 0; r < m.getNumRows(); r++) {
                for (int c = 0; c < m.getNumColumns(); c++) {
                    holdMatrix.setValue(matrice[r][c]-m.getValue(r,c),r,c);
                }
            }
            return holdMatrix;
        }else{
            return null;
        }
    }
    public Matrix multiply(Matrix m){
        if(myColumn == m.getNumRows()) {
            Matrix holdMatrix = new Matrix(myRow, m.getNumColumns());
            //         [2,3]
            //[1,4,6] *[5,8] = [64,89]
            //         [7,9]
            for (int i = 0; i < holdMatrix.getNumRows(); i++){
                for (int j = 0; j < holdMatrix.getNumColumns(); j++) {
                    for (int k = 0; k < myColumn; k++) {
                        holdMatrix.setValue(holdMatrix.getValue(i, j) + (matrice[i][k] * m.getValue(k, j)), i, j);

                    }
                }
        }
        return holdMatrix;
        }else{
            return null;
        }
    }
    public Matrix scalar(double val){
        Matrix holdMatrix = new Matrix(myRow, myColumn);
        for (int r = 0; r < myRow; r++) {
            for (int c = 0; c < myColumn; c++) {
                holdMatrix.setValue(matrice[r][c]*val,r,c);
            }
        }
        return holdMatrix;
    }
    public String toString() {
        String str = "";
        for(int r=0; r< myRow; r++){
            for(int c=0; c< myColumn; c++){
                str = str + Double.toString(matrice[r][c]) + " ";
            }
            str = str + "\n";
        }
        return str;
    }

}
