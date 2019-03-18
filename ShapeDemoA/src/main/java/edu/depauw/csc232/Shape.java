package edu.depauw.csc232;

public interface Shape {
	/**
	 * Returns the character at the given row, column in this shape's rendering.
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	char renderAt(int row, int col);
	
	int width();
	
	int height();
	
	/**
	 * Render this shape to the console.
	 */
	default void render() {
		for (int row = 0; row < height(); row++) {
			// draw one row
			for (int col = 0; col < width(); col++) {
				System.out.print(renderAt(row, col));
			}
			System.out.println();
		}
	}
}
