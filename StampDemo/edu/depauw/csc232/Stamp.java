package edu.depauw.csc232;

import javafx.scene.canvas.GraphicsContext;

/**
 * A Stamp is a graphic object that will be drawn relative to the current
 * origin of the GraphicsContext.
 * 
 * @author bhoward
 */
public interface Stamp {

   void draw(GraphicsContext gc);

}
