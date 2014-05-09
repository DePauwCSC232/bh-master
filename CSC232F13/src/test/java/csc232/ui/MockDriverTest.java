/**
CSC232 - Spring 2014
A unit test for a simple driver for an adventure game. (Homework #5)

@author Brian Howard <bhoward@depauw.edu>
@version 2014-05-09
 */

package csc232.ui;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InOrder;

import acm.io.IOModel;

/**
 * Unit test for the {@link Driver} class, using Mockito method.
 */
public class MockDriverTest
{
   @Test
   public void testLong()
   {
      IOModel io = mock(IOModel.class);
      when(io.readLine("kitchen> "))
               .thenReturn("look")
               .thenReturn("examine")
               .thenReturn("examine sandwich")
               .thenReturn("examine flashlight")
               .thenReturn("examine screwdriver")
               .thenReturn("eat sandwich")
               .thenReturn("quit");
      
      Driver driver = new Driver(io);
      driver.run();
      
      InOrder inOrder = inOrder(io);
      inOrder.verify(io).println("It is full of appliances and utensils, but not much food\nIt contains: sandwich, cheesecake");
      inOrder.verify(io).println("No item specified");
      inOrder.verify(io).println("a peanut-butter and jelly sandwich");
      inOrder.verify(io).println("There is no such item here");
      inOrder.verify(io).println("a flathead screwdriver");
      inOrder.verify(io).println("I don't know how to do that");
      inOrder.verify(io).println("Goodbye!");
   }

   @Test
   public void testShort()
   {
      IOModel io = mock(IOModel.class);
      when(io.readLine("kitchen> "))
               .thenReturn("l")
               .thenReturn("x")
               .thenReturn("x sandwich")
               .thenReturn("x flashlight")
               .thenReturn("x screwdriver")
               .thenReturn("e sandwich")
               .thenReturn("q");
      
      Driver driver = new Driver(io);
      driver.run();
      
      InOrder inOrder = inOrder(io);
      inOrder.verify(io).println("It is full of appliances and utensils, but not much food\nIt contains: sandwich, cheesecake");
      inOrder.verify(io).println("No item specified");
      inOrder.verify(io).println("a peanut-butter and jelly sandwich");
      inOrder.verify(io).println("There is no such item here");
      inOrder.verify(io).println("a flathead screwdriver");
      inOrder.verify(io).println("I don't know how to do that");
      inOrder.verify(io).println("Goodbye!");
   }
}
