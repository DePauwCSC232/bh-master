package bhsol.ui;

import java.awt.Image;
import java.awt.event.MouseEvent;

/**
 * Provide default implementations of some of the Item interface.
 * 
 * @author bhoward
 */
public abstract class AbstractItem implements Item
{
   public int getX()
   {
      return x;
   }

   public void setX(int x)
   {
      this.x = x;
   }

   public int getY()
   {
      return y;
   }

   public void setY(int y)
   {
      this.y = y;
   }

   public boolean underMouse(MouseEvent event)
   {
      // check whether the event is over this item's image
      Image image = getImage();
      return x <= event.getX() && event.getX() < x + image.getWidth(null)
               && y <= event.getY() && event.getY() < y + image.getHeight(null);
   }

   private int x;
   private int y;
}
