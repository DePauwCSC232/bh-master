////////////////////////////////////////////////////////////////////////////////
// File:             AccountRules.java
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
    * @param account
    * 
    */
   void processEndOfDay(Account account);

   /**
    * @param account
    */
   void processEndOfMonth(Account account);
}
