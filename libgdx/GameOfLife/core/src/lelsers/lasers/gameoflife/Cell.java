package lelsers.lasers.gameoflife;

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

    public void setNeighbors(int neighbors) {
        this.neighbors = neighbors;
    }

    public void sync() {
        if (neighbors == 3) {
            alive = true;
        }
        else if (neighbors < 2 || neighbors > 3) {
            alive = false;
        }
    }

    public void render(ShapeRenderer shape, boolean simple) {
        if (alive) {
            if (simple) {
                shape.rect(x, y, size, size);
            }
        }
    }
}
