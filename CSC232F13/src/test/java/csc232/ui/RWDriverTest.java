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
      String testInput = "look\nexamine\nexamine sandwich\nexamine flashlight\nexamine screwdriver\neat sandwich\nquit";
      String expectedOutput = "kitchen> It is full of appliances and utensils, but not much food" + NL
               + "It contains: sandwich, cheesecake" + NL
               + "Exits are: north" + NL
               + "kitchen> No item specified" + NL
               + "kitchen> a peanut-butter and jelly sandwich" + NL
               + "kitchen> There is no such item here" + NL
               + "kitchen> a flathead screwdriver" + NL
               + "kitchen> I don't know how to do that" + NL
               + "kitchen> Goodbye!" + NL;
      runTest(testInput, expectedOutput);
   }

   @Test
   public void testShort()
   {
      String testInput = "l\nx\nx sandwich\nx flashlight\nx screwdriver\ne sandwich\nq";
      String expectedOutput = "kitchen> It is full of appliances and utensils, but not much food" + NL
               + "It contains: sandwich, cheesecake" + NL
               + "Exits are: north" + NL
               + "kitchen> No item specified" + NL
               + "kitchen> a peanut-butter and jelly sandwich" + NL
               + "kitchen> There is no such item here" + NL
               + "kitchen> a flathead screwdriver" + NL
               + "kitchen> I don't know how to do that" + NL
               + "kitchen> Goodbye!" + NL;
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
