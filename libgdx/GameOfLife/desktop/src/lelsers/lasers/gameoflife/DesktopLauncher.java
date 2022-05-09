package lelsers.lasers.gameoflife;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	private static final int SIZE = 16;
	private static final int SPACER = 4;
	private static final int FPS = 8;

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setResizable(false);
		config.setWindowedMode(45 * (SIZE + SPACER) + SPACER, 28 * (SIZE + SPACER) + SPACER);

		config.setTitle("Game Of Life");
		new Lwjgl3Application(new GameOfLife(SIZE, SPACER, FPS), config);
	}
}
