////////////////////////////////////////////////////////////////////////////////
// File:             MoneyTest.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.depauw.csc232.bbb.model.Money;

/**
 * JUnit test for the Money class. Assumes the default en_US locale.
 * 
 * @author bhoward
 */
public class MoneyTest {
   /**
    * Test method for {@link edu.depauw.csc232.bbb.model.Money#toString()}.
    */
   @Test
   public void testToString() {
      Money money1 = new Money("123456.78");
      assertEquals("$123,456.78", money1.toString());

      Money money2 = new Money("12345678.90");
      assertEquals("$12,345,678.90", money2.toString());

      Money money3 = new Money("0");
      assertEquals("$0.00", money3.toString());

      Money money4 = new Money(".1234");
      assertEquals("$0.12", money4.toString());

      Money money5 = new Money(".3456");
      assertEquals("$0.35", money5.toString());

      Money money6 = new Money("-1234");
      String result = money6.toString();
      // TODO why is Windows different than Mac here?
      if (result.startsWith("-")) {
         assertEquals("-$1,234.00", result);
      } else {
         assertEquals("($1,234.00)", result);
      }

      Money money7 = new Money("123.005");
      assertEquals("$123.00", money7.toString());

      Money money8 = new Money("123.006");
      assertEquals("$123.01", money8.toString());

      Money money9 = new Money("123.015");
      assertEquals("$123.02", money9.toString());
   }

   /**
    * Test method for {@link edu.depauw.csc232.bbb.model.Money#equals()}.
    */
   @Test
   public void testEquals() {
      Money money1 = new Money("123.00");
      Money money2 = new Money("123");
      Money money3 = new Money("123.005");

      assertTrue(money1.compareTo(money2) == 0);
      assertTrue(money1.compareTo(money3) < 0);
      assertTrue(money2.compareTo(money3) < 0);
      assertTrue(money3.compareTo(money2) > 0);

      assertEquals(money1, money2);
      assertNotEquals(money1, money3);
      assertNotEquals(money2, money3);
      assertTrue(money1.lessThan(money3));
      assertTrue(money2.lessThan(money3));
      assertTrue(money3.greaterThan(money2));
   }
}
