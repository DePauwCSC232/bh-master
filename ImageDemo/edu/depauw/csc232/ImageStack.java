package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 * An ImageStack is a collection of Images that will be drawn on the given
 * Canvas. The canvas will be filled with colored pixels determined by asking
 * each Image in turn what color should be painted at each (x, y) position
 * within the unit square: 0.0 <= x <= 1.0 (left to right) and 0.0 <= y <= 1.0
 * (top to bottom). When the canvas is resized, the images will be redrawn.
 * 
 * @author bhoward
 */
public class ImageStack {
   private Canvas canvas;
   private List<Image> images;

   /**
    * Construct an empty stack of images.
    * 
    * @param canvas
    */
   public ImageStack(Canvas canvas) {
      this.canvas = canvas;
      this.images = new ArrayList<>();
   }

   /**
    * Add an image to be drawn on the top of the stack.
    * 
    * @param image
    */
   public void addImage(Image image) {
      images.add(image);
   }

   public void draw(double width, double height) {
      GraphicsContext g = canvas.getGraphicsContext2D();
      PixelWriter writer = g.getPixelWriter();
      for (Image image : images) {
         drawImage(image, writer, width, height);
      }
   }

   private void drawImage(Image image, PixelWriter writer, double w, double h) {
      for (int i = 0; i < w; i++) {
         for (int j = 0; j < h; j++) {
            Color c = image.colorAt(i / w, j / h);
            if (!c.equals(Image.CLEAR)) {
               writer.setColor(i, j, c);
            }
         }
      }
   }
}
