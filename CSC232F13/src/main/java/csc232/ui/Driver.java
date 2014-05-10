/**
CSC232 - Spring 2014
A simple driver for an adventure game.

@author Brian Howard <bhoward@depauw.edu>
@version 2014-05-09
 */

package csc232.ui;

import javax.swing.JFrame;

import acm.io.IOConsole;
import acm.io.IOModel;
import csc232.model.ContainerItem;
import csc232.model.GameState;
import csc232.model.Item;

/**
 * The <code>Driver</code> class manages the user interface to the adventure
 * game. It keeps track of the desired {@link IOModel}, and the current
 * location, inventory, and map, and it knows how to respond to user commands.
 */
public class Driver
{
   public static void main(String[] args)
   {
      // // Create a Driver using System.in/out as the IOModel
      // Driver d = new Driver(IOConsole.SYSTEM_CONSOLE);
      //
      // // Create a Driver using dialog boxes as the IOModel
      // Driver d = new Driver(new IODialog());

      // Create a Driver using a Swing interface
      JFrame frame = new JFrame("Adventure!");
      frame.setSize(500, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      IOConsole console = new IOConsole();
      frame.add(console);
      frame.setVisible(true);

      Driver d = new Driver(console);
      d.run();

      // pause for five seconds
      try
      {
         Thread.sleep(5000);
      }
      catch (InterruptedException e)
      {
         // ignore this
      }

      frame.dispose();
   }

   /**
    * Construct a <code>Driver</code> using the given {@link IOModel}. For now,
    * also create a sample location and some simple {@link Item}s.
    * 
    * @param io
    *           The I/O device to use when communicating with the user
    */
   public Driver(IOModel io)
   {
      this.io = io;
      this.gameState = new GameState();
   }

   /**
    * Interact with the user. Recognized commands are:
    * <ul>
    * <li>quit (q): end the game</li>
    * <li>look (l): print a description of the current location</li>
    * <li>examine (x) NAME: print a description of the named item, if present</li>
    * <li>take (t) NAME [from CONTAINER]: move item from location or named
    *   container into inventory</li>
    * <li>put (p) NAME [in CONTAINER]: move item from inventory to location or
    *   named container</li>
    * <li>use (u) NAME: alias for "put name"</li>
    * <li>inventory (i): list items in inventory</li>
    * <li>go (g) DIRECTION: move to a new location in the given direction</li>
    * <li>help (h): list available commands</li>
    * </ul>
    */
   public void run()
   {
      String[] words = getCommand();

      while (!gameState.reachedGoal())
      {
         if (words[0].equals("quit") || words[0].equals("q"))
         {
            break;
         }
         else if (words[0].equals("look") || words[0].equals("l"))
         {
            io.println(gameState.getLocationDescription());
            String exits = gameState.listNeighbors();
            if (!exits.equals(""))
            {
               io.println("Exits are: " + exits);
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
               Item item = gameState.findItem(words[1]);

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
         else if (words[0].equals("take") || words[0].equals("t"))
         {
            if (words.length == 1)
            {
               io.println("No item specified");
            }
            else
            {
               ContainerItem source = gameState.getLocation();

               // Handle taking from a named container
               if (words.length > 2)
               {
                  // Assume the last word is the container name
                  String containerName = words[words.length - 1];
                  source = findContainer(containerName);
               }

               Item item = source.lookup(words[1]);
               if (item == null)
               {
                  io.println("There is no such item here");
               }
               else
               {
                  source.removeItem(item);
                  gameState.addInventoryItem(item);
                  io.println("Taken");
                  gameState.addMove();
               }
            }
         }
         else if (words[0].equals("put") || words[0].equals("p")
                  || words[0].equals("use") || words[0].equals("u"))
         {
            if (words.length == 1)
            {
               io.println("No item specified");
            }
            else
            {
               ContainerItem target = gameState.getLocation();

               // Handle putting into a named container
               if (words.length > 2)
               {
                  // Assume the last word is the container name
                  String containerName = words[words.length - 1];
                  target = findContainer(containerName);
               }

               Item item = gameState.lookupInventory(words[1]);
               if (item == null)
               {
                  io.println("You do not have that item");
               }
               else
               {
                  gameState.removeInventoryItem(item);
                  target.addItem(item, gameState);
                  io.println("Done");
                  gameState.addMove();
               }
            }
         }
         else if (words[0].equals("inventory") || words[0].equals("i"))
         {
            io.println("You have: " + gameState.listInventory());
         }
         else if (words[0].equals("go") || words[0].equals("g"))
         {
            if (words.length == 1)
            {
               io.println("No direction specified");
            }
            else
            {
               boolean success = gameState.move(words[1]);
               if (!success)
               {
                  io.println("You can't go that way.");
               }
               else
               {
                  gameState.addMove();
               }
            }
         }
         else if (words[0].equals("help") || words[0].equals("h"))
         {
            io.println("quit (q): end the game");
            io.println("look (l): print a description of the current location");
            io.println("examine (x) NAME: print a description of the named item, if present");
            io.println("take (t) NAME [from CONTAINER]: move item from location or named container into inventory");
            io.println("put (p) NAME [in CONTAINER]: move item from inventory to location or named container");
            io.println("use (u) NAME: alias for \"put NAME\"");
            io.println("inventory (i): list items in inventory");
            io.println("go (g) DIRECTION: move to a new location in the given direction");
            io.println("help (h): list available commands");
         }
         else
         {
            io.println("I don't know how to do that");
         }

         words = getCommand();
      }

      if (gameState.reachedGoal())
      {
         io.println(gameState.getLocationDescription()); // this is a hack...
         io.println("*** YOU WIN!!! ***");
      }
      
      io.println("Goodbye!");
   }

   private ContainerItem findContainer(String containerName)
   {
      Item container = gameState.findItem(containerName);

      // Check that the container really is a ContainerItem
      if (container != null && container instanceof ContainerItem)
      {
         return (ContainerItem) container;
      }
      else
      {
         io.println("There is no such container here");
         return null;
      }
   }

   private String[] getCommand()
   {
      String prompt = gameState.getPrompt() + "> ";
      String line = io.readLine(prompt);
      return line.trim().toLowerCase().split(" ");
   }

   private IOModel io;
   private GameState gameState;
}
