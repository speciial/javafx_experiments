package io.github.speciial.fxmlBase;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BaseWindow {

    @FXML private TextField input_number;

    @FXML private Button button_mod;
    @FXML private Button button_CE;
    @FXML private Button button_C;
    @FXML private Button button_back;
    @FXML private Button button_oneOverX;
    @FXML private Button button_square;
    @FXML private Button button_sqrt;
    @FXML private Button button_negate;
    @FXML private Button button_decimal;
    @FXML private Button button_equals;

    @FXML private Button button_divide;
    @FXML private Button button_multiply;
    @FXML private Button button_subtract;
    @FXML private Button button_add;

    @FXML private Button button_zero;
    @FXML private Button button_one;
    @FXML private Button button_two;
    @FXML private Button button_three;
    @FXML private Button button_four;
    @FXML private Button button_five;
    @FXML private Button button_six;
    @FXML private Button button_seven;
    @FXML private Button button_eight;
    @FXML private Button button_nine;


    public void initialize() {
        input_number.setEditable(false);
        input_number.setAlignment(Pos.CENTER_RIGHT);

        button_zero.setOnAction(actionEvent -> {
            input_number.setText(input_number.getText() + "0");
        });
    }

}
