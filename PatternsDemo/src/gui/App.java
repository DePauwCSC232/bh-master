package gui;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class App implements Observer {
	private TrafficLight light;

	private JFrame frame;

	private JLabel lblState;
	private JMenuBar menuBar;
	private JMenu mnWindow;
	private JMenuItem mntmShowLight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
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
	public App() {
		light = new TrafficLight();
		initialize();
		light.addObserver(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				light.next();
			}
		});
		frame.getContentPane().add(btnNext, BorderLayout.CENTER);
		
		lblState = new JLabel(light.toString());
		Box stateDisplay = Box.createHorizontalBox();
		stateDisplay.add(new JLabel("Current State: "));
		stateDisplay.add(lblState);
		frame.getContentPane().add(stateDisplay, BorderLayout.NORTH);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnWindow = new JMenu("Window");
		menuBar.add(mnWindow);
		
		mntmShowLight = new JMenuItem("Show Light");
		mntmShowLight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LightDisplay display = new LightDisplay(light.getState());
				display.setVisible(true);
				light.addObserver(display);
			}
		});
		mnWindow.add(mntmShowLight);
	}

	@Override
	public void update(Observable o, Object arg) {
		lblState.setText(light.toString());
	}
}
