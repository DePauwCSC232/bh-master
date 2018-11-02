////////////////////////////////////////////////////////////////////////////////
// File:             DigitAction.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Swing Action object that responds to any digit key press.
 */
@SuppressWarnings("serial")
public class DigitAction extends AbstractAction {
	public DigitAction(Calculator calculator, int digit) {
		super(Integer.toString(digit));
		this.calculator = calculator;
		this.digit = digit;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		calculator.pressDigit(digit);
	}
	
	private Calculator calculator;
	private int digit;
}
