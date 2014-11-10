package bhsol.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import bhsol.model.Card;

/**
 * A {@link Transferable} class for playing cards, to support drag-and-drop.
 * 
 * @author bhoward
 */
public class CardTransferable implements Transferable
{
   public CardTransferable(Card card)
   {
      this.card = card;
   }

   public DataFlavor[] getTransferDataFlavors()
   {
      return new DataFlavor[] { cardFlavor };
   }

   public boolean isDataFlavorSupported(DataFlavor flavor)
   {
      return flavor == cardFlavor;
   }

   public Object getTransferData(DataFlavor flavor)
   {
      return card;
   }

   static
   {
      String cardType = DataFlavor.javaJVMLocalObjectMimeType + ";class="
               + Card.class.getName();
      DataFlavor flavor = null;
      try
      {
         flavor = new DataFlavor(cardType);
      }
      catch (ClassNotFoundException e)
      {
         e.printStackTrace();
         System.exit(1); // this shouldn't happen...
      }
      cardFlavor = flavor;
   }

   private Card card;

   public static final DataFlavor cardFlavor;
}
