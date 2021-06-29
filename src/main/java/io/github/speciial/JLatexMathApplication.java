package io.github.speciial;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JLatexMathApplication extends Application {

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 600);
        StackPane root = new StackPane(canvas);

        String latex = "\\ a^2 + b^2 = c^2 \\";
        TeXFormula teXFormula = new TeXFormula(latex);

        TeXIcon icon = teXFormula.createTeXIcon(TeXConstants.STYLE_DISPLAY, (float) Font.getDefault().getSize());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        WritableImage outputImage = new WritableImage(icon.getIconWidth(), icon.getIconHeight());
        BufferedImage image = new BufferedImage(icon.getIconWidth(),
                icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.createGraphics();
        try {
            icon.paintIcon(new JPanel(), g, 0, 0);
        } finally {
            g.dispose();
        }
        SwingFXUtils.toFXImage(image, outputImage);

        gc.drawImage(outputImage, icon.getIconWidth(), icon.getIconHeight());

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("JLatexViewer");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
