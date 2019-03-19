package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern -- two shapes stacked into a new shape.
 */
public class StackShape implements Shape {
   private Shape top, bottom;

   public StackShape(Shape top, Shape bottom) {
      this.top = top;
      this.bottom = bottom;
   }

   @Override
   public List<String> render() {
      int topWidth = top.width();
      int botWidth = bottom.width();
      int width = Math.max(topWidth, botWidth);

      String topPad = (topWidth < width) ? Util.repeat(" ", width - topWidth)
         : "";
      String botPad = (botWidth < width) ? Util.repeat(" ", width - botWidth)
         : "";

      List<String> result = new ArrayList<>();
      for (String line : top.render()) {
         result.add(line + topPad);
      }
      for (String line : bottom.render()) {
         result.add(line + botPad);
      }
      return result;
   }

   @Override
   public int width() {
      return Math.max(top.width(), bottom.width());
   }

   @Override
   public int height() {
      return top.height() + bottom.height();
   }

}
