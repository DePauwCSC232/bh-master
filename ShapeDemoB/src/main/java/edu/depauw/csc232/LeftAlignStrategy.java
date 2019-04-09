package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

public class LeftAlignStrategy implements AlignStrategy {

	@Override
	public List<String> align(Shape top, Shape bottom) {
		List<String> result = new ArrayList<>();
		result.add("");
		result.add("");
		
		return result;
	}

}
