package edu.depauw.csc232;

import javafx.scene.canvas.GraphicsContext;

/**
 * A Stamp that looks like a simple house with a triangular roof.
 * 
 * @author bhoward
 */
public class HouseStamp implements Stamp {
   /**
    * Construct a Stamp for a simple house with a triangular roof. The given
    * width and height are for the main body of the house, while the roof is
    * half that height.
    * 
    * @param width
    * @param height
    */
   public HouseStamp(double width, double height) {
      this.width = width;
      this.height = height;
   }

   @Override
   public void draw(GraphicsContext gc) {
      double roofHeight = height / 2.0;
      double[] roofX = { 0.0,        width,      width / 2.0 };
      double[] roofY = { roofHeight, roofHeight, 0.0 };
      int roofN = 3;
      
      gc.fillRect(0, roofHeight, width, height);
      gc.strokeRect(0, roofHeight, width, height);
      gc.fillPolygon(roofX, roofY, roofN);
      gc.strokePolygon(roofX, roofY, roofN);
   }

   private double width, height;
}
