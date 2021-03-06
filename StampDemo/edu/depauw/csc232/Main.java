package edu.depauw.csc232;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      Canvas canvas = new Canvas(600, 400);
      GraphicsContext gc = canvas.getGraphicsContext2D();

      Stamp stamp = makeStamp();
      stamp.draw(gc);

      Pane root = new Pane(canvas);
      Scene scene = new Scene(root);

      stage.setScene(scene);
      stage.setTitle("Stamp Demo");
      stage.show();
   }

   /**
    * Demonstration of creating a Stamp.
    * 
    * @return the Stamp
    */
   public Stamp makeStamp() {
      Stamp house = new HouseStamp(20.0, 10.0);
      Stamp colorHouse = new RandomColorStamp(house);
      Stamp row = new StampCollection(new HorizontalStrategy(50.0), house, colorHouse, colorHouse, colorHouse);
      Stamp grid = new StampCollection(new VerticalStrategy(30.0), row, row, row);
      return grid;
   }

   public static void main(String[] args) {
      launch(args);
   }
}
