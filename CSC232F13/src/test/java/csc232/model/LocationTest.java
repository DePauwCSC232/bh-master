package csc232.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class LocationTest
{

   @Test
   public void testGetters()
   {
      Location kitchen = new Location("kitchen",
               "It is full of appliances and utensils, but not much food");

      assertEquals("kitchen", kitchen.getShortName());
      assertEquals("It is full of appliances and utensils, but not much food",
               kitchen.getDescription());
      assertEquals(0, kitchen.getItemCount());
      assertEquals(
               "Location:\n  shortName: kitchen\n  description: It is full of appliances and utensils, but not much food\n  contents: ",
               kitchen.toString());
      assertEquals("", kitchen.listContents());
   }

   @Test
   public void testAddItem()
   {
      Location kitchen = new Location("kitchen",
               "It is full of appliances and utensils, but not much food");
      Item sandwich = new Item("sandwich", "consumable",
               "a peanut-butter and jelly sandwich");
      Item flashlight = new Item("flashlight", "tool",
               "an ordinary flashlight, currently turned off");

      kitchen.addItem(sandwich);
      
      assertEquals("kitchen", kitchen.getShortName());
      assertEquals("It is full of appliances and utensils, but not much food",
               kitchen.getDescription());
      assertEquals(1, kitchen.getItemCount());
      assertEquals(sandwich, kitchen.getItemByIndex(0));
      assertEquals(
               "Location:\n  shortName: kitchen\n  description: It is full of appliances and utensils, but not much food\n  contents: sandwich",
               kitchen.toString());
      assertEquals("sandwich", kitchen.listContents());
      
      kitchen.addItem(flashlight);
      
      assertEquals("kitchen", kitchen.getShortName());
      assertEquals("It is full of appliances and utensils, but not much food",
               kitchen.getDescription());
      assertEquals(2, kitchen.getItemCount());
      assertEquals(sandwich, kitchen.getItemByIndex(0));
      assertEquals(flashlight, kitchen.getItemByIndex(1));
      assertEquals(
               "Location:\n  shortName: kitchen\n  description: It is full of appliances and utensils, but not much food\n  contents: sandwich, flashlight",
               kitchen.toString());
      assertEquals("sandwich, flashlight", kitchen.listContents());
   }

   @Test
   public void testLookup()
   {
      Location kitchen = new Location("kitchen",
               "It is full of appliances and utensils, but not much food");
      Item sandwich = new Item("sandwich", "consumable",
               "a peanut-butter and jelly sandwich");
      Item flashlight = new Item("flashlight", "tool",
               "an ordinary flashlight, currently turned off");

      assertEquals(null, kitchen.lookup("sandwich"));
      assertEquals(null, kitchen.lookup("flashlight"));
      assertEquals(null, kitchen.lookup("screwdriver"));
      
      kitchen.addItem(sandwich);
      
      assertEquals(sandwich, kitchen.lookup("sandwich"));
      assertEquals(null, kitchen.lookup("flashlight"));
      assertEquals(null, kitchen.lookup("screwdriver"));
      
      kitchen.addItem(flashlight);
      
      assertEquals(sandwich, kitchen.lookup("sandwich"));
      assertEquals(flashlight, kitchen.lookup("flashlight"));
      assertEquals(null, kitchen.lookup("screwdriver"));
   }
   
   @Test
   public void testRemoveItem()
   {
      Location kitchen = new Location("kitchen",
               "It is full of appliances and utensils, but not much food");
      Item sandwich = new Item("sandwich", "consumable",
               "a peanut-butter and jelly sandwich");
      Item flashlight = new Item("flashlight", "tool",
               "an ordinary flashlight, currently turned off");

      kitchen.addItem(sandwich);
      kitchen.addItem(flashlight);
      
      kitchen.removeItem(sandwich);
      
      assertEquals(1, kitchen.getItemCount());
      assertEquals(flashlight, kitchen.getItemByIndex(0));
      
      kitchen.removeItem(sandwich); // Second remove has no effect
      
      assertEquals(1, kitchen.getItemCount());
      assertEquals(flashlight, kitchen.getItemByIndex(0));
      
      kitchen.removeItem(flashlight);
      
      assertEquals(0, kitchen.getItemCount());
      
      // Same tests, but remove in the other order
      kitchen.addItem(sandwich);
      kitchen.addItem(flashlight);
      
      kitchen.removeItem(flashlight);
      
      assertEquals(1, kitchen.getItemCount());
      assertEquals(sandwich, kitchen.getItemByIndex(0));
      
      kitchen.removeItem(flashlight); // Second remove has no effect
      
      assertEquals(1, kitchen.getItemCount());
      assertEquals(sandwich, kitchen.getItemByIndex(0));
      
      kitchen.removeItem(sandwich);
      
      assertEquals(0, kitchen.getItemCount());
   }
}
