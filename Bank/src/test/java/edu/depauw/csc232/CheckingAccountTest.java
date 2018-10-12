////////////////////////////////////////////////////////////////////////////////
// File:             CheckingAccountTest.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   (include Web URLs and description of any information used)
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author DePauw
 *
 */
public class CheckingAccountTest {

   /**
    * Test method for {@link edu.depauw.csc232.CheckingAccount#balance()}.
    */
   @Test
   public void testBalance() {
      CheckingAccount acct1 = new CheckingAccount();
      CheckingAccount acct2 = new CheckingAccount(new Money("123.45"));
      
      assertTrue(acct1.balance().compareTo(new Money(0)) == 0);
      assertTrue(acct2.balance().compareTo(new Money(123, 45)) == 0);
   }

   /**
    * Test method for {@link edu.depauw.csc232.CheckingAccount#deposit(edu.depauw.csc232.Money)}.
    */
   @Test
   public void testDeposit() {
      CheckingAccount acct1 = new CheckingAccount();
      acct1.deposit(new Money(42, 0));
      
      assertTrue(acct1.balance().compareTo(new Money("42.00")) == 0);
   }

   /**
    * Test method for {@link edu.depauw.csc232.CheckingAccount#withdraw(edu.depauw.csc232.Money)}.
    */
   @Test
   public void testWithdraw() {
      CheckingAccount acct1 = new CheckingAccount();
      assertFalse(acct1.withdraw(new Money(42, 0)));
      assertTrue(acct1.balance().compareTo(new Money(0)) == 0);
      
      CheckingAccount acct2 = new CheckingAccount(new Money("123.45"));
      assertTrue(acct2.withdraw(new Money(42, 0)));
      assertTrue(acct2.balance().compareTo(new Money(81, 45)) == 0);
   }

   @Test
   public void testTransfer() {
      CheckingAccount acct1 = new CheckingAccount();
      CheckingAccount acct2 = new CheckingAccount(new Money("123.45"));
      
      assertTrue(acct1.transferIn(new Money(20), acct2));
      assertTrue(acct1.balance().compareTo(new Money(20)) == 0);
      assertTrue(acct2.balance().compareTo(new Money("103.45")) == 0);
      
      assertFalse(acct1.transferOut(new Money(42), acct2));
      assertTrue(acct1.balance().compareTo(new Money(20)) == 0);
      assertTrue(acct2.balance().compareTo(new Money("103.45")) == 0);
   }
}
