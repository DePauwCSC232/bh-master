package csc232.model;

public class SwitchLocation extends ContainerItem
{

   public SwitchLocation(String shortName, String descriptionA, Item trigger,
            String direction, ContainerItem location, String descriptionB)
   {
      super(shortName, descriptionA);
      this.trigger = trigger;
      this.direction = direction;
      this.location = location;
      this.newDescription = descriptionB;
   }

   @Override
   public String getDescription(GameState gameState)
   {
      // TODO Auto-generated method stub
      return super.getDescription(gameState);
   }

   @Override
   public void addItem(Item item, GameState gameState)
   {
      if (item == trigger)
      {
         // TODO
      }
      super.addItem(item);
   }

   private Item trigger;
   private String direction;
   private ContainerItem location;
   private String newDescription;
}
