package bhsol.ui;

import java.awt.event.MouseEvent;

import bhsol.model.Deck;

/**
 * A PacketItem is a FanItem that is being dragged from one place to another; it
 * knows where it came from. The deck will not be empty.
 * 
 * @author bhoward
 */
public class PacketItem extends FanItem
{
   public PacketItem(FanItem origin, Deck deck, CardImages images)
   {
      this(origin, deck, images, false);
   }

   public PacketItem(FanItem origin, Deck deck, CardImages images,
            boolean isVertical)
   {
      super(deck, images, isVertical);
      this.origin = origin;
   }

   @Override
   public void endDrag(Item target, MouseEvent event)
   {
      if (target instanceof DeckItem)
      {
         DeckItem deckItem = (DeckItem) target;
         for (int i = 0; i < size(); i++)
         {
            deckItem.addCard(getCard(i));
         }
      }
   }

   @Override
   public void cancelDrag(MouseEvent event)
   {
      for (int i = 0; i < size(); i++)
      {
         origin.addCard(getCard(i));
      }
   }

   public FanItem getOrigin()
   {
      return origin;
   }

   private FanItem origin;
}
