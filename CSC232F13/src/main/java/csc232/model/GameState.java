package csc232.model;

/**
 * Wraps up information about the player's inventory, location, and the game map
 * in a single object.
 */
public class GameState
{
   public GameState()
   {
      this.inventory = new ContainerItem("inventory",
               "The stuff you are carrying");
      this.map = new GameMap();
//      this.location = populateDemoMap();
      this.location = populateCastleMap();
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
      return location.getDescription(this);
   }

   /**
    * @return a prompt string based on the current game state
    */
   public String getPrompt()
   {
      return location.getShortName();
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

   private ContainerItem populateDemoMap()
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

      library.addItem(new Item("dictionary", "media", "it is very heavy"));
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

   /**
    * Load items and locations for the Parsely game, "ACTION CASTLE!" (c) 2009,
    * Jared A. Sorensen / Memento Mori Theatricks http://www.memento-mori.com/
    * Content used here solely for educational purposes, not for distribution
    */
   private ContainerItem populateCastleMap()
   {
      ContainerItem cottage = new ContainerItem("cottage",
               "You are standing in a small cottage.");
      ContainerItem garden = new ContainerItem("garden",
               "You are standing on a lush garden path. There is a cottage here.");
      ContainerItem pond = new ContainerItem("pond",
               "You are at the edge of a small fishing pond.");
      ContainerItem path = new ContainerItem("path",
               "You are walking along a winding path. There is a tall tree here.");
      
      ContainerItem tree = new ContainerItem("tree",
               "You are at the top of the tall tree.");
      Item branch = new Item("branch", "tool", "a stout, dead branch");
      tree.addItem(branch);
      
      ContainerItem hall = new ContainerItem("hall", "You stand inside the Great Feasting Hall.");
      
      Item key = new Item("key", "tool", "a heavy iron key");
      ContainerItem courtyard = new SwitchItem(
               "courtyard",
               "You are in the courtyard of ACTION CASTLE. There is a guard here, blocking the path east.",
               branch,
               key,
               false,
               "east",
               hall,
               "You are in the courtyard of ACTION CASTLE. There is an unconscious guard here.");
      Item fish = new Item("fish", "food", "a raw fish");
      pond.addItem(fish); // TODO make the pond a Switch
      
      ContainerItem drawbridge = new SwitchItem(
               "drawbridge",
               "You are standing on one side of a drawbridge leading to ACTION CASTLE. There is a mean troll here.",
               fish,
               null,
               false,
               "east",
               courtyard,
               "You are standing on one side of a drawbridge leading to ACTION CASTLE. There is a satisfied troll here.");

      map.addLocation(cottage);
      map.addLocation(garden);
      map.addLocation(pond);
      map.addLocation(path);
      map.addLocation(tree);
      map.addLocation(drawbridge);
      map.addLocation(courtyard);
      map.addLocation(hall);
      
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
      
      map.addNeighbor(hall, "west", courtyard);
      
      return cottage;
   }

   private GameMap map;
   private ContainerItem inventory;
   private ContainerItem location;
}