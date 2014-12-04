package bhsol.ui;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bhsol.model.Card;
import bhsol.model.Deck;

@SuppressWarnings("serial")
public class PerpetualMotion extends JPanel
{
   public PerpetualMotion()
   {
      Deck stockD = new Deck();
      stockD.fill();
      stockD.shuffle();
   
      CardImages images = new CardImages("/cards/", ".png");
   
      final DeckComponent stock = new DeckComponent(stockD, images);
      stock.setDraggable(false);
   
      final DeckComponent[] tableau = new DeckComponent[4];
      for (int i = 0; i < tableau.length; i++)
      {
         tableau[i] = new DeckComponent(new Deck(), images);
         tableau[i].setDraggable(true);
         tableau[i].setDeckListener(new DeckListener()
         {
            public void handleClick(DeckComponent deckComponent)
            {
               // Do nothing
            }
   
            public boolean checkDrop(DeckComponent deckComponent, Card card)
            {
               if (deckComponent.isEmpty())
               {
                  return false;
               }
               Card top = deckComponent.getTopCard();
               return top.getRank().equals(card.getRank());
            }
         });
      }
   
      final DeckComponent foundation = new DeckComponent(new Deck(), images);
      foundation.setDraggable(false);
   
      stock.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            if (stock.isEmpty())
            {
               for (int i = 0; i < tableau.length; i++)
               {
                  while (!tableau[i].isEmpty())
                  {
                     Card card = tableau[i].removeTopCard();
                     card.flip();
                     stock.addCard(card);
                     pause(20);
                  }
               }
            }
            else
            {
               for (int i = 0; i < tableau.length; i++)
               {
                  Card card = stock.removeTopCard();
                  card.flip();
                  tableau[i].addCard(card);
                  pause(50);
               }
            }
         }
   
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            return false;
         }
      });
   
      foundation.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            // Make sure all tableau decks are non-empty
            for (int i = 0; i < tableau.length; i++)
            {
               if (tableau[i].isEmpty())
               {
                  return;
               }
            }
   
            // Check tableau top cards for equal ranks
            for (int i = 0; i < tableau.length - 1; i++)
            {
               Card c1 = tableau[i].getTopCard();
               Card c2 = tableau[i + 1].getTopCard();
               if (!c1.getRank().equals(c2.getRank()))
               {
                  return;
               }
            }
   
            // All top tableau cards are equal rank, so move to foundation
            for (int i = 0; i < tableau.length; i++)
            {
               Card card = tableau[i].removeTopCard();
               foundation.addCard(card);
               pause(50);
            }
   
            // Check for empty stock and tableau -- end of game
            if (stock.isEmpty())
            {
               for (int i = 0; i < tableau.length; i++)
               {
                  if (!tableau[i].isEmpty())
                  {
                     return;
                  }
               }
   
               JOptionPane.showMessageDialog(PerpetualMotion.this, "You Win!");
            }
         }
   
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            return false;
         }
      });
   
      setBackground(new Color(0, 128, 0));
      add(stock);
      add(Box.createHorizontalStrut(50));
      for (DeckComponent tab : tableau)
      {
         add(tab);
      }
      add(Box.createHorizontalStrut(50));
      add(foundation);
   }
   
   /**
    * Pause for a given number of milliseconds, to allow the GUI to repaint.
    * 
    * @param millis
    */
   public static void pause(int millis)
   {
      try
      {
         Thread.sleep(millis);
      }
      catch (InterruptedException e)
      {
         // ignore
      }
   }
   
   /**
    * Run a stand-alone game of Perpetual Motion
    * 
    * @param args
    */
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Perpetual Motion");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(600, 300);

      frame.add(new PerpetualMotion());
      
      frame.setVisible(true);
   }
}
