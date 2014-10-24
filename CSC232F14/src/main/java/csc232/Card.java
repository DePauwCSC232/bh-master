package csc232;

/**
 * Represent a card from an ordinary 52-card deck, including a rank, a suit, and
 * whether the card is face-up or face-down. The rank and suit are immutable,
 * but the face-up/face-down status may be changed.
 * 
 * @author bhoward
 */
public class Card implements Comparable<Card>
{
   /**
    * Construct a card with the given rank and suit; it is initially face-down.
    * 
    * @param rank
    * @param suit
    */
   public Card(Rank rank, Suit suit)
   {
      this.rank = rank;
      this.suit = suit;
      this.faceUp = false;
   }

   /**
    * Construct a card with the given rank, suit, and face-up status.
    * 
    * @param rank
    * @param suit
    * @param faceUp true if the card is initially face-up
    */
   public Card(Rank rank, Suit suit, boolean faceUp)
   {
      this.rank = rank;
      this.suit = suit;
      this.faceUp = faceUp;
   }

   public Rank getRank()
   {
      return rank;
   }

   public Suit getSuit()
   {
      return suit;
   }

   public boolean isFaceUp()
   {
      return faceUp;
   }

   /**
    * Change the state (face-up/face-down) by flipping the card over.
    */
   public void flip()
   {
      faceUp = !faceUp;
   }

   @Override
   public String toString()
   {
      return "Card [rank=" + rank + ", suit=" + suit + ", faceUp=" + faceUp
               + "]";
   }

   public int compareTo(Card other)
   {
      // Default comparison is by suit (alphabetically), then by rank (ace to
      // king) within each suit
      int result = suit.compareTo(other.suit);
      if (result == 0)
      {
         result = rank.compareTo(other.rank);
      }

      return result;
   }

   private Rank rank;
   private Suit suit;
   private boolean faceUp;
}
