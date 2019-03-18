/**
 * 
 */
package edu.depauw.csc232;

import java.util.List;

/**
 * Shapes that can be displayed in characters on the console.
 */
public interface Shape {
	/**
	 * Render the shape as a list of String.
	 */
	List<String> render();
	
	/**
	 * Render the shape on the console.
	 */
	default void displayToConsole() {
		for (String s : render()) {
			System.out.println(s);
		}
	}
}
