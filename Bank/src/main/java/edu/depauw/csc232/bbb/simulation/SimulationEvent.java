////////////////////////////////////////////////////////////////////////////////
// File:             SimulationEvent.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.simulation;

import java.time.LocalDateTime;

/**
 * @author bhoward
 *
 */
public interface SimulationEvent extends Comparable<SimulationEvent> {
   /**
    * @param simulation
    */
   void perform(Simulation simulation);

   LocalDateTime getTime();
   
   default int compareTo(SimulationEvent other) {
      return getTime().compareTo(other.getTime());
   }
}
