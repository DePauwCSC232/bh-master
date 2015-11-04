package bhsol.ui;

import java.awt.Image;
import java.awt.event.MouseEvent;

/**
 * An Item is an independent entity that can be displayed on a game table. It
 * has a position and image, plus call-backs for various events. Examples are a
 * single card, a stack or hand of cards, or an empty space where a card may be
 * placed.
 * 
 * @author bhoward
 */
public interface Item
{
   /**
    * @return the current x-coordinate of the upper-right corner of this item
    */
   int getX();

   /**
    * @param x
    *           the new x-coordinate of the upper-right corner of this item
    */
   void setX(int x);

   /**
    * @return the current y-coordinate of the upper-right corner of this item
    */
   int getY();

   /**
    * @param y
    *           the new y-coordinate of the upper-right corner of this item
    */
   void setY(int y);

   /**
    * @return the image to display for this item
    */
   Image getImage();

   /**
    * Return whether this item is under the location of the given mouse event.
    * 
    * @param event
    *           the mouse event
    * @return true if the event applies to this item
    */
   boolean underMouse(MouseEvent event);

   /**
    * Respond to a mouse click on this item.
    * 
    * @param event
    *           the mouseClicked event
    */
   void handleClick(MouseEvent event);

   /**
    * Return whether the given item may be dropped on this.
    * 
    * @param item
    *           the candidate for dropping on this item
    * @param event
    *           the mouseMoved event being checked
    * @return true if this item will accept the drop
    */
   boolean canDrop(Item item, MouseEvent event);

   /**
    * Process an item dropped on this. A previous call to canDrop for the given
    * item should already have returned true.
    * 
    * @param item
    *           the dropped item
    * @param event
    *           the mouseReleased event for the drop
    */
   void handleDrop(Item item, MouseEvent event);

   /**
    * Return whether the given item may be dragged. This could be used to change
    * the mouse appearance, for example. To avoid lag, this should do minimal
    * computation and return quickly.
    * 
    * @param event
    *           the mouseDragged event being checked
    * @return true if this item will respond to being dragged
    */
   boolean canDrag(MouseEvent event);

   /**
    * Return an Item (perhaps this) that will be dragged with the mouse for a
    * potential drop.
    * 
    * @param event
    *           the initial mouseDragged event recognized as a drag.
    * @return this Item, or a new Item, to be dragged
    */
   Item startDrag(MouseEvent event);

   /**
    * Notify this item that it has been dropped on the given item.
    * 
    * @param item
    *           the target of the drop
    * @param event
    *           the mouseReleased event for the drop
    */
   void endDrag(Item item, MouseEvent event);

   /**
    * Notify this item that its current drag motion has been cancelled.
    * 
    * @param event
    *           the mouse event that cancelled the drag
    */
   void cancelDrag(MouseEvent event);
}
