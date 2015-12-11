package bhsol.perpetualmotion;

import java.awt.Color;

import javax.swing.JFrame;

import bhsol.model.Deck;
import bhsol.ui.CardImages;
import bhsol.ui.Table;

public class Game
{

   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Perpetual Motion");
      frame.setSize(600, 250);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      Table table = new Table();
      table.setBackground(DARK_GREEN);
      frame.add(table);

      CardImages images = new CardImages("/cards/", ".png");

      Tableau[] tableau = new Tableau[4];
      for (int i = 0; i < 4; i++)
      {
         tableau[i] = new Tableau(new Deck(), images);
         tableau[i].setX(150 + 100 * i);
         tableau[i].setY(50);
         table.addItem(tableau[i]);
      }

      Deck stock = new Deck();
      stock.fill();
      stock.shuffle();
      Stock stockItem = new Stock(stock, images, tableau);
      stockItem.setX(50);
      stockItem.setY(50);
      table.addItem(stockItem);

      frame.setVisible(true);
   }

   private static final Color DARK_GREEN = new Color(0, 128, 0);
}
