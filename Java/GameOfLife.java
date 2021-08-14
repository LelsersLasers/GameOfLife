

public class GameOfLife {

    public static void main(String[] args) {
        int width = 45; // 20
        int height = 28; // 12
        int aliveChanceOnSpawn = 20;
        int delay = 50;

        Cell[][] cells = new Cell[width][height];

        for (int xPos = 0; xPos < width; xPos++) {
            for (int yPos = 0; yPos < height; yPos++) {
                cells[xPos][yPos] = new Cell(aliveChanceOnSpawn);
            }
        }

        while (true) {
            draw(width, height, cells);
            updateNeighbors(width, height, cells);
            try {
                Thread.sleep(delay);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static void draw(int width, int height, Cell[][] cells) {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        for (int i = 0; i < width + 2; i++) {
            System.out.print("#");
        }
        System.out.println("");
        for (int yPos = 0; yPos < height; yPos++) {
            System.out.print("#");
            for (int xPos = 0; xPos < width; xPos++) {
                cells[xPos][yPos].drawCell();
            }
            System.out.println("#");
        }
        for (int i = 0; i < width + 2; i++) {
            System.out.print("#");
        }
        System.out.println("");
    }

    static void updateNeighbors(int width, int height, Cell[][] cells) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int neighbors = 0;
                if (i - 1 > 0 && j - 1 > 0 && cells[i - 1][j - 1].alive) {
                    neighbors = neighbors + 1;
                }
                if (i - 1 > 0 && cells[i - 1][j].alive) {
                    neighbors = neighbors + 1;
                }
                if (i - 1 > 0 && j + 1 < height && cells[i - 1][j + 1].alive) {
                    neighbors = neighbors + 1;
                }
                if (j - 1 > 0 && cells[i][j - 1].alive) {
                    neighbors = neighbors + 1;
                }
                if (j + 1 < height && cells[i][j + 1].alive) {
                    neighbors = neighbors + 1;
                }
                if (i + 1 < width && j - 1 > 0 && cells[i + 1][j - 1].alive) {
                    neighbors = neighbors + 1;
                }
                if (i + 1 < width && cells[i + 1][j].alive) {
                    neighbors = neighbors + 1;
                }
                if (i + 1 < width && j + 1 < height && cells[i + 1][j + 1].alive) {
                    neighbors = neighbors + 1;
                }
                cells[i][j].neighbors = neighbors;
                if (neighbors == 3) {
                    cells[i][j].shouldBeAlive = true;
                }
                else if (neighbors < 2 || neighbors > 3) {
                    cells[i][j].shouldBeAlive = false;
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].alive = cells[i][j].shouldBeAlive;
            }
        }
    }
}