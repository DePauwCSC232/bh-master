////////////////////////////////////////////////////////////////////////////////
// File:             Money.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents an amount of money.
 *
 * @author bhoward
 */
public class Money implements Comparable<Money> {
   public Money(BigDecimal amount) {
      this.amount = amount;
   }

   public Money(String stringAmount) {
      this.amount = new BigDecimal(stringAmount);
   }

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
      return Objects.equals(amount, other.amount);
   }

   @Override
   public int hashCode() {
      return Objects.hash(amount);
   }

   public Money subtract(Money money) {
      return new Money(amount.subtract(money.amount));
   }

   @Override
   public String toString() {
      return NUMBER_FORMAT.format(amount);
   }

   private BigDecimal amount;

   private static final NumberFormat NUMBER_FORMAT = NumberFormat
      .getCurrencyInstance(Locale.US);
}
