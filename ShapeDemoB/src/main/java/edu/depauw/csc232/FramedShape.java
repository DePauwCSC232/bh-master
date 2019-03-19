package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorator Pattern -- created a new shape with a frame of X's around a given
 * shape.
 */
public class FramedShape implements Shape {
   private Shape shape;

   public FramedShape(Shape shape) {
      this.shape = shape;
   }

   @Override
   public List<String> render() {
      int width = shape.width() + 2;
      String border = Util.repeat("X", width);

      List<String> result = new ArrayList<>();
      result.add(border);
      for (String line : shape.render()) {
         result.add("X" + line + "X");
      }
      result.add(border);

      return result;
   }

   @Override
   public int width() {
      return shape.width() + 2;
   }

   @Override
   public int height() {
      return shape.height() + 2;
   }

}
