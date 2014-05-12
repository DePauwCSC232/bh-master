package csc232.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class SwitchItemTest
{
   @Test
   public void testSwitchItem()
   {
      Item trigger = new Item("trigger", "", "");
      SwitchItem test = new SwitchItem("a", "b", trigger, null, false, null, null, "c", 0);
      Item foo = new Item("foo", "", "");
      GameState gameState = mock(GameState.class);
      
      // a SwitchItem behaves like an ordinary ContainerItem:
      assertEquals("a", test.getShortName());
      assertEquals("b", test.getDescription());
      assertEquals("container", test.getType());
      assertEquals(0, test.getItemCount());
      
      test.addItem(foo, gameState);
      assertEquals("b\nIt contains: foo", test.getDescription());
      assertEquals(1, test.getItemCount());
      assertEquals(foo, test.lookup("foo"));
      assertEquals(null, test.lookup("bar"));
      assertEquals(foo, test.getItemByIndex(0));
   }
   
   @Test
   public void testAddItem1()
   {
      Item trigger = new Item("trigger", "", "");
      SwitchItem test = new SwitchItem("a", "b", trigger, null, false, null, null, "c", 0);
      Item foo = new Item("foo", "", "");
      GameState gameState = mock(GameState.class);
      
      // Inserting the trigger item changes the description:
      test.addItem(trigger, gameState);
      assertEquals("c", test.getDescription());
      test.addItem(foo, gameState);
      assertEquals("c\nIt contains: foo", test.getDescription());
      
      // When hidden and direction are null, keep is false, and points is 0,
      // nothing else changes:
      assertEquals(null, test.lookup("trigger"));
      assertEquals(null, test.lookup("bar"));
      verify(gameState, never()).putNeighbor(any(ContainerItem.class), any(String.class), any(ContainerItem.class));
      verify(gameState).addPoints(0);
   }
   
   @Test
   public void testAddItem2()
   {
      Item trigger = new Item("trigger", "", "");
      Item bar = new Item("bar", "", "");
      ContainerItem baz = new ContainerItem("baz", "");
      SwitchItem test = new SwitchItem("a", "b", trigger, bar, true, "north", baz, "c", 1);
      Item foo = new Item("foo", "", "");
      GameState gameState = mock(GameState.class);
      
      // Inserting the trigger item changes the description:
      test.addItem(trigger, gameState);
      assertEquals("c\nIt contains: bar, trigger", test.getDescription());
      test.addItem(foo, gameState);
      assertEquals("c\nIt contains: bar, trigger, foo", test.getDescription());
      
      // Trigger object is added to test because keep is true:
      assertEquals(trigger, test.lookup("trigger"));
      
      // Hidden item (bar) is also added to test:
      assertEquals(bar, test.lookup("bar"));
      
      // Map of test's neighbors has changed -- baz is to the north:
      verify(gameState).putNeighbor(test, "north", baz);
      
      // Points were increased:
      verify(gameState).addPoints(1);
   }
}
