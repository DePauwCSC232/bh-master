////////////////////////////////////////////////////////////////////////////////
// File:             Simulation.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.simulation;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

import edu.depauw.csc232.bbb.model.Bank;

/**
 * @author bhoward
 *
 */
public class Simulation {
   public Simulation() {
      this.events = new PriorityQueue<>();
   }

   public static void main(String[] args) {
      Bank bank = new Bank();
      Simulation sim = new Simulation();

      for (int date = 1; date <= 31; date++) {
         sim.addEvent(new EndOfDayEvent(bank, LocalDateTime.of(2019, 1, date, 23, 59)));
      }
      sim.addEvent(new EndOfMonthEvent(bank, LocalDateTime.of(2019, 2, 1, 0, 0)));

      sim.start();
   }

   /**
    * @param endOfDayEvent
    */
   private void addEvent(SimulationEvent event) {
      events.add(event);
   }

   private void start() {
      while (!events.isEmpty()) {
         SimulationEvent event = events.remove();
         event.perform(this);
      }
   }

   private PriorityQueue<SimulationEvent> events;
}
