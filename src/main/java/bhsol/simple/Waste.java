package bhsol.simple;

import java.awt.event.MouseEvent;

import bhsol.model.Deck;
import bhsol.ui.CardImages;
import bhsol.ui.DeckItem;
import bhsol.ui.Item;

public class Waste extends DeckItem
{
   public Waste(CardImages images)
   {
      super(new Deck(), images);
   }

   @Override
   public boolean canDrag(MouseEvent event)
   {
      return false;
   }

   @Override
   public boolean canDrop(Item item, MouseEvent event)
   {
      if (item instanceof FreeCard)
      {
         FreeCard card = (FreeCard) item;
         return !card.getCard().isFaceUp();
      }
      else
      {
         return false;
      }
   }
}
