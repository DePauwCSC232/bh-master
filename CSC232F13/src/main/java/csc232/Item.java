/**
   CSC232A - Fall 2013
   A class to represent an item in a text adventure.

   @author Brian Howard <bhoward@depauw.edu>
   @author Scott Thede <sthede@depauw.edu>
   @version 2013-10-04
 */

package csc232;

/**
 * The <code>Item</code> class represents an item in a text adventure. It has a
 * short name, a type, and a description; all are strings, and none are mutable.
 */
public class Item
{
   /**
    * Construct an <code>Item</code> object given a short name, type, and
    * description.
    * 
    * @param shortName
    *           the name by which the item may be referenced
    * @param type
    *           the category of this item
    * @param description
    *           a longer text describing the item
    */
   public Item(String shortName, String type, String description)
   {
      this.shortName = shortName;
      this.type = type;
      this.description = description;
   }

   /**
    * Override the default <code>toString</code> method to return the address
    * formatted into two lines.
    */
   public String toString()
   {
      return "Item:\n" + "  shortName: " + shortName + "\n" + "  type: " + type
               + "\n" + "  description: " + description + "\n";
   }

   /**
    * Get the short name from this item.
    */
   String getShortName()
   {
      return shortName;
   }

   /**
    * Get the type from this item.
    */
   String getType()
   {
      return type;
   }

   /**
    * Get the description from this item.
    */
   String getDescription()
   {
      return description;
   }

   private String shortName;
   private String type;
   private String description;
}
