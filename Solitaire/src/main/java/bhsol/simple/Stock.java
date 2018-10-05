package bhsol.simple;

import java.awt.event.MouseEvent;
import java.util.Random;

import bhsol.model.Card;
import bhsol.model.Deck;
import bhsol.model.Rank;
import bhsol.ui.CardImages;
import bhsol.ui.DeckItem;
import bhsol.ui.Item;

public class Stock extends DeckItem
{
   public Stock(CardImages images)
   {
      super(makeDeck(), images);
      this.images = images;
   }

   @Override
   public void handleClick(MouseEvent event)
   {
      if (isEmpty())
      {
         Game.setStatus("You Win!");
         return;
      }

      Card top = deal();
      top.flip();
      Item topItem = new FreeCard(top, images.getImage(top), 500, 50);
      Game.addToTable(topItem);

      int value = computeValue(top.getRank());
      Random random = new Random();
      for (int i = 0; i < value; i++)
      {
         if (isEmpty())
         {
            Game.setStatus("You Lose!");
            return;
         }
         
         Card card = deal();
         int x = random.nextInt(250) + 150;
         int y = random.nextInt(100) + 50;
         Item item = new FreeCard(card, images.getImage(card), x, y);
         Game.addToTable(item);
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

   private static Deck makeDeck()
   {
      Deck deck = new Deck();
      deck.fill();
      deck.shuffle();
      return deck;
   }

   /**
    * Compute the "value" of the given rank: ace is 1, two is 2, ..., ten is 10,
    * and all face cards are also 10.
    * 
    * @param rank
    * @return The value of the given rank
    */
   private static int computeValue(Rank rank)
   {
      switch (rank)
      {
      case Ace:
         return 1;
      case Two:
         return 2;
      case Three:
         return 3;
      case Four:
         return 4;
      case Five:
         return 5;
      case Six:
         return 6;
      case Seven:
         return 7;
      case Eight:
         return 8;
      case Nine:
         return 9;
      case Ten:
         return 10;
      case Jack:
         return 10;
      case Queen:
         return 10;
      case King:
         return 10;
      default:
         return 0; // this should not happen
      }
   }

   private CardImages images;
}
