package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LightDisplay extends JFrame implements Observer {
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public LightDisplay(LightState state) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setColor(state);
	}

	@Override
	public void update(Observable o, Object arg) {
		TrafficLight light = (TrafficLight) o;
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

}
