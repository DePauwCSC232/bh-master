package edu.depauw.csc232;

import java.util.Arrays;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

/**
 * A Stamp that consists of a horizontal row of given stamps.
 * 
 * @author bhoward
 */
public class StampRow implements Stamp {
   public StampRow(double spacing, Stamp ... stamps) {
      this.spacing = spacing;
      this.stamps = Arrays.asList(stamps);
   }

   @Override
   public void draw(GraphicsContext gc) {
      gc.save();
      for (Stamp stamp : stamps) {
         stamp.draw(gc);
         gc.translate(spacing, 0.0);
      }
      gc.restore();
   }

   private double spacing;
   private List<Stamp> stamps;
}
