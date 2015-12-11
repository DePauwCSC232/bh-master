package bhsol.model;

/**
 * Represent the rank of an ordinary playing card, ace through king. Each rank
 * also holds a one-character abbreviation and an integer value (1 to 13).
 * 
 * @author bhoward
 */
public enum Rank
{
   Ace('A', 1),
      Two('2', 2),
      Three('3', 3),
      Four('4', 4),
      Five('5', 5),
      Six('6', 6),
      Seven('7', 7),
      Eight('8', 8),
      Nine('9', 9),
      Ten('T', 10),
      Jack('J', 11),
      Queen('Q', 12),
      King('K', 13);

   Rank(char abbrev, int value)
   {
      this.abbrev = abbrev;
      this.value = value;
   }

   /**
    * @return the abbreviation for this rank
    */
   public char getAbbrev()
   {
      return abbrev;
   }
   
   /**
    * @return the value for this rank
    */
   public int getValue()
   {
      return value;
   }

   private final char abbrev;
   private final int value;
}
