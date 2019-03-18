package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

/**
 * Decorator Pattern -- created a new shape with a frame of X's around a given shape.
 */
public class FramedShape implements Shape {
	private Shape shape;

	public FramedShape(Shape shape) {
		super();
		this.shape = shape;
	}

	@Override
	public List<String> render() {
		List<String> inner = shape.render();
		List<String> result = new ArrayList<>();

		String border;
		if (inner.isEmpty()) {
			border = "XX";
		} else {
			String line = inner.get(0);
			int width = line.length() + 2;
			border = "";
			for (int i = 0; i < width; i++) {
				border = border + "X";
			}
		}
		result.add(border);
		for (String line : inner) {
			result.add("X" + line + "X");
		}
		result.add(border);

		return result;
	}

}
