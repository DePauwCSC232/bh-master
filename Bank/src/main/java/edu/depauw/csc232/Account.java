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

import java.math.BigDecimal;

public abstract class Account {
   public BigDecimal balance() {
      return balance;
   }

   public abstract boolean deposit(BigDecimal amount, Account from);

   public abstract boolean withdraw(BigDecimal amount, Account to);

   private BigDecimal balance;
}
