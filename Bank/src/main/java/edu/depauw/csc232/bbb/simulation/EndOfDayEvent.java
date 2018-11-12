////////////////////////////////////////////////////////////////////////////////
// File:             EndOfDayEvent.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   (include Web URLs and description of any information used)
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.simulation;

import java.time.LocalDateTime;

import edu.depauw.csc232.bbb.model.Bank;

/**
 * @author bhoward
 *
 */
public class EndOfDayEvent implements SimulationEvent {
   /**
    * @param time
    */
   public EndOfDayEvent(Bank bank, LocalDateTime time) {
      this.bank = bank;
      this.time = time;
   }

   /* (non-Javadoc)
    * @see edu.depauw.csc232.bbb.simulation.SimulationEvent#perform(edu.depauw.csc232.bbb.simulation.Simulation)
    */
   @Override
   public void perform(Simulation simulation) {
      bank.processEndOfDay();
   }
   
   public LocalDateTime getTime() {
      return time;
   }
   
   private Bank bank;
   private LocalDateTime time;
}
