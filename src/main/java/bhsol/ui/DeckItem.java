package bhsol.ui;

import java.awt.Image;
import java.awt.event.MouseEvent;

import bhsol.model.Card;
import bhsol.model.Deck;

/**
 * Represents a deck of cards to be displayed as a stack on a card game table.
 * The cards are drawn using the provided CardImages object.
 * 
 * @author bhoward
 */
public class DeckItem extends AbstractItem
{
   /**
    * Construct a DeckItem for the given deck and set of card images.
    * 
    * @param deck
    * @param images
    */
   public DeckItem(Deck deck, CardImages images)
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

   public Image getImage()
   {
      if (deck.isEmpty())
      {
         return images.getImage(null);
      }
      else
      {
         return images.getImage(deck.getTop());
      }
   }

   public void handleClick(MouseEvent event)
   {
      // do nothing
   }

   public boolean canDrop(Item item, MouseEvent event)
   {
      // allow any card to be dropped on top of the deck
      return item instanceof CardItem;
   }

   public void handleDrop(Item item, MouseEvent event)
   {
      // the CardItem will add itself in its endDrag method
   }

   public boolean canDrag(MouseEvent event)
   {
      // allow the top card to be dragged
      return !deck.isEmpty();
   }

   public Item startDrag(MouseEvent event)
   {
      Card card = deck.deal();
      if (!card.isFaceUp())
      {
         card.flip();
      }

      Item item = new CardItem(this, card, images.getImage(card));
      item.setX(getX());
      item.setY(getY());
      return item;
   }

   public void endDrag(Item item, MouseEvent event)
   {
      // not used
   }

   public void cancelDrag(MouseEvent event)
   {
      // not used
   }

   private Deck deck;
   private CardImages images;
}
