////////////////////////////////////////////////////////////////////////////////
// File:             CanvasDemo.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class CanvasDemo {
   public static final int WIDTH = 400;
   public static final int HEIGHT = 400;

   public static void main(String[] args) {
      JFrame f = new JFrame();
      Canvas canvas = new Canvas();

//      canvas.addImage(Image.ellipse(0, 0, 1, 1, Color.RED));
      
      // Uncomment the following when done to make a picture:
      canvas.addImage(Image.rectangle(0, 0, 1, 1, Color.DARK_GRAY));
      canvas.addImage(Image.rectangle(0, 0.8, 1, 0.2, Color.BLACK));
      canvas.addImage(Image.ellipse(0.1, 0.1, 0.2, 0.2, Color.WHITE));
      canvas.addImage(Image.ellipse(0.5, 0.5, 0.4, 0.3, Color.ORANGE));
      canvas.addImage(Image.rectangle(0.68, 0.45, 0.04, 0.06, Color.GREEN));
      canvas.addImage(Image.rectangle(0.6, 0.55, 0.05, 0.1, Color.YELLOW));
      canvas.addImage(Image.rectangle(0.75, 0.55, 0.05, 0.1, Color.YELLOW));
      canvas.addImage(Image.rectangle(0.6, 0.7, 0.2, 0.05, Color.YELLOW));

      f.setSize(WIDTH, HEIGHT);
      f.setLayout(new BorderLayout());
      f.getContentPane().add(canvas, BorderLayout.CENTER);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);
   }
}
