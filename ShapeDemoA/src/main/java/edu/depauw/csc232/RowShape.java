package edu.depauw.csc232;

import java.util.List;

/**
 * Composite Pattern -- put two shapes in a row
 */
public class RowShape implements Shape {
	private Shape left, right;
	private AlignmentStrategy alignStrategy;
	
	/**
	 * General-purpose constructor from two shapes and an alignment strategy.
	 * 
	 * @param left
	 * @param right
	 * @param alignStrategy
	 */
	public RowShape(Shape left, Shape right, AlignmentStrategy alignStrategy) {
		this.left = left;
		this.right = right;
		this.alignStrategy = alignStrategy;
	}
	
	/**
	 * Special case of constructor where alignment strategy defaults to top.
	 * 
	 * @param left
	 * @param right
	 */
	public RowShape(Shape left, Shape right) {
		this(left, right, new TopAlignmentStrategy());
	}
	
	@Override
	public char renderAt(int row, int col) {
		List<Integer> shift = alignStrategy.align(left, right);
		int leftW = left.width();
		int leftH = left.height();
		int rightH = right.height();
		int leftS = shift.get(0);
		int rightS = shift.get(1);
		
		if (col < leftW && leftS <= row && row < leftH + leftS) {
			return left.renderAt(row - leftS, col);
		} else if (col >= leftW && rightS <= row && row < rightH + rightS) {
		   return right.renderAt(row - rightS, col - leftW);
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
		return Math.max(left.height(), right.height());
	}

}
