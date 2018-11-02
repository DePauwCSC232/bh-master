////////////////////////////////////////////////////////////////////////////////
// File:             DoubleState.java
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
 * Represents the state of the calculator when a number with a decimal point is
 * being entered. Keeps track of the previous total and a pending operator.
 */
public class DoubleState implements CalculatorState {
	public DoubleState(double total, BinaryOperator<Double> op, double current, double scale) {
		this.total = total;
		this.op = op;
		this.current = current;
		this.scale = scale;
	}

	@Override
	public String getDisplayText() {
		return Double.toString(current);
	}

	@Override
	public CalculatorState pressDigit(int digit) {
		return new DoubleState(total, op, current + digit * scale, scale / 10);
	}

	@Override
	public CalculatorState pressOperator(BinaryOperator<Double> newOp) {
		double result = op.apply(total, current);
		return new TotalState(result, newOp);
	}

	@Override
	public CalculatorState pressDecimal() {
		return this;
	}

	private double total;
	private BinaryOperator<Double> op;
	private double current;
	private double scale;
}
