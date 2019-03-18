package edu.depauw.csc232;

/**
 * Decorator Pattern -- wrap a frame of X's around a given shape.
 */
public class FramedShape implements Shape {
	private Shape shape;
	
	public FramedShape(Shape shape) {
		super();
		this.shape = shape;
	}

	@Override
	public char renderAt(int row, int col) {
		if (row > 0 && row <= shape.height() && col > 0 && col <= shape.width()) {
			// position in original shape
			return shape.renderAt(row - 1, col - 1);
		} else {
			return 'X';
		}
	}

	@Override
	public int width() {
		return shape.width() + 2;
	}

	@Override
	public int height() {
		return shape.height() + 2;
	}

}
