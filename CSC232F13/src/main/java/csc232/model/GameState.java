/**
CSC232 - Spring 2014
A class to represent the player's state in an adventure game.

@author Brian Howard <bhoward@depauw.edu>
@version 2014-05-09
 */

package csc232.model;

/**
 * Wraps up information about the player's inventory, location, moves, points,
 * and the game map in a single object.
 */
public class GameState
{
   public GameState()
   {
      this.inventory = new ContainerItem("inventory",
               "The stuff you are carrying");
      this.map = new GameMap();
      // this.location = populateDemoMap(); // Use this for testing...
      this.location = populateCastleMap();
      
      this.moves = 0;
      this.points = 0;
   }

   public ContainerItem getLocation()
   {
      return location;
   }

   /**
    * @return the description string for the current location
    */
   public String getLocationDescription()
   {
      return location.getDescription();
   }

   /**
    * @return a prompt string based on the current game state
    */
   public String getPrompt()
   {
      return location.getShortName() + " [" + moves + " moves; " + points + "/" + goal + " points]";
   }

   /**
    * @return a list of exit directions from the current location
    */
   public String listNeighbors()
   {
      return map.listNeighbors(location);
   }

   /**
    * Change the map by adding (or revising) a neighbor to the given location in
    * the specified direction.
    * 
    * @param location
    * @param direction
    * @param neighbor
    */
   public void putNeighbor(ContainerItem location, String direction,
            ContainerItem neighbor)
   {
      map.addNeighbor(location, direction, neighbor);
   }

   /**
    * @return a list of items in the inventory
    */
   public String listInventory()
   {
      return inventory.listContents();
   }

   /**
    * Lookup a named {@link Item} in the inventory. Returns null if not found.
    * 
    * @param name
    *           the short name of the desired item
    * @return the item, or null if not found
    */
   public Item lookupInventory(String name)
   {
      return inventory.lookup(name);
   }

   /**
    * Add an {@link Item} to the inventory.
    * 
    * @param item
    */
   public void addInventoryItem(Item item)
   {
      inventory.addItem(item, this);
   }

   /**
    * Remove an {@link Item} from the inventory
    * 
    * @param item
    */
   public void removeInventoryItem(Item item)
   {
      inventory.removeItem(item);
   }

   /**
    * Lookup a named {@link Item} in the inventory or the current location.
    * Returns null if not found in either place.
    * 
    * @param name
    *           the short name of the desired item
    * @return the item, or null if not found
    */
   public Item findItem(String name)
   {
      Item item = location.lookup(name);
      if (item == null)
      {
         // Check in your inventory
         item = inventory.lookup(name);
      }
      return item;
   }

   /**
    * Attempt to move in the given direction. If unable, stay in the same
    * location and return false.
    * 
    * @param direction
    * @return true if successful
    */
   public boolean move(String direction)
   {
      ContainerItem destination = map.getNeighbor(location, direction);
      if (destination == null)
      {
         return false;
      }
      else
      {
         location = destination;
         return true;
      }
   }
   
   public void addPoints(int points)
   {
      this.points += points;
   }
   
   public void addMove()
   {
      moves++;
   }
   
   public boolean reachedGoal()
   {
      return points >= goal;
   }

// This map was used for earlier testing:
//   private ContainerItem populateDemoMap()
//   {
//      ContainerItem kitchen = new ContainerItem("kitchen",
//               "It is full of appliances and utensils, but not much food");
//      ContainerItem hallway = new ContainerItem("hallway",
//               "It is long and narrow");
//      ContainerItem library = new ContainerItem("library",
//               "It is filled with books");
//
//      kitchen.addItem(new Item("sandwich", "consumable",
//               "a peanut-butter and jelly sandwich"));
//      kitchen.addItem(new Item("cheesecake", "consumable",
//               "it's magically delicious!"));
//
//      ContainerItem backpack = new ContainerItem("backpack",
//               "a seemingly ordinary backpack");
//      backpack.addItem(new Item("flashlight", "tool",
//               "an ordinary flashlight, currently turned off"));
//
//      hallway.addItem(backpack);
//      hallway.addItem(new Item("mail", "media",
//               "just a bunch of bills and junk mail"));
//
//      library.addItem(new Item("dictionary", "media", "it is very heavy"));
//      library.addItem(new Item("novel", "media",
//               "it's \"The Hitchhiker's Guide to the Galaxy\"!"));
//      library.addItem(new Item("spellbook", "media",
//               "a powerful book of spells"));
//
//      inventory.addItem(new Item("screwdriver", "tool",
//               "a flathead screwdriver"));
//
//      map.addLocation(kitchen);
//      map.addLocation(hallway);
//      map.addLocation(library);
//
//      map.addNeighbor(kitchen, "north", hallway);
//      map.addNeighbor(hallway, "south", kitchen);
//
//      map.addNeighbor(hallway, "east", library);
//      map.addNeighbor(library, "west", hallway);
//      
//      return kitchen;
//   }

   /**
    * Load items and locations for the Parsely game, "ACTION CASTLE!" (c) 2009,
    * Jared A. Sorensen / Memento Mori Theatricks
    * http://www.memento-mori.com/
    * Content used here solely for educational purposes, not for distribution
    */
   private ContainerItem populateCastleMap()
   {
      Item lamp = new Item("lamp", "tool", "an ordinary lamp");
      inventory.addItem(lamp);

      ContainerItem cottage = new ContainerItem("cottage",
               "You are standing in a small cottage.");
      Item pole = new Item("pole", "tool", "a simple fishing pole");
      cottage.addItem(pole);

      ContainerItem garden = new ContainerItem("garden",
               "You are standing on a lush garden path. There is a cottage here.");
      Item rose = new Item("rose", "tool", "a beautiful red rose");
      garden.addItem(rose);

      Item fish = new Item("fish", "food", "a raw fish");
      ContainerItem pond = new SwitchItem("pond",
               "You are at the edge of a small fishing pond.", pole, fish,
               true, null, null, "You are at the edge of a small fishing pond.", 10);

      ContainerItem path = new ContainerItem("path",
               "You are walking along a winding path. There is a tall tree here.");

      ContainerItem tree = new ContainerItem("tree",
               "You are at the top of the tall tree.");
      Item branch = new Item("branch", "tool", "a stout, dead branch");
      tree.addItem(branch);

      ContainerItem hall = new ContainerItem("hall",
               "You stand inside the Great Feasting Hall.");
      Item candle = new Item("candle", "tool",
               "a strange candle, covered in strange runes");
      hall.addItem(candle);

      Item key = new Item("key", "tool", "a heavy iron key");
      ContainerItem courtyard = new SwitchItem(
               "courtyard",
               "You are in the courtyard of ACTION CASTLE. There is a guard here, blocking the path east.",
               branch,
               key,
               false,
               "east",
               hall,
               "You are in the courtyard of ACTION CASTLE. There is an unconscious guard here.", 20);

      ContainerItem drawbridge = new SwitchItem(
               "drawbridge",
               "You are standing on one side of a drawbridge leading to ACTION CASTLE. There is a mean troll here.",
               fish,
               null,
               false,
               "east",
               courtyard,
               "You are standing on one side of a drawbridge leading to ACTION CASTLE. There is a satisfied troll here.", 20);

      ContainerItem tower = new SwitchItem(
               "tower",
               "You are inside a tower. The princess is here.",
               rose,
               null,
               false,
               null,
               null,
               "You are inside a tower. The princess is here. She says she will marry you if you have a crown.", 10);

      ContainerItem towerStairs = new SwitchItem(
               "tower stairs",
               "You are climbing the stairs to the tower. There is a locked door here.",
               key, null, false, "up", tower,
               "You are climbing the stairs to the tower. There is an unlocked door here.", 5);

      Item crown = new Item("crown", "tool", "an ornate golden crown");
      ContainerItem dungeon = new SwitchItem(
               "dungeon",
               "You are in the dungeon. There is a spooky ghost here, wearing a crown.",
               candle, crown, true, null, null, "You are in the dungeon.", 10);

      ContainerItem dungeonStairs = new SwitchItem(
               "dungeon stairs",
               "You are climbing the stairs down to the dungeon. It is too dark to see!",
               lamp, null, true, "down", dungeon,
               "You are climbing the stairs down to the dungeon.", 5);

      ContainerItem throne = new SwitchItem(
               "throne room",
               "This is the throne room of ACTION CASTLE. There is an ornate golden throne here.",
               crown,
               null,
               true,
               null,
               null,
               "This is the throne room of ACTION CASTLE. You are sitting in an ornate golden throne, with your new queen by your side.", 20);

      map.addLocation(cottage);
      map.addLocation(garden);
      map.addLocation(pond);
      map.addLocation(path);
      map.addLocation(tree);
      map.addLocation(drawbridge);
      map.addLocation(courtyard);
      map.addLocation(hall);
      map.addLocation(towerStairs);
      map.addLocation(tower);
      map.addLocation(dungeonStairs);
      map.addLocation(dungeon);
      map.addLocation(throne);

      map.addNeighbor(cottage, "out", garden);
      map.addNeighbor(garden, "in", cottage);

      map.addNeighbor(garden, "south", pond);
      map.addNeighbor(pond, "north", garden);

      map.addNeighbor(garden, "north", path);
      map.addNeighbor(path, "south", garden);

      map.addNeighbor(path, "up", tree);
      map.addNeighbor(tree, "down", path);

      map.addNeighbor(path, "east", drawbridge);
      map.addNeighbor(drawbridge, "west", path);

      map.addNeighbor(courtyard, "west", drawbridge);

      map.addNeighbor(courtyard, "up", towerStairs);
      map.addNeighbor(towerStairs, "down", courtyard);

      map.addNeighbor(tower, "down", towerStairs);

      map.addNeighbor(courtyard, "down", dungeonStairs);
      map.addNeighbor(dungeonStairs, "up", courtyard);

      map.addNeighbor(dungeon, "up", dungeonStairs);

      map.addNeighbor(hall, "west", courtyard);

      map.addNeighbor(hall, "east", throne);
      map.addNeighbor(throne, "west", hall);

      this.goal = 100;
      
      return cottage;
   }

   private GameMap map;
   private ContainerItem inventory;
   private ContainerItem location;
   private int moves;
   private int points;
   private int goal;
}