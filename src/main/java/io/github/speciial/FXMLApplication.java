package io.github.speciial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLApplication extends Application {

    /*
        Take a closer look at the Java Jigsaw stuff, so that
        packages can be exported more conveniently!
     */

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxmlBase/base_window.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FXML Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
