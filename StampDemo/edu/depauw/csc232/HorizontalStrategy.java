package edu.depauw.csc232;

import javafx.scene.canvas.GraphicsContext;

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
