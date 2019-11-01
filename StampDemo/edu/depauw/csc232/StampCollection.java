package edu.depauw.csc232;

import java.util.Arrays;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

/**
 * A Stamp that is a composite of a list of stamps, arranged according to a
 * DisplayStrategy.
 * 
 * @author bhoward
 */
public class StampCollection implements Stamp {
   public StampCollection(DisplayStrategy strategy, Stamp... stamps) {
      this.strategy = strategy;
      this.stamps = Arrays.asList(stamps);
   }

   @Override
   public void draw(GraphicsContext gc) {
      int i = 0;
      for (Stamp stamp : stamps) {
         gc.save();
         strategy.prepare(gc, i);
         stamp.draw(gc);
         gc.restore();
         i++;
      }
   }

   private DisplayStrategy strategy;
   private List<Stamp> stamps;
}
