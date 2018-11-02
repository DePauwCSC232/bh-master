////////////////////////////////////////////////////////////////////////////////
// File:             Square.java
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
      if (0.0 <= x && x <= 1.0 && 0.0 <= y && y <= 1.0) {
         return color;
      } else {
         return CLEAR;
      }
   }

   private Color color;
}
