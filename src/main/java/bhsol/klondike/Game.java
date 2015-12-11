package bhsol.klondike;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import bhsol.model.Card;
import bhsol.model.Deck;
import bhsol.model.Rank;
import bhsol.ui.CardImages;
import bhsol.ui.DeckItem;
import bhsol.ui.Table;

public class Game
{

   public static void main(String[] args)
   {
      frame = makeFrame();
      frame.setVisible(true);
   }

   public static JFrame makeFrame()
   {
      JFrame frame = new JFrame("Klondike");
      frame.setSize(1000, 600);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setLayout(new BorderLayout());

      Table table = new Table();
      table.setBackground(DARK_GREEN);
      frame.add(table, BorderLayout.CENTER);

      status = new JLabel("Welcome to Klondike");
      frame.add(status, BorderLayout.SOUTH);

      CardImages images = new CardImages("/cards/", ".png");

      tableaus = new Tableau[7];
      for (int i = 0; i < 7; i++)
      {
         tableaus[i] = new Tableau(images);
         tableaus[i].setX(150 + 100 * i);
         tableaus[i].setY(50);
         table.addItem(tableaus[i]);
      }

      foundations = new Foundation[4];
      for (int i = 0; i < 4; i++)
      {
         foundations[i] = new Foundation(images);
         foundations[i].setX(875);
         foundations[i].setY(50 + 125 * i);
         table.addItem(foundations[i]);
      }

      Deck stockDeck = new Deck();
      stockDeck.fill();
      stockDeck.shuffle();
      stock = new Stock(stockDeck, images);
      stock.setX(25);
      stock.setY(50);
      table.addItem(stock);

      waste = new Waste(images);
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

      return frame;
   }

   public static void dealOne()
   {
      Card c = stock.deal();
      c.flip();
      waste.addCard(c);
   }

   public static void recycleWaste()
   {
      while (!waste.isEmpty())
      {
         Card c = waste.deal();
         c.flip();
         stock.addCard(c);
      }
   }

   public static void tryFoundation(DeckItem deckItem)
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

   public static void checkForWin()
   {
      int count = 0;
      for (int i = 0; i < 4; i++)
      {
         count += foundations[i].size();
      }

      if (count == 52)
      {
         showStatus("You Win!");
      }
   }

   /**
    * Display a message in the status bar.
    * 
    * @param message
    */
   public static void showStatus(String message)
   {
      status.setText(message);
   }

   private static void moveToFoundation(DeckItem deckItem, Foundation found)
   {
      Card c = deckItem.deal();
      found.addCard(c);
      if (deckItem instanceof Tableau) {
         ((Tableau) deckItem).flipIfExposed();
      }
      
      checkForWin();
   }

   private static final Color DARK_GREEN = new Color(0, 128, 0);

   private static JFrame frame;
   private static JLabel status;

   private static Foundation[] foundations;
   private static Tableau[] tableaus;
   private static Stock stock;
   private static Waste waste;
}
