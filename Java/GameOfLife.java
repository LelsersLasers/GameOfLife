

public class GameOfLife {

    public static final int WIDTH = 45;
    public static final int HEIGHT = 27;
    public static final double aliveChanceOnSpawn = 0.2;
    public static final double FPS = 8.0;

    public static void main(String[] args) {
        Cell[][] cells = new Cell[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) cells[x][y] = new Cell(aliveChanceOnSpawn);
        }
        while (true) {
            double start = System.nanoTime();

            draw(cells);
            updateNeighbors(cells);

            double sleepTime = (1_000_000_000.0/FPS - (System.nanoTime() - start))/1_000_000.0;
            try { Thread.sleep((long)((sleepTime > 0) ? sleepTime : 0)); }
            catch(InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }

    static void draw(Cell[][] cells) {
        System.out.print("\033[H\033[2J"); // won't work with windows cmd
        System.out.flush();
        for (int i = 0; i < WIDTH + 2; i++) System.out.print("#");
        System.out.println("");
        for (int y = 0; y < HEIGHT; y++) {
            System.out.print("#");
            for (int x = 0; x < WIDTH; x++) cells[x][y].drawCell();
            System.out.println("#");
        }
        for (int i = 0; i < WIDTH + 2; i++) System.out.print("#");
    }

    static void updateNeighbors(Cell[][] cells) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                cells[x][y].clearNeighbors();
				int[][] offsets = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
				for (int[] offset : offsets) {
					try { cells[x][y].addNeighbor(cells[x + offset[0]][y + offset[1]].getAlive()); }
					catch (ArrayIndexOutOfBoundsException ignored) {}
				}
            }
        }
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) cells[x][y].sync();
        }
    }
}