////////////////////////////////////////////////////////////////////////////////
// File:             ScaledImage.java
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
 * Modifies an Image to be scaled by given factors in the x and y directions.
 */
public final class ScaledImage implements Image {
   /**
    * Construct an Image based on the given one, but scaled to be sx times as wide
    * and sy times as tall. The image is scaled about the origin, (0, 0).
    * 
    * @param image The original image to be scaled
    * @param sx    the scale factor for the x-coordinates
    * @param sy    the scale factor for the y-coordinates
    */
   public ScaledImage(Image image, double sx, double sy) {
      this.image = image;
      this.sy = sy;
      this.sx = sx;
   }

   @Override
   public Color colorAt(double x, double y) {
      // Convert a request for the color at a scaled position into a
      // request for the corresponding color in the original Image.
      return image.colorAt(x / sx, y / sy);
   }

   private final Image image;
   private final double sx;
   private final double sy;
}