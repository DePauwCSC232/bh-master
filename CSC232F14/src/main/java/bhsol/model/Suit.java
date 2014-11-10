package bhsol.model;

/**
 * Represent the suit of an ordinary playing card: clubs, diamonds, hearts, or spades.
 * Each suit has an associated color, either black or red, plus a single-character
 * abbreviation.
 * 
 * @author bhoward
 */
public enum Suit
{
   Clubs(false, 'C'), Diamonds(true, 'D'), Hearts(true, 'H'), Spades(false, 'S');
   
   Suit(boolean isRed, char abbrev) {
      this.isRed = isRed;
      this.abbrev = abbrev;
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
   
   /**
    * Get the single-character abbreviation associated with this rank.
    * 
    * @return the abbreviation
    */
   public char getAbbrev()
   {
      return abbrev;
   }

   private boolean isRed;
   private char abbrev;
}
