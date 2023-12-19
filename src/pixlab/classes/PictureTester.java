package pixlab.classes;

import java.awt.Color;

/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  public static void testZeroRed()
  {
	  Picture beach = new Picture("beach.jpg");
	  //beach.explore();
	  beach.zeroRed();
	  beach.explore();
  }
  
  public static void testZeroGreen()
  {
	  Picture beach = new Picture("beach.jpg");
	  //beach.explore();
	  beach.zeroGreen();
	  beach.explore();
  }
  
  public static void testKeepOnlyBlue()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.keepOnlyBlue();
	  beach.explore();
  }
  
  public static void testKeepOnlyRed()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.keepOnlyRed();
	  beach.explore();
  }
  
  public static void testKeepOnlyGreen()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.keepOnlyGreen();
	  beach.explore();
  }
  
  public static void testClearBlueOverValue()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.clearBlueOverValue(200);
	  beach.explore();
  }
  
  public static void testMirrorDiagonal()
  {
	  Picture caterpillar = new Picture("caterpillar.jpg");
	  caterpillar.explore();
	  caterpillar.mirrorDiagonal();
	  caterpillar.explore();
  }
  
  public static void testLowerBitDepth()
  {
	  Picture motorCycle = new Picture("blueMotorCycle.jpg");
	  motorCycle.explore();
	  motorCycle.lowerBitDepth();
	  motorCycle.explore();
  }
  
  public static void testEdgeDetectionHighlight()
  {
	  Picture swan = new Picture("swan.jpg");
	  swan.explore();
	  swan.edgeDetectionHighlight(10, 1, Color.blue);
	  swan.explore();
  }
  
  public static void testAverageColors()
  {
	  Picture motorCycle = new Picture("blueMotorCycle.jpg");
	  motorCycle.averageColors();
	  motorCycle.explore();
  }
  
  public static void testColorShift()
  {
	  Picture motorCycle = new Picture("blueMotorCycle.jpg");
	  motorCycle.lowerBitDepth();
	  motorCycle.shiftRed(5);
	  //motorCycle.shiftGreen(5);
	  motorCycle.shiftBlue(10);
	  motorCycle.explore();
  }
  
  public static void testCaption()
  {
	  Picture pupper = new Picture("koala.jpg");
	  pupper.captionPicture("Bite my furry ass stupid");
	  pupper.explore();
  }
  
  public static void testChromakey()
  {
	  Picture blue = new Picture("blue-mark.jpg");
	  Picture moon = new Picture("moon-surface.jpg");
	  
	  blue.explore();
	  blue.chromakey(moon, new Color(8, 37, 77), 50);
	  blue.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testZeroRed();
    //testZeroGreen();
	//testKeepOnlyBlue();
	//testKeepOnlyRed();
	//testKeepOnlyGreen();
	//testClearBlueOverValue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
	//testMirrorDiagonal();
    //testNegate();
	  //testLowerBitDepth();
	  //testEdgeDetectionHighlight();
	  //testEdgeDetection();
	  //testCaption();
	  testChromakey();
	  //testColorShift();
	  //testAverageColors();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}