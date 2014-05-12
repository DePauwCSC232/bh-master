package csc232.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameMapTest
{
   @Test
   public void test()
   {
      GameMap map = new GameMap();
      ContainerItem foo = new ContainerItem("foo", "");
      ContainerItem bar = new ContainerItem("bar", "");
      ContainerItem baz = new ContainerItem("baz", "");
      
      map.addNeighbor(foo, "a", bar);
      assertEquals(bar, map.getNeighbor(foo, "a"));
      assertEquals(null, map.getNeighbor(foo, "b"));
      assertEquals("a", map.listNeighbors(foo));
      assertEquals("", map.listNeighbors(bar));
      
      map.addNeighbor(foo, "b", baz);
      assertEquals(bar, map.getNeighbor(foo, "a"));
      assertEquals(baz, map.getNeighbor(foo, "b"));
      assertTrue(map.listNeighbors(foo).equals("a, b")
               || map.listNeighbors(foo).equals("b, a"));
   }
}
