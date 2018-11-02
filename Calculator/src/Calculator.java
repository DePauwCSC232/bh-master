////////////////////////////////////////////////////////////////////////////////
// File:             Calculator.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Calculator extends JPanel {
	private JTextField output;
	private double runningTotal;
	private String lastOperation;
	private boolean numberInProgress;

	public static int WIDTH_IN_CHARACTERS = 10;

	public void clear() {
		lastOperation = "=";
		runningTotal = 0;
		output.setText("0");
		numberInProgress = false;
	}

	public Calculator() {
		output = new JTextField(WIDTH_IN_CHARACTERS);
		output.setEditable(false);
		add(output);

		clear();

		JButton n0 = new JButton("0");
		JButton n1 = new JButton("1");
		JButton n2 = new JButton("2");
		JButton n3 = new JButton("3");
		JButton n4 = new JButton("4");
		JButton n5 = new JButton("5");
		JButton n6 = new JButton("6");
		JButton n7 = new JButton("7");
		JButton n8 = new JButton("8");
		JButton n9 = new JButton("9");
		JButton n10 = new JButton("+");
		JButton n11 = new JButton("-");
		JButton n12 = new JButton("*");
		JButton n13 = new JButton("/");
		JButton n14 = new JButton("=");
		JButton n15 = new JButton("C");

		n1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String current = output.getText();

				String result;
				if (numberInProgress) {
					result = current + "1";
				} else {
					result = "1";
					numberInProgress = true;
				}
				output.setText(result);
			}
		});

		n10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performLastOperation();

				lastOperation = "+";
				numberInProgress = false;
			}
		});

		n15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		add(n0);
		add(n1);
		add(n2);
		add(n3);
		add(n4);
		add(n5);
		add(n6);
		add(n7);
		add(n8);
		add(n9);
		add(n10);
		add(n11);
		add(n12);
		add(n13);
		add(n14);
		add(n15);
	}

	private void performLastOperation() {
		double value = Double.parseDouble(output.getText());
		if (lastOperation.equals("+")) {
			runningTotal = runningTotal + value;
		} else if (lastOperation.equals("-")) {
			runningTotal = runningTotal - value;
		} else if (lastOperation.equals("*")) {
			runningTotal = runningTotal * value;
		} else if (lastOperation.equals("/")) {
			runningTotal = runningTotal / value;
		} else if (lastOperation.equals("=")) {
			runningTotal = value;
		}
		output.setText(Double.toString(runningTotal));
	}

	public static void main(String[] args) {
		JFrame calculatorFrame = new JFrame();
		calculatorFrame.setSize(150, 250);
		calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new Calculator();
		calculatorFrame.add(panel);

		calculatorFrame.setVisible(true);
	}
}
