////////////////////////////////////////////////////////////////////////////////
// File:             CheckingAccountTest.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author DePauw
 *
 */
public class CheckingAccountTest {
   /**
    * Test method for {@link edu.depauw.csc232.Account#balance()}.
    */
   @Test
   public void testBalance() {
      Account acct1 = new Account(CheckingAccountRules.INSTANCE);
      Account acct2 = new Account(CheckingAccountRules.INSTANCE, new Money("123.45"));

      assertTrue(acct1.balance().compareTo(new Money(0)) == 0);
      assertTrue(acct2.balance().compareTo(new Money(123, 45)) == 0);
   }

   /**
    * Test method for
    * {@link edu.depauw.csc232.Account#deposit(edu.depauw.csc232.Money)}.
    */
   @Test
   public void testDeposit() {
      Account acct1 = new Account(CheckingAccountRules.INSTANCE);
      acct1.deposit(new Money(42, 0));

      assertTrue(acct1.balance().compareTo(new Money("42.00")) == 0);
   }

   /**
    * Test method for
    * {@link edu.depauw.csc232.Account#withdraw(edu.depauw.csc232.Money)}.
    */
   @Test
   public void testWithdraw() {
      Account acct1 = new Account(CheckingAccountRules.INSTANCE);
      assertFalse(acct1.withdraw(new Money(52, 0)));
      assertTrue(acct1.balance().compareTo(new Money(0)) == 0);

      Account acct2 = new Account(CheckingAccountRules.INSTANCE, new Money("123.45"));
      assertTrue(acct2.withdraw(new Money(52, 0)));
      assertTrue(acct2.balance().compareTo(new Money(71, 45)) == 0);
   }

   @Test
   public void testTransfer() {
      Account acct1 = new Account(CheckingAccountRules.INSTANCE);
      Account acct2 = new Account(CheckingAccountRules.INSTANCE, new Money("123.45"));

      assertTrue(acct1.transferIn(new Money(20), acct2));
      assertTrue(acct1.balance().compareTo(new Money(20)) == 0);
      assertTrue(acct2.balance().compareTo(new Money("103.45")) == 0);

      assertFalse(acct1.transferOut(new Money(82), acct2));
      assertTrue(acct1.balance().compareTo(new Money(20)) == 0);
      assertTrue(acct2.balance().compareTo(new Money("103.45")) == 0);
   }
}
