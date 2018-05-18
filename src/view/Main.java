package view;

import controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Sprite;

public class Main extends Application {

    private GraphicsContext gc;
    private Controller controller;

    public void repaintScene() {

        gc.drawImage( new Image("file:cloth.png"), 0, 0 );

        for (Sprite source: controller.getSources()){
            repaintSprite(source);
        }

    }

    private void repaintSprite(Sprite sprite) {
        gc.drawImage(sprite.getImage(), sprite.getPositionX(), sprite.getPositionY());
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setResizable(false);

        Group root = new Group();

        controller=new Controller(this, "file:flour.png");

        double sceneWidth =100, sceneHeight=200;

        for (Sprite sprite:controller.getSources()){
            sceneWidth+=sprite.getBoundary().getWidth();
            if (sprite.getBoundary().getHeight()>sceneHeight)
                sceneHeight=sprite.getBoundary().getHeight();
        }

        Scene scene=new Scene(root, sceneWidth, sceneHeight);

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (controller.getSourceInPosition(event.getX(),event.getY()).isPresent())
                    controller.onDragCurrentSource(controller.getSourceInPosition(event.getX(),event.getY()).get(),
                            event.getX(), event.getY());
            }
        });

        primaryStage.setScene(scene);

        primaryStage.setTitle("Cook");
        primaryStage.show();

        Canvas canvas = new Canvas( scene.getWidth(), scene.getHeight() );

        root.getChildren().add(canvas);

        this.gc = canvas.getGraphicsContext2D();

        repaintScene();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
