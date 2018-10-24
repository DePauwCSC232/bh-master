package gui;

import java.util.Observable;

public class TrafficLight extends Observable {
	public TrafficLight() {
		state = LightState.GREEN;
	}
	
	public LightState getState() {
		return state;
	}

	public void next() {
		switch (state) {
		case GREEN:
			state = LightState.YELLOW;
			break;
		case YELLOW:
			state = LightState.RED;
			break;
		case RED:
			state = LightState.GREEN;
			break;
		}
		setChanged();
		notifyObservers();
	}
	
	public String toString() {
		return state.toString();
	}

	private LightState state;
}
