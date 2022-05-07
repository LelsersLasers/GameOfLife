package lelsers.lasers.gameoflife;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Cell {
    private boolean isAlive, shouldBeAlive;
    private int x, y, size;

    Cell(double aliveChanceOnSpawn, int s, int i, int j) {
        isAlive = Math.random() < aliveChanceOnSpawn;
        shouldBeAlive = isAlive;
        size = s;
        x = i;
        y = j;
    }

    public boolean getIsAlive() {
        return isAlive;
    }
    public void setShouldBeAlive(boolean alive) {
        shouldBeAlive = alive;
    }

    public void sync() {
        isAlive = shouldBeAlive;
    }

    public void render(ShapeRenderer shape) {
        if (isAlive) {
            shape.setColor(Color.WHITE);
            shape.rect(x, y, size, size);
        }
    }
}
