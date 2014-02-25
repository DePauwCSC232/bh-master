/**
   CSC232 - Spring 2014
   Test suite for <code>Address</code> objects.

   @author Brian Howard <bhoward@depauw.edu>
   @version 2014-02-24
*/

package csc232;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
   A simple JUnit test suite for the {@link Address} class. Most of these
   tests are too trivial to be interesting; this is only a demonstration.
*/
public class AddressTest
{
   /**
      Initializes the <code>address</code> field used in the rest of the suite.
   */
   @Before
   public void setUp()
   {
      address = new Address("602 S. College", "Greencastle", "IN", "46135");
   }

   @Test
   public void testToString()
   {
      assertEquals("602 S. College\nGreencastle, IN  46135", address.toString());
   }

   @Test
   public void testGetStreet()
   {
      assertEquals("602 S. College", address.getStreet());
   }

   @Test
   public void testGetCity()
   {
      assertEquals("Greencastle", address.getCity());
   }

   @Test
   public void testGetState()
   {
      assertEquals("IN", address.getState());
   }

   @Test
   public void testGetZIP()
   {
      assertEquals("46135", address.getZIP());
   }

   private Address address;
}
