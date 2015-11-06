package bhsol.ui;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import bhsol.model.Card;

/**
 * Manage a set of playing card images loaded from a directory of image files.
 * 
 * @author bhoward
 */
public class CardImages
{
   /**
    * Load a set of card images from resources relative to the class loader.
    * Each resource name is composed of a resourcePrefix, a main part, and a
    * suffix. The main part of each name will be either a card abbreviation,
    * such as "AC", "2C", ..., or one of the special names "blank", "back",
    * "back2", "joker", or "joker2". The resourcePrefix might be something like
    * "/cards/", while the suffix will be something like ".png".
    * 
    * @param resourcePrefix
    * @param suffix
    */
   public CardImages(String resourcePrefix, String suffix)
   {
      List<String> names = new ArrayList<String>();
      names.add("blank");
      names.add("back");
      names.add("back2");
      names.add("joker");
      names.add("joker2");

      char[] ranks = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J',
         'Q', 'K' };
      char[] suits = { 'C', 'D', 'H', 'S' };
      for (char rank : ranks)
      {
         for (char suit : suits)
         {
            names.add("" + rank + suit);
         }
      }

      images = new HashMap<String, Image>();
      try
      {
         for (String name : names)
         {
            String resourceName = resourcePrefix + name + suffix;
            Image image = ImageIO
                     .read(CardImages.class.getResource(resourceName));
            images.put(name, image);
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.exit(1);
      }
   }

   /**
    * Retrieve the appropriate image for the given playing card. Uses the
    * {@link Card#getAbbrev()} method to select a face-up card. If the specified
    * card is null, returns a blank card image.
    * 
    * @param card
    *           the desired playing card, or null
    * @return the appropriate Image
    */
   public Image getImage(Card card)
   {
      if (card == null)
      {
         return images.get("blank");
      }
      else if (card.isFaceUp())
      {
         return images.get(card.getAbbrev());
      }
      else
      {
         return images.get("back");
      }
   }

   private Map<String, Image> images;
}
