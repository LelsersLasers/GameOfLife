package lelsers.lasers.gameoflife;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Cell {
    private boolean alive;
    private int x, y, size, neighbors, status;

    Cell(double aliveChanceOnSpawn, int size, int x, int y) {
        alive = Math.random() < aliveChanceOnSpawn;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public boolean getAlive() { return alive; }
    public void clearNeighbors() { neighbors = 0; }
    public void addNeighbor(boolean condition) { neighbors += condition ? 1 : 0; }

    public void sync() {
        boolean aliveBefore = alive;
        if (neighbors == 3) alive = true;
        else if (neighbors < 2 || neighbors > 3) alive = false;

        if (!aliveBefore && !alive) status = 0; // still dead
        else if (!aliveBefore) status = 1; // newly alive
        else if (!alive) status = 2; // newly dead
        else status = 3; // still alive
    }

    public void render(ShapeRenderer shape, int drawMode) {
        if (drawMode == 0) { // gold if alive else black
            if (alive) shape.setColor(Color.GOLDENROD);
            else shape.setColor(Color.BLACK);
        }
        else if (drawMode == 1) { // black if still dead, green if newly alive, red if newly dead, blue if still alive
            Color[] colors = {Color.BLACK, Color.GREEN, Color.RED, Color.BLUE};
            shape.setColor(colors[status]);
        }
        else { // yellow if alive else neighbors as greyscale
            if (alive) shape.setColor(Color.YELLOW);
            else {
                float brightness = (float)neighbors/8;
                shape.setColor(new Color(brightness, brightness, brightness, 1));
            }
        }
        shape.rect(x, y, size, size);
    }

    public void writeText(SpriteBatch batch, BitmapFont font) {
        if (neighbors > 0) {
            font.draw(batch, Integer.toString(neighbors), (int)(x + size * 1.0/4), (int)(y + size * 3.0/4));
        }
    }
}
