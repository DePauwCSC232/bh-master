package bhsol.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import bhsol.model.Card;
import bhsol.model.Deck;

/**
 * Represents a deck of cards to be displayed in a horizontal or vertical fan on
 * a card game table. The cards are drawn using the provided CardImages object.
 * 
 * @author bhoward
 */
public class FanItem extends DeckItem
{
   /**
    * Construct a horizontal FanItem for the given deck and set of card images.
    * 
    * @param deck
    * @param images
    */
   public FanItem(Deck deck, CardImages images)
   {
      this(deck, images, false);
   }

   /**
    * Construct a FanItem for the given deck and set of card images, with the given
    * fan direction (true for vertical).
    * 
    * @param deck
    * @param images
    * @param isVertical
    */
   public FanItem(Deck deck, CardImages images, boolean isVertical)
   {
      super(deck, images);
      this.images = images;
      if (isVertical) {
         xOFFSET = 0;
         yOFFSET = VOFFSET;
      } else {
         xOFFSET = HOFFSET;
         yOFFSET = 0;
      }
   }
   
   @Override
   public Image getImage()
   {
      if (isEmpty())
      {
         return images.getImage(null);
      }
      else
      {
         Image top = images.getImage(getTop());
         int width = top.getWidth(null) + xOFFSET * (size() - 1);
         int height = top.getHeight(null) + yOFFSET * (size() - 1);
         BufferedImage image = new BufferedImage(width, height,
                  BufferedImage.TYPE_INT_ARGB);
         Graphics g = image.getGraphics();
         for (int i = 0; i < size(); i++)
         {
            Card card = getCard(i);
            Image cardImage = images.getImage(card);
            g.drawImage(cardImage, xOFFSET * i, yOFFSET * i, null);
         }
         return image;
      }
   }

   @Override
   public boolean canDrop(Item item, MouseEvent event)
   {
      return item instanceof PacketItem;
   }

   @Override
   public void handleDrop(Item item, MouseEvent event)
   {
      // the PacketItem will add itself in its endDrag method,
      // so there is nothing to do here
   }

   @Override
   public Item startDrag(MouseEvent event)
   {
      int n = identifyCard(event);

      // Create a new deck with the selected cards
      Deck deck1 = new Deck();
      for (int i = n; i < size(); i++)
      {
         deck1.add(getCard(i));
      }

      // Remove those cards from our deck
      for (int i = 0; i < deck1.size(); i++)
      {
         deal();
      }

      Item result = new PacketItem(this, deck1, images, yOFFSET != 0);
      result.setX(getX() + n * xOFFSET);
      result.setY(getY() + n * yOFFSET);
      return result;
   }

   protected int identifyCard(MouseEvent event)
   {
      // Determine how many cards are selected
      int dx = event.getX() - getX();
      int dy = event.getY() - getY();
      if (yOFFSET != 0) {
         return Math.min(dy / yOFFSET, size() - 1);
      } else {
         return Math.min(dx / xOFFSET, size() - 1);
      }
   }

   private static int HOFFSET = 12;
   private static int VOFFSET = 18;

   private int xOFFSET;
   private int yOFFSET;
   
   private CardImages images;
}
