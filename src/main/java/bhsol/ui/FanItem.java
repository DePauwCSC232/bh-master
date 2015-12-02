package bhsol.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import bhsol.model.Card;
import bhsol.model.Deck;

/**
 * Represents a deck of cards to be displayed in a horizontal fan on a card game
 * table. The cards are drawn using the provided CardImages object.
 * 
 * @author bhoward
 */
public class FanItem extends AbstractItem
{
   /**
    * Construct a FanItem for the given deck and set of card images.
    * 
    * @param deck
    * @param images
    */
   public FanItem(Deck deck, CardImages images)
   {
      this.deck = deck;
      this.images = images;
   }

   /**
    * Add the given card to the deck.
    * 
    * @param card
    */
   public void addCard(Card card)
   {
      deck.add(card);
   }

   @Override
   public Image getImage()
   {
      if (deck.isEmpty())
      {
         return images.getImage(null);
      }
      else
      {
         Image top = images.getImage(deck.getTop());
         int width = top.getWidth(null) + OFFSET * (deck.size() - 1);
         int height = top.getHeight(null);
         BufferedImage image = new BufferedImage(width, height,
                  BufferedImage.TYPE_INT_ARGB);
         Graphics g = image.getGraphics();
         for (int i = 0; i < deck.size(); i++)
         {
            Card card = deck.get(i);
            Image cardImage = images.getImage(card);
            g.drawImage(cardImage, OFFSET * i, 0, null);
         }
         return image;
      }
   }

   @Override
   public void handleClick(MouseEvent event)
   {
      // do nothing
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
   public boolean canDrag(MouseEvent event)
   {
      return !deck.isEmpty();
   }

   @Override
   public Item startDrag(MouseEvent event)
   {
      // Determine how many cards are selected
      int dx = event.getX() - getX();
      int dy = event.getY() - getY();
      int n = Math.min(dx / OFFSET, deck.size() - 1);
      
      // Create a new deck with the selected cards
      Deck deck1 = new Deck();
      for (int i = n; i < deck.size(); i++) {
         deck1.add(deck.get(i));
      }
      
      // Remove those cards from our deck
      for (int i = 0; i < deck1.size(); i++) {
         deck.deal();
      }
      
      Item result = new PacketItem(this, deck1, images);
      result.setX(event.getX() - dx + n * OFFSET);
      result.setY(event.getY() - dy);
      return result;
   }

   @Override
   public void endDrag(Item target, MouseEvent event)
   {
      // not used
   }

   @Override
   public void cancelDrag(MouseEvent event)
   {
      // not used
   }

   private static int OFFSET = 12;

   protected Deck deck;
   private CardImages images;
}
