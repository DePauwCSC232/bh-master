package csc232.model;

/**
 * A <code>SwitchItem</code> behaves like an ordinary {@link ContainerItem},
 * except when a trigger object is added to it, it switches to the triggered
 * state: the description will change, and the hidden object (if not null) will
 * be added to the container. Furthermore, if it is being used as a location, a
 * new or modified exit will be inserted in the map, going in the specified
 * direction and leading to the specified neighbor. Finally, the
 * <code>keep</code> flag determines whether the trigger item is actually added
 * or whether it is discarded.
 */
public class SwitchItem extends ContainerItem
{
   public SwitchItem(String shortName, String descriptionA, Item trigger,
            Item hidden, boolean keep, String direction,
            ContainerItem neighbor, String descriptionB)
   {
      super(shortName, descriptionA);
      this.trigger = trigger;
      this.hidden = hidden;
      this.keep = keep;
      this.direction = direction;
      this.neighbor = neighbor;
      this.newDescription = descriptionB;
      this.triggered = false;
   }

   @Override
   public String getDescription(GameState gameState)
   {
      if (triggered)
      {
         return newDescription;
      }
      else
      {
         return super.getDescription(gameState);
      }
   }

   @Override
   public void addItem(Item item, GameState gameState)
   {
      if (item == trigger)
      {
         triggered = true;
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
   private boolean triggered;
}
