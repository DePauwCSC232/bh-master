////////////////////////////////////////////////////////////////////////////////
// File:             SavingsAccountRules.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

/**
 * @author bhoward
 *
 */
public class SavingsAccountRules implements AccountRules {
   @Override
   public boolean canWithdraw(Account account, Money amount) {
      Money balance = account.balance();
      return balance.greaterThanOrEqual(amount);
   }

   @Override
   public void processEndOfDay(Bank bank, Account account) {
      // Nothing to do here
   }

   @Override
   public void processEndOfMonth(Bank bank, Account account) {
      Money interest = account.balance().multiply(MONTHLY_INTEREST_RATE);
      account.transferIn(interest, bank.getFeeAccount());
   }

   @Override
   public String toString() {
      return "SAVINGS";
   }

   /**
    * An instance of these rules. New savings accounts should be created with
    * <code>new Account(SavingsAccountRules.INSTANCE)</code>.
    */
   public static final AccountRules INSTANCE = new SavingsAccountRules();

   public static final double MONTHLY_INTEREST_RATE = 0.005;
}
