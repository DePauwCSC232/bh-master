////////////////////////////////////////////////////////////////////////////////
// File:             OperatorAction.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import java.awt.event.ActionEvent;
import java.util.function.BinaryOperator;

import javax.swing.AbstractAction;

/**
 * Swing Action object that responds to any operator key press.
 */
@SuppressWarnings("serial")
public class OperatorAction extends AbstractAction {
	public OperatorAction(Calculator calculator, String name, BinaryOperator<Double> op) {
		super(name);
		this.calculator = calculator;
		this.op = op;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		calculator.pressOperator(op);
	}

	private Calculator calculator;
	private BinaryOperator<Double> op;
}
