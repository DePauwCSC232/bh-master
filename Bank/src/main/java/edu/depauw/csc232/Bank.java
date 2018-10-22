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
      
      if (isChecking) {
         accounts.put(accountNumber, new CheckingAccount());
      } else {
         accounts.put(accountNumber, new SavingsAccount());
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
}
