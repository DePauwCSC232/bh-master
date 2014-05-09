/**
CSC232 - Spring 2014
A class to represent an item in an adventure game.

@author Brian Howard <bhoward@depauw.edu>
@version 2014-05-09
 */

package csc232.model;

/**
 * The <code>Item</code> class represents a simple item. It has a short name
 * that may be used to refer to the item in commands, as well as a type (such as
 * "food" or "tool") and a longer description. An item is immutable (there are
 * no setters or mutators).
 */
public class Item
{
   /**
    * Construct an <code>Item</code> given its short name, type, and
    * description.
    * 
    * @param shortName
    * @param type
    * @param description
    */
   public Item(String shortName, String type, String description)
   {
      this.shortName = shortName;
      this.type = type;
      this.description = description;
   }

   /**
    * @return the shortName
    */
   public String getShortName()
   {
      return shortName;
   }

   /**
    * @return the type
    */
   public String getType()
   {
      return type;
   }

   /**
    * Get the description of this item, which in subclasses might depend on
    * information in the GameState.
    * 
    * @param gameState
    *           the current GameState, or null if not available
    * @return the description
    */
   public String getDescription(GameState gameState)
   {
      return description;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Item:\n"
               + "  shortName: " + getShortName() + "\n"
               + "  type: " + getType() + "\n"
               + "  description: " + getDescription(null);
   }

   private String shortName;
   private String type;
   private String description;
}
