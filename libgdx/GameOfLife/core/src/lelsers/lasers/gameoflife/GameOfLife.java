package lelsers.lasers.gameoflife;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOfLife extends Game {

	private final int SIZE = 16;
	private final int SPACER = 4;
	private final double spawnChance = 0.2;

	private boolean pause = false;

	private ShapeRenderer shape;
	private Cell[][] cells;

	@Override
	public void create() {
		shape = new ShapeRenderer();

		cells = new Cell[Gdx.graphics.getWidth()/(SIZE + SPACER) + 1][Gdx.graphics.getHeight()/(SIZE + SPACER) + 1];
		randomizeCells();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		shape.setColor(Color.GOLDENROD);
	}

	@Override
	public void render() {
		handleInputs();

		if (!pause) {
			updateCells();
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		drawCells();
		shape.end();
	}

	private void randomizeCells() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(spawnChance, SIZE, i * (SIZE + SPACER) + SPACER, j * (SIZE + SPACER) + SPACER);
			}
		}
	}

	private void handleInputs() {
		pause = Gdx.input.isTouched();

		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			randomizeCells();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	private void drawCells() {
		for (Cell[] cell : cells) {
			for (Cell c : cell) {
				c.sync();
				c.render(shape);
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

				int neighbors = 0;
				for (int[] offset : offsets) {
					try {
						neighbors += cells[i + offset[0]][j + offset[1]].getIsAlive() ? 1 : 0;
					}
					catch (ArrayIndexOutOfBoundsException ignored) {}
				}

				if (neighbors == 3) {
					cells[i][j].setShouldBeAlive(true);
				}
				else if (neighbors < 2 || neighbors > 3) {
					cells[i][j].setShouldBeAlive(false);
				}
			}
		}
	}
}
