package bhsol.ui;

import java.awt.Image;
import java.awt.event.MouseEvent;

/**
 * A SimpleItem is an implementation of the Item interface that holds a static
 * image, keeps track of a position, and allows itself to be dragged. When
 * released, it returns to its original position. It does not respond to clicks
 * or drops.
 * 
 * @author bhoward
 */
public class SimpleItem implements Item
{
   public SimpleItem(Image image)
   {
      this.image = image;
   }

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

   public Image getImage()
   {
      return image;
   }

   public boolean underMouse(MouseEvent event)
   {
      // check whether the event is over this item
      return x <= event.getX() && event.getX() < x + image.getWidth(null)
               && y <= event.getY() && event.getY() < y + image.getHeight(null);
   }

   public void handleClick(MouseEvent event)
   {
      // do nothing
   }

   public boolean canDrop(Item item, MouseEvent event)
   {
      return false;
   }

   public void handleDrop(Item item, MouseEvent event)
   {
      // do nothing
   }

   public boolean canDrag(MouseEvent event)
   {
      return true;
   }

   public Item startDrag(MouseEvent event)
   {
      startX = x;
      startY = y;
      return this;
   }

   public void endDrag(Item item, MouseEvent event)
   {
      // do nothing
   }

   public void cancelDrag(MouseEvent event)
   {
      // return to starting position
      x = startX;
      y = startY;
   }

   private int x, y;
   private int startX, startY;
   private Image image;
}
