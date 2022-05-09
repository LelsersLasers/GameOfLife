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

	private int size;
	private int spacer;
	private final double spawnChance = 0.2;

	private int fps;
	private double frame = 1;

	private boolean pause = false;
	private boolean simpleDraw = true;

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

		if (!pause) {
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
	}

	private void handleInputs() {
		pause = Gdx.input.isTouched();

		if (Gdx.input.isKeyPressed(Input.Keys.R)) {
			randomizeCells();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			simpleDraw = !simpleDraw;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			fps++;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
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
