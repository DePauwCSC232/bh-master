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
 * @author bhoward
 */
public class Bank {
   public Bank() {
      this.accounts = new HashMap<>();
      this.availableRules = new HashMap<>();
      availableRules.put("CHECKING", CheckingAccountRules.INSTANCE);
      availableRules.put("SAVINGS", SavingsAccountRules.INSTANCE);
   }

   public int createAccount(Customer customer, String accountType) throws BankException {
      AccountRules rules = availableRules.get(accountType);
      if (rules == null) {
         throw new BankException("Account type " + accountType + " is unavailable");
      }

      int accountNumber = nextAccountNumber;
      nextAccountNumber += 1;

      accounts.put(accountNumber, new Account(customer, rules));

      return accountNumber;
   }

   public Account getAccount(int accountNumber, String password) throws BankException {
      Account account = accounts.get(accountNumber);
      if (account.authorize(password)) {
         return accounts.get(accountNumber);
      } else {
         throw new BankException("Unauthorized access to account " + accountNumber);
      }
   }

   public void processEndOfDay() {
      for (Account account : accounts.values()) {
         account.processEndOfDay();
      }
   }

   public void processEndOfMonth() {
      for (Account account : accounts.values()) {
         account.processEndOfMonth();
      }
   }

   private Map<Integer, Account> accounts;
   private Map<String, AccountRules> availableRules;

   private int nextAccountNumber = 1;

   public static final Account FEE_ACCOUNT = null; // TODO
}
