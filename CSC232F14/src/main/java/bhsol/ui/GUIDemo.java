package bhsol.ui;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bhsol.model.Card;
import bhsol.model.Deck;

public class GUIDemo
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Demo");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame.setSize(400, 300);
      
      Deck deckA = new Deck();
      deckA.fill();
      deckA.getTop().flip();
      
      Deck deckB = new Deck();
      deckB.add(deckA.deal());
      
      Deck deckC = new Deck();
      
      deckA.getTop().flip();
      
      File imageDirectory = new File("src/main/resources/cards");
      CardImages images = new CardImages(imageDirectory);
      
      final DeckComponent dcA = new DeckComponent(deckA, images);
      final DeckComponent dcB = new DeckComponent(deckB, images, DeckComponent.FAN_VERTICAL);
      final DeckComponent dcC = new DeckComponent(deckC, images, DeckComponent.FAN_HORIZONTAL);
      
      dcA.setDeckListener(new DeckListener()
      {
         public void handleClick(DeckComponent deckComponent)
         {
            // When clicked, deal a card to deck C
            Card card = deckComponent.removeTopCard();
            
            Card newTop = deckComponent.getTopCard();
            if (newTop != null && !newTop.isFaceUp()) {
               deckComponent.flipTopCard();
            }
            
            if (card != null)
            {
               dcC.addCard(card);
            }
         }
         
         public boolean checkDrop(DeckComponent deckComponent, Card card)
         {
            // Only allow drops of the same suit as the top card
            return card.getSuit().equals(deckComponent.getTopCard().getSuit());
         }
      });
      
      dcA.setDraggable(true);
      dcB.setDraggable(true);
      dcC.setDraggable(false);
            
      JPanel panel = new JPanel();
      panel.add(dcA);
      panel.add(dcB);
      panel.add(dcC);
      
      frame.add(panel);
      frame.setVisible(true);
   }
}
