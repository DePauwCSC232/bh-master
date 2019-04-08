package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

public class CenterAlignStrategy implements AlignStrategy {

	@Override
	public List<String> align(Shape top, Shape bottom) {
		int topW = top.width();
		int botW = bottom.width();
		int width = Math.max(topW, botW);
		
		List<String> result = new ArrayList<>();
		result.add(Util.repeat(" ", (width - topW) / 2));
		result.add(Util.repeat(" ", (width - botW) / 2));
		return result;
	}

}
