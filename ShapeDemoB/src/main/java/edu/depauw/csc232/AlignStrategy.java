package edu.depauw.csc232;

import java.util.List;

public interface AlignStrategy {

	/**
	 * Compute a list of left-side padding strings for the given stack of shapes.
	 * 
	 * @param top
	 * @param bottom
	 * @return
	 */
	List<String> align(Shape top, Shape bottom);

}
