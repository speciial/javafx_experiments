package io.github.speciial.ant;

import javafx.scene.canvas.GraphicsContext;

public class Ant {

    public Town currentTown;
    public vec2 pos;

    public double movingDuration;

    public Ant(Town town) {
        this.currentTown = town;
        pos = new vec2(town.pos.x, town.pos.y);
    }

    public void draw(GraphicsContext gc) {

    }

}
