////////////////////////////////////////////////////////////////////////////////
// File:             Bank.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bhoward
 */
public class Bank {
   public Bank() {
      this.accounts = new HashMap<>();
   }

   // TODO associate account with a customer
   public int createAccount(boolean isChecking) {
      int accountNumber = nextAccountNumber;
      nextAccountNumber += 1;

      // TODO make this nicer -- have a collection of available rules?
      if (isChecking) {
         accounts.put(accountNumber, new Account(CheckingAccountRules.INSTANCE));
      } else {
         accounts.put(accountNumber, new Account(SavingsAccountRules.INSTANCE));
      }

      return accountNumber;
   }

   // TODO check authorization of customer
   public Account getAccount(int accountNumber) {
      return accounts.get(accountNumber);
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

   private int nextAccountNumber = 1;

   public static final Account FEE_ACCOUNT = null; // TODO
}
