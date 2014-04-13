/**
CSC232 - Spring 2014
A class to test Item creation and printing. (Homework #2)

@author Brian Howard <bhoward@depauw.edu>
@version 2014-04-11
 */

package csc232.ui;

import csc232.model.Item;

/**
 * This class contains a very simple driver to test the {@link Item} class. Two
 * items are created, then printed to the system console.
 */
public class ItemTestDriver
{
   public static void main(String[] args)
   {
      Item sandwich = new Item("sandwich", "consumable",
               "a peanut-butter and jelly sandwich");
      Item flashlight = new Item("flashlight", "tool",
               "an ordinary flashlight, currently turned off");

      System.out.println(sandwich);
      System.out.println(flashlight);
   }
}
