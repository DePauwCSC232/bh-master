package bhsol.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

/**
 * Manage a collection of Items for a game, and route mouse actions (click,
 * move, and drag/drop) to them.
 * 
 * @author bhoward
 */
@SuppressWarnings("serial")
public class Table extends JPanel
{
   /**
    * Construct an empty table, and initialize the mouse listeners.
    */
   public Table()
   {
      items = new ArrayList<Item>();

      MouseInputListener tableListener = new TableListener();
      addMouseListener(tableListener);
      addMouseMotionListener(tableListener);
   }

   /**
    * Add the given Item on top of any existing items.
    * 
    * @param item
    */
   public void addItem(Item item)
   {
      items.add(item);
   }

   /**
    * Removes the given Item from the table.
    * 
    * @param item
    * @return true if the item was present
    */
   public boolean removeItem(Item item)
   {
      return items.remove(item);
   }

   /**
    * Cause the given Item, if present, to be moved on top of all other items.
    * 
    * @param item
    */
   public void bringItemToFront(Item item)
   {
      if (items.remove(item))
      {
         items.add(item);
      }
   }

   @Override
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      // draw each of the items not involved in a drag;
      // the highlighted item, if present, gets a border
      for (Item item : items)
      {
         if (item == dragItem)
            continue;
         Image image = item.getImage();
         int x = item.getX();
         int y = item.getY();
         if (item == highlightItem)
         {
            int w = image.getWidth(null);
            int h = image.getHeight(null);

            g.setColor(HIGHLIGHT_BORDER_COLOR);
            int borderX = x - HIGHLIGHT_BORDER_WIDTH;
            int borderY = y - HIGHLIGHT_BORDER_WIDTH;
            int borderW = w + HIGHLIGHT_BORDER_WIDTH * 2;
            int borderH = h + HIGHLIGHT_BORDER_WIDTH * 2;
            g.fillRoundRect(borderX, borderY, borderW, borderH,
                     HIGHLIGHT_BORDER_WIDTH * 2, HIGHLIGHT_BORDER_WIDTH * 2);
         }
         g.drawImage(image, x, y, null);
      }

      // now draw the item being dragged, if any
      // it is drawn on top, with a shadow underneath
      if (dragItem != null)
      {
         Image image = dragItem.getImage();
         int x = dragItem.getX();
         int y = dragItem.getY();
         int w = image.getWidth(null);
         int h = image.getHeight(null);

         g.setColor(DRAG_SHADOW_COLOR);
         g.fillRoundRect(x, y, w + SHADOW_WIDTH, h + SHADOW_WIDTH,
                  SHADOW_WIDTH * 2, SHADOW_WIDTH * 2);
         g.drawImage(image, x, y, null);
      }
   }

   private List<Item> items; // maintain this in z-order (back to front)
   private Item highlightItem;
   private Item dragItem;

   private static final int HIGHLIGHT_BORDER_WIDTH = 2;
   private static final int SHADOW_WIDTH = 5;

   private static final Color DRAG_SHADOW_COLOR = new Color(0, 0, 0, 64);
   private static final Color HIGHLIGHT_BORDER_COLOR = new Color(0, 0, 0);

   private static final Cursor MAIN_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
   private static final Cursor DRAG_CURSOR = new Cursor(Cursor.HAND_CURSOR);

   /**
    * A TableListener translates mouse input events into appropriate actions for
    * a game table and its items. It supports clicking, dragging, and dropping
    * of items.
    * 
    * @author bhoward
    */
   final class TableListener extends MouseInputAdapter
   {
      private Point point;

      @Override
      public void mouseClicked(MouseEvent e)
      {
         Item item = findItem(e);
         if (item != null)
         {
            item.handleClick(e);
            repaint();
         }
      }

      @Override
      public void mousePressed(MouseEvent e)
      {
         point = e.getPoint();
      }

      @Override
      public void mouseReleased(MouseEvent e)
      {
         if (dragItem != null)
         {
            Item item = findItem(e);
            if (item != null && item.canDrop(dragItem, e))
            {
               item.handleDrop(dragItem, e);
               dragItem.endDrag(item, e);
            }
            else
            {
               dragItem.cancelDrag(e);
            }
            dragItem = null;
            highlightItem = null;

            repaint();
         }
      }

      @Override
      public void mouseDragged(MouseEvent e)
      {
         if (dragItem == null)
         {
            Item item = findItem(e);
            if (item != null && item.canDrag(e))
            {
               dragItem = item.startDrag(e);
            }
         }

         if (dragItem != null)
         {
            Point point2 = e.getPoint();
            int dx = point2.x - point.x;
            int dy = point2.y - point.y;
            dragItem.setX(dragItem.getX() + dx);
            dragItem.setY(dragItem.getY() + dy);
            point = point2;

            Item item = findItem(e);
            if (item != null && item.canDrop(dragItem, e))
            {
               highlightItem = item;
            }
            else
            {
               highlightItem = null;
            }

            repaint();
         }
      }

      @Override
      public void mouseMoved(MouseEvent e)
      {
         Item item = findItem(e);
         if (item != null && item.canDrag(e))
         {
            setCursor(DRAG_CURSOR);
         }
         else
         {
            setCursor(MAIN_CURSOR);
         }
      }

      @Override
      public void mouseExited(MouseEvent e)
      {
         if (dragItem != null)
         {
            dragItem.cancelDrag(e);
            dragItem = null;
            highlightItem = null;

            repaint();
         }
      }

      private Item findItem(MouseEvent e)
      {
         for (int i = items.size() - 1; i >= 0; i--)
         {
            Item item = items.get(i);
            if (item != dragItem && item.underMouse(e))
            {
               return item;
            }
         }

         return null;
      }
   }
}
