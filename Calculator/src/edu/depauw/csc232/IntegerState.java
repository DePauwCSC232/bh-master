////////////////////////////////////////////////////////////////////////////////
// File:             IntegerState.java
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
 * Represents the state of the calculator when a number is being entered, but no
 * decimal point has yet been seen. Keeps track of the previous total and a
 * pending operator.
 */
public class IntegerState implements CalculatorState {
	public IntegerState(double total, BinaryOperator<Double> op, long current) {
		this.total = total;
		this.op = op;
		this.current = current;
	}

	@Override
	public String getDisplayText() {
		return Long.toString(current) + ".";
	}

	@Override
	public CalculatorState pressDigit(int digit) {
		return new IntegerState(total, op, current * 10 + digit);
	}

	@Override
	public CalculatorState pressOperator(BinaryOperator<Double> newOp) {
		double result = op.apply(total, (double) current);
		return new TotalState(result, newOp);
	}

	@Override
	public CalculatorState pressDecimal() {
		return new DoubleState(total, op, current, 0.1);
	}

	private double total;
	private BinaryOperator<Double> op;
	private long current;
}
