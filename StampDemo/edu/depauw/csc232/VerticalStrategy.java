package edu.depauw.csc232;

import javafx.scene.canvas.GraphicsContext;

/**
 * A DisplayStrategy that separates successive stamps by a given vertical space.
 * 
 * @author bhoward
 */
public class VerticalStrategy implements DisplayStrategy {
   public VerticalStrategy(double spacing) {
      this.spacing = spacing;
   }

   @Override
   public void prepare(GraphicsContext gc, int i) {
      gc.translate(0.0, i * spacing);
   }

   private double spacing;
}
