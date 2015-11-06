package bhsol.ui;

import java.awt.Image;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import bhsol.model.Card;
import bhsol.model.Rank;
import bhsol.model.Suit;

/**
 * Manage a set of playing card images loaded from a directory of image files.
 * 
 * @author bhoward
 */
public class CardImages
{
   /**
    * Load a set of card images from the given directory. Assumes that the
    * directory contains a series of image files, identified by having a suffix
    * such as ".png". The main part of each file name should be either a card
    * abbreviation, such as "AC", "2C", ..., or one of the special names
    * "blank", "back", "back2", "joker", or "joker2".
    * 
    * @param imageDirectory
    */
   public CardImages(File imageDirectory)
   {
      try
      {
         File[] files = imageDirectory.listFiles(new FileFilter()
         {
            String[] suffixes = ImageIO.getReaderFileSuffixes();

            public boolean accept(File pathname)
            {
               for (String suffix : suffixes)
               {
                  if (pathname.getName().endsWith(suffix))
                  {
                     return true;
                  }
               }
               return false;
            }
         });

         for (File file : files)
         {
            Image image = ImageIO.read(file);
            int suffixIndex = file.getName().indexOf('.');
            String name = file.getName().substring(0, suffixIndex);
            images.put(name, image);
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
         System.exit(1); // harsh...
      }
   }

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
      for (Rank r : Rank.values())
      {
         for (Suit s : Suit.values())
         {
            names.add("" + r.getAbbrev() + s.getAbbrev());
         }
      }

      try
      {
         for (String name : names)
         {
            String resourceName = resourcePrefix + name + suffix;
            URL url = CardImages.class.getResource(resourceName);
            Image image = ImageIO.read(url);
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
         return useAlternate ? images.get("back2") : images.get("back");
      }
   }

   /**
    * Switch between standard ("back") and alternate ("back2") back designs.
    * 
    * @param useAlternate
    *           true for alternate, false for standard
    */
   public void setAlternate(boolean useAlternate)
   {
      this.useAlternate = useAlternate;
   }

   private Map<String, Image> images = new HashMap<String, Image>();
   private boolean useAlternate = false;
}
