import java.util.Random;


public class Cell {
   
    int neighbors = - 1;
    boolean alive = false;
    boolean shouldBeAlive;

    Cell(int aliveChanceOnSpawn) {
        if (new Random().nextInt(100) < aliveChanceOnSpawn) {
            alive = true;
        }
        shouldBeAlive = alive;
    }

    void drawCell() {
        if (alive) {
            // print(neighbors);
            System.out.print(".");
        }
        else {
            System.out.print(" ");
        }
    }
}