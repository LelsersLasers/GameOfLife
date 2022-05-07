package lelsers.lasers.gameoflife;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOfLife extends Game {

	private final int SIZE = 8;
	private final int SPACER = 2;
	private final double spawnChance = 0.3;

	private ShapeRenderer shape;
	private Cell[][] cells;

	@Override
	public void create() {
		shape = new ShapeRenderer();

		cells = new Cell[Gdx.graphics.getWidth()/(SIZE + SPACER) + 1][Gdx.graphics.getHeight()/(SIZE + SPACER) + 1];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(spawnChance, SIZE, i * (SIZE + SPACER) + SPACER, j * (SIZE + SPACER) + SPACER);
			}
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		updateAndDrawCells();
		shape.end();
	}

	private void updateAndDrawCells() {

		for (int i = 1; i < cells.length - 1; i++) {
			for (int j = 1; j < cells[i].length - 1; j++) {
				int offsets[][] = {
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
				for (int offset[] : offsets) {
					neighbors += cells[i + offset[0]][j + offset[1]].getIsAlive() ? 1 : 0;
				}

				if (neighbors == 3) {
					cells[i][j].setShouldBeAlive(true);
				}
				else if (neighbors < 2 || neighbors > 3) {
					cells[i][j].setShouldBeAlive(false);
				}
			}
		}

		for (Cell[] cell : cells) {
			for (Cell c : cell) {
				c.sync();
				c.render(shape);
			}
		}
	}
}
