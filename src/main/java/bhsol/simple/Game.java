package bhsol.simple;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import bhsol.ui.CardImages;
import bhsol.ui.Item;
import bhsol.ui.Table;

public class Game
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Simple Game");
      frame.setSize(600, 350);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setLayout(new BorderLayout());
      
      status = new JLabel("Click Stock");
      frame.add(status, BorderLayout.SOUTH);

      table = new Table();
      table.setBackground(DARK_GREEN);
      frame.add(table, BorderLayout.CENTER);

      CardImages images = new CardImages("/cards/", ".png");
      
      Stock stock = new Stock(images);
      stock.setX(50);
      stock.setY(50);
      table.addItem(stock);
      
      Waste waste = new Waste(images);
      waste.setX(50);
      waste.setY(150);
      table.addItem(waste);

      frame.setVisible(true);
   }
   
   public static void setStatus(String message)
   {
      status.setText(message);
   }
   
   public static void addToTable(Item item)
   {
      table.addItem(item);
   }
   
   public static void removeFromTable(Item item)
   {
      table.removeItem(item);
   }

   private static final Color DARK_GREEN = new Color(0, 128, 0);
   private static JLabel status;
   private static Table table;
}
