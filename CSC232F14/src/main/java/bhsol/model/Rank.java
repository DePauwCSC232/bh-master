package bhsol.model;

/**
 * Represent the rank of an ordinary playing card, ace through king.
 * Each rank also has an associated value, 1 to 10 (all face cards are 10),
 * and a single-character abbreviation.
 * 
 * @author bhoward
 */
public enum Rank
{
   Ace(1, 'A'), Two(2, '2'), Three(3, '3'), Four(4, '4'), Five(5, '5'),
   Six(6, '6'), Seven(7, '7'), Eight(8, '8'), Nine(9, '9'), Ten(10, 'T'),
   Jack(10, 'J'), Queen(10, 'Q'), King(10, 'K');

   Rank(int value, char abbrev)
   {
      this.value = value;
      this.abbrev = abbrev;
   }

   /**
    * Get the value associated with this rank.
    * 
    * @return the value, from 1 to 10
    */
   public int getValue()
   {
      return value;
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

   private final int value;
   private final char abbrev;
}
