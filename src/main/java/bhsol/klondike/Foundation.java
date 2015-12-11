package bhsol.klondike;

import java.awt.event.MouseEvent;

import bhsol.model.Card;
import bhsol.model.Deck;
import bhsol.model.Rank;
import bhsol.ui.CardImages;
import bhsol.ui.CardItem;
import bhsol.ui.DeckItem;
import bhsol.ui.Item;
import bhsol.ui.PacketItem;

public class Foundation extends DeckItem
{
   public Foundation(CardImages images)
   {
      super(new Deck(), images);
   }

   @Override
   public boolean canDrop(Item item, MouseEvent event)
   {
      Card testCard = null;
      if (item instanceof CardItem)
      {
         CardItem cardItem = (CardItem) item;
         testCard = cardItem.getCard();
      }
      else if (item instanceof PacketItem)
      {
         PacketItem packet = (PacketItem) item;
         if (packet.size() > 1)
         {
            return false;
         }
         testCard = packet.getBottom();
      }

      if (isEmpty())
      {
         return testCard.getRank() == Rank.Ace;
      }

      Card topCard = getTop();

      boolean sameSuit = topCard.getSuit() == testCard.getSuit();
      boolean adjacentRank = topCard.getRank().getValue() + 1 == testCard
               .getRank().getValue();
      return sameSuit && adjacentRank;
   }

   @Override
   public void handleDrop(Item item, MouseEvent event)
   {
      if (item instanceof PacketItem)
      {
         PacketItem packet = (PacketItem) item;
         Tableau tab = (Tableau) packet.getOrigin();
         tab.flipIfExposed();
      }
      
      Game.checkForWin();
   }
}
