package edu.depauw.csc232;

import java.awt.Color;

public class Circle implements Image {
	public Circle(Color color) {
		this.color = color;
	}

	@Override
	public Color colorAt(double x, double y) {
		double dx = x - 0.5;
		double dy = y - 0.5;
		double d = Math.sqrt(dx * dx + dy * dy);
		if (d <= 0.5) {
			return color;
		} else {
			return CLEAR;
		}
	}

	private Color color;
}
