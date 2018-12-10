////////////////////////////////////////////////////////////////////////////////
// File:             CheckingAccountTest.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.depauw.csc232.bbb.model.Account;
import edu.depauw.csc232.bbb.model.CheckingAccountRules;
import edu.depauw.csc232.bbb.model.Money;

/**
 * @author DePauw
 *
 */
public class CheckingAccountTest {
   /**
    * Test method for {@link edu.depauw.csc232.bbb.model.Account#balance()}.
    */
   @Test
   public void testBalance() {
      Customer x = new Customer("X", "x");
      Account acct1 = new Account(1, x, CheckingAccountRules.INSTANCE);
      Account acct2 = new Account(2, x, CheckingAccountRules.INSTANCE, new Money("123.45"));

      assertTrue(acct1.balance().equals(Money.ZERO));
      assertTrue(acct2.balance().equals(new Money(123, 45)));
   }

   /**
    * Test method for
    * {@link edu.depauw.csc232.bbb.model.Account#deposit(edu.depauw.csc232.bbb.model.Money)}.
    */
   @Test
   public void testDeposit() {
      Customer x = new Customer("X", "x");
      Account acct1 = new Account(1, x, CheckingAccountRules.INSTANCE);
      acct1.deposit(new Money(42, 0));

      assertTrue(acct1.balance().equals(new Money("42.00")));
   }

   /**
    * Test method for
    * {@link edu.depauw.csc232.bbb.model.Account#withdraw(edu.depauw.csc232.bbb.model.Money)}.
    */
   @Test
   public void testWithdraw() {
      Customer x = new Customer("X", "x");
      Account acct1 = new Account(1, x, CheckingAccountRules.INSTANCE);
      assertFalse(acct1.withdraw(new Money(52, 0)));
      assertTrue(acct1.balance().equals(new Money(0)));

      Account acct2 = new Account(2, x, CheckingAccountRules.INSTANCE, new Money("123.45"));
      assertTrue(acct2.withdraw(new Money(52, 0)));
      assertTrue(acct2.balance().equals(new Money(71, 45)));
   }

   @Test
   public void testTransfer() {
      Customer x = new Customer("X", "x");
      Account acct1 = new Account(1, x, CheckingAccountRules.INSTANCE);
      Account acct2 = new Account(2, x, CheckingAccountRules.INSTANCE, new Money("123.45"));

      assertTrue(acct1.transferIn(new Money(20), acct2));
      assertTrue(acct1.balance().equals(new Money(20)));
      assertTrue(acct2.balance().equals(new Money("103.45")));

      assertFalse(acct1.transferOut(new Money(82), acct2));
      assertTrue(acct1.balance().equals(new Money(20)));
      assertTrue(acct2.balance().equals(new Money("103.45")));
   }
}
