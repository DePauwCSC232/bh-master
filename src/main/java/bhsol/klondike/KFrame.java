package bhsol.klondike;

import java.awt.Color;

import bhsol.model.Card;
import bhsol.model.Deck;
import bhsol.model.Rank;
import bhsol.ui.CardImages;
import bhsol.ui.DeckItem;
import bhsol.ui.GameFrame;
import bhsol.ui.Table;

@SuppressWarnings("serial")
public class KFrame extends GameFrame
{
   public KFrame(CardImages images)
   {
      super("Klondike", 1000, 600);
      this.images = images;
   }

   @Override
   public void layoutGame(Table table)
   {
      table.setBackground(DARK_GREEN);
      showStatus("Welcome to Klondike");
      
      tableaus = new Tableau[7];
      for (int i = 0; i < 7; i++)
      {
         tableaus[i] = new Tableau(images, this);
         tableaus[i].setX(150 + 100 * i);
         tableaus[i].setY(50);
         table.addItem(tableaus[i]);
      }

      foundations = new Foundation[4];
      for (int i = 0; i < 4; i++)
      {
         foundations[i] = new Foundation(images, this);
         foundations[i].setX(875);
         foundations[i].setY(50 + 125 * i);
         table.addItem(foundations[i]);
      }

      Deck stockDeck = new Deck();
      stockDeck.fill();
      stockDeck.shuffle();
      stock = new Stock(stockDeck, images, this);
      stock.setX(25);
      stock.setY(50);
      table.addItem(stock);

      waste = new Waste(images, this);
      waste.setX(25);
      waste.setY(175);
      table.addItem(waste);

      for (int i = 0; i < 7; i++)
      {
         Card c = stock.deal();
         c.flip();
         tableaus[i].addCard(c);
         for (int j = i + 1; j < 7; j++)
         {
            c = stock.deal();
            tableaus[j].addCard(c);
         }
      }
   }
   
   public void dealOne()
   {
      Card c = stock.deal();
      c.flip();
      waste.addCard(c);
   }

   public void recycleWaste()
   {
      while (!waste.isEmpty())
      {
         Card c = waste.deal();
         c.flip();
         stock.addCard(c);
      }
   }

   public void tryFoundation(DeckItem deckItem)
   {
      Card c = deckItem.getTop();
      if (c.getRank() == Rank.Ace)
      {
         // look for an empty foundation
         for (int i = 0; i < 4; i++)
         {
            if (foundations[i].isEmpty())
            {
               moveToFoundation(deckItem, foundations[i]);
               break;
            }
         }
      }
      else
      {
         // look for a matching foundation
         for (int i = 0; i < 4; i++)
         {
            if (!foundations[i].isEmpty())
            {
               Card c2 = foundations[i].getTop();
               boolean sameSuit = c.getSuit() == c2.getSuit();
               boolean adjacentRank = c.getRank()
                        .getValue() == c2.getRank().getValue() + 1;
               if (sameSuit && adjacentRank)
               {
                  moveToFoundation(deckItem, foundations[i]);
                  break;
               }
            }
         }
      }
   }

   public void checkForWin()
   {
      int count = 0;
      for (int i = 0; i < 4; i++)
      {
         count += foundations[i].size();
      }

      if (count == 52)
      {
         showWin();
      }
   }

   private void moveToFoundation(DeckItem deckItem, Foundation found)
   {
      Card c = deckItem.deal();
      found.addCard(c);
      if (deckItem instanceof Tableau) {
         ((Tableau) deckItem).flipIfExposed();
      }
      
      checkForWin();
   }

   private static final Color DARK_GREEN = new Color(0, 128, 0);

   private CardImages images;
   private Foundation[] foundations;
   private Tableau[] tableaus;
   private Stock stock;
   private Waste waste;
}
