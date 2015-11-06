package bhsol.ui;

import java.awt.Image;
import java.awt.event.MouseEvent;

import bhsol.model.Card;

/**
 * Represents a card being dragged from a DeckItem.
 * 
 * @author bhoward
 */
public class CardItem extends AbstractItem
{
   /**
    * Construct a CardItem for a card being dragged from the given
    * origin DeckItem, with a provided static Image.
    * 
    * @param origin the DeckItem from which this card is being dragged
    * @param card the Card being dragged
    * @param image the Image to display for the card
    */
   public CardItem(DeckItem origin, Card card, Image image)
   {
      this.origin = origin;
      this.card = card;
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
      // do not accept drops
      return false;
   }

   public void handleDrop(Item item, MouseEvent event)
   {
      // not used
   }

   public boolean canDrag(MouseEvent event)
   {
      // can't start another drag while already dragging
      return false;
   }

   public Item startDrag(MouseEvent event)
   {
      // not used
      return null;
   }

   public void endDrag(Item item, MouseEvent event)
   {
      // this card has been dropped on a target deck; add it there
      DeckItem target = (DeckItem) item;
      target.addCard(card);
   }

   public void cancelDrag(MouseEvent event)
   {
      // return the card to its origin deck
      origin.addCard(card);
   }

   private DeckItem origin;
   private Card card;
   private Image image;
}
