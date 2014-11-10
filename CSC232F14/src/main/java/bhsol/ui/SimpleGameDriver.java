package bhsol.ui;

import bhsol.model.Card;
import bhsol.model.Deck;

/**
 * Plays a simple card game, as described in Homework #4.
 * 
 * @author bhoward
 */
public class SimpleGameDriver
{
   public static void main(String[] args)
   {
      Deck deck = new Deck();
      deck.fill();
      deck.shuffle();

      boolean win = true;
      while (!deck.isEmpty())
      {
         Card card = deck.deal();
         System.out.println(card);

         for (int i = 1; i <= card.getRank().getValue(); i++)
         {
            if (deck.isEmpty())
            {
               win = false;
            }
            else
            {
               deck.deal();
               System.out.println("Dealing card " + i);
            }
         }
      }

      if (win)
      {
         System.out.println("You Won!");
      }
      else
      {
         System.out.println("You Lost!");
      }
   }
}
