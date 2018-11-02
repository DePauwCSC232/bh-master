////////////////////////////////////////////////////////////////////////////////
// File:             ShiftedImage.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

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
      return image.colorAt(x - dx, y - dy);
   }

   private final Image image;
   private final double dx;
   private final double dy;
}