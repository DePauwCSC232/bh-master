package bhsol.perpetualmotion;

import java.awt.event.MouseEvent;

import bhsol.model.Card;
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
      Deck deck = getDeck();
      if (deck.isEmpty())
      {
         Game.collectTableaus();

         if (deck.isEmpty())
         {
            Game.showStatus("You Win!");
         }
      }
      else
      {
         Card card0 = deck.deal();
         Card card1 = deck.deal();
         Card card2 = deck.deal();
         Card card3 = deck.deal();

         if (card0.getRank() == card1.getRank()
            && card0.getRank() == card2.getRank()
            && card0.getRank() == card3.getRank())
         {
            Game.showStatus("All four were " + card0.getRank());
         }
         else
         {
            card0.flip();
            card1.flip();
            card2.flip();
            card3.flip();
            Game.dealCards(card0, card1, card2, card3);
         }
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
