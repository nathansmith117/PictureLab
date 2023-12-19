package pixlab.classes;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

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
  
  public void zeroRed()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (Pixel[] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			  pixelObj.setRed(0);
		  }
	  }
  }
  
  public void zeroGreen()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  pixels[row][col].setGreen(0);
		  }
	  }
  }
  
  public void keepOnlyBlue()
  {
	  zeroRed();
	  zeroGreen();
  }
  
  public void keepOnlyRed()
  {
	  zeroBlue();
	  zeroGreen();
  }
  
  public void keepOnlyGreen()
  {
	  zeroRed();
	  zeroBlue();
  }
  
  public void clearBlueOverValue(int value)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (Pixel[] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			  if (pixelObj.getBlue() >= value)
			  {
				  pixelObj.setBlue(0);
			  }
		  }
	  }
  }
  
  public void mirrorDiagonal()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < row; col++)
		  {
			  
			  Pixel upperPixel = pixels[row][col];
			  Pixel lowerPixel = pixels[pixels.length - 1 - row][pixels[0].length - 1 - col];
			  lowerPixel.setColor(upperPixel.getColor());
			  lowerPixel.setColor(Color.black);
			  upperPixel.setColor(Color.blue);
		  }
	  }
  }
  
  public void lowerBitDepth()
  {
	  double scalar = Math.pow(2.0, 2.0) / Math.pow(2.0, 8.0);
	  
	  Pixel[][] pixels = this.getPixels2D();
	  
	  for (Pixel[] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			  int blue = (int)(pixelObj.getBlue() * scalar);
			  int green = (int)(pixelObj.getGreen() * scalar);
			  int red = (int)(pixelObj.getRed() * scalar);
			  
			  blue /= scalar;
			  green /= scalar;
			  red /= scalar;
			  
			  pixelObj.setBlue(blue);
			  pixelObj.setGreen(green);
			  pixelObj.setRed(red);
		  }
	  }
  }
  
  public void edgeDetectionHighlight(int edgeDist, int sampleSize, Color highlightColor)
  {
	 
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel[][] pixelsCopy = new Picture(this).getPixels2D();
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < pixels[0].length; col++)
		  {
			  int[] positions = {-sampleSize, 0, sampleSize};
			  int redSum = 0;
			  int greenSum = 0;
			  int blueSum = 0;
			  int colorCount = 0;
			  
			  for (int y : positions)
			  {
				  for (int x : positions)
				  {
					  if (x == 0 && y == 0)
					  {
						  continue;
					  }
					  
					  int currentRow = row + y;
					  int currentCol = col + x;
					  
					  // Bounds check then sum.
					  if (currentRow >= 0 && currentRow < pixels.length
							  && currentCol >= 0 && currentCol < pixels[0].length)
					  {
						  Color currentColor = pixelsCopy[currentRow][currentCol].getColor();
						  redSum += currentColor.getRed();
						  greenSum += currentColor.getGreen();
						  blueSum += currentColor.getBlue();
						  colorCount++;
					  }
				  }
			  }
			  
			  redSum /= colorCount;
			  greenSum /= colorCount;
			  blueSum /= colorCount;
			  
			  Color colorAverage = new Color(redSum, greenSum, blueSum);
			  
			  if (pixelsCopy[row][col].colorDistance(colorAverage) > edgeDist)
			  {
				  pixels[row][col].setColor(highlightColor);
			  }
			  else
			  {
				  pixels[row][col].setColor(Color.white);
			  }
		  }
	  }
  }
  
  public void captionPicture(String caption)
  {
	  int row = (int)(this.getHeight() * 0.1);
	  int column = (int)(this.getWidth() * 0.0);
	  
	  addColorfulMessage(caption, column, row, 2);
  }
  
  public void shiftRed(int shift)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture copy = new Picture(this);
	  Pixel[][] copiedPixels = copy.getPixels2D();
	  
	  int width = pixels[0].length;
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < width; col++)
		  {
			  int shiftedCol = Math.abs(col + shift) % width;
			  
			  Pixel source = copiedPixels[row][col];
			  Pixel destination = pixels[row][shiftedCol];
			  
			  destination.setRed(source.getRed());
		  }
	  }
  }
  
  public void shiftGreen(int shift)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture copy = new Picture(this);
	  Pixel[][] copiedPixels = copy.getPixels2D();
	  
	  int width = pixels[0].length;
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < width; col++)
		  {
			  int shiftedCol = Math.abs(col + shift) % width;
			  
			  Pixel source = copiedPixels[row][col];
			  Pixel destination = pixels[row][shiftedCol];
			  
			  destination.setGreen(source.getGreen());
		  }
	  }
  }
  
  public void shiftBlue(int shift)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture copy = new Picture(this);
	  Pixel[][] copiedPixels = copy.getPixels2D();
	  
	  int width = pixels[0].length;
	  
	  for (int row = 0; row < pixels.length; row++)
	  {
		  for (int col = 0; col < width; col++)
		  {
			  int shiftedCol = Math.abs(col + shift) % width;
			  
			  Pixel source = copiedPixels[row][col];
			  Pixel destination = pixels[row][shiftedCol];
			  
			  destination.setBlue(source.getBlue());
		  }
	  }
  }
  
  public void averageColors()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  int sum = 0;
	  
	  for (Pixel[] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			  sum += pixelObj.getColor().getRGB();
		  }
	  }
	  
	  int average = sum / (pixels.length * pixels[0].length);
	  
	  for (Pixel[] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			  pixelObj.setColor(new Color(average));
		  }
	  }
  }
  
  public void chromakey(Picture picture, Color color, double distance)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel[][] pixels2 = picture.getPixels2D();
	  
	  for (int y = 0; y < pixels.length; y++)
	  {
		  for (int x = 0; x < pixels[0].length; x++)
		  {
			  if (pixels[y][x].colorDistance(color) <= distance)
			  {
				  pixels[y][x].setColor(pixels2[y % pixels2.length][x % pixels2[0].length].getColor());  
			  }
		  }
	  }
  }
  
  public void sortColors()
  {
	  Pixel[] pixels = this.getPixels();
	  
	  for (int outer = 1; outer < pixels.length; outer++)
	  {
		  Pixel tested = pixels[outer];
		  
		  int inner = outer - 1;
		  
		  while (inner >= 0 && tested.getColorMagnitude() < pixels[inner].getColorMagnitude())
		  {
			  pixels[inner + 1].setColor(pixels[inner].getColor());
			  inner--;
		  }
		  
		  pixels[inner + 1].setColor(tested.getColor());
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
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
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
    Picture gengar = new Picture("gengar.png");
    gengar.explore();
    gengar.zeroBlue();
    gengar.explore();

    Picture annie = new Picture("annie.jpg");
    annie.explore();
    annie.zeroBlue();
    annie.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
