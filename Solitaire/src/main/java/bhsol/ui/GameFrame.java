package bhsol.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public abstract class GameFrame extends JFrame
{
   public GameFrame(String title, int width, int height)
   {
      super(title);
      setSize(width, height);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setLayout(new BorderLayout());

      win = false;

      table = new Table();
      add(table, BorderLayout.CENTER);

      status = new JLabel();
      add(status, BorderLayout.SOUTH);
   }

   /**
    * Call this to finish laying out the game and display the frame on the
    * screen.
    */
   public void display()
   {
      layoutGame(table);
      setVisible(true);
   }

   /**
    * Define this in a subclass to add items to the table.
    * 
    * @param table
    */
   public abstract void layoutGame(Table table);

   /**
    * Display a message in the status bar.
    * 
    * @param message
    */
   public void showStatus(String message)
   {
      status.setText(message);
   }

   /**
    * Mark this game as won, and display an appropriate message.
    */
   public void showWin()
   {
      showStatus("You Win!");
      win = true;
   }

   /**
    * @return true if the game has been won
    */
   public boolean isWin()
   {
      return win;
   }

   private Table table;
   private JLabel status;
   private boolean win;
}
