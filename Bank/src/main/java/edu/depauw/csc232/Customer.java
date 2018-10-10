////////////////////////////////////////////////////////////////////////////////
// File:             Customer.java
// Course:           CSC232A/B, Fall 2018
// Authors:          Brian Howard
//
// Acknowledgements: None
//
// Online sources:   None
////////////////////////////////////////////////////////////////////////////////

package edu.depauw.csc232;

/**
 * The <code>Customer</code> class describes a customer in a simulation.
 *
 * @author bhoward
 */
public class Customer {
   /**
    * Construct a customer given name, age, and arrival time (in military time
    * format: 0000 to 2359).
    *
    * @param name
    * @param age
    * @param arrivalTime
    */
   public Customer(String name, int age, String arrivalTime) {
      this.name = name;
      this.age = age;
      this.arrivalTime = arrivalTime;
   }

   /**
    * @return this customer's age in years
    */
   public int getAge() {
      return age;
   }

   /**
    * @return this customer's arrival time (U.S. military time format)
    */
   public String getArrivalTime() {
      return arrivalTime;
   }

   /**
    * @return this customer's name
    */
   public String getName() {
      return name;
   }

   @Override
   public String toString() {
      return "Customer:\n"
               + "    name: " + name + "\n"
               + "    age: " + age + "\n"
               + "    arrival time: " + arrivalTime;
   }

   private String name;
   private int age;
   private String arrivalTime;
}
