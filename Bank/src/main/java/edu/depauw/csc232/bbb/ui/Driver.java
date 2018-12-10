////////////////////////////////////////////////////////////////////////////////
// File:             Driver.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.ui;

import edu.depauw.csc232.bbb.model.Account;
import edu.depauw.csc232.bbb.model.Bank;
import edu.depauw.csc232.bbb.model.BankException;
import edu.depauw.csc232.bbb.model.Customer;
import edu.depauw.csc232.bbb.model.Money;

/**
 * @author bhoward
 */
public class Driver {
   public static void main(String[] args) {
      // This is just a simple test driver
      try {
         Bank bank = new Bank();
         Customer brian = new Customer("Brian Howard", "my secret password");
         int chkAcctNum = bank.createAccount(brian, "CHECKING");
         Account chkAcct = bank.getAccount(chkAcctNum, "my secret password");

         int svgAcctNum = bank.createAccount(brian, "SAVINGS");
         Account svgAcct = bank.getAccount(svgAcctNum, "my secret password");

         // Day one:
         chkAcct.deposit(new Money("100.00"));
         chkAcct.transferOut(new Money("60.00"), svgAcct);
         chkAcct.withdraw(new Money("50.00"));
         System.out.println("Midday checking balance: " + chkAcct.balance());
         chkAcct.transferIn(new Money("15.00"), svgAcct);
         bank.processEndOfDay();

         System.out.println("Day one");
         System.out.println("  checking: " + chkAcct.balance());
         System.out.println("  savings:  " + svgAcct.balance());
         System.out.println();
         
         // Day two:
         chkAcct.deposit(new Money("10.00"));
         chkAcct.withdraw(new Money("25.00"));
         svgAcct.transferOut(new Money("40.00"), chkAcct);
         bank.processEndOfDay();
         
         System.out.println("Day two");
         System.out.println("  checking: " + chkAcct.balance());
         System.out.println("  savings:  " + svgAcct.balance());
         System.out.println();
         
         // End of month:
         bank.processEndOfMonth();
      } catch (BankException be) {
         System.err.println("Something went wrong: " + be.getMessage());
      }
   }
}
