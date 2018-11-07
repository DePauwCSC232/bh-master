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
 * Describes a general account in a bank simulation.
 *
 * @author bhoward
 */
public class Account {
   /**
    * @param rules
    */
   public Account(AccountRules rules) {
      this.rules = rules;
      this.balance = Money.ZERO;
   }

   /**
    * @param rules
    * @param balance
    */
   public Account(AccountRules rules, Money balance) {
      this.rules = rules;
      this.balance = balance;
   }

   /**
    * Get the balance of this account.
    * 
    * @return the current balance
    */
   public Money balance() {
      return balance;
   }

   /**
    * Deposit money into this account. A deposit should always succeed.
    * 
    * @param amount how much money to deposit
    */
   public void deposit(Money amount) {
      balance = balance.add(amount);
   }

   /**
    * Attempt to transfer money into this account. If unable to complete the
    * transfer, return false and leave both balances unchanged.
    * 
    * @param amount how much money to transfer
    * @param from   which account to take the money from
    * @return true if successful
    */
   public boolean transferIn(Money amount, Account from) {
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
    * @param amount how much money to transfer
    * @param to     which account to put the money in
    * @return true if successful
    */
   public boolean transferOut(Money amount, Account to) {
      return to.transferIn(amount, this);
   }

   /**
    * Attempt to withdraw money from this account. If unable to complete the
    * action, return false and leave the balance unchanged.
    * 
    * @param amount of money to withdraw
    * @return true if successful
    */
   public boolean withdraw(Money amount) {
      if (rules.canWithdraw(this, amount)) {
         balance = balance.subtract(amount);
         return true;
      } else {
         return false;
      }
   }

   /**
    * Perform any required end-of-day processing on this account.
    */
   public void processEndOfDay() {
      rules.processEndOfDay(this);
   }

   /**
    * Perform any required end-of-month processing on this account. This will be
    * called after {@link edu.depauw.csc232.Account#processEndOfDay()} on the last
    * day of the month.
    */
   public void processEndOfMonth() {
      rules.processEndOfMonth(this);
   }

   private Money balance;

   private AccountRules rules;
}
