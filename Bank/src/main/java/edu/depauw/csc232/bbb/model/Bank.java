////////////////////////////////////////////////////////////////////////////////
// File:             Bank.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The bank object in a bank simulation.
 * 
 * @author bhoward
 */
public class Bank {
   /**
    * Create a bank with a few types of accounts, and an account that manages the
    * bank's fees.
    */
   public Bank() {
      this.accounts = new HashMap<>();
      this.availableRules = new HashMap<>();
      addRule(CheckingAccountRules.INSTANCE);
      addRule(SavingsAccountRules.INSTANCE);

      Customer bankInternal = new Customer("Bank (Internal)", null);
      this.feeAccount = new Account(0, bankInternal, FeeAccountRules.INSTANCE);
      accounts.put(0, feeAccount);
   }

   /**
    * Factory method to create an account of a specified type for the given
    * customer.
    * 
    * @param customer
    * @param accountType
    * @return the created account
    * @throws BankException
    */
   public int createAccount(Customer customer, String accountType) throws BankException {
      AccountRules rules = availableRules.get(accountType);
      if (rules == null) {
         throw new BankException("Account type " + accountType + " is unavailable");
      }

      int accountNumber = nextAccountNumber;
      nextAccountNumber += 1;

      accounts.put(accountNumber, new Account(accountNumber, customer, rules));

      return accountNumber;
   }

   /**
    * Retrieve the given account by its number and password.
    * 
    * @param accountNumber
    * @param password
    * @return
    * @throws BankException
    */
   public Account getAccount(int accountNumber, String password) throws BankException {
      Account account = accounts.get(accountNumber);
      if (account.authorize(password)) {
         return account;
      } else {
         throw new BankException("Unauthorized access to account " + accountNumber);
      }
   }

   /**
    * Perform end-of-day processing for all accounts in the bank.
    */
   public void processEndOfDay() {
      for (Account account : accounts.values()) {
         account.processEndOfDay(this);
      }
   }

   /**
    * Perform end-of-month processing for all accounts in the bank. First apply any
    * fees and pay any interest, then generate all of the statements.
    */
   public void processEndOfMonth() {
      for (Account account : accounts.values()) {
         account.processEndOfMonth(this);
      }
      for (Account account : accounts.values()) {
         account.printStatement();
      }
   }

   /**
    * Get the bank's internal account for collecting fees.
    * 
    * @return the fee account object
    */
   public Account getFeeAccount() {
      return feeAccount;
   }

   private void addRule(AccountRules rules) {
      availableRules.put(rules.toString(), rules);
   }

   private Map<Integer, Account> accounts;
   private Map<String, AccountRules> availableRules;

   private int nextAccountNumber = 1;

   private final Account feeAccount;
}
