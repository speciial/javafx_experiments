module javafx.experiments {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires jlatexmath;

    opens io.github.speciial.fxmlBase;

    exports io.github.speciial;
    exports io.github.speciial.fxmlBase;
}