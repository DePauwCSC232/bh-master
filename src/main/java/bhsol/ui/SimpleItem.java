package bhsol.ui;

import java.awt.Image;
import java.awt.event.MouseEvent;

/**
 * A SimpleItem is an implementation of the Item interface that holds a static
 * image and allows itself to be dragged. When released, it returns to its
 * original position. It does not respond to clicks or drops.
 * 
 * @author bhoward
 */
public class SimpleItem extends AbstractItem
{
   public SimpleItem(Image image)
   {
      this.image = image;
   }

   public Image getImage()
   {
      return image;
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
      startX = getX();
      startY = getY();
      return this;
   }

   public void endDrag(Item item, MouseEvent event)
   {
      // do nothing
   }

   public void cancelDrag(MouseEvent event)
   {
      // return to starting position
      setX(startX);
      setY(startY);
   }

   private int startX;
   private int startY;
   private Image image;
}
