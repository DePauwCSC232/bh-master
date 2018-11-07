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

   /*
    * (non-Javadoc)
    * 
    * @see edu.depauw.csc232.AccountRules#withdraw(edu.depauw.csc232.Money)
    */
   @Override
   public boolean canWithdraw(Account account, Money amount) {
      // TODO Auto-generated method stub
      return false;
   }

   /*
    * (non-Javadoc)
    * 
    * @see edu.depauw.csc232.AccountRules#processEndOfDay()
    */
   @Override
   public void processEndOfDay(Account account) {
      // TODO Auto-generated method stub

   }

   /*
    * (non-Javadoc)
    * 
    * @see edu.depauw.csc232.AccountRules#processEndOfMonth()
    */
   @Override
   public void processEndOfMonth(Account account) {
      // TODO Auto-generated method stub

   }

   public static final AccountRules INSTANCE = new SavingsAccountRules();

}
