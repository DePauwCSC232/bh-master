package edu.depauw.csc232;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * A Canvas is a Swing component that can display a collection of Images. The
 * component will be filled with colored pixels determined by asking each Image
 * in turn what color should be painted at each (x, y) position within the unit
 * square: 0.0 <= x <= 1.0 (left to right) and 0.0 <= y <= 1.0 (top to bottom).
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel {
   private ArrayList<Image> images;

   /**
    * Construct a Canvas with an empty list of Images.
    */
   public Canvas() {
      images = new ArrayList<Image>();
   }

   /** Add an image to be drawn on top of the canvas. */
   public void addImage(Image s) {
      images.add(s);
   }

   @Override
   public void paint(Graphics g) {
      drawImages(g);
   }

   /* draw all of the images on the canvas in the specified graphics context */
   private void drawImages(Graphics g) {
      for (Image image : images) {
         drawImage(g, image);
      }
   }

   /* draw the specified image onto the specified graphics context */
   private void drawImage(Graphics g, Image image) {
      int height = this.getHeight();
      int width = this.getWidth();

      for (int i = 0; i < width; i++) {
         double x = (double) i / width; // x ranges from 0.0 to 1.0
         for (int j = 0; j < height; j++) {
            double y = (double) j / height; // y ranges from 0.0 to 1.0
            g.setColor(image.colorAt(x, y));
            g.drawLine(i, j, i, j); // draw a point
         }
      }
   }
}
