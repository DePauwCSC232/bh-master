////////////////////////////////////////////////////////////////////////////////
// File:             CustomerTest.java
// Course:           CSC232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.depauw.csc232.bbb.model.Customer;

/**
 * A simple JUnit test of the <code>Customer</code> class.
 * 
 * @author bhoward
 */
public class CustomerTest {
   @Test
   public void testToString() {
      Customer brian = new Customer("Brian Howard", 53, "1230");
      String expected = "Customer:\n" + 
               "    name: Brian Howard\n" + 
               "    age: 53\n" + 
               "    arrival time: 1230";
      assertEquals(expected, brian.toString());
   }
}
