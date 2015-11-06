package bhsol.model;

/**
 * Represent the rank of an ordinary playing card, ace through king. Each rank
 * also holds a one-character abbreviation.
 * 
 * @author bhoward
 */
public enum Rank
{
   Ace('A'),
      Two('2'),
      Three('3'),
      Four('4'),
      Five('5'),
      Six('6'),
      Seven('7'),
      Eight('8'),
      Nine('9'),
      Ten('T'),
      Jack('J'),
      Queen('Q'),
      King('K');

   Rank(char abbrev)
   {
      this.abbrev = abbrev;
   }

   /**
    * @return the abbreviation for this rank
    */
   public char getAbbrev()
   {
      return abbrev;
   }

   private final char abbrev;
}
