/**
CSC232 - Spring 2014
A class to represent an interactive container/location in an adventure game.

@author Brian Howard <bhoward@depauw.edu>
@version 2014-05-09
 */

package csc232.model;

/**
 * A <code>SwitchItem</code> behaves like an ordinary {@link ContainerItem},
 * except when a trigger object is added to it, it switches to the triggered
 * state: the description will change, the hidden object (if not null) will be
 * added to the container, and the points will be added to the player's total.
 * Furthermore, if it is being used as a location, a new or modified exit will
 * be inserted in the map, going in the specified direction and leading to the
 * specified neighbor. Finally, the <code>keep</code> flag determines whether
 * the trigger item is actually added or whether it is discarded.
 */
public class SwitchItem extends ContainerItem
{
   public SwitchItem(String shortName, String description, Item trigger,
            Item hidden, boolean keep, String direction,
            ContainerItem neighbor, String newDescription, int points)
   {
      super(shortName, description);
      this.trigger = trigger;
      this.hidden = hidden;
      this.keep = keep;
      this.direction = direction;
      this.neighbor = neighbor;
      this.newDescription = newDescription;
      this.points = points;
      this.triggered = false;
   }

   /* (non-Javadoc)
    * @see csc232.model.ContainerItem#addItem(csc232.model.Item, csc232.model.GameState)
    */
   @Override
   public void addItem(Item item, GameState gameState)
   {
      if (!triggered && item == trigger)
      {
         triggered = true;
         setDescription(newDescription);
         gameState.addPoints(points);
         if (hidden != null)
         {
            super.addItem(hidden, gameState);
         }
         if (direction != null)
         {
            gameState.putNeighbor(this, direction, neighbor);
         }
         if (keep)
         {
            super.addItem(item, gameState);
         }
      }
      else
      {
         super.addItem(item, gameState);
      }
   }

   private Item trigger;
   private Item hidden;
   private boolean keep;
   private String direction;
   private ContainerItem neighbor;
   private String newDescription;
   private int points;
   private boolean triggered;
}
