package bhsol.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.Test;

public class DeckTest
{
   @Test
   public void testFill()
   {
      Deck d = new Deck();

      d.fill();
      assertEquals(52, d.size());

      d.fill();
      assertEquals(104, d.size());
   }

   @Test
   public void testShuffle()
   {
      Deck d = new Deck();

      Card c1 = new Card(Rank.Ace, Suit.Clubs);
      Card c2 = new Card(Rank.Two, Suit.Diamonds);

      d.add(c1);
      d.add(c2);

      d.shuffle();

      Card c3 = d.deal();
      Card c4 = d.deal();

      assertTrue(c3.equals(c1) && c4.equals(c2)
               || c3.equals(c2) && c4.equals(c1));
   }

   @Test
   public void testDeal()
   {
      Deck d = new Deck();

      Card c1 = new Card(Rank.Ace, Suit.Clubs);
      Card c2 = new Card(Rank.Two, Suit.Diamonds);

      d.add(c1);
      d.add(c2);

      Card c3 = d.deal();
      Card c4 = d.deal();

      assertTrue(c3.equals(c2) && c4.equals(c1));

      // Now we expect an exception to be thrown
      try
      {
         d.deal();
         // we shouldn't get here
         fail("The deck should have been empty");
      }
      catch (EmptyStackException e)
      {
      }
   }

   @Test
   public void testAdd()
   {
      Deck d = new Deck();
      d.add(new Card(Rank.King, Suit.Clubs));
      assertEquals(1, d.size());

      d.add(new Card(Rank.Queen, Suit.Diamonds));
      assertEquals(2, d.size());
   }

   @Test
   public void testIsEmpty()
   {
      Deck d = new Deck();
      assertTrue(d.isEmpty());
      d.add(new Card(Rank.King, Suit.Clubs));
      assertFalse(d.isEmpty());

   }

   @Test
   public void testClear()
   {
      Deck d = new Deck();
      d.fill();
      d.clear();
      assertTrue(d.isEmpty());
      assertEquals(0, d.size());
   }

}
