package bhsol.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class SolitaireDriver
{
   public static void main(String[] args)
   {
      frame = new JFrame("BH Solitaire");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(800, 600);
      
      Action playPerpetualMotion = new AbstractAction("Perpetual Motion") {
         public void actionPerformed(ActionEvent e)
         {
            frame.setContentPane(new PerpetualMotion());
            frame.validate();
         }
      };
      
      Action playFreeCell = new AbstractAction("FreeCell") {
         public void actionPerformed(ActionEvent e)
         {
            frame.setContentPane(new FreeCell());
            frame.validate();
         }
      };
      
      JMenu gameMenu = new JMenu("Game");
      gameMenu.add(new JMenuItem(playPerpetualMotion));
      gameMenu.add(new JMenuItem(playFreeCell));
      
      JMenuBar menubar = new JMenuBar();
      menubar.add(gameMenu);
      frame.setJMenuBar(menubar);
      
      frame.setVisible(true);
   }

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
   
   public static void winGame()
   {
      JOptionPane.showMessageDialog(frame, "You Win!");
   }
   
   private static JFrame frame;
}
