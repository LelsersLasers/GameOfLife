

public class GameOfLife {

    public static final int width = 45;
    public static final int height = 28;
    public static final double aliveChanceOnSpawn = 0.20;
    public static final int delay = 50;

    public static void main(String[] args) {
        Cell[][] cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(aliveChanceOnSpawn);
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
        System.out.print("\033[H\033[2J"); // won't work with windows cmd
        System.out.flush();
        for (int i = 0; i < width + 2; i++) System.out.print("#");
        System.out.println("");
        for (int yPos = 0; yPos < height; yPos++) {
            System.out.print("#");
            for (int xPos = 0; xPos < width; xPos++) cells[xPos][yPos].drawCell();
            System.out.println("#");
        }
        for (int i = 0; i < width + 2; i++) System.out.print("#");
        System.out.println("");
    }

    static void updateNeighbors(int width, int height, Cell[][] cells) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].clearNeighbors();
				int[][] offsets = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
				for (int[] offset : offsets) {
					try {
                        cells[i][j].addNeighbor(cells[i + offset[0]][j + offset[1]].getAlive());
					}
					catch (ArrayIndexOutOfBoundsException ignored) {}
				}
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].sync();
            }
        }
    }
}