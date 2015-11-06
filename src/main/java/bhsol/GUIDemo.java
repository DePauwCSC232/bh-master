package bhsol;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import bhsol.model.Deck;
import bhsol.ui.CardImages;
import bhsol.ui.DeckItem;
import bhsol.ui.Item;
import bhsol.ui.Table;

/**
 * A simple demonstration of using DeckItems on a Table. Cards may be dragged
 * from one deck to the other.
 * 
 * @author bhoward
 */
public class GUIDemo
{
   public static void main(String[] args) throws IOException
   {
      JFrame frame = new JFrame("Demo");
      frame.setSize(600, 400);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      Table table = new Table();
      table.setBackground(DARK_GREEN);
      frame.add(table);

      CardImages images = new CardImages("/cards/", ".png");

      Deck deck1 = new Deck();
      deck1.fill();
      Item deckItem1 = new DeckItem(deck1, images);
      deckItem1.setX(100);
      deckItem1.setY(100);
      table.addItem(deckItem1);

      Deck deck2 = new Deck();
      Item deckItem2 = new DeckItem(deck2, images);
      deckItem2.setX(400);
      deckItem2.setY(100);
      table.addItem(deckItem2);

      frame.setVisible(true);
   }

   private static final Color DARK_GREEN = new Color(0, 128, 0);
}
