package bhsol.perpetualmotion;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import bhsol.model.Card;
import bhsol.model.Deck;
import bhsol.ui.CardImages;
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
      JFrame frame = new JFrame("Perpetual Motion");
      frame.setSize(600, 250);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setLayout(new BorderLayout());

      Table table = new Table();
      table.setBackground(DARK_GREEN);
      frame.add(table, BorderLayout.CENTER);

      status = new JLabel("Click the stock to play");
      frame.add(status, BorderLayout.SOUTH);

      CardImages images = new CardImages("/cards/", ".png");

      tableaus = new Tableau[4];
      for (int i = 0; i < 4; i++)
      {
         tableaus[i] = new Tableau(new Deck(), images);
         tableaus[i].setX(150 + 100 * i);
         tableaus[i].setY(50);
         table.addItem(tableaus[i]);
      }

      Deck stock = new Deck();
      stock.fill();
      stock.shuffle();
      stockItem = new Stock(stock, images);
      stockItem.setX(50);
      stockItem.setY(50);
      table.addItem(stockItem);
      return frame;
   }

   /**
    * Return all of the cards from the tableaus to the stock.
    */
   public static void collectTableaus()
   {
      for (int i = 0; i < 4; i++)
      {
         Deck tab = tableaus[i].getDeck();
         while (!tab.isEmpty())
         {
            Card card = tab.deal();
            card.flip();
            stockItem.addCard(card);
         }
      }
   }

   /**
    * Add the four given cards to each of the tableaus in turn.
    * 
    * @param card0
    * @param card1
    * @param card2
    * @param card3
    */
   public static void dealCards(Card card0, Card card1, Card card2, Card card3)
   {
      tableaus[0].addCard(card0);
      tableaus[1].addCard(card1);
      tableaus[2].addCard(card2);
      tableaus[3].addCard(card3);
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

   private static final Color DARK_GREEN = new Color(0, 128, 0);

   private static JFrame frame;
   private static JLabel status;

   private static Tableau[] tableaus;
   private static Stock stockItem;
}
