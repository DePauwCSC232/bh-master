////////////////////////////////////////////////////////////////////////////////
// File:             Log.java
// Course:           CSC 232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232.bbb.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A Log keeps a record of transactions for an Account in a bank simulation.
 * 
 * @author bhoward
 */
public class Log {
   /**
    * Construct an empty log.
    */
   public Log() {
      this.records = new ArrayList<>();
   }

   /**
    * Add a record to the end of the log.
    * 
    * @param record
    */
   public void append(String record) {
      records.add(record);
   }

   /**
    * Print the log to the given PrintStream.
    * 
    * @param out
    */
   public void printTo(PrintStream out) {
      for (String record : records) {
         out.println(record);
      }
   }

   /**
    * 
    */
   public void clear() {
      records.clear();
   }

   private List<String> records;
}
