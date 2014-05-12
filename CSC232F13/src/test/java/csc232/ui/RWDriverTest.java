/**
CSC232 - Spring 2014
A unit test for a simple driver for an adventure game. (Homework #5)

@author Brian Howard <bhoward@depauw.edu>
@version 2014-05-09
 */

package csc232.ui;

import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import acm.io.IOModel;

/**
 * Unit test for the {@link Driver} class, using string reader/writer method.
 */
public class RWDriverTest
{
   @Test
   public void testLong()
   {
      String testInput = "look\n"
               + "inventory\n"
               + "examine\n"
               + "examine pole\n"
               + "examine rose\n"
               + "break pole\n"
               + "take pole\n"
               + "examine pole\n"
               + "take fish\n"
               + "look\n"
               + "go north\n"
               + "go out\n"
               + "look\n"
               + "take rose\n"
               + "inventory\n"
               + "go south\n"
               + "look\n"
               + "use pole\n"
               + "look\n"
               + "take fish\n"
               + "put rose\n"
               + "look\n"
               + "inventory\n"
               + "put fish in rose\n"
               + "look\n"
               + "inventory\n"
               + "take fish from rose\n"
               + "go north\n"
               + "look\n"
               + "quit";
      String expectedOutput = "cottage [0 moves; 0/100 points]> You are standing in a small cottage." + NL
               + "It contains: pole" + NL
               + "Exits are: out" + NL
               + "cottage [0 moves; 0/100 points]> You have: lamp" + NL
               + "cottage [0 moves; 0/100 points]> No item specified" + NL
               + "cottage [0 moves; 0/100 points]> a simple fishing pole" + NL
               + "cottage [0 moves; 0/100 points]> There is no such item here" + NL
               + "cottage [0 moves; 0/100 points]> I don't know how to do that" + NL
               + "cottage [0 moves; 0/100 points]> Taken" + NL
               + "cottage [1 moves; 0/100 points]> a simple fishing pole" + NL
               + "cottage [1 moves; 0/100 points]> There is no such item here" + NL
               + "cottage [1 moves; 0/100 points]> You are standing in a small cottage." + NL
               + "Exits are: out" + NL
               + "cottage [1 moves; 0/100 points]> You can't go that way." + NL
               + "cottage [1 moves; 0/100 points]> OK" + NL
               + "garden [2 moves; 0/100 points]> You are standing on a lush garden path. There is a cottage here." + NL
               + "It contains: rose" + NL
               + "Exits are: south, north, in" + NL
               + "garden [2 moves; 0/100 points]> Taken" + NL
               + "garden [3 moves; 0/100 points]> You have: lamp, pole, rose" + NL
               + "garden [3 moves; 0/100 points]> OK" + NL
               + "pond [4 moves; 0/100 points]> You are at the edge of a small fishing pond." + NL
               + "Exits are: north" + NL
               + "pond [4 moves; 0/100 points]> Done" + NL
               + "pond [5 moves; 10/100 points]> You are at the edge of a small fishing pond." + NL
               + "It contains: fish, pole" + NL
               + "Exits are: north" + NL
               + "pond [5 moves; 10/100 points]> Taken" + NL
               + "pond [6 moves; 10/100 points]> Done" + NL
               + "pond [7 moves; 10/100 points]> You are at the edge of a small fishing pond." + NL
               + "It contains: pole, rose" + NL
               + "Exits are: north" + NL
               + "pond [7 moves; 10/100 points]> You have: lamp, fish" + NL
               + "pond [7 moves; 10/100 points]> There is no such container here" + NL
               + "pond [7 moves; 10/100 points]> You are at the edge of a small fishing pond." + NL
               + "It contains: pole, rose" + NL
               + "Exits are: north" + NL
               + "pond [7 moves; 10/100 points]> You have: lamp, fish" + NL
               + "pond [7 moves; 10/100 points]> There is no such container here" + NL
               + "pond [7 moves; 10/100 points]> OK" + NL
               + "garden [8 moves; 10/100 points]> You are standing on a lush garden path. There is a cottage here." + NL
               + "Exits are: south, north, in" + NL
               + "garden [8 moves; 10/100 points]> Goodbye!" + NL;

      runTest(testInput, expectedOutput);
   }

   @Test
   public void testShort()
   {
      String testInput = "l\n"
               + "i\n"
               + "x\n"
               + "x pole\n"
               + "x rose\n"
               + "b pole\n"
               + "t pole\n"
               + "x pole\n"
               + "t fish\n"
               + "l\n"
               + "g north\n"
               + "g out\n"
               + "l\n"
               + "t rose\n"
               + "i\n"
               + "g south\n"
               + "l\n"
               + "u pole\n"
               + "l\n"
               + "t fish\n"
               + "p rose\n"
               + "l\n"
               + "i\n"
               + "p fish rose\n"
               + "l\n"
               + "i\n"
               + "t fish rose\n"
               + "g north\n"
               + "l\n"
               + "q";
      String expectedOutput = "cottage [0 moves; 0/100 points]> You are standing in a small cottage." + NL
               + "It contains: pole" + NL
               + "Exits are: out" + NL
               + "cottage [0 moves; 0/100 points]> You have: lamp" + NL
               + "cottage [0 moves; 0/100 points]> No item specified" + NL
               + "cottage [0 moves; 0/100 points]> a simple fishing pole" + NL
               + "cottage [0 moves; 0/100 points]> There is no such item here" + NL
               + "cottage [0 moves; 0/100 points]> I don't know how to do that" + NL
               + "cottage [0 moves; 0/100 points]> Taken" + NL
               + "cottage [1 moves; 0/100 points]> a simple fishing pole" + NL
               + "cottage [1 moves; 0/100 points]> There is no such item here" + NL
               + "cottage [1 moves; 0/100 points]> You are standing in a small cottage." + NL
               + "Exits are: out" + NL
               + "cottage [1 moves; 0/100 points]> You can't go that way." + NL
               + "cottage [1 moves; 0/100 points]> OK" + NL
               + "garden [2 moves; 0/100 points]> You are standing on a lush garden path. There is a cottage here." + NL
               + "It contains: rose" + NL
               + "Exits are: south, north, in" + NL
               + "garden [2 moves; 0/100 points]> Taken" + NL
               + "garden [3 moves; 0/100 points]> You have: lamp, pole, rose" + NL
               + "garden [3 moves; 0/100 points]> OK" + NL
               + "pond [4 moves; 0/100 points]> You are at the edge of a small fishing pond." + NL
               + "Exits are: north" + NL
               + "pond [4 moves; 0/100 points]> Done" + NL
               + "pond [5 moves; 10/100 points]> You are at the edge of a small fishing pond." + NL
               + "It contains: fish, pole" + NL
               + "Exits are: north" + NL
               + "pond [5 moves; 10/100 points]> Taken" + NL
               + "pond [6 moves; 10/100 points]> Done" + NL
               + "pond [7 moves; 10/100 points]> You are at the edge of a small fishing pond." + NL
               + "It contains: pole, rose" + NL
               + "Exits are: north" + NL
               + "pond [7 moves; 10/100 points]> You have: lamp, fish" + NL
               + "pond [7 moves; 10/100 points]> There is no such container here" + NL
               + "pond [7 moves; 10/100 points]> You are at the edge of a small fishing pond." + NL
               + "It contains: pole, rose" + NL
               + "Exits are: north" + NL
               + "pond [7 moves; 10/100 points]> You have: lamp, fish" + NL
               + "pond [7 moves; 10/100 points]> There is no such container here" + NL
               + "pond [7 moves; 10/100 points]> OK" + NL
               + "garden [8 moves; 10/100 points]> You are standing on a lush garden path. There is a cottage here." + NL
               + "Exits are: south, north, in" + NL
               + "garden [8 moves; 10/100 points]> Goodbye!" + NL;

      runTest(testInput, expectedOutput);
   }

   private void runTest(String testInput, String expectedOutput)
   {
      Reader in = new StringReader(testInput);
      Writer out = new StringWriter();

      // The ReaderWriterIOModel is only needed here because the Driver has been
      // written around a single IOModel object (for future extension) rather
      // than a separate Reader and Writer
      IOModel io = new ReaderWriterIOModel(in, out);

      Driver driver = new Driver(io);
      driver.run();

      assertEquals(expectedOutput, out.toString());
   }

   private final String NL = System.getProperty("line.separator");
}
