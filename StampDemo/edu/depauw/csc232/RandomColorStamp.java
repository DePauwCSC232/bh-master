package edu.depauw.csc232;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Decorate a given Stamp with a randomly selected fill color.
 * 
 * @author bhoward
 */
public class RandomColorStamp implements Stamp {
   public RandomColorStamp(Stamp stamp) {
      this.stamp = stamp;
   }

   @Override
   public void draw(GraphicsContext gc) {
      gc.save();
      Paint p = Color.hsb(Math.random() * 360.0, 1.0, 1.0);
      gc.setFill(p);
      stamp.draw(gc);
      gc.restore();
   }

   private Stamp stamp;
}
