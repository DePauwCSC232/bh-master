package edu.depauw.csc232;

import java.awt.Color;

public class Square implements Image {
	public Square(Color color) {
		this.color = color;
	}

	@Override
	public Color colorAt(double x, double y) {
		if (0 <= x && x <= 1 && 0 <= y && y <= 1) {
			return color;
		} else {
			return CLEAR;
		}
	}

	private Color color;
}
