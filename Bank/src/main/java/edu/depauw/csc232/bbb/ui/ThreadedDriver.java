////////////////////////////////////////////////////////////////////////////////
// File:             ThreadedDriver.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.ui;

import edu.depauw.csc232.bbb.model.Account;
import edu.depauw.csc232.bbb.model.CheckingAccountRules;
import edu.depauw.csc232.bbb.model.Customer;
import edu.depauw.csc232.bbb.model.Money;

/**
 * This shows an example of using threads. It should also motivate the use of
 * synchronization when using threads.
 */
public class ThreadedDriver {
   public static void main(String[] args) {
      Customer brian = new Customer("Brian Howard", "my secret password");
      Account account = new Account(brian, CheckingAccountRules.INSTANCE, new Money(100));
      Runnable task = new Runnable() {
         @Override
         public void run() {
            for (int i = 0; i < 1000; i++) {
               // Try to take out $100
               if (account.withdraw(new Money(100))) {
                  Money balance = account.balance();
                  if (balance.lessThan(Money.ZERO)) {
                     System.err.println("Oops! Balance is " + balance);
                  }
                  // Put the $100 back
                  account.deposit(new Money(100));
               }
            }
         }
      };

      Thread thread1 = new Thread(task);
      Thread thread2 = new Thread(task);

      thread1.start();
      thread2.start();
   }
}
