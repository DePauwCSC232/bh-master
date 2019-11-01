package edu.depauw.csc232;

import javafx.scene.canvas.GraphicsContext;

/**
 * Provides a strategy for displaying each of a collection of Stamps in a
 * collection.
 * 
 * @author bhoward
 */
public interface DisplayStrategy {

   /**
    * Modify the given GraphicsContext to prepare for the ith stamp in a
    * collection.
    * 
    * @param gc
    * @param i
    */
   void prepare(GraphicsContext gc, int i);

}
