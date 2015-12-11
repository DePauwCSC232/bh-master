package bhsol.klondike;

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
   public void handleClick(MouseEvent event)
   {
      if (!isEmpty())
      {
         Game.tryFoundation(this);
      }
   }

   @Override
   public boolean canDrop(Item item, MouseEvent event)
   {
      return false;
   }
}
