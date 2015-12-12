package bhsol.perpetualmotion;

import java.awt.Color;

import bhsol.model.Deck;
import bhsol.ui.CardImages;
import bhsol.ui.GameFrame;
import bhsol.ui.Table;

@SuppressWarnings("serial")
public class PMFrame extends GameFrame
{
   public PMFrame(CardImages images)
   {
      super("Perpetual Motion", 600, 250);
      this.images = images;
   }

   @Override
   public void layoutGame(Table table)
   {
      table.setBackground(DARK_GREEN);
      showStatus("Welcome to Perpetual Motion");
      
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
      Stock stockItem = new Stock(stock, images, tableau, this);
      stockItem.setX(50);
      stockItem.setY(50);
      table.addItem(stockItem);
   }

   private static final Color DARK_GREEN = new Color(0, 128, 0);

   private CardImages images;
}
