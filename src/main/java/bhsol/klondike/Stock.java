package bhsol.klondike;

import java.awt.event.MouseEvent;

import bhsol.model.Deck;
import bhsol.ui.CardImages;
import bhsol.ui.DeckItem;
import bhsol.ui.Item;

public class Stock extends DeckItem
{
   public Stock(Deck deck, CardImages images)
   {
      super(deck, images);
   }

   @Override
   public void handleClick(MouseEvent event)
   {
      if (isEmpty())
      {
         Game.recycleWaste();
      }
      else
      {
         Game.dealOne();
      }
   }

   @Override
   public boolean canDrop(Item item, MouseEvent event)
   {
      return false;
   }

   @Override
   public boolean canDrag(MouseEvent event)
   {
      return false;
   }

   @Override
   public Item startDrag(MouseEvent event)
   {
      return null;
   }
}
