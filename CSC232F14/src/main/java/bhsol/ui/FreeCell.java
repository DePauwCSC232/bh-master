package bhsol.ui;

import java.awt.Color;

import javax.swing.JPanel;

import bhsol.model.Card;
import bhsol.model.Deck;
import bhsol.model.Rank;

public class FreeCell extends JPanel
{
   public FreeCell()
   {
      Deck stockD = new Deck();
      stockD.fill();
      stockD.shuffle();

      CardImages images = new CardImages("/cards/", ".png");

      cell = new DeckComponent[4];
      foundation = new DeckComponent[4];
      tableau = new DeckComponent[8];

      for (int i = 0; i < cell.length; i++)
      {
         cell[i] = new DeckComponent(new Deck(), images);
         cell[i].setDraggable(true);
         cell[i].setDeckListener(new DeckListener()
         {
            public void handleClick(DeckComponent deckComponent)
            {
               moveToFoundation(deckComponent);
            }

            public boolean checkDrop(DeckComponent target, Card card)
            {
               return target.isEmpty();
            }
         });
      }

      for (int i = 0; i < foundation.length; i++)
      {
         foundation[i] = new DeckComponent(new Deck(), images);
         foundation[i].setDraggable(false);
      }

      for (int i = 0; i < tableau.length; i++)
      {
         tableau[i] = new DeckComponent(new Deck(), images,
                  DeckComponent.FAN_VERTICAL);
         tableau[i].setDraggable(true);
         tableau[i].setDeckListener(new DeckListener()
         {
            public void handleClick(DeckComponent deckComponent)
            {
               moveToFoundation(deckComponent);
            }

            public boolean checkDrop(DeckComponent target, Card card)
            {
               Card top = target.getTopCard();
               return (top == null)
                        || (!sameColor(card, top) && nextRank(top, card));
            }
         });
      }

      // Deal out the cards
      int i = 0;
      while (!stockD.isEmpty())
      {
         Card card = stockD.deal();
         card.flip();
         tableau[i].addCard(card);
         i = (i + 1) % tableau.length;
      }

      setBackground(DARK_GREEN);

      add(createBoxPanel(cell));
      add(createBoxPanel(foundation));
      add(createBoxPanel(tableau));
   }

   private void moveToFoundation(DeckComponent deckComponent)
   {
      Card top = deckComponent.getTopCard();
      if (top == null)
      {
         return;
      }

      int suit = top.getSuit().ordinal();
      if (foundation[suit].isEmpty())
      {
         if (top.getRank() != Rank.Ace)
         {
            return;
         }
      }
      else
      {
         if (!nextRank(top, foundation[suit].getTopCard()))
         {
            return;
         }
      }

      deckComponent.removeTopCard();
      foundation[suit].addCard(top);

      // Check for win
      for (int i = 0; i < cell.length; i++)
      {
         if (!cell[i].isEmpty())
         {
            return;
         }
      }

      for (int i = 0; i < tableau.length; i++)
      {
         if (!tableau[i].isEmpty())
         {
            return;
         }
      }

      SolitaireDriver.winGame();
   }

   private static JPanel createBoxPanel(DeckComponent[] decks)
   {
      JPanel panel = new JPanel();
      panel.setBackground(DARK_GREEN);

      for (DeckComponent dc : decks)
      {
         panel.add(dc);
      }

      return panel;
   }

   private static boolean nextRank(Card a, Card b)
   {
      return a.getRank().ordinal() == b.getRank().ordinal() + 1;
   }

   private static boolean sameColor(Card a, Card b)
   {
      return a.getSuit().isRed() == b.getSuit().isRed();
   }

   private DeckComponent[] cell;
   private DeckComponent[] foundation;
   private DeckComponent[] tableau;

   private static final Color DARK_GREEN = new Color(0, 128, 0);
}
