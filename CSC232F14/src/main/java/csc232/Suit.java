package csc232;

/**
 * Represent the suit of an ordinary playing card: clubs, diamonds, hearts, or spades.
 * Each suit has an associated color, either black or red.
 * 
 * @author bhoward
 */
public enum Suit
{
   Clubs(false), Diamonds(true), Hearts(true), Spades(false);
   
   Suit(boolean isRed) {
      this.isRed = isRed;
   }
   
   /**
    * Check whether this suit is red.
    * 
    * @return true if red, false if black
    */
   public boolean isRed()
   {
      return isRed;
   }
   
   private boolean isRed;
}
