package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern -- two shapes stacked into a new shape.
 */
public class StackShape implements Shape {
	private Shape top, bottom;
	
	public StackShape(Shape top, Shape bottom) {
		super();
		this.top = top;
		this.bottom = bottom;
	}

	@Override
	public List<String> render() {
		List<String> renderTop = top.render();
		List<String> renderBottom = bottom.render();
		
		List<String> result = new ArrayList<>();
		
		int topWidth = 0;
		int bottomWidth = 0;
		if (!renderTop.isEmpty()) {
			topWidth = renderTop.get(0).length();
		}
		if (!renderBottom.isEmpty()) {
			bottomWidth = renderBottom.get(0).length();
		}
		int totalWidth = Math.max(topWidth, bottomWidth);
		
		String topPad = "";
		String bottomPad = "";
		for (int i = topWidth; i < totalWidth; i++) {
			topPad = topPad + " ";
		}
		for (int i = bottomWidth; i < totalWidth; i++) {
			bottomPad = bottomPad + " ";
		}
		
		for (String line : renderTop) {
			result.add(line + topPad);
		}
		for (String line : renderBottom) {
			result.add(line + bottomPad);
		}
		
		return result;
	}

}
