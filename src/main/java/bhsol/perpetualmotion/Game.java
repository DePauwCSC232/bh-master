package bhsol.perpetualmotion;

import bhsol.ui.CardImages;
import bhsol.ui.GameFrame;

public class Game
{
   public static void main(String[] args)
   {
      CardImages images = new CardImages("/cards/", ".png");
      GameFrame frame = makeFrame(images);
      frame.display();
   }

   public static GameFrame makeFrame(CardImages images)
   {
      return new PMFrame(images);
   }
}
