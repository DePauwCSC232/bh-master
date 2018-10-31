package edu.depauw.csc232;

import java.awt.Color;

/**
 * An Image consisting of a filled Circle with center at (0.5, 0.5) and radius
 * 0.5. That is, it will fill the unit square of the Canvas.
 */
public class Circle implements Image {
   /**
    * Construct a circle Image that fills the unit square with the given color.
    * 
    * @param color the fill color
    */
   public Circle(Color color) {
      this.color = color;
   }

   @Override
   public Color colorAt(double x, double y) {
      double dx = x - 0.5;
      double dy = y - 0.5;
      double d = Math.sqrt(dx * dx + dy * dy);
      if (d <= 0.5) {
         return color;
      } else {
         return CLEAR;
      }
   }

   private Color color;
}
