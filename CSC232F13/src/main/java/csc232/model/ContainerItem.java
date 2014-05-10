/**
CSC232 - Spring 2014
A class to represent a container or a location in an adventure game.

@author Brian Howard <bhoward@depauw.edu>
@version 2014-05-09
 */

package csc232.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A <code>ContainerItem</code> is an {@link Item} that can also hold other
 * items. It may also be used as a location.
 */
public class ContainerItem extends Item
{
   public ContainerItem(String shortName, String description)
   {
      super(shortName, "container", description);
      items = new ArrayList<Item>();
   }

   @Override
   public String getDescription()
   {
      String result = super.getDescription();
      if (getItemCount() > 0)
      {
         result = result + "\nIt contains: " + listContents();
      }
      
      return result;
   }

   /**
    * Add an item to this container. Takes the current {@link GameState},
    * in case a subclass wants to modify its behavior based on state.
    * 
    * @param item
    * @param gameState current state, or null if not available
    */
   public void addItem(Item item, GameState gameState)
   {
      items.add(item);
   }

   /**
    * Add an item to this container. Passes a null {@link GameState}, for
    * cases where we don't have one available.
    * 
    * @param item
    */
   public void addItem(Item item)
   {
      addItem(item, null);
   }

   /**
    * Remove an item from this container. If the item is not present, do
    * nothing.
    * 
    * @param item
    *           the {@link Item} to remove
    */
   public void removeItem(Item item)
   {
      items.remove(item);
   }

   /**
    * Retrieve an item from this container by specifying its short name.
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
    * Find out how many items are in this container.
    * 
    * @return the number of items
    */
   public int getItemCount()
   {
      return items.size();
   }

   /**
    * Retrieve an item from this container by specifying an index. Precondition:
    * 0 <= index < getItemCount()
    * 
    * @param index
    * @return the desired item
    */
   public Item getItemByIndex(int index)
   {
      return items.get(index);
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

   private List<Item> items;
}
