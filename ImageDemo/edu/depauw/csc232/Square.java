package edu.depauw.csc232;

import java.awt.Color;

/**
 * An Image consisting of a filled square that exactly fits the unit square of
 * the Canvas.
 */
public class Square implements Image {
   /**
    * Construct a square Image that fills the unit square with the given color.
    * 
    * @param color the fill color
    */
   public Square(Color color) {
      this.color = color;
   }

   @Override
   public Color colorAt(double x, double y) {
      // TODO return color if (x, y) is inside the unit square, and CLEAR otherwise.
      return CLEAR;
   }

   private Color color;
}
