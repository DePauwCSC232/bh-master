/**
   CSC232A - Fall 2013
   Test suite for <code>Item</code> objects.

   @author Brian Howard <bhoward@depauw.edu>
   @author Scott Thede <sthede@depauw.edu>
   @version 2013-10-04
*/

package csc232;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
   A simple JUnit test suite for the {@link Item} class. Most of these
   tests are too trivial to be interesting; this is only a demonstration.
*/
public class ItemTest
{
   /**
      Initializes the <code>item</code> field used in the rest of the suite.
      
      @throws Exception
   */
   @Before
   public void setUp() throws Exception
   {
      item = new Item("screwdriver", "tool", "a flathead screwdriver");
   }

   @Test
   public void testToString()
   {
      assertEquals("Item:\n  shortName: screwdriver\n  type: tool\n  description: a flathead screwdriver\n", item.toString());
   }

   @Test
   public void testGetShortName()
   {
      assertEquals("screwdriver", item.getShortName());
   }

   @Test
   public void testGetType()
   {
      assertEquals("tool", item.getType());
   }

   @Test
   public void testGetDescription()
   {
      assertEquals("a flathead screwdriver", item.getDescription());
   }

   private Item item;
}
