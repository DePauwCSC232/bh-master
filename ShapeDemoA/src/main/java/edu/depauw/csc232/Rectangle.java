package edu.depauw.csc232;

public class Rectangle implements Shape {
	private int width, height;

	public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public char renderAt(int row, int col) {
		return '*';
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
