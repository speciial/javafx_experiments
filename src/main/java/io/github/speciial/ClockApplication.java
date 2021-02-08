package io.github.speciial;

import io.github.speciial.girdClock.GridClock;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClockApplication extends Application {

    @Override
    public void start(Stage stage) {
        GridClock clock = new GridClock(10.0, 5.0, 6.0);

        Scene scene = new Scene(new StackPane(clock.getCanvas()));
        stage.setScene(scene);
        stage.setTitle("Digital Clock");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}