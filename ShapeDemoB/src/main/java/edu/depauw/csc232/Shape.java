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
	
	/**
	 * Return the width of this shape.
	 */
	default int width() {
	   List<String> lines = render();
	   if (lines.isEmpty()) {
	      return 0;
	   } else {
	      return lines.get(0).length();
	   }
	}
	
	/**
	 * Return the height of this shape.
	 */
	default int height() {
	   List<String> lines = render();
	   return lines.size();
	}
}
