package io.github.speciial.fxmlBase;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BaseWindow {
    @FXML
    public Label randomLabel;

    public void initialize() {
        randomLabel.setText("Random Text");
    }

}
