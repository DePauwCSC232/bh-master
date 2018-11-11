////////////////////////////////////////////////////////////////////////////////
// File:             Money.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Represents an amount of money.
 *
 * @author bhoward
 */
public class Money implements Comparable<Money> {
   /**
    * Construct an amount of money from a BigDecimal number.
    *
    * @param amount the desired amount
    */
   public Money(BigDecimal amount) {
      this.amount = amount;
   }

   /**
    * Construct an amount of money with the given number of dollars.
    *
    * @param dollars the desired amount
    */
   public Money(long dollars) {
      this.amount = BigDecimal.valueOf(dollars);
   }

   /**
    * Construct an amount of money with the given number of dollars and cents.
    *
    * @param dollars the desired amount of dollars
    * @param cents   the desired amount of cents
    */
   public Money(long dollars, long cents) {
      this.amount = BigDecimal.valueOf(dollars * 100 + cents, 2);
   }

   /**
    * Construct an amount of money from a string representation such as
    * "123456.78".
    *
    * @param stringAmount the desired amount as a String
    */
   public Money(String stringAmount) {
      this.amount = new BigDecimal(stringAmount);
   }

   /**
    * Add the given money amount to this and return the sum.
    *
    * @param money the amount to add
    * @return the sum
    */
   public Money add(Money money) {
      return new Money(amount.add(money.amount));
   }

   @Override
   public int compareTo(Money other) {
      return amount.compareTo(other.amount);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      Money other = (Money) obj;
      return amount.compareTo(other.amount) == 0;
   }

   @Override
   public int hashCode() {
      // This is more expensive than amount.hashCode(), but it is
      // required to be compatible with the above equals() method.
      return toString().hashCode();
   }

   /**
    * Subtract the given money amount from this and return the difference.
    *
    * @param money the amount to subtract
    * @return the difference
    */
   public Money subtract(Money money) {
      return new Money(amount.subtract(money.amount));
   }

   @Override
   public String toString() {
      return NUMBER_FORMAT.format(amount);
   }

   private BigDecimal amount;

   private static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

   public static final Money ZERO = new Money(0);
}
