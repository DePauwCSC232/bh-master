////////////////////////////////////////////////////////////////////////////////
// File:             Account.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

/**
 * Describes a general account in a bank simulation.
 *
 * @author bhoward
 */
public class Account {
   /**
    * Construct an account with an initial zero balance, following the given
    * account rules.
    * 
    * @param rules the AccountRules governing this account
    */
   public Account(Customer customer, AccountRules rules) {
      this(customer, rules, Money.ZERO);
   }

   /**
    * Construct an account with the given initial balance, following the given
    * account rules.
    * 
    * @param rules   the AccountRules governing this account
    * @param balance the initial balance
    */
   public Account(Customer customer, AccountRules rules, Money balance) {
      this.customer = customer;
      this.rules = rules;
      this.balance = balance;
   }

   /**
    * Get the balance of this account.
    * 
    * @return the current balance
    */
   public synchronized Money balance() {
      return balance;
   }

   /**
    * Deposit money into this account. A deposit should always succeed.
    * Precondition: amount must be greater than zero
    * 
    * @param amount how much money to deposit
    */
   public synchronized void deposit(Money amount) {
      balance = balance.add(amount);
   }

   /**
    * Attempt to withdraw money from this account. If unable to complete the
    * action, return false and leave the balance unchanged.
    * 
    * @param amount of money to withdraw
    * @return true if successful
    */
   public synchronized boolean withdraw(Money amount) {
      if (rules.canWithdraw(this, amount)) {
         balance = balance.subtract(amount);
         return true;
      } else {
         return false;
      }
   }

   /**
    * Attempt to transfer money into this account. If unable to complete the
    * transfer, return false and leave both balances unchanged. Not synchronized to
    * avoid deadlock, and because any access to the balance is made through
    * synchronized methods.
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
    * transfer, return false and leave both balances unchanged. Not synchronized to
    * avoid deadlock, and because any access to the balance is made through
    * synchronized methods.
    * 
    * @param amount how much money to transfer
    * @param to     which account to put the money in
    * @return true if successful
    */
   public boolean transferOut(Money amount, Account to) {
      return to.transferIn(amount, this);
   }

   /**
    * Perform any required end-of-day processing on this account. Not synchronized
    * to avoid deadlock, and because any access to the balance is made through
    * synchronized methods.
    */
   public void processEndOfDay() {
      rules.processEndOfDay(this);
   }

   /**
    * Perform any required end-of-month processing on this account. This will be
    * called after {@link edu.depauw.csc232.Account#processEndOfDay()} on the last
    * day of the month. Not synchronized to avoid deadlock, and because any access
    * to the balance is made through synchronized methods.
    */
   public void processEndOfMonth() {
      rules.processEndOfMonth(this);
   }

   private Customer customer;

   private Money balance;

   private AccountRules rules;
}
