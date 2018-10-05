package bhsol.perpetualmotion;

import java.awt.event.MouseEvent;

import bhsol.model.Card;
import bhsol.model.Deck;
import bhsol.ui.CardImages;
import bhsol.ui.DeckItem;
import bhsol.ui.Item;

public class Stock extends DeckItem
{
   public Stock(Deck deck, CardImages images, Tableau[] tableau, PMFrame frame)
   {
      super(deck, images);
      this.tableau = tableau;
      this.frame = frame;
   }

   @Override
   public void handleClick(MouseEvent event)
   {
      if (isEmpty())
      {
         for (int i = 0; i < 4; i++)
         {
            while (!tableau[i].isEmpty())
            {
               Card card = tableau[i].deal();
               card.flip();
               addCard(card);
            }
         }

         if (isEmpty())
         {
            frame.showWin();
         }
      }
      else
      {
         Card card0 = deal();
         Card card1 = deal();
         Card card2 = deal();
         Card card3 = deal();

         if (card0.getRank() == card1.getRank()
            && card0.getRank() == card2.getRank()
            && card0.getRank() == card3.getRank())
         {
            frame.showStatus("All four were " + card0.getRank());
         }
         else
         {
            card0.flip();
            card1.flip();
            card2.flip();
            card3.flip();
            tableau[0].addCard(card0);
            tableau[1].addCard(card1);
            tableau[2].addCard(card2);
            tableau[3].addCard(card3);
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

   private Tableau[] tableau;
   private PMFrame frame;
}
