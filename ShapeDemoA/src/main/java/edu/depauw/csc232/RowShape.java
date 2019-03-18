package edu.depauw.csc232;

/**
 * Composite Pattern -- put two shapes in a row
 */
public class RowShape implements Shape {
	private Shape left, right;
	
	public RowShape(Shape left, Shape right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public char renderAt(int row, int col) {
		if (col < left.width() && row < left.height()) {
			return left.renderAt(row, col);
		} else if (col >= left.width() && row < right.height()) {
		   return right.renderAt(row, col - left.width());
		} else {
		   return ' ';
		}
	}

	@Override
	public int width() {
		return left.width() + right.width();
	}

	@Override
	public int height() {
		if (left.height() > right.height()) {
			return left.height();
		} else {
			return right.height();
		}
	}

}
