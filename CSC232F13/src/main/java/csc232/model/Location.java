/**
CSC232 - Spring 2014
A class to represent a location in an adventure game.

@author Brian Howard <bhoward@depauw.edu>
@version 2014-04-11
 */

package csc232.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>Location</code> class represents a location, such as a room. It has
 * a short name and a description, just like an {@link Item}. It also contains a
 * collection of items.
 */
public class Location
{
   /**
    * Construct a location with the given short name and description, and an
    * empty collection of items.
    * 
    * @param shortName
    * @param description
    */
   public Location(String shortName, String description)
   {
      this.shortName = shortName;
      this.description = description;
      this.items = new ArrayList<Item>();
   }

   /**
    * @return the shortName
    */
   public String getShortName()
   {
      return shortName;
   }

   /**
    * @return the description
    */
   public String getDescription()
   {
      return description;
   }

   /**
    * Add an item to this location.
    * 
    * @param item
    */
   public void addItem(Item item)
   {
      items.add(item);
   }
   
   /**
    * Remove an item from this location. If the item is not present, do nothing.
    * 
    * @param item the {@link Item} to remove
    */
   public void removeItem(Item item)
   {
      items.remove(item);
   }

   /**
    * Retrieve an item from this location by specifying its short name.
    * 
    * @param name
    *           the short name of the desired item
    * @return the item, or null if not found
    */
   public Item lookup(String name)
   {
      for (Item item : items)
      {
         if (item.getShortName().equals(name))
         {
            return item;
         }
      }

      // Not found
      return null;
   }

   /**
    * Find out how many items are at this location.
    * 
    * @return the number of items
    */
   public int getItemCount()
   {
      return items.size();
   }

   /**
    * Retrieve an item from this location by specifying an index.
    * Precondition: 0 <= index < getItemCount()
    * 
    * @param index
    * @return the desired item
    */
   public Item getItemByIndex(int index)
   {
      return items.get(index);
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Location:\n"
               + "  shortName: " + shortName + "\n"
               + "  description: " + description + "\n"
               + "  contents: " + listContents();
   }

   /**
    * Get a comma-separated list of the short names of the contents.
    * 
    * @return the list of the contents
    */
   public String listContents()
   {
      String contents = "";
      for (Item item : items)
      {
         // Put commas _between_ items in the list
         if (!contents.equals(""))
         {
            contents = contents + ", ";
         }
         
         contents = contents + item.getShortName();
      }
      return contents;
   }

   private String shortName;
   private String description;
   private List<Item> items;
}
