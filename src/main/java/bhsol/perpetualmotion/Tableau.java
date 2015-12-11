package bhsol.perpetualmotion;

import java.awt.event.MouseEvent;

import bhsol.model.Card;
import bhsol.model.Deck;
import bhsol.ui.CardImages;
import bhsol.ui.CardItem;
import bhsol.ui.DeckItem;
import bhsol.ui.Item;

public class Tableau extends DeckItem
{
   public Tableau(Deck deck, CardImages images)
   {
      super(deck, images);
   }

   @Override
   public boolean canDrop(Item item, MouseEvent event)
   {
      if (isEmpty())
         return false;

      Card topCard = getTop();
      Card testCard = ((CardItem) item).getCard();
      return testCard.getRank() == topCard.getRank();
   }
}
