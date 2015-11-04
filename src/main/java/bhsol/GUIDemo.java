package bhsol;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import bhsol.ui.Item;
import bhsol.ui.SimpleItem;
import bhsol.ui.Table;

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

      char ranks[] = { 'A', 'K', 'Q', 'J', 'T' };

      int x = 200;
      int y = 100;
      for (char rank : ranks)
      {
         String filename = "/cards/" + rank + "S.png";
         Image image = ImageIO.read(GUIDemo.class.getResource(filename));
         Item card = new SimpleItem(image)
         {
            @Override
            public void cancelDrag(MouseEvent event)
            {
               table.bringItemToFront(this);
               super.cancelDrag(event);
            }
         };
         table.addItem(card);
         card.setX(x);
         card.setY(y);
         x += 20;
      }

      frame.setVisible(true);
   }
   
   private static final Color DARK_GREEN = new Color(0, 128, 0);
}
