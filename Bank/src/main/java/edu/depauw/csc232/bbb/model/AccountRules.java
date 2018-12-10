////////////////////////////////////////////////////////////////////////////////
// File:             AccountRules.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

/**
 * An AccountRules object specifies the strategies for checking whether an
 * account will allow a withdrawal, and for end-of-day/month processing.
 */
public interface AccountRules {
   /**
    * Check whether these account rules allow the requested withdrawal.
    * 
    * @param account the Account from which the withdrawal is requested
    * @param amount  the Money amount of the withdrawal
    * @return true if the rules allow the withdrawal
    */
   boolean canWithdraw(Account account, Money amount);

   /**
    * Handle the end-of-day processing for this kind of account.
    * 
    * @param account the Account being processed
    */
   void processEndOfDay(Bank bank, Account account);

   /**
    * Handle the end-of-month processing for this kind of account.
    * 
    * @param account the Account being processed
    */
   void processEndOfMonth(Bank bank, Account account);
}
