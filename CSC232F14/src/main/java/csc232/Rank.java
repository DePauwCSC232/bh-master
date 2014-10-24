package csc232;

/**
 * Represent the rank of an ordinary playing card, ace through king.
 * Each rank also has an associated value, 1 to 10 (all face cards are 10).
 * 
 * @author bhoward
 */
public enum Rank
{
   Ace(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7),
   Eight(8), Nine(9), Ten(10), Jack(10), Queen(10), King(10);

   Rank(int value)
   {
      this.value = value;
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

   private final int value;
}
