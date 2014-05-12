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
      when(io.readLine("cottage [0 moves; 0/100 points]> "))
         .thenReturn("look")
         .thenReturn("inventory")
         .thenReturn("examine")
         .thenReturn("examine pole")
         .thenReturn("examine rose")
         .thenReturn("break pole")
         .thenReturn("take pole");
      when(io.readLine("cottage [1 moves; 0/100 points]> "))
         .thenReturn("examine pole")
         .thenReturn("take fish")
         .thenReturn("look")
         .thenReturn("go north")
         .thenReturn("go out");
      when(io.readLine("garden [2 moves; 0/100 points]> "))
         .thenReturn("look")
         .thenReturn("take rose");
      when(io.readLine("garden [3 moves; 0/100 points]> "))
         .thenReturn("inventory")
         .thenReturn("go south");
      when(io.readLine("pond [4 moves; 0/100 points]> "))
         .thenReturn("look")
         .thenReturn("use pole");
      when(io.readLine("pond [5 moves; 10/100 points]> "))
         .thenReturn("look")
         .thenReturn("take fish");
      when(io.readLine("pond [6 moves; 10/100 points]> "))
         .thenReturn("put rose");
      when(io.readLine("pond [7 moves; 10/100 points]> "))
         .thenReturn("look")
         .thenReturn("inventory")
         .thenReturn("put fish in rose")
         .thenReturn("look")
         .thenReturn("inventory")
         .thenReturn("take fish from rose")
         .thenReturn("go north");
      when(io.readLine("garden [8 moves; 10/100 points]> "))
         .thenReturn("look")
         .thenReturn("quit");
      
      Driver driver = new Driver(io);
      driver.run();
      
      InOrder inOrder = inOrder(io);
      inOrder.verify(io).println("You are standing in a small cottage.\nIt contains: pole");
      inOrder.verify(io).println("Exits are: out");
      inOrder.verify(io).println("You have: lamp, apple");
      inOrder.verify(io).println("No item specified");
      inOrder.verify(io).println("a simple fishing pole");
      inOrder.verify(io).println("There is no such item here");
      inOrder.verify(io).println("I don't know how to do that");
      inOrder.verify(io).println("Taken");
      inOrder.verify(io).println("a simple fishing pole");
      inOrder.verify(io).println("There is no such item here");
      inOrder.verify(io).println("You are standing in a small cottage.");
      inOrder.verify(io).println("Exits are: out");
      inOrder.verify(io).println("You can't go that way.");
      inOrder.verify(io).println("OK");
      inOrder.verify(io).println("You are standing on a lush garden path. There is a cottage here.\nIt contains: rose");
      inOrder.verify(io).println("Exits are: south, north, in");
      inOrder.verify(io).println("Taken");
      inOrder.verify(io).println("You have: lamp, apple, pole, rose");
      inOrder.verify(io).println("OK");
      inOrder.verify(io).println("You are at the edge of a small fishing pond.");
      inOrder.verify(io).println("Exits are: north");
      inOrder.verify(io).println("Done");
      inOrder.verify(io).println("You are at the edge of a small fishing pond.\nIt contains: fish, pole");
      inOrder.verify(io).println("Exits are: north");
      inOrder.verify(io).println("Taken");
      inOrder.verify(io).println("Done");
      inOrder.verify(io).println("You are at the edge of a small fishing pond.\nIt contains: pole, rose");
      inOrder.verify(io).println("Exits are: north");
      inOrder.verify(io).println("You have: lamp, apple, fish");
      inOrder.verify(io).println("There is no such container here");
      inOrder.verify(io).println("You are at the edge of a small fishing pond.\nIt contains: pole, rose");
      inOrder.verify(io).println("Exits are: north");
      inOrder.verify(io).println("You have: lamp, apple, fish");
      inOrder.verify(io).println("There is no such container here");
      inOrder.verify(io).println("OK");
      inOrder.verify(io).println("You are standing on a lush garden path. There is a cottage here.");
      inOrder.verify(io).println("Exits are: south, north, in");
      inOrder.verify(io).println("Goodbye!");
   }

   @Test
   public void testShort()
   {
      IOModel io = mock(IOModel.class);
      when(io.readLine("cottage [0 moves; 0/100 points]> "))
         .thenReturn("l")
         .thenReturn("i")
         .thenReturn("x")
         .thenReturn("x pole")
         .thenReturn("x rose")
         .thenReturn("b pole")
         .thenReturn("t pole");
      when(io.readLine("cottage [1 moves; 0/100 points]> "))
         .thenReturn("x pole")
         .thenReturn("t fish")
         .thenReturn("l")
         .thenReturn("g north")
         .thenReturn("g out");
      when(io.readLine("garden [2 moves; 0/100 points]> "))
         .thenReturn("l")
         .thenReturn("t rose");
      when(io.readLine("garden [3 moves; 0/100 points]> "))
         .thenReturn("i")
         .thenReturn("g south");
      when(io.readLine("pond [4 moves; 0/100 points]> "))
         .thenReturn("l")
         .thenReturn("u pole");
      when(io.readLine("pond [5 moves; 10/100 points]> "))
         .thenReturn("l")
         .thenReturn("t fish");
      when(io.readLine("pond [6 moves; 10/100 points]> "))
         .thenReturn("p rose");
      when(io.readLine("pond [7 moves; 10/100 points]> "))
         .thenReturn("l")
         .thenReturn("i")
         .thenReturn("p fish in rose")
         .thenReturn("l")
         .thenReturn("i")
         .thenReturn("t fish from rose")
         .thenReturn("g north");
      when(io.readLine("garden [8 moves; 10/100 points]> "))
         .thenReturn("l")
         .thenReturn("q");

      Driver driver = new Driver(io);
      driver.run();

      InOrder inOrder = inOrder(io);
      inOrder.verify(io).println("You are standing in a small cottage.\nIt contains: pole");
      inOrder.verify(io).println("Exits are: out");
      inOrder.verify(io).println("You have: lamp, apple");
      inOrder.verify(io).println("No item specified");
      inOrder.verify(io).println("a simple fishing pole");
      inOrder.verify(io).println("There is no such item here");
      inOrder.verify(io).println("I don't know how to do that");
      inOrder.verify(io).println("Taken");
      inOrder.verify(io).println("a simple fishing pole");
      inOrder.verify(io).println("There is no such item here");
      inOrder.verify(io).println("You are standing in a small cottage.");
      inOrder.verify(io).println("Exits are: out");
      inOrder.verify(io).println("You can't go that way.");
      inOrder.verify(io).println("OK");
      inOrder.verify(io).println("You are standing on a lush garden path. There is a cottage here.\nIt contains: rose");
      inOrder.verify(io).println("Exits are: south, north, in");
      inOrder.verify(io).println("Taken");
      inOrder.verify(io).println("You have: lamp, apple, pole, rose");
      inOrder.verify(io).println("OK");
      inOrder.verify(io).println("You are at the edge of a small fishing pond.");
      inOrder.verify(io).println("Exits are: north");
      inOrder.verify(io).println("Done");
      inOrder.verify(io).println("You are at the edge of a small fishing pond.\nIt contains: fish, pole");
      inOrder.verify(io).println("Exits are: north");
      inOrder.verify(io).println("Taken");
      inOrder.verify(io).println("Done");
      inOrder.verify(io).println("You are at the edge of a small fishing pond.\nIt contains: pole, rose");
      inOrder.verify(io).println("Exits are: north");
      inOrder.verify(io).println("You have: lamp, apple, fish");
      inOrder.verify(io).println("There is no such container here");
      inOrder.verify(io).println("You are at the edge of a small fishing pond.\nIt contains: pole, rose");
      inOrder.verify(io).println("Exits are: north");
      inOrder.verify(io).println("You have: lamp, apple, fish");
      inOrder.verify(io).println("There is no such container here");
      inOrder.verify(io).println("OK");
      inOrder.verify(io).println("You are standing on a lush garden path. There is a cottage here.");
      inOrder.verify(io).println("Exits are: south, north, in");
      inOrder.verify(io).println("Goodbye!");
   }
}
