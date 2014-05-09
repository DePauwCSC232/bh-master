/**
CSC232 - Spring 2014
A class to represent a map in an adventure game.

@author Brian Howard <bhoward@depauw.edu>
@version 2014-05-09
 */

package csc232.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A <code>GameMap</code> keeps track of all of the available locations, as well
 * as the connections between them.
 */
public class GameMap
{
   public GameMap()
   {
      this.locations = new HashMap<String, ContainerItem>();
      this.neighbors = new HashMap<String, Map<String, String>>();
   }

   /**
    * Add a {@link ContainerItem} to the collection of known locations.
    * 
    * @param location
    */
   public void addLocation(ContainerItem location)
   {
      String name = location.getShortName();
      locations.put(name, location);
      neighbors.put(name, new HashMap<String, String>());
   }

   /**
    * Add a connection from the given location, in the given direction, to reach
    * the given neighboring location. Assumes that both locations have already
    * been added to the map.
    * 
    * @param location
    *           the starting location
    * @param direction
    *           the desired exit direction
    * @param neighbor
    *           the destination
    */
   public void addNeighbor(ContainerItem location, String direction,
            ContainerItem neighbor)
   {
      String name = location.getShortName();
      neighbors.get(name).put(direction, neighbor.getShortName());
   }

   /**
    * Return a comma-separated string of directions in which neighbors may be
    * reached from the given location. If there are no neighbors, return "".
    * Assumes that the given {@link ContainerItem} is actually a location,
    * present in the map.
    * 
    * @param location
    *           the starting location
    * @return the list of available exit directions
    */
   public String listNeighbors(ContainerItem location)
   {
      String result = "";

      String name = location.getShortName();
      for (String direction : neighbors.get(name).keySet())
      {
         if (!result.equals(""))
         {
            result = result + ", ";
         }

         result = result + direction;
      }

      return result;
   }

   /**
    * Return the neighbor of the given location in the given direction, or null
    * if there is no such neighbor. Assumes that the given {@link ContainerItem}
    * is actually a location, present in the map.
    * 
    * @param location
    *           the starting location
    * @param direction
    *           the direction in which to travel
    * @return the neighbor, or null
    */
   public ContainerItem getNeighbor(ContainerItem location, String direction)
   {
      String name = location.getShortName();
      String neighbor = neighbors.get(name).get(direction);
      return locations.get(neighbor);
   }

   private Map<String, ContainerItem> locations;
   private Map<String, Map<String, String>> neighbors;
}
