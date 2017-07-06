package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.midfag.game.Main;

public class DesktopLauncher {
	public static LwjglApplicationConfiguration cfg;
	
	public static void main (String[] arg) {
		cfg = new LwjglApplicationConfiguration();
		
		//cfg.useGL30=true;
		cfg.title = "EndlessWorld";
		
		cfg.width = 1000;
		cfg.height = 700;
		cfg.samples=4;
		
		new LwjglApplication(new Main(), cfg);
	}
}
