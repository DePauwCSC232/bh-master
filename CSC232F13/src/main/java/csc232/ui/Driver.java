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
import csc232.model.GameMap;
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
      this.inventory = new ContainerItem("inventory",
               "The stuff you are carrying");
      this.map = new GameMap();
      
      this.location = populateMap();
   }
   
   /**
    * Interact with the user. Recognized commands are:
    * <ul>
    * <li>quit (q): end the game</li>
    * <li>look (l): print a description of the current location</li>
    * <li>examine (x) NAME: print a description of the named item, if present</li>
    * <li>take (t) NAME [from CONTAINER]: move item from location or named container into inventory</li>
    * <li>put (p) NAME [in CONTAINER]: move item from inventory to location or named container</li>
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
            io.println(location.getDescription());
            String exits = map.listNeighbors(location);
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
               Item item = location.lookup(words[1]);
               if (item == null)
               {
                  // Check in your inventory
                  item = inventory.lookup(words[1]);
               }
               
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
            ContainerItem source = location;
            
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
               inventory.addItem(item);
               io.println("Taken");
            }
         }
         else if (words[0].equals("put") || words[0].equals("p"))
         {
            ContainerItem target = location;
            
            // Handle putting into a named container
            if (words.length > 2)
            {
               // Assume the last word is the container name
               String containerName = words[words.length - 1];
               target = findContainer(containerName);
            }
            
            Item item = inventory.lookup(words[1]);
            if (item == null)
            {
               io.println("You do not have that item");
            }
            else
            {
               inventory.removeItem(item);
               target.addItem(item);
               io.println("Done");
            }
         }
         else if (words[0].equals("inventory") || words[0].equals("i"))
         {
            io.println("You have: " + inventory.listContents());
         }
         else if (words[0].equals("go") || words[0].equals("g"))
         {
            if (words.length == 1)
            {
               io.println("No direction specified");
            }
            else
            {
               ContainerItem destination = map.getNeighbor(location, words[1]);
               if (destination == null)
               {
                  io.println("You can't go that way.");
               }
               else
               {
                  location = destination;
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

      io.println("Goodbye!");
   }

   private ContainerItem findContainer(String containerName)
   {
      Item container = inventory.lookup(containerName);
      
      // If container is not in inventory, check the location
      if (container == null)
      {
         container = location.lookup(containerName);
      }
      
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
      String prompt = location.getShortName() + "> ";
      String line = io.readLine(prompt);
      return line.trim().toLowerCase().split(" ");
   }

   private ContainerItem populateMap()
   {
      ContainerItem kitchen = new ContainerItem("kitchen",
               "It is full of appliances and utensils, but not much food");
      ContainerItem hallway = new ContainerItem("hallway",
               "It is long and narrow");
      ContainerItem library = new ContainerItem("library",
               "It is filled with books");

      kitchen.addItem(new Item("sandwich", "consumable",
               "a peanut-butter and jelly sandwich"));
      kitchen.addItem(new Item("cheesecake", "consumable",
               "it's magically delicious!"));
      
      ContainerItem backpack = new ContainerItem("backpack",
               "a seemingly ordinary backpack");
      backpack.addItem(new Item("flashlight", "tool",
               "an ordinary flashlight, currently turned off"));
      
      hallway.addItem(backpack);
      hallway.addItem(new Item("mail", "media",
               "just a bunch of bills and junk mail"));
      
      library.addItem(new Item("dictionary", "media",
               "it is very heavy"));
      library.addItem(new Item("novel", "media",
               "it's \"The Hitchhiker's Guide to the Galaxy\"!"));
      library.addItem(new Item("spellbook", "media",
               "a powerful book of spells"));
      
      inventory.addItem(new Item("screwdriver", "tool",
               "a flathead screwdriver"));
      
      map.addLocation(kitchen);
      map.addLocation(hallway);
      map.addLocation(library);
      
      map.addNeighbor(kitchen, "north", hallway);
      map.addNeighbor(hallway, "south", kitchen);
      
      map.addNeighbor(hallway, "east", library);
      map.addNeighbor(library, "west", hallway);
      
      return kitchen;
   }

   private IOModel io;
   private ContainerItem location;
   private ContainerItem inventory;
   private GameMap map;
}
