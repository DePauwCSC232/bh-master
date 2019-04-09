package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

public class TopAlignmentStrategy implements AlignmentStrategy {

	@Override
	public List<Integer> align(Shape left, Shape right) {
		List<Integer> result = new ArrayList<>();
		result.add(0);
		result.add(0);
		return result;
	}

}
