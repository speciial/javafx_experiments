package io.github.speciial.girdClock;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.time.LocalTime;


public class GridClock {

    private int dotWidth;
    private int dotHeight;

    private double width;
    private double height;

    private double dotSize;
    private double outlineSize;
    private double space;

    private Canvas canvas;
    private GraphicsContext gc;

    public GridClock(double dotSize, double outlineSize, double space) {
        // hours, minutes, seconds
        this.dotSize = dotSize;
        this.outlineSize = outlineSize;
        this.space = space;

        this.dotWidth = GridNumbers.GRID_WIDTH * 6 + 2;
        this.dotHeight = GridNumbers.GRID_HEIGHT;

        // adding space once for initial offset
        this.width = this.dotWidth * (dotSize + space) + space;
        this.height = this.dotHeight * (dotSize + space) + space;

        this.canvas = new Canvas(this.width, this.height);
        this.gc = canvas.getGraphicsContext2D();

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                LocalTime now =  LocalTime.now();
                int hour = now.getHour();
                int minute = now.getMinute();
                int second = now.getSecond();

                clearCanvas();
                drawTimer(hour, minute, second);
            }
        }.start();
    }

    private void drawTimer(int hours, int minutes, int seconds) {
        char[] hourChar = (hours + "").toCharArray();
        char[] minuteChar = (minutes + "").toCharArray();
        char[] secondChar = (seconds + "").toCharArray();
        if(hourChar.length < 2) {
            drawNumber(0, 0);
            drawNumber(hourChar[0] - 48, 5);
        } else {
            drawNumber(hourChar[0] - 48, 0);
            drawNumber(hourChar[1] - 48, 5);
        }
        drawSpacer(10);
        if(minuteChar.length < 2) {
            drawNumber(0, 11);
            drawNumber(minuteChar[0] - 48, 16);
        } else {
            drawNumber(minuteChar[0] - 48, 11);
            drawNumber(minuteChar[1] - 48, 16);
        }
        drawSpacer(21);
        if(secondChar.length < 2) {
            drawNumber(0, 22);
            drawNumber(secondChar[0] - 48, 27);
        } else {
            drawNumber(secondChar[0] - 48, 22);
            drawNumber(secondChar[1] - 48, 27);
        }
    }

    private void drawNumber(int number, int dotOffset) {
        int numGridIndex = 0;
        for(int y = 0; y < GridNumbers.GRID_HEIGHT; y++) {
            for(int x = dotOffset; x < (GridNumbers.GRID_WIDTH + dotOffset); x++) {
                if(GridNumbers.NUMBERS[number][numGridIndex] == 0) {
                    gc.setFill(Color.color(0.2, 0.2, 0.2));
                    gc.fillOval(x * (dotSize + space) + space, y * (dotSize + space) + space, dotSize, dotSize);

                } else {
                    gc.setFill(Color.GREEN);
                    gc.fillOval(x * (dotSize + space) + space, y * (dotSize + space) + space, dotSize, dotSize);

                    double centerX = (x * (dotSize + space) + space) - (outlineSize / 2);
                    double centerY = (y * (dotSize + space) + space) - (outlineSize / 2);
                    gc.setFill(Color.color(0.0, 1.0, 0.0, 0.5));
                    gc.fillOval(centerX, centerY, dotSize + outlineSize, dotSize + outlineSize);
                }

                numGridIndex++;
            }
        }
    }

    private void drawSpacer(int dotOffset) {
        for(int y = 0; y < GridNumbers.GRID_HEIGHT; y++) {
            if((y % 2) == 0 || y == 1) {
                gc.setFill(Color.color(0.2, 0.2, 0.2));
                gc.fillOval(dotOffset * (dotSize + space) + space, y * (dotSize + space) + space, dotSize, dotSize);

            } else {
                gc.setFill(Color.GREEN);
                gc.fillOval(dotOffset * (dotSize + space) + space, y * (dotSize + space) + space, dotSize, dotSize);

                double sizeAdd = 5;
                double centerX = (dotOffset * (dotSize + space) + space) - (sizeAdd / 2);
                double centerY = (y * (dotSize + space) + space) - (sizeAdd / 2);
                gc.setFill(Color.color(0.0, 1.0, 0.0, 0.5));
                gc.fillOval(centerX, centerY, dotSize + sizeAdd, dotSize + sizeAdd);
            }
        }
    }

    private void clearCanvas() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, this.width, this.height);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

}
