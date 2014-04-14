/**
CSC232 - Spring 2014
A unit test for a simple driver for an adventure game. (Homework #5)

@author Brian Howard <bhoward@depauw.edu>
@version 2014-04-11
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
      String expectedOutput = "kitchen> It is full of appliances and utensils, but not much food\n"
               + "You see: sandwich, flashlight\n"
               + "kitchen> No item specified\n"
               + "kitchen> a peanut-butter and jelly sandwich\n"
               + "kitchen> an ordinary flashlight, currently turned off\n"
               + "kitchen> There is no such item here\n"
               + "kitchen> I don't know how to do that\n"
               + "kitchen> Goodbye!\n";
      runTest(testInput, expectedOutput);
   }

   @Test
   public void testShort()
   {
      String testInput = "l\nx\nx sandwich\nx flashlight\nx screwdriver\ne sandwich\nq";
      String expectedOutput = "kitchen> It is full of appliances and utensils, but not much food\n"
               + "You see: sandwich, flashlight\n"
               + "kitchen> No item specified\n"
               + "kitchen> a peanut-butter and jelly sandwich\n"
               + "kitchen> an ordinary flashlight, currently turned off\n"
               + "kitchen> There is no such item here\n"
               + "kitchen> I don't know how to do that\n"
               + "kitchen> Goodbye!\n";
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
}
