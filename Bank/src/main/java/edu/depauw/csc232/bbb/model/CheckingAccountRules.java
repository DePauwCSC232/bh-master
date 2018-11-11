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
 * @author bhoward
 *
 */
public class CheckingAccountRules implements AccountRules {
   /*
    * (non-Javadoc)
    * 
    * @see edu.depauw.csc232.AccountRules#canWithdraw(edu.depauw.csc232.Account,
    * edu.depauw.csc232.Money)
    */
   @Override
   public boolean canWithdraw(Account account, Money amount) {
      Money balance = account.balance();
      return balance.subtract(amount).compareTo(OVERDRAFT_LIMIT) >= 0;
   }

   /*
    * (non-Javadoc)
    * 
    * @see edu.depauw.csc232.AccountRules#processEndOfDay()
    */
   @Override
   public void processEndOfDay(Account account) {
      Money balance = account.balance();
      if (balance.compareTo(Money.ZERO) < 0) {
         account.transferOut(OVERDRAFT_CHARGE, Bank.FEE_ACCOUNT);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * edu.depauw.csc232.AccountRules#processEndOfMonth(edu.depauw.csc232.Account)
    */
   @Override
   public void processEndOfMonth(Account account) {
      // TODO generate statement -- need to log transactions
   }

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

}
