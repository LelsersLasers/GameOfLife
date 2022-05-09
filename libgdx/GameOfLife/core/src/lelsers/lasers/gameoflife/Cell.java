package lelsers.lasers.gameoflife;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Cell {
    private boolean alive;
    private int x, y, size;

    private int neighbors = 0;

    Cell(double aliveChanceOnSpawn, int size, int x, int y) {
        alive = Math.random() < aliveChanceOnSpawn;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public boolean getAlive() {
        return alive;
    }

    public void clearNeighbors() {
        neighbors = 0;
    }

    public void addNeighbor(boolean condition) {
        neighbors += condition ? 1 : 0;
    }

    public void sync() {
        if (neighbors == 3) {
            alive = true;
        }
        else if (neighbors < 2 || neighbors > 3) {
            alive = false;
        }
    }

    public void render(ShapeRenderer shape, boolean simpleDraw) {
        shape.setColor(Color.BLACK);
        if (alive) {
            shape.setColor(Color.GOLDENROD);
        }
        else if (!simpleDraw) {
            float brightness = (float)neighbors/9 + (float)1/9;
            shape.setColor(new Color(brightness, brightness, brightness, 1));
        }
        shape.rect(x, y, size, size);
    }
}
