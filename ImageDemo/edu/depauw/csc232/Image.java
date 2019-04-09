////////////////////////////////////////////////////////////////////////////////
// File:             Image.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import javafx.scene.paint.Color;

/**
 * An Image is completely determined by the color at any given point (x, y). The
 * standard range for x and y is between 0.0 and 1.0, although to support images
 * that have been transformed (e.g., shifted or scaled) the color must be
 * defined for all values of x and y; beyond the extent of the image,
 * <code>colorAt(x, y)</code> should return <code>Image.CLEAR</code>.
 */
public interface Image {
   /**
    * Return the color at the given point in the image.
    * 
    * @param x the x-coordinate of the desired point
    * @param y the y-coordinate of the desired point
    * @return the color at point (x, y)
    */
   Color colorAt(double x, double y);

   /**
    * Create a shifted version of this image, dx units to the right and dy units
    * down.
    * 
    * @param dx the amount by which to shift the x-coordinates
    * @param dy the amount by which to shift the y-coordinates
    * @return the shifted image
    */
   default Image shifted(double dx, double dy) {
      return new ShiftedImage(this, dx, dy);
   }

   /**
    * Create a scaled version of this image, with width multiplied by sx and height
    * multiplied by sy. The image is scaled about the origin, (0, 0).
    * 
    * @param sx scale factor for the x-coordinates
    * @param sy scale factor for the y-coordinates
    * @return the scaled image
    */
   default Image scaled(double sx, double sy) {
      return new ScaledImage(this, sx, sy);
   }

   /**
    * Create a filled rectangle with upper-left corner at (x, y), with width w,
    * height h, and the given fill color.
    * 
    * @param x     x-coordinate of left edge
    * @param y     y-coordinate of top edge
    * @param w     width
    * @param h     height
    * @param color fill color
    * @return the rectangle image
    */
   static Image rectangle(double x, double y, double w, double h, Color color) {
      return new Square(color).scaled(w, h).shifted(x, y);
   }

   /**
    * Create a filled ellipse inside a bounding box with upper-left corner at (x,
    * y), width w, height h, and the given fill color.
    * 
    * @param x     x-coordinate of left edge
    * @param y     y-coordinate of top edge
    * @param w     width
    * @param h     height
    * @param color fill color
    * @return the ellipse image
    */
   static Image ellipse(double x, double y, double w, double h, Color color) {
      return new Circle(color).scaled(w, h).shifted(x, y);
   }

   /**
    * The default color outside of the visible part of an image.
    */
   static final Color CLEAR = new Color(0, 0, 0, 0);
}
