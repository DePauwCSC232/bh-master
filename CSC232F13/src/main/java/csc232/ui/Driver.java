/**
CSC232 - Spring 2014
A simple driver for an adventure game.

@author Brian Howard <bhoward@depauw.edu>
@version 2014-05-09
 */

package csc232.ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.StringReader;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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
   @SuppressWarnings("serial")
   public static void main(String[] args)
   {
      // // Create a Driver using System.in/out as the IOModel
      // Driver d = new Driver(IOConsole.SYSTEM_CONSOLE);
      //
      // // Create a Driver using dialog boxes as the IOModel
      // Driver d = new Driver(new IODialog());

      // Create a Driver using a Swing interface
      JFrame frame = new JFrame("Adventure!");
      frame.setSize(800, 500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      console = new IOConsole();
      frame.add(console);
      
      Action quit = new AbstractAction("Quit")
      {
         {
            putValue(MNEMONIC_KEY, KeyEvent.VK_Q);
         }
         
         public void actionPerformed(ActionEvent arg0)
         {
            runCommand("quit\n");
         }
      };
      
      Action help = new AbstractAction("Help")
      {
         {
            putValue(MNEMONIC_KEY, KeyEvent.VK_H);
         }
         
         public void actionPerformed(ActionEvent arg0)
         {
            runCommand("help\n");
         }
      };
      
      Action large = new AbstractAction("LARGE")
      {
         {
            putValue(MNEMONIC_KEY, KeyEvent.VK_L);
         }
         
         public void actionPerformed(ActionEvent arg0)
         {
            console.setFont("Monospaced-bold-18");
         }
      };
      
      Action small = new AbstractAction("small")
      {
         {
            putValue(MNEMONIC_KEY, KeyEvent.VK_S);
         }
         
         public void actionPerformed(ActionEvent arg0)
         {
            console.setFont("Monospaced-plain-12");
         }
      };
      
      JMenuBar menubar = new JMenuBar();
      JMenu actions = new JMenu("Actions");
      actions.add(help);
      actions.add(large);
      actions.add(small);
      actions.add(quit);
      menubar.add(actions);
      frame.setJMenuBar(menubar);
      
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
    * Construct a <code>Driver</code> using the given {@link IOModel}.
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
    * container into inventory</li>
    * <li>put (p) NAME [in CONTAINER]: move item from inventory to location or
    * named container</li>
    * <li>use (u) NAME: alias for "put name"</li>
    * <li>inventory (i): list items in inventory</li>
    * <li>go (g) DIRECTION: move to a new location in the given direction</li>
    * <li>help (h): list available commands</li>
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
                  source = gameState.findContainer(containerName);
               }

               if (source == null)
               {
                  io.println("There is no such container here");
               }
               else
               {
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
                  target = gameState.findContainer(containerName);
               }

               if (target == null)
               {
                  io.println("There is no such container here");
               }
               else
               {
                  Item item = gameState.lookupInventory(words[1]);
                  if (item == null)
                  {
                     io.println("You do not have that item");
                  }
                  else
                  {
                     gameState.removeInventoryItem(item);
                     String message = target.addItem(item, gameState);
                     if (message != null)
                     {
                        io.println(message);
                     }
                     else
                     {
                        io.println("Done");
                     }
                     gameState.addMove();
                  }
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
                  io.println("OK");
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
         io.println("*** YOU WIN!!! ***");
      }

      io.println("You achieved " + gameState.getPoints()
               + " points out of " + gameState.getGoal()
               + ", taking " + gameState.getMoves() + " moves!");
      io.println("Goodbye!");
   }

   private String[] getCommand()
   {
      String prompt = gameState.getPrompt() + "> ";
      String line = io.readLine(prompt);
      return line.trim().toLowerCase().split(" ");
   }

   private static void runCommand(String command)
   {
      console.setInputScript(new BufferedReader(new StringReader(command)));
   }
   
   private IOModel io;
   private GameState gameState;
   private static IOConsole console;
}
