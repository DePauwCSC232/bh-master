package edu.depauw.csc232;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

   @Override
   public void start(Stage stage) throws Exception {
      Canvas canvas = new Canvas(600, 400);

      ImageStack stack = new ImageStack(canvas);
      stack.addImage(Image.rectangle(0, 0, 1, 1, Color.DARKGRAY));
      stack.addImage(Image.rectangle(0, 0.8, 1, 0.2, Color.BLACK));
      stack.addImage(Image.ellipse(0.1, 0.1, 0.2, 0.2, Color.WHITE));
      stack.addImage(Image.ellipse(0.5, 0.5, 0.4, 0.3, Color.ORANGE));
      stack.addImage(Image.rectangle(0.68, 0.45, 0.04, 0.06, Color.GREEN));
      stack.addImage(Image.rectangle(0.6, 0.55, 0.05, 0.1, Color.YELLOW));
      stack.addImage(Image.rectangle(0.75, 0.55, 0.05, 0.1, Color.YELLOW));
      stack.addImage(Image.rectangle(0.6, 0.7, 0.2, 0.05, Color.YELLOW));

      Pane root = new Pane(canvas);
      Scene scene = new Scene(root);

      scene.widthProperty().addListener(e -> stack.draw(scene.getWidth(), scene.getHeight()));
      scene.heightProperty().addListener(e -> stack.draw(scene.getWidth(), scene.getHeight()));

      stage.setScene(scene);
      stage.setTitle("Image Demo");
      stage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}
