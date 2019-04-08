package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern -- two shapes stacked into a new shape, aligned according
 * to some strategy.
 */
public class StackShape implements Shape {
	private Shape top, bottom;
	AlignStrategy alignStrategy;

	/**
	 * General-purpose constructor from all relevant information.
	 * 
	 * @param top
	 * @param bottom
	 * @param alignStrategy
	 */
	public StackShape(Shape top, Shape bottom, AlignStrategy alignStrategy) {
		this.top = top;
		this.bottom = bottom;
		this.alignStrategy = alignStrategy;
	}

	/**
	 * Special case of constructor that uses a default LeftAlignStrategy.
	 * 
	 * @param top
	 * @param bottom
	 */
	public StackShape(Shape top, Shape bottom) {
		this(top, bottom, new LeftAlignStrategy());
	}

	@Override
	public List<String> render() {
		int topWidth = top.width();
		int botWidth = bottom.width();
		int width = Math.max(topWidth, botWidth);
		
		// Compute the left-side padding strings
		List<String> pad = alignStrategy.align(top, bottom);
		String topLeftPad = pad.get(0);
		String botLeftPad = pad.get(1);

		// Compute the right-side padding strings
		String topRightPad = Util.repeat(" ", width - topWidth - topLeftPad.length());
		String botRightPad = Util.repeat(" ", width - botWidth - botLeftPad.length());

		List<String> result = new ArrayList<>();
		for (String line : top.render()) {
			result.add(topLeftPad + line + topRightPad);
		}
		for (String line : bottom.render()) {
			result.add(botLeftPad + line + botRightPad);
		}
		return result;
	}

	@Override
	public int width() {
		return Math.max(top.width(), bottom.width());
	}

	@Override
	public int height() {
		return top.height() + bottom.height();
	}

}
