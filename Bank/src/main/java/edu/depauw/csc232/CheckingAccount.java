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
 * Models a checking account in a bank simulation.
 * 
 * @author bhoward
 */
public class CheckingAccount implements Account {
   /**
    * Construct a checking account with a default zero balance.
    */
   public CheckingAccount() {
      this.balance = new Money(0);
   }

   /**
    * Construct a checking account with the given balance.
    * 
    * @param amount
    *           the starting balance
    */
   public CheckingAccount(Money amount) {
      this.balance = amount;
   }

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
      if (balance.subtract(amount).compareTo(OVERDRAFT_LIMIT) >= 0) {
         balance = balance.subtract(amount);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void processEndOfDay() {
      if (balance.compareTo(new Money(0)) < 0) {
         balance = balance.subtract(OVERDRAFT_CHARGE);
         // TODO transfer the charge to the bank
      }
   }

   @Override
   public void processEndOfMonth() {
      // TODO generate statement -- need to log transactions
   }

   private Money balance;

   /**
    * Minimum transient balance for a checking account. An account may drop to
    * this balance without incurring a fee as long as it returns to a
    * non-negative balance by the end of the day.
    */
   public static final Money OVERDRAFT_LIMIT = new Money("-50.00");

   /**
    * Charge to be deducted from balance if account is negative at the end of
    * the day.
    */
   public static final Money OVERDRAFT_CHARGE = new Money("20.00");
}
