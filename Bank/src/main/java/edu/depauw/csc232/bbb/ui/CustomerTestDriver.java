////////////////////////////////////////////////////////////////////////////////
// File:             CustomerTestDriver.java
// Course:           CSC232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.ui;

import edu.depauw.csc232.bbb.model.Customer;

/**
 * Tests the <code>Customer</code> class by creating two objects and printing
 * them to standard output.
 * 
 * @author bhoward
 */
public class CustomerTestDriver {
   public static void main(String[] args) {
      Customer brian = new Customer("Brian Howard", "my secret password");
      Customer alice = new Customer("Alice Howard", "A+Better-P455W0RD");
      
      System.out.println(brian);
      System.out.println(alice);
   }
}
