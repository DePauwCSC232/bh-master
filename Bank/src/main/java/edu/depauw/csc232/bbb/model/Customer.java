////////////////////////////////////////////////////////////////////////////////
// File:             Customer.java
// Course:           CSC232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

/**
 * The <code>Customer</code> class describes a customer in a bank simulation.
 *
 * @author bhoward
 */
public class Customer {
   /**
    * Construct a customer given name and password. The password is in cleartext,
    * because we aren't trying to be secure in this simulation.
    *
    * @param name
    * @param password
    */
   public Customer(String name, String password) {
      this.name = name;
      this.password = password;
   }

   /**
    * @return this customer's name
    */
   public String getName() {
      return name;
   }

   /**
    * Check whether the given password matches the saved one. This is very
    * insecure.
    * 
    * @param password2 The password string to check
    * @return true if the passwords match
    */
   public boolean checkPassword(String password2) {
      return password.equals(password2);
   }

   @Override
   public String toString() {
      return "Customer(name: " + name + ", password: " + password + ")";
   }

   private String name;
   private String password;
}
