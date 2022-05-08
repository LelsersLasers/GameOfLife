package lelsers.lasers.gameoflife;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import lelsers.lasers.gameoflife.GameOfLife;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(10);
		config.setResizable(false);
		config.setWindowedMode(45 * 20 + 4, 28 * 20 + 4);

		config.setTitle("Game Of Life");
		new Lwjgl3Application(new GameOfLife(), config);
	}
}
