package edu.depauw.csc232;

public class Triangle implements Shape {
	private int width, height;

	public Triangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public char renderAt(int row, int col) {
		if (width * row <= col * height) {
			return '*';
		} else {
			return ' ';
		}
	}

	@Override
	public int width() {
		return width;
	}

	@Override
	public int height() {
		return height;
	}

}
