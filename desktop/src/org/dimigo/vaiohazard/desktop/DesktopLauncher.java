package org.dimigo.vaiohazard.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.dimigo.vaiohazard.VaioHazardGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Vaio Hazard";
		config.width = 1280;
		config.height = 720;
		config.resizable = true;

		new LwjglApplication(new VaioHazardGame(), config);
	}
}
