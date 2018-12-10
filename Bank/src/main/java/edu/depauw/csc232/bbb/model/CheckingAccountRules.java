////////////////////////////////////////////////////////////////////////////////
// File:             CheckingAccountRules.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

/**
 * The AccountRules for a checking account.
 * 
 * @author bhoward
 */
public class CheckingAccountRules implements AccountRules {
   @Override
   public boolean canWithdraw(Account account, Money amount) {
      Money balance = account.balance();
      return balance.subtract(amount).greaterThanOrEqual(OVERDRAFT_LIMIT);
   }

   @Override
   public void processEndOfDay(Bank bank, Account account) {
      Money balance = account.balance();
      if (balance.lessThan(Money.ZERO)) {
         account.transferOut(OVERDRAFT_CHARGE, bank.getFeeAccount());
      }
   }

   @Override
   public void processEndOfMonth(Bank bank, Account account) {
      account.transferOut(MONTHLY_CHARGE, bank.getFeeAccount());
   }

   @Override
   public String toString() {
      return "CHECKING";
   }

   /**
    * An instance of these rules. New checking accounts should be created with
    * <code>new Account(CheckingAccountRules.INSTANCE)</code>.
    */
   public static final CheckingAccountRules INSTANCE = new CheckingAccountRules();

   /**
    * Minimum transient balance for a checking account. An account may drop to this
    * balance without incurring a fee as long as it returns to a non-negative
    * balance by the end of the day.
    */
   public static final Money OVERDRAFT_LIMIT = new Money("-50.00");

   /**
    * Charge to be deducted from balance if account is negative at the end of the
    * day.
    */
   public static final Money OVERDRAFT_CHARGE = new Money("20.00");

   /**
    * Monthly fee for a checking account.
    */
   public static final Money MONTHLY_CHARGE = new Money("5.00");
}
