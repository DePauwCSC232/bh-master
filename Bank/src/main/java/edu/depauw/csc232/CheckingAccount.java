////////////////////////////////////////////////////////////////////////////////
// File:             CheckingAccount.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

/**
 * @author bhoward
 *
 */
public class CheckingAccount implements Account {
   @Override
   public Money balance() {
      return balance;
   }

   @Override
   public void deposit(Money amount) {
      balance = balance.add(amount);
   }

   @Override
   public boolean withdraw(Money amount) {
      if (balance.compareTo(amount) >= 0) {
         balance = balance.subtract(amount);
         return true;
      } else {
         return false;
      }
   }

   private Money balance;
}
