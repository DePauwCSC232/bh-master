package bhsol.ui;

import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import bhsol.model.Deck;

/**
 * A simple demonstration of using FanItems on a Table. Cards may be dragged
 * from one deck to the other.
 * 
 * @author bhoward
 */
public class GUIDemo2
{
   public static void main(String[] args) throws IOException
   {
      JFrame frame = new JFrame("Demo");
      frame.setSize(800, 600);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      Table table = new Table();
      table.setBackground(DARK_GREEN);
      frame.add(table);

      CardImages images = new CardImages("/cards/", ".png");

      Deck deck1 = new Deck();
      deck1.fill();
      for (int i = 0; i < deck1.size(); i++)
      {
         deck1.get(i).flip();
      }
      Item fanItem1 = new FanItem(deck1, images);
      fanItem1.setX(50);
      fanItem1.setY(100);
      table.addItem(fanItem1);

      Deck deck2 = new Deck();
      Item fanItem2 = new FanItem(deck2, images);
      fanItem2.setX(50);
      fanItem2.setY(300);
      table.addItem(fanItem2);

      frame.setVisible(true);
   }

   private static final Color DARK_GREEN = new Color(0, 128, 0);
}
