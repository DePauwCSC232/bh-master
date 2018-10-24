package gui;

import java.util.ArrayList;
import java.util.List;

public class TrafficLight {
	public TrafficLight() {
		state = LightState.GREEN;
		observers = new ArrayList<>();
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
		notifyObservers();
	}
	
	private void notifyObservers() {
		for (LightObserver o : observers) {
			o.update(this);
		}
	}

	public void addObserver(LightObserver o) {
		observers.add(o);
	}
	
	public String toString() {
		return state.toString();
	}

	private LightState state;
	private List<LightObserver> observers;
}
