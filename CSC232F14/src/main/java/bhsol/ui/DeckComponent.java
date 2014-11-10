package bhsol.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import bhsol.model.Card;
import bhsol.model.Deck;

/**
 * A Swing GUI component that displays a {@link Deck} of {@link Card} objects,
 * possibly fanned-out horizontally or vertically, and supports drag-and-drop
 * between multiple decks. A {@link DeckListener} may be added to customize
 * rules for when a card may be dropped on this deck, and to handle clicks.
 * 
 * @author bhoward
 */
@SuppressWarnings("serial")
public class DeckComponent extends JComponent
{
   /**
    * Construct a <code>DeckComponent</code> with a given deck and card images.
    * The deck is squared (<i>i.e.</i>, it is not fanned-out).
    * 
    * @param deck
    * @param images
    */
   public DeckComponent(Deck deck, CardImages images)
   {
      this(deck, images, FAN_NONE);
   }

   /**
    * Construct a <code>DeckComponent</code> with a given deck and card images,
    * and a given direction in which the cards should be fanned.
    * 
    * @param deck
    * @param images
    * @param fanDirection
    *           one of FAN_HORIZONTAL, FAN_VERTICAL, or FAN_NONE
    */
   public DeckComponent(Deck deck, CardImages images, int fanDirection)
   {
      this.deck = deck;
      this.images = images;
      this.fanDirection = fanDirection;

      Image blank = images.getImage(null);
      int width = blank.getWidth(null);
      int height = blank.getHeight(null);
      if ((fanDirection & FAN_HORIZONTAL) != 0)
      {
         width = (int) (width * FAN_EXPANSION_FACTOR);
      }
      if ((fanDirection & FAN_VERTICAL) != 0)
      {
         height = (int) (height * FAN_EXPANSION_FACTOR);
      }
      this.setPreferredSize(new Dimension(width, height));

      initMouseListener();
      initTransferHandler();
   }

   /**
    * Attach a {@link DeckListener} to this DeckComponent. To remove a listener,
    * set it to <code>null</code>.
    * 
    * @param listener
    *           the new DeckListener, or null
    */
   public void setDeckListener(DeckListener listener)
   {
      this.listener = listener;
   }

   /**
    * Look at the top card of the deck without removing it.
    * 
    * @return the current top card from the deck, or null if none
    */
   public Card getTopCard()
   {
      if (!deck.isEmpty())
      {
         return deck.getTop();
      }
      else
      {
         return null;
      }
   }

   /**
    * Remove the top card from the deck. The next card, if there is one, is
    * flipped face-up.
    * 
    * @return the former top card from the deck, or null if none
    */
   public Card removeTopCard()
   {
      if (!deck.isEmpty())
      {
         Card card = deck.deal();
         if (!deck.isEmpty() && !deck.getTop().isFaceUp())
         {
            deck.getTop().flip();
         }
         repaint();
         return card;
      }

      return null;
   }

   /**
    * Add a card to the top of the deck.
    * 
    * @param card
    */
   public void addCard(Card card)
   {
      deck.add(card);
      repaint();
   }

   /**
    * Constants describing the desired fan direction.
    */
   public static final int FAN_NONE = 0, FAN_HORIZONTAL = 1, FAN_VERTICAL = 2;

   protected Image getTopCardImage()
   {
      Card top = getTopCard();
      return images.getImage(top);
   }

   protected void initTransferHandler()
   {
      // Create a transfer handler that allows the top card of one deck to be
      // dragged and dropped onto another deck (unless a listener replies false
      // to checkDrop(card))
      this.setTransferHandler(new TransferHandler()
      {
         public int getSourceActions(JComponent c)
         {
            if (getTopCard() != null)
            {
               return MOVE;
            }
            else
            {
               return 0;
            }
         }

         public Image getDragImage()
         {
            return getTopCardImage();
         }

         // This is called to check whether the currently dragged card may be
         // dropped onto this deck
         public boolean canImport(TransferSupport support)
         {
            Card card = getCard(support.getTransferable());

            if (card.equals(getTopCard()))
            {
               return false;
            }

            if (listener != null
                     && !listener.checkDrop(DeckComponent.this, card))
            {
               return false;
            }

            return true;
         }

         // This is called when the dragged card is dropped onto this deck
         public boolean importData(TransferSupport support)
         {
            Card card = getCard(support.getTransferable());
            addCard(card);
            return true;
         }

         protected Transferable createTransferable(JComponent c)
         {
            Card card = getTopCard();
            return new CardTransferable(card);
         }

         // This is called when a card is dragged away from this deck and
         // dropped on another deck
         protected void exportDone(JComponent source, Transferable data,
                  int action)
         {
            if (action == MOVE)
            {
               removeTopCard();
            }
         }

         private Card getCard(Transferable transfer)
         {
            try
            {
               return (Card) transfer
                        .getTransferData(CardTransferable.cardFlavor);
            }
            catch (UnsupportedFlavorException e)
            {
               e.printStackTrace();
               return null;
            }
            catch (IOException e)
            {
               e.printStackTrace();
               return null;
            }
         }
      });
   }

   protected void initMouseListener()
   {
      this.addMouseListener(new MouseAdapter()
      {
         @Override
         public void mouseClicked(MouseEvent e)
         {
            if (listener != null)
            {
               listener.handleClick(DeckComponent.this);
               repaint();
            }
         }
      });

      this.addMouseMotionListener(new MouseMotionAdapter()
      {
         @Override
         public void mouseDragged(MouseEvent e)
         {
            TransferHandler handler = getTransferHandler();
            handler.exportAsDrag(DeckComponent.this, e, TransferHandler.MOVE);
         }
      });
   }

   protected void paintComponent(Graphics g)
   {
      super.paintComponent(g);

      if (fanDirection != 0)
      {
         Image blank = images.getImage(null);
         g.drawImage(blank, 0, 0, null);

         int x = 0, y = 0;
         int dx = 0, dy = 0;
         if ((fanDirection & FAN_HORIZONTAL) != 0)
         {
            int cardWidth = blank.getWidth(null);
            dx = cardWidth / (MAX_UNSQUEEZED_CARDS - 1);
            if (dx * (deck.size() - 1) > cardWidth)
            {
               dx = cardWidth / (deck.size() - 1);
            }
         }
         if ((fanDirection & FAN_VERTICAL) != 0)
         {
            int cardHeight = blank.getHeight(null);
            dy = cardHeight / (MAX_UNSQUEEZED_CARDS - 1);
            if (dy * (deck.size() - 1) > cardHeight)
            {
               dy = cardHeight / (deck.size() - 1);
            }
         }
         for (Card c : deck)
         {
            g.drawImage(images.getImage(c), x, y, null);
            x += dx;
            y += dy;
         }
      }
      else
      {
         g.drawImage(getTopCardImage(), 0, 0, null);
      }
   }

   private Deck deck;
   private CardImages images;
   private int fanDirection;

   private DeckListener listener;
   
   // Customize these to adjust how cards are packed when fanned
   private static final double FAN_EXPANSION_FACTOR = 2.0;
   private static final int MAX_UNSQUEEZED_CARDS = 7;
}
