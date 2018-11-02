////////////////////////////////////////////////////////////////////////////////
// File:             CalculatorState.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import java.util.function.BinaryOperator;

/**
 * Interface describing the operations provided by the current state of the
 * calculator.
 */
public interface CalculatorState {
	/**
	 * @return the current String to be displayed
	 */
	String getDisplayText();

	/**
	 * Handle a digit key press.
	 * 
	 * @param digit
	 * @return the new state after the key press
	 */
	CalculatorState pressDigit(int digit);

	/**
	 * Handle an operator key press.
	 * 
	 * @param op
	 * @return the new state after the operator key press
	 */
	CalculatorState pressOperator(BinaryOperator<Double> op);

	/**
	 * Handle pressing the decimal point key.
	 * 
	 * @return the new state after the decimal point key press.
	 */
	CalculatorState pressDecimal();
}
