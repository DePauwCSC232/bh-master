package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LightDisplay implements LightObserver {
	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public LightDisplay(LightState state) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		setColor(state);
	}

	@Override
	public void update(TrafficLight light) {
		LightState state = light.getState();
		setColor(state);
	}

	private void setColor(LightState state) {
		switch (state) {
		case GREEN:
			contentPane.setBackground(Color.GREEN);
			break;
		case YELLOW:
			contentPane.setBackground(Color.YELLOW);
			break;
		case RED:
			contentPane.setBackground(Color.RED);
			break;
		}
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
