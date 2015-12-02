package bhsol.ui;

import java.awt.event.MouseEvent;

import bhsol.model.Deck;

/**
 * A PacketItem is a FanItem that is being dragged from one place to another; it
 * knows where it came from.
 * 
 * @author bhoward
 */
public class PacketItem extends FanItem
{
   public PacketItem(FanItem origin, Deck deck, CardImages images)
   {
      super(deck, images);
      this.origin = origin;
   }

   @Override
   public void endDrag(Item target, MouseEvent event)
   {
      FanItem fan = (FanItem) target;
      for (int i = 0; i < deck.size(); i++)
      {
         fan.addCard(deck.get(i));
      }
   }

   @Override
   public void cancelDrag(MouseEvent event)
   {
      for (int i = 0; i < deck.size(); i++)
      {
         origin.addCard(deck.get(i));
      }
   }

   private FanItem origin;
}
