/**
   CSC232A - Fall 2013
   A simple JUnit test suite for the {@link Address} class.

   @author Brian Howard <bhoward@depauw.edu>
   @author Scott Thede <sthede@depauw.edu>
   @version 2013-09-23
 */
package csc232;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AddressTest
{
   @Before
   public void setUp() throws Exception
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
