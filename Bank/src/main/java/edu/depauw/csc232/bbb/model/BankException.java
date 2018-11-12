////////////////////////////////////////////////////////////////////////////////
// File:             BankException.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   (include Web URLs and description of any information used)
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

/**
 * @author bhoward
 *
 */
@SuppressWarnings("serial")
public class BankException extends Exception {

   /**
    * @param string
    */
   public BankException(String message) {
      super(message);
   }

}
