package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

public class RightAlignStrategy implements AlignStrategy {

	@Override
	public List<String> align(Shape top, Shape bottom) {
		int topW = top.width();
		int botW = bottom.width();
		int width = Math.max(topW, botW);
		
		List<String> result = new ArrayList<>();
		result.add(Util.repeat(" ", width - topW));
		result.add(Util.repeat(" ", width - botW));
		return result;
	}

}
