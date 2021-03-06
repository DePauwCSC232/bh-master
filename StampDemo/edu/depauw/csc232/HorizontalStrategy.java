package edu.depauw.csc232;

import javafx.scene.canvas.GraphicsContext;

/**
 * A DisplayStrategy that separates successive stamps by a given horizontal
 * space.
 * 
 * @author bhoward
 */
public class HorizontalStrategy implements DisplayStrategy {
   public HorizontalStrategy(double spacing) {
      this.spacing = spacing;
   }

   @Override
   public void prepare(GraphicsContext gc, int i) {
      gc.translate(i * spacing, 0.0);
   }

   private double spacing;
}
