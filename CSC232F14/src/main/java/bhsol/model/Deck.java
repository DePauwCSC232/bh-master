package bhsol.model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Random;

/**
 * Represent a deck of cards.
 * 
 * @author bhoward
 */
public class Deck implements Iterable<Card>
{
   /**
    * Construct an empty deck of cards.
    */
   public Deck()
   {
      cards = new ArrayList<Card>();
   }

   /**
    * Add a standard set of 52 playing cards to this deck. May be called
    * multiple times to play with multiple decks.
    */
   public void fill()
   {
      for (Suit suit : Suit.values())
      {
         for (Rank rank : Rank.values())
         {
            add(new Card(rank, suit));
         }
      }
   }

   /**
    * Shuffle this deck into a random order, where all orderings are equally
    * likely. Uses the <a
    * href="http://en.wikipedia.org/wiki/Fisher-Yates_shuffle">Fisher-Yates
    * algorithm</a>
    */
   public void shuffle()
   {
      // Alternately, just call Collections.shuffle(cards) ...
      for (int i = cards.size(); i > 0; i--)
      {
         // Choose a card from 0 to i-1
         int j = random.nextInt(i);

         // Swap that card with position i-1
         Card temp = cards.get(i - 1);
         cards.set(i - 1, cards.get(j));
         cards.set(j, temp);
      }
   }

   /**
    * Remove and return the topmost (most recently added) card from this deck.
    * If this deck is empty, throws {@link EmptyStackException}.
    * 
    * @return the former top card from this deck
    */
   public Card deal()
   {
      if (cards.isEmpty())
      {
         throw new EmptyStackException();
      }
      return cards.remove(cards.size() - 1);
   }

   /**
    * Add the given card to the top of this deck.
    * 
    * @param card
    */
   public void add(Card card)
   {
      cards.add(card);
   }

   /**
    * Look at the topmost (most recently added) card of this deck.
    * If this deck is empty, throws {@link EmptyStackException}.
    * 
    * @return the top card
    */
   public Card getTop()
   {
      if (cards.isEmpty())
      {
         throw new EmptyStackException();
      }
      return cards.get(cards.size() - 1);
   }
   
   /**
    * Check whether this deck is empty.
    * 
    * @return true if there are no cards in the deck
    */
   public boolean isEmpty()
   {
      return cards.isEmpty();
   }

   /**
    * Clear out all of the cards from this deck, leaving it empty.
    */
   public void clear()
   {
      cards.clear();
   }
   
   /**
    * Get the number of cards in this deck.
    * 
    * @return the size of the deck
    */
   public int size()
   {
      return cards.size();
   }

   /* (non-Javadoc)
    * @see java.lang.Iterable#iterator()
    */
   public Iterator<Card> iterator()
   {
      return cards.iterator();
   }
   
   private ArrayList<Card> cards;

   private static Random random = new Random();
}
