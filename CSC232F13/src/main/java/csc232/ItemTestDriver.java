/**
   CSC232A - Fall 2013
   A simple program to test the <code>Item</code> class.
   
   @author Brian Howard <bhoward@depauw.edu>
   @author Scott Thede <sthede@depauw.edu>
   @version 2013-10-04
*/

package csc232;

/**
   Create two {@link Item} objects and print them out.
*/
public class ItemTestDriver
{
   public static void main(String[] args)
   {
      Item item1 = new Item("sandwich", "consumable",
               "a peanut-butter and jelly sandwich");
      Item item2 = new Item("screwdriver", "tool", "a flathead screwdriver");

      System.out.print(item1);
      System.out.println();
      System.out.print(item2);
   }
}
