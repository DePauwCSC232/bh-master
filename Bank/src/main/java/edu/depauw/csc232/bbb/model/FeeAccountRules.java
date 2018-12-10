////////////////////////////////////////////////////////////////////////////////
// File:             FeeAccountRules.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   (include Web URLs and description of any information used)
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

/**
 * The AccountRules for the bank's internal fee account.
 * 
 * @author bhoward
 */
public class FeeAccountRules implements AccountRules {
   @Override
   public boolean canWithdraw(Account account, Money amount) {
      return true; // Bank may withdraw from here to pay interest
   }

   @Override
   public void processEndOfDay(Bank bank, Account account) {
      // Nothing to do
   }

   @Override
   public void processEndOfMonth(Bank bank, Account account) {
      // Nothing to do
   }
   
   @Override
   public String toString() {
      return "FEES (Internal)";
   }

   public static final AccountRules INSTANCE = new FeeAccountRules();
}
