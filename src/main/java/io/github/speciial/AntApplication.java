package io.github.speciial;

import io.github.speciial.ant.Ant;
import io.github.speciial.ant.Town;
import io.github.speciial.ant.vec2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AntApplication extends Application {

    Town[] towns = new Town[20];
    int currentTownIndex = 0;

    Ant ant;

    private double euclideanDistance(vec2 p1, vec2 p2) {
        double xx = (p1.x - p2.x) * (p1.x - p2.x);
        double yy = (p1.y - p2.y) * (p1.y - p2.y);

        return Math.sqrt(xx + yy);
    }

    private vec2 lerp(vec2 p1, vec2 p2, double t) {
        vec2 result = new vec2();

        result.x = ((1 - t) * p1.x) + (t * p2.x);
        result.y = ((1 - t) * p1.y) + (t * p2.y);

        return result;
    }

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        EventHandler<KeyEvent> keyEventEventHandler = keyEvent -> {
            if(keyEvent.getCode().equals(KeyCode.S)) {
                // TODO: Start animation
            }
        };

        canvas.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getSceneX();
            double y = mouseEvent.getSceneY();

            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                towns[currentTownIndex] = new Town(x, y);
                currentTownIndex++;

                if (currentTownIndex == 20) {
                    currentTownIndex = 0;
                }
            } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                vec2 mousePos = new vec2(x, y);
                for (int townIndex = 0; townIndex < 20; townIndex++) {
                    if (towns[townIndex] != null) {
                        if (euclideanDistance(mousePos, towns[townIndex].pos) < 10) {
                            ant = new Ant(towns[townIndex]);
                        }
                    }
                }
            }
        });


        AnimationTimer timer = new AnimationTimer() {
            long lastTime;

            boolean shouldSetClosestTown = true;

            @Override
            public void handle(long now) {
                long deltaNS = now - lastTime;
                double deltaMS = deltaNS / 1000000.0;

                gc.setFill(Color.rgb(50, 50, 50));
                gc.fillRect(0, 0, 1280, 720);

                for (int townIndex = 0; townIndex < 20; townIndex++) {
                    if (towns[townIndex] != null) {
                        if (ant != null) {
                            gc.setFill(Color.rgb(200, 80, 100));
                            gc.strokeLine(ant.pos.x, ant.pos.y, towns[townIndex].pos.x, towns[townIndex].pos.y);
                        }

                        gc.setFill(Color.rgb(200, 80, 100));
                        gc.fillOval(towns[townIndex].pos.x - 10, towns[townIndex].pos.y - 10, 20, 20);
                    }
                }

                if (ant != null) {
                    ant.movingDuration += deltaMS;
                    if(ant.movingDuration >= 1) {
                        ant.movingDuration = 0;
                    }

                    gc.setFill(Color.rgb(0, 0, 0));
                    gc.fillOval(ant.pos.x - 8, ant.pos.y - 8, 16, 16);
                }

                lastTime = now;
            }
        };
        timer.start();

        Scene scene = new Scene(new StackPane(canvas));
        scene.setOnKeyPressed(keyEventEventHandler);
        stage.setTitle("Ant Simulation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
