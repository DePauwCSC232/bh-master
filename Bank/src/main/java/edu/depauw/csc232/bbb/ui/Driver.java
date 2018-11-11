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
import edu.depauw.csc232.bbb.model.Money;

/**
 * @author bhoward
 */
public class Driver {
   public static void main(String[] args) {
      // This is just a simple test driver
      Bank bank = new Bank();
      int chkAcctNum = bank.createAccount(true);
      Account chkAcct = bank.getAccount(chkAcctNum);
      
      int svgAcctNum = bank.createAccount(false);
      Account svgAcct = bank.getAccount(svgAcctNum);
      
      chkAcct.deposit(new Money("100.00"));
      chkAcct.transferOut(new Money("60.00"), svgAcct);
      chkAcct.withdraw(new Money("50.00"));
      System.out.println(chkAcct.balance());
      chkAcct.transferIn(new Money("15.00"), svgAcct);
      bank.processEndOfDay();
      
      System.out.println(chkAcct.balance());
      System.out.println(svgAcct.balance());
   }
}
