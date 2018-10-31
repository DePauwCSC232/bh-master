package edu.depauw.csc232;

import java.awt.Color;

/**
 * Modifies an Image to be shifted by the given amounts in the x and y
 * directions.
 */
public final class ShiftedImage implements Image {
   /**
    * Construct an Image based on the given one, but shifted dx units to the right
    * and dy units down.
    * 
    * @param image The original image to be shifted
    * @param dx    the amount to shift x (to the right)
    * @param dy    the amount to shift y (down)
    */
   public ShiftedImage(Image image, double dx, double dy) {
      this.image = image;
      this.dx = dx;
      this.dy = dy;
   }

   @Override
   public Color colorAt(double x, double y) {
      // TODO convert a request for a color in the shifted image into a request
      // for the corresponding color in the original image.
      return image.colorAt(x, y);
   }

   private final Image image;
   private final double dx;
   private final double dy;
}