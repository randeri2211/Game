package Screens;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import static com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration.getDisplayMode;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setFullscreenMode(getDisplayMode());
		config.setForegroundFPS(144);
		config.setTitle("My GDX Game");
		new Lwjgl3Application(new Main(), config);
	}
}
