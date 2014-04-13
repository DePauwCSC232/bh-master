/**
CSC232 - Spring 2014
A simple driver for an adventure game. (Homework #4)

@author Brian Howard <bhoward@depauw.edu>
@version 2014-04-11
 */

package csc232.ui;

import acm.io.IOConsole;
import acm.io.IOModel;
import csc232.model.Item;
import csc232.model.Location;

/**
 * The <code>Driver</code> class manages the user interface to the adventure
 * game. It keeps track of the desired {@link IOModel}, and the current
 * {@link Location}, and it knows how to respond to user commands.
 */
public class Driver
{
   public static void main(String[] args)
   {
      // Create a Driver using System.in/out as the IOModel
      Driver d = new Driver(IOConsole.SYSTEM_CONSOLE);
      d.run();
   }

   /**
    * Construct a <code>Driver</code> using the given {@link IOModel}.
    * For now, also create a sample {@link Location} and some simple
    * {@link Item}s.
    * 
    * @param io
    *           The I/O device to use when communicating with the user
    */
   public Driver(IOModel io)
   {
      this.io = io;
      this.location = new Location("kitchen", "It is full of appliances and utensils, but not much food");
      
      Item sandwich = new Item("sandwich", "consumable",
               "a peanut-butter and jelly sandwich");
      Item flashlight = new Item("flashlight", "tool",
               "an ordinary flashlight, currently turned off");
      
      location.addItem(sandwich);
      location.addItem(flashlight);
   }

   /**
    * Interact with the user. Recognized commands are:
    * <ul>
    * <li>quit (q): end the game</li>
    * <li>look (l): print a description of the current location</li>
    * <li>examine (x) NAME: print a description of the named item, if present</li>
    * </ul>
    */
   public void run()
   {
      String[] words = getCommand();

      while (true)
      {
         if (words[0].equals("quit") || words[0].equals("q"))
         {
            break;
         }
         else if (words[0].equals("look") || words[0].equals("l"))
         {
            io.println(location.getDescription());
            if (location.getItemCount() > 0)
            {
               io.println("You see: " + location.listContents());
            }
         }
         else if (words[0].equals("examine") || words[0].equals("x"))
         {
            if (words.length == 1)
            {
               io.println("No item specified");
            }
            else
            {
               Item item = location.lookup(words[1]);
               if (item == null)
               {
                  io.println("There is no such item here");
               }
               else
               {
                  io.println(item.getDescription());
               }
            }
         }
         else
         {
            io.println("I don't know how to do that");
         }

         words = getCommand();
      }

      io.println("Goodbye!");
   }

   private String[] getCommand()
   {
      String prompt = location.getShortName() + "> ";
      String line = io.readLine(prompt);
      return line.trim().toLowerCase().split(" ");
   }

   private IOModel io;
   private Location location;
}
