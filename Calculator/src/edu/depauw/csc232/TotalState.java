////////////////////////////////////////////////////////////////////////////////
// File:             TotalState.java
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
 * Represents the state of the calculator when a total of some previous
 * operation is being displayed. Keeps track of any pending operation, awaiting
 * the entry of another number.
 */
public class TotalState implements CalculatorState {
	public TotalState(double total, BinaryOperator<Double> op) {
		this.total = total;
		this.op = op;
	}

	@Override
	public String getDisplayText() {
		return Double.toString(total);
	}

	@Override
	public CalculatorState pressDigit(int digit) {
		return new IntegerState(total, op, digit);
	}

	@Override
	public CalculatorState pressOperator(BinaryOperator<Double> op) {
		return new TotalState(total, op);
	}

	@Override
	public CalculatorState pressDecimal() {
		return new DoubleState(total, op, 0, 0.1);
	}

	private double total;
	private BinaryOperator<Double> op;
}
