package bhsol.klondike;

import java.awt.event.MouseEvent;

import bhsol.model.Card;
import bhsol.model.Deck;
import bhsol.model.Rank;
import bhsol.ui.CardImages;
import bhsol.ui.CardItem;
import bhsol.ui.FanItem;
import bhsol.ui.Item;
import bhsol.ui.PacketItem;

public class Tableau extends FanItem
{
   public Tableau(CardImages images, KFrame frame)
   {
      super(new Deck(), images, true);
      this.frame = frame;
   }

   @Override
   public void handleClick(MouseEvent event)
   {
      if (!isEmpty())
      {
         frame.tryFoundation(this);
      }
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
         testCard = packet.getBottom();
      }

      if (isEmpty())
      {
         return testCard.getRank() == Rank.King;
      }

      Card topCard = getTop();

      boolean differentColor = topCard.getSuit().isRed() != testCard.getSuit()
               .isRed();
      boolean adjacentRank = topCard.getRank().getValue() - 1 == testCard
               .getRank().getValue();
      return differentColor && adjacentRank;
   }

   @Override
   public void handleDrop(Item item, MouseEvent event)
   {
      if (item instanceof PacketItem)
      {
         PacketItem packet = (PacketItem) item;
         Tableau tab = (Tableau) packet.getOrigin();
         if (!tab.isEmpty())
         {
            Card top = tab.getTop();
            if (!top.isFaceUp())
            {
               top.flip();
            }
         }
      }
   }

   @Override
   public boolean canDrag(MouseEvent event)
   {
      if (isEmpty())
      {
         return false;
      }

      int n = identifyCard(event);

      return getCard(n).isFaceUp();
   }

   public void flipIfExposed()
   {
      if (!isEmpty())
      {
         Card top = getTop();
         if (!top.isFaceUp())
         {
            top.flip();
         }
      }
   }
   
   private KFrame frame;
}
