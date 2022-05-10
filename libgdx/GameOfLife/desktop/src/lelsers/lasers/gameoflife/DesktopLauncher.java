package lelsers.lasers.gameoflife;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	// Settings
	private static final int WIDTH = 45;
	private static final int HEIGHT = 28;
	private static final int SIZE = 16;
	private static final int SPACER = 4;
	private static final int INITIAL_FPS = 8;

	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setResizable(false);
		config.setWindowIcon("icon.png");
		config.setWindowedMode(WIDTH * (SIZE + SPACER) + SPACER, HEIGHT * (SIZE + SPACER) + SPACER);

		config.setTitle("Game Of Life");
		new Lwjgl3Application(new GameOfLife(SIZE, SPACER, INITIAL_FPS), config);
	}
}
