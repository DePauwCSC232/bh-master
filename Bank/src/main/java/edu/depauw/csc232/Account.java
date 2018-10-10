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
 * Common interface for accounts in a bank simulation.
 *
 * @author bhoward
 */
public interface Account {
   Money balance();

   boolean deposit(Money amount);

   boolean transferIn(Money amount, Account from);

   boolean transferOut(Money amount, Account to);

   boolean withdraw(Money amount);
}
