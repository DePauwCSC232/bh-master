package edu.depauw.csc232;

import java.util.ArrayList;
import java.util.List;

public class CenterAlignmentStrategy implements AlignmentStrategy {

	@Override
	public List<Integer> align(Shape left, Shape right) {
		List<Integer> result = new ArrayList<>();
		int leftH = left.height();
		int rightH = right.height();
		int height = Math.max(leftH, rightH);
		int leftS = (height - leftH) / 2;
		int rightS = (height - rightH) / 2;
		result.add(leftS);
		result.add(rightS);
		return result;
	}

}
