////////////////////////////////////////////////////////////////////////////////
// File:             Calculator.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.function.BinaryOperator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 * Demonstration of a calculator using Swing and some design patterns. Interface
 * created with Swing Designer plug-in in Eclipse.
 */
public class Calculator {
	private JFrame frame;
	private JLabel displayLabel;

	private CalculatorState state;

	private static BinaryOperator<Double> EQ_OP = (total, value) -> value;
	private static BinaryOperator<Double> ADD_OP = (total, value) -> total + value;
	private static BinaryOperator<Double> SUB_OP = (total, value) -> total - value;
	private static BinaryOperator<Double> MUL_OP = (total, value) -> total * value;
	private static BinaryOperator<Double> DIV_OP = (total, value) -> total / value;

	// Note that a "lambda" (anonymous function) is equivalent to the following:
//	private static BinaryOperator<Double> DIV_OP = new BinaryOperator<Double>() {
//		@Override
//		public Double apply(Double total, Double value) {
//			return total / value;
//		}
//	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator window = new Calculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculator() {
		initialize();
		clear();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 315, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 75, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);

		displayLabel = new JLabel("0.");
		displayLabel.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		panel.add(displayLabel);
		displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JButton button = new JButton(new DigitAction(this, 7));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 1;
		frame.getContentPane().add(button, gbc_button);

		JButton button_1 = new JButton(new DigitAction(this, 8));
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 1;
		frame.getContentPane().add(button_1, gbc_button_1);

		JButton button_2 = new JButton(new DigitAction(this, 9));
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 1;
		frame.getContentPane().add(button_2, gbc_button_2);

		JButton button_3 = new JButton(new OperatorAction(this, "/", DIV_OP));
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(0, 0, 5, 0);
		gbc_button_3.gridx = 3;
		gbc_button_3.gridy = 1;
		frame.getContentPane().add(button_3, gbc_button_3);

		JButton button_4 = new JButton(new DigitAction(this, 4));
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.insets = new Insets(0, 0, 5, 5);
		gbc_button_4.gridx = 0;
		gbc_button_4.gridy = 2;
		frame.getContentPane().add(button_4, gbc_button_4);

		JButton button_5 = new JButton(new DigitAction(this, 5));
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.insets = new Insets(0, 0, 5, 5);
		gbc_button_5.gridx = 1;
		gbc_button_5.gridy = 2;
		frame.getContentPane().add(button_5, gbc_button_5);

		JButton button_6 = new JButton(new DigitAction(this, 6));
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.insets = new Insets(0, 0, 5, 5);
		gbc_button_6.gridx = 2;
		gbc_button_6.gridy = 2;
		frame.getContentPane().add(button_6, gbc_button_6);

		JButton button_7 = new JButton(new OperatorAction(this, "*", MUL_OP));
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.insets = new Insets(0, 0, 5, 0);
		gbc_button_7.gridx = 3;
		gbc_button_7.gridy = 2;
		frame.getContentPane().add(button_7, gbc_button_7);

		JButton button_8 = new JButton(new DigitAction(this, 1));
		GridBagConstraints gbc_button_8 = new GridBagConstraints();
		gbc_button_8.insets = new Insets(0, 0, 5, 5);
		gbc_button_8.gridx = 0;
		gbc_button_8.gridy = 3;
		frame.getContentPane().add(button_8, gbc_button_8);

		JButton button_9 = new JButton(new DigitAction(this, 2));
		GridBagConstraints gbc_button_9 = new GridBagConstraints();
		gbc_button_9.insets = new Insets(0, 0, 5, 5);
		gbc_button_9.gridx = 1;
		gbc_button_9.gridy = 3;
		frame.getContentPane().add(button_9, gbc_button_9);

		JButton button_10 = new JButton(new DigitAction(this, 3));
		GridBagConstraints gbc_button_10 = new GridBagConstraints();
		gbc_button_10.insets = new Insets(0, 0, 5, 5);
		gbc_button_10.gridx = 2;
		gbc_button_10.gridy = 3;
		frame.getContentPane().add(button_10, gbc_button_10);

		JButton button_11 = new JButton(new OperatorAction(this, "-", SUB_OP));
		GridBagConstraints gbc_button_11 = new GridBagConstraints();
		gbc_button_11.insets = new Insets(0, 0, 5, 0);
		gbc_button_11.gridx = 3;
		gbc_button_11.gridy = 3;
		frame.getContentPane().add(button_11, gbc_button_11);

		JButton button_12 = new JButton(new DigitAction(this, 0));
		GridBagConstraints gbc_button_12 = new GridBagConstraints();
		gbc_button_12.insets = new Insets(0, 0, 0, 5);
		gbc_button_12.gridx = 0;
		gbc_button_12.gridy = 4;
		frame.getContentPane().add(button_12, gbc_button_12);

		JButton button_13 = new JButton(".");
		button_13.addActionListener(e -> pressDecimal());
		GridBagConstraints gbc_button_13 = new GridBagConstraints();
		gbc_button_13.insets = new Insets(0, 0, 0, 5);
		gbc_button_13.gridx = 1;
		gbc_button_13.gridy = 4;
		frame.getContentPane().add(button_13, gbc_button_13);

		JButton button_14 = new JButton(new OperatorAction(this, "=", EQ_OP));
		GridBagConstraints gbc_button_14 = new GridBagConstraints();
		gbc_button_14.insets = new Insets(0, 0, 0, 5);
		gbc_button_14.gridx = 2;
		gbc_button_14.gridy = 4;
		frame.getContentPane().add(button_14, gbc_button_14);

		JButton button_15 = new JButton(new OperatorAction(this, "+", ADD_OP));
		GridBagConstraints gbc_button_15 = new GridBagConstraints();
		gbc_button_15.gridx = 3;
		gbc_button_15.gridy = 4;
		frame.getContentPane().add(button_15, gbc_button_15);
	}

	/**
	 * Reset the calculator to its initial state. Running total is zero, and no
	 * operation pending.
	 */
	public void clear() {
		state = new TotalState(0.0, EQ_OP);
		updateDisplay();
	}

	/**
	 * Show the current display value in the label.
	 */
	public void updateDisplay() {
		displayLabel.setText(state.getDisplayText());
	}

	/**
	 * Handle a digit press by delegating to the current state.
	 * 
	 * @param digit
	 */
	public void pressDigit(int digit) {
		state = state.pressDigit(digit);
		updateDisplay();
	}

	/**
	 * Handle an operator press by delegating to the current state.
	 * 
	 * @param op
	 */
	public void pressOperator(BinaryOperator<Double> op) {
		state = state.pressOperator(op);
		updateDisplay();
	}

	/**
	 * Handle a decimal point press by delegating to the current state.
	 */
	private void pressDecimal() {
		state = state.pressDecimal();
		updateDisplay();
	}
}
