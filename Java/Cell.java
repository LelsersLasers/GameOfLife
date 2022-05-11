public class Cell {
    private boolean alive;
    private int neighbors;

    Cell(double aliveChanceOnSpawn) {
        alive = Math.random() < aliveChanceOnSpawn;
    }

    public boolean getAlive() { return alive; }
    public void clearNeighbors() { neighbors = 0; }
    public void addNeighbor(boolean condition) { neighbors += condition ? 1 : 0; }

    public void sync() {
        if (neighbors == 3) alive = true;
        else if (neighbors < 2 || neighbors > 3) alive = false;
    }

    void drawCell() {
        if (alive)System.out.print(".");
        else System.out.print(" ");
    }
}