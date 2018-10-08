////////////////////////////////////////////////////////////////////////////////
// File:             CustomerTestDriver.java
// Course:           CSC232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

/**
 * Tests the <code>Customer</code> class by creating two objects and printing
 * them to standard output.
 * 
 * @author bhoward
 */
public class CustomerTestDriver {
   public static void main(String[] args) {
      Customer brian = new Customer("Brian Howard", 53, "1230");
      Customer alice = new Customer("Alice Howard", 13, "1245");
      
      System.out.println(brian);
      System.out.println(alice);
   }
}
