package bhsol.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bhsol.ui.CardImages;
import bhsol.ui.GameFrame;

public class Main
{
   public static void main(String[] args)
   {
      frame = new JFrame("Solitaire");
      frame.setSize(200, 200);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      CardImages images = new CardImages("/cards/", ".png");

      JComponent buttons = Box.createVerticalBox();

      JButton klondike = new JButton("Klondike");
      klondike.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            GameFrame kframe = bhsol.klondike.Game.makeFrame(images);
            setWindowListener(kframe);
            kframe.display();
            frame.setVisible(false);
         }
      });
      buttons.add(klondike);

      JButton perpetual = new JButton("Perpetual Motion");
      perpetual.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            GameFrame pframe = bhsol.perpetualmotion.Game.makeFrame(images);
            setWindowListener(pframe);
            pframe.display();
            frame.setVisible(false);
         }
      });
      buttons.add(perpetual);
      
      winLabel = new JLabel(" Wins: 0");
      buttons.add(winLabel);
      lossLabel = new JLabel(" Losses: 0");
      buttons.add(lossLabel);

      frame.add(buttons);
      frame.setVisible(true);
   }
   
   /**
    * Add a listener for closing the given GameFrame, to reopen the
    * menu and record a win/loss.
    * 
    * @param gframe
    */
   private static void setWindowListener(GameFrame gframe)
   {
      gframe.addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosed(WindowEvent e)
         {
            frame.setVisible(true);
            if (gframe.isWin())
            {
               recordWin();
            }
            else
            {
               recordLoss();
            }
         }
      });
   }
   
   private static void recordWin()
   {
      ++wins;
      winLabel.setText(" Wins: " + wins);
   }
   
   private static void recordLoss()
   {
      ++losses;
      lossLabel.setText(" Losses: " + losses);
   }
   
   private static JFrame frame;
   private static JLabel winLabel;
   private static JLabel lossLabel;
   private static int wins;
   private static int losses;
}
