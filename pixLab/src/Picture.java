import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  /** Method to set all colors that are not blue to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
        pixelObj.setRed(0);
      }
    }
  }
  /** Method to negate all colors */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(pixelObj.getGreen()-255);
        pixelObj.setRed(pixelObj.getRed()-255);
        pixelObj.setBlue(pixelObj.getBlue()-255);
      }
    }
  }

  /**
   *  Method to convert image to grayscale.
   */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int avg = (int)((pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3);
        pixelObj.setRed(avg);
        pixelObj.setBlue(avg);
        pixelObj.setGreen(avg);
      }
    }
  }
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();

    int redAverage = 0;
    int greenAverage = 0;
    int blueAverage = 0;
    int totalPixels = 0;

    int maxRed = 0;
    int minRed = 255;
    int maxGreen = 0;
    int minGreen = 255;
    int maxBlue = 0;
    int minBlue = 255;

    // takes a sample from a fish and finds the average color value and range of colors
    for (int row = 26; row < 36; row++)
    {
      for (int col = 178; col < 198; col++)
      {
        totalPixels++;

        Pixel myPixel = pixels[row][col];

        redAverage += myPixel.getRed();
        greenAverage += myPixel.getGreen();
        blueAverage += myPixel.getBlue();

        if (myPixel.getRed() > maxRed) { maxRed = myPixel.getRed(); }
        if (myPixel.getRed() < minRed) { minRed = myPixel.getRed(); }
        if (myPixel.getGreen() > maxGreen) { maxGreen = myPixel.getGreen(); }
        if (myPixel.getGreen() < minGreen) { minGreen = myPixel.getGreen(); }
        if (myPixel.getBlue() > maxBlue) { maxBlue = myPixel.getBlue(); }
        if (myPixel.getGreen() < minBlue) { minBlue = myPixel.getBlue(); }

      }
    }

    redAverage = redAverage / totalPixels;
    greenAverage = greenAverage / totalPixels;
    blueAverage = blueAverage / totalPixels;

    Color averageColor = new Color(redAverage, greenAverage, blueAverage);

    // calculates the range
    int redRange = (maxRed - minRed);
    int greenRange = (maxGreen - minGreen);
    int blueRange = (maxBlue - minBlue);

    int redDistance = redRange;
    int greenDistance = greenRange;
    int blueDistance = blueRange;

    double maxDistance = Math.sqrt(redDistance * redDistance +
            greenDistance * greenDistance +
            blueDistance * blueDistance);

    double tolerance = 1.7; // higher tolerance means more pixels will be identified as "fish"

    // changes the image based on calculated distance from sample value
    for (int row = 0; row < pixels.length; row++) // Pixel[] rowArray : pixels)
    {
      for (int col = 0; col < pixels[0].length; col++) // Pixel pixelObj : rowArray)
      {
        Pixel myPixel = pixels[row][col]; //

        boolean closeEnough = myPixel.colorDistance(averageColor) < maxDistance * tolerance; // stopped here, define this***
        // System.out.println(myPixel.colorDistance(averageColor));
        if (closeEnough)
        {
          myPixel.setBlue(myPixel.getBlue() + 50);
        }
        else
        {
          myPixel.setBlue(myPixel.getBlue() - 50);
        }
      }
    }
  }
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  /** Method that mirrors the picture around a
   * vertical mirror in the center of the picture
   * from Right to left */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    }
  }
  /**Method that mirrors the picture around a
   * horizontal mirror in the center of the picture
   * */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height - 1 - row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }
  }
  /**Method that mirrors the picture around a
   * horizontal mirror in the center of the picture from Bottom to Top.
   * */
  public void mirrorHorizontalBottomToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[height - 1 - row][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    }
  }
  /**Method that mirrors the picture around a diagonal mirror from top right to bottom left
   * */
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topRightPixel = null;
    Pixel bottomLeftPixel = null;
    int maxLength;
    if (pixels.length < pixels[0].length) { maxLength = pixels.length; }
    else {maxLength = pixels[0].length; }

    for (int row = 0; row < maxLength; row++)
    {
      for (int col = row; col < maxLength; col++)
      {
        topRightPixel = pixels[row][col];
        bottomLeftPixel = pixels[col][row];
        topRightPixel.setColor(bottomLeftPixel.getColor());
      }
    }
  }
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        count++;
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    System.out.println(count);
  }
  /** Mirrors the arms of the snowman */
  public void mirrorArms()
  {
    int mirrorPoint = 193;
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();

    // Left arm
    for (int row = 158; row < mirrorPoint; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 103; col < 170; col++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[mirrorPoint - row + mirrorPoint][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    }

    int mirrorPoint2 = 198;
    Pixel topPixel2 = null;
    Pixel bottomPixel2 = null;

    // Right arm
    for (int row = 171; row < mirrorPoint2; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 239; col < 294; col++)
      {
        topPixel2 = pixels[row][col];
        bottomPixel2 = pixels[mirrorPoint2 - row + mirrorPoint2][col];
        bottomPixel2.setColor(topPixel2.getColor());
      }
    }
  }
  /** Mirrors the seagull */
  public void mirrorGull()
  {
    int mirrorPoint = 345;
    Pixel rightPixel = null;
    Pixel leftPixel = null;
    Pixel[][] pixels = this.getPixels2D();

    // Seagull
    for (int row = 235; row < 323; row++)
    {
      for (int col = 238; col < mirrorPoint; col++)
      {
        rightPixel = pixels[row][col];
        leftPixel = pixels[row][mirrorPoint - col + mirrorPoint/3];
        leftPixel.setColor(rightPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  public void copy2(Picture fromPic, int startRow, int endRow, int startCol, int endCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow;
         fromRow < fromPixels.length &&
                 toRow < endRow;
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol;
           fromCol < fromPixels[0].length &&
                   toCol < endCol;
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
        //System.out.println("Fromrow " + fromRow + " toRow " + toRow + " fromCol" + fromCol + " toCol " + toCol);
        //System.out.println(fromPixels[0].length);
      }
    }
  }


  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }

  public void createCollage2()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");

    //this.copy(flower1,100,0);
    this.copy2(flower1,0,100, 0, 100);
    //flower1.explore();
    //this.copy(flower1,200,0);
    // Mirroring
    int mirrorPoint = 98;
    Pixel rightPixel = null;
    Pixel leftPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    for (int row = 0; row < 98; row++)
    {
      for (int col = 0; col < 88; col++)
      {
        rightPixel = pixels[row][col];
        leftPixel = pixels[mirrorPoint - row + mirrorPoint][col];
        leftPixel.setColor(rightPixel.getColor());
      }
    }
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy2(flowerNoBlue,300,350,80,500);

    Picture flowerinverse = new Picture(flower2);
    flowerinverse.grayscale();
    this.copy2(flowerinverse, 100, 300, 80, 300);
    //this.copy(flowerNoBlue,300,0);
    //this.copy(flower1,400,0);
    //this.copy(flower2,500,0);
    //this.mirrorVertical();


    this.write("collage.jpg");
  }
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
