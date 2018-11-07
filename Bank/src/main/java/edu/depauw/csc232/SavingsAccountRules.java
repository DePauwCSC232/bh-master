////////////////////////////////////////////////////////////////////////////////
// File:             SavingsAccountRules.java
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
public class SavingsAccountRules implements AccountRules {
   @Override
   public boolean canWithdraw(Account account, Money amount) {
      Money balance = account.balance();
      return balance.compareTo(amount) >= 0;
   }

   @Override
   public void processEndOfDay(Account account) {
      // TODO Auto-generated method stub

   }

   @Override
   public void processEndOfMonth(Account account) {
      // TODO Pay interest monthly

   }

   /**
    * An instance of these rules. New savings accounts should be created with
    * <code>new Account(SavingsAccountRules.INSTANCE)</code>.
    */
   public static final AccountRules INSTANCE = new SavingsAccountRules();

}
