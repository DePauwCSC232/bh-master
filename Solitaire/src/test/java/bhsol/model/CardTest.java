package bhsol.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CardTest
{
   @Test
   public void testGetRank()
   {
      Card c = new Card(Rank.Ace, Suit.Spades);
      assertEquals(Rank.Ace, c.getRank());
   }

   @Test
   public void testGetSuit()
   {
      Card c = new Card(Rank.Ace, Suit.Spades);
      assertEquals(Suit.Spades, c.getSuit());
   }

   @Test
   public void testIsFaceUp()
   {
      Card c1 = new Card(Rank.Ace, Suit.Spades);
      Card c2 = new Card(Rank.Two, Suit.Diamonds, false);
      Card c3 = new Card(Rank.Three, Suit.Hearts, true);
      assertFalse(c1.isFaceUp());
      assertFalse(c2.isFaceUp());
      assertTrue(c3.isFaceUp());
   }

   @Test
   public void testFlip()
   {
      Card c = new Card(Rank.Ace, Suit.Spades);
      assertFalse(c.isFaceUp());
      c.flip();
      assertTrue(c.isFaceUp());
      c.flip();
      assertFalse(c.isFaceUp());
   }

   @Test
   public void testToString()
   {
      Card c = new Card(Rank.Ace, Suit.Spades);
      assertEquals("Card [rank=Ace, suit=Spades, faceUp=false]", c.toString());
   }
}
