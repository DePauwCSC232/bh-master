////////////////////////////////////////////////////////////////////////////////
// File:             Account.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

/**
 * Common base class for accounts in a bank simulation.
 *
 * @author bhoward
 */
public interface Account {
   /**
    * Get the balance of this account.
    * 
    * @return the current balance
    */
   Money balance();

   /**
    * Deposit money into this account. A deposit should always succeed.
    * 
    * @param amount
    *           how much money to deposit
    */
   void deposit(Money amount);

   /**
    * Attempt to transfer money into this account. If unable to complete the
    * transfer, return false and leave both balances unchanged.
    * 
    * @param amount
    *           how much money to transfer
    * @param from
    *           which account to take the money from
    * @return true if successful
    */
   default boolean transferIn(Money amount, Account from) {
      boolean result = from.withdraw(amount);
      if (result) {
         deposit(amount);
      }
      return result;
   }

   /**
    * Attempt to transfer money out of this account. If unable to complete the
    * transfer, return false and leave both balances unchanged.
    * 
    * @param amount
    *           how much money to transfer
    * @param to
    *           which account to put the money in
    * @return true if successful
    */
   default boolean transferOut(Money amount, Account to) {
      return to.transferIn(amount, this);
   }

   /**
    * Attempt to withdraw money from this account. If unable to complete the
    * action, return false and leave the balance unchanged.
    * 
    * @param amount
    *           of money to withdraw
    * @return true if successful
    */
   boolean withdraw(Money amount);
}
