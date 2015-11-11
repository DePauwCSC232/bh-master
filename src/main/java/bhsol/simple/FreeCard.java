package bhsol.simple;

import java.awt.Image;
import java.awt.event.MouseEvent;

import bhsol.model.Card;
import bhsol.ui.AbstractItem;
import bhsol.ui.DeckItem;
import bhsol.ui.Item;

public class FreeCard extends AbstractItem
{
   public FreeCard(Card card, Image image, int x, int y)
   {
      this.card = card;
      this.image = image;
      setX(x);
      setY(y);
   }
   
   public Card getCard()
   {
      return card;
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
      // not used
   }

   public boolean canDrag(MouseEvent event)
   {
      return true;
   }

   public Item startDrag(MouseEvent event)
   {
      return this;
   }

   public void endDrag(Item item, MouseEvent event)
   {
      Game.removeFromTable(this);
      
      // this card has been dropped on a target deck; add it there
      DeckItem target = (DeckItem) item;
      target.addCard(card);
   }

   public void cancelDrag(MouseEvent event)
   {
      // do nothing -- leave the card where it is
   }

   private Card card;
   private Image image;
}
