package lelsers.lasers.gameoflife;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameOfLife extends Game {

	private int size;
	private int spacer;
	private final double spawnChance = 0.2;

	private int fps;
	private double frame = 1;

	private boolean paused = false;
	private boolean simpleDraw = true;

	private ToggleKey mouseTK = new ToggleKey();
	private ToggleKey spaceTK = new ToggleKey();
	private ToggleKey upTK = new ToggleKey();
	private ToggleKey downTK = new ToggleKey();

	private ShapeRenderer shape;
	private Cell[][] cells;

	public GameOfLife(int size, int spacer, int fps) {
		this.size = size;
		this.spacer = spacer;
		this.fps = fps;
	}

	@Override
	public void create() {
		shape = new ShapeRenderer();

		cells = new Cell[Gdx.graphics.getWidth()/(size + spacer)][Gdx.graphics.getHeight()/(size + spacer)];
		randomizeCells();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		shape.setColor(Color.GOLDENROD);
	}

	@Override
	public void render() {
		handleInputs();

		if (!paused) {
			frame += Gdx.graphics.getDeltaTime();
			if (frame >= 1.0/fps) {
				updateCells();
				while (frame >= 1.0/fps) {
					frame -= 1.0/fps;
				}
			}
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		drawCells();
		shape.end();
	}

	private void randomizeCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(spawnChance, size, i * (size + spacer) + spacer, j * (size + spacer) + spacer);
			}
		}
		updateCells();
	}

	private void handleInputs() {
		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			randomizeCells();
		}

		if (mouseTK.down(Gdx.input.isTouched())) {
			paused = !paused;
		}
		if (spaceTK.down(Gdx.input.isKeyPressed(Input.Keys.SPACE))) {
			simpleDraw = !simpleDraw;
		}

		if (upTK.down(Gdx.input.isKeyPressed(Input.Keys.UP))) {
			fps++;
		}
		if (downTK.down(Gdx.input.isKeyPressed(Input.Keys.DOWN))) {
			if (fps > 1) {
				fps--;
			}
		}

		if (Gdx.input.isKeyPressed(Input.Keys.Q) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	private void drawCells() {
		for (Cell[] cell : cells) {
			for (Cell c : cell) {
				c.sync();
				c.render(shape, simpleDraw);
			}
		}
	}

	private void updateCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				int[][] offsets = {
						{1, 0},
						{1, 1},
						{1, -1},
						{0, 1},
						{0, -1},
						{-1, 0},
						{-1, 1},
						{-1, -1}
				};

                cells[i][j].clearNeighbors();
				for (int[] offset : offsets) {
					try {
                        cells[i][j].addNeighbor(cells[i + offset[0]][j + offset[1]].getAlive());
					}
					catch (ArrayIndexOutOfBoundsException ignored) {}
				}
			}
		}
	}
}
