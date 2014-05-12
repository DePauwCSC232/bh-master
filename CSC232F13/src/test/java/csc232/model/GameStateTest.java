package csc232.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameStateTest
{
   @Test
   public void testGetters()
   {
      GameState gameState = new GameState();
      assertEquals("cottage", gameState.getLocation().getShortName());
      assertEquals("You are standing in a small cottage.\nIt contains: pole",
               gameState.getLocationDescription());
      assertEquals("cottage [0 moves; 0/100 points]", gameState.getPrompt());
      assertEquals("out", gameState.listNeighbors());
      assertEquals("lamp", gameState.listInventory());
      assertEquals("lamp", gameState.lookupInventory("lamp").getShortName());
      assertEquals(null, gameState.lookupInventory("pole"));
      assertEquals(null, gameState.lookupInventory("fish"));
      assertEquals("lamp", gameState.findItem("lamp").getShortName());
      assertEquals("pole", gameState.findItem("pole").getShortName());
      assertEquals(null, gameState.findItem("fish"));
      assertFalse(gameState.reachedGoal());
      
      // Note: one disadvantage of testing the specific game coded into
      // the GameState is that not all of the features are used -- in
      // particular, there are no ContainerItems lying around the map,
      // so we can't test gameState.findContainerItem(name) properly

      gameState.addMove();
      assertEquals("cottage [1 moves; 0/100 points]", gameState.getPrompt());
      assertFalse(gameState.reachedGoal());

      gameState.addPoints(40);
      assertEquals("cottage [1 moves; 40/100 points]", gameState.getPrompt());
      assertFalse(gameState.reachedGoal());

      gameState.addPoints(60);
      assertEquals("cottage [1 moves; 100/100 points]", gameState.getPrompt());
      assertTrue(gameState.reachedGoal());
   }

   @Test
   public void testInventory()
   {
      GameState gameState = new GameState();
      Item fish = new Item("fish", "", "");
      gameState.addInventoryItem(fish);

      assertEquals("lamp, fish", gameState.listInventory());
      assertEquals("lamp", gameState.lookupInventory("lamp").getShortName());
      assertEquals(null, gameState.lookupInventory("pole"));
      assertEquals(fish, gameState.lookupInventory("fish"));
      assertEquals("lamp", gameState.findItem("lamp").getShortName());
      assertEquals("pole", gameState.findItem("pole").getShortName());
      assertEquals(fish, gameState.findItem("fish"));

      Item lamp = gameState.lookupInventory("lamp");
      gameState.removeInventoryItem(lamp);

      assertEquals("fish", gameState.listInventory());
      assertEquals(null, gameState.lookupInventory("lamp"));
      assertEquals(null, gameState.lookupInventory("pole"));
      assertEquals(fish, gameState.lookupInventory("fish"));
      assertEquals(null, gameState.findItem("lamp"));
      assertEquals("pole", gameState.findItem("pole").getShortName());
      assertEquals(fish, gameState.findItem("fish"));

      gameState.getLocation().addItem(lamp);

      assertEquals("fish", gameState.listInventory());
      assertEquals(null, gameState.lookupInventory("lamp"));
      assertEquals(null, gameState.lookupInventory("pole"));
      assertEquals(fish, gameState.lookupInventory("fish"));
      assertEquals(lamp, gameState.findItem("lamp"));
      assertEquals("pole", gameState.findItem("pole").getShortName());
      assertEquals(fish, gameState.findItem("fish"));
   }

   @Test
   public void testMove()
   {
      GameState gameState = new GameState();

      assertFalse(gameState.move("a"));
      assertEquals("cottage", gameState.getLocation().getShortName());

      assertTrue(gameState.move("out"));
      assertEquals("garden", gameState.getLocation().getShortName());
   }

   @Test
   public void testPutNeighbor()
   {
      GameState gameState = new GameState();
      ContainerItem foo = new ContainerItem("foo", "");
      
      gameState.putNeighbor(gameState.getLocation(), "a", foo);
      assertTrue(gameState.listNeighbors().equals("a, out")
               || gameState.listNeighbors().equals("out, a"));
      
      assertTrue(gameState.move("a"));
      assertEquals(foo, gameState.getLocation());
   }
}
