package bhsol.ui;

import bhsol.model.Card;

/**
 * Implement this interface and attach the listener to a {@link DeckComponent}
 * to customize its behavior.
 * 
 * @author bhoward
 */
public interface DeckListener
{
   /**
    * This is called to check whether a card may be dropped on the deck.
    * 
    * @param deckComponent
    *           the deck component firing the event
    * @param card
    *           the candidate card to be dropped
    * @return true if it is a legal drop
    */
   boolean checkDrop(DeckComponent deckComponent, Card card);

   /**
    * This is called when the deck is clicked on. A typical action is to remove
    * the top card ({@link deckComponent#removeTopCard()}); if it is not null,
    * then add it to another DeckComponent.
    * 
    * @param deckComponent
    *           the deck component firing the event
    */
   void handleClick(DeckComponent deckComponent);
}
