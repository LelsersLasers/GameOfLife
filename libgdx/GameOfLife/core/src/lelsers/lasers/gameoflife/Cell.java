package lelsers.lasers.gameoflife;

public class Cell {
    private boolean isAlive;
    private boolean shouldBeAlive;

    Cell(float aliveChanceOnSpawn) {
        isAlive = Math.random() < aliveChanceOnSpawn;
        shouldBeAlive = isAlive;
    }

    public boolean getIsAlive() {
        return isAlive;
    }
    public void setIsAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isShouldBeAlive() {
        return shouldBeAlive;
    }
    public void setShouldBeAlive(boolean alive) {
        shouldBeAlive = alive;
    }
}
