package csc232.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest
{

   @Test
   public void testOneItem()
   {
      Item sandwich = new Item("sandwich", "consumable",
               "a peanut-butter and jelly sandwich");
      
      assertEquals("sandwich", sandwich.getShortName());
      assertEquals("consumable", sandwich.getType());
      assertEquals("a peanut-butter and jelly sandwich", sandwich.getDescription());
      assertEquals("Item:\n  shortName: sandwich\n  type: consumable\n  description: a peanut-butter and jelly sandwich", sandwich.toString());
   }

   @Test
   public void testTwoItems()
   {
      Item sandwich = new Item("sandwich", "consumable",
               "a peanut-butter and jelly sandwich");
      Item flashlight = new Item("flashlight", "tool",
               "an ordinary flashlight, currently turned off");
      
      assertEquals("sandwich", sandwich.getShortName());
      assertEquals("consumable", sandwich.getType());
      assertEquals("a peanut-butter and jelly sandwich", sandwich.getDescription());
      assertEquals("Item:\n  shortName: sandwich\n  type: consumable\n  description: a peanut-butter and jelly sandwich", sandwich.toString());

      assertEquals("flashlight", flashlight.getShortName());
      assertEquals("tool", flashlight.getType());
      assertEquals("an ordinary flashlight, currently turned off", flashlight.getDescription());
      assertEquals("Item:\n  shortName: flashlight\n  type: tool\n  description: an ordinary flashlight, currently turned off", flashlight.toString());
   }

}
