package com.mygdx.game.desktop;

import java.awt.DisplayMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.TestGDX;

public class DesktopLauncher {
	public static void main (String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false; 
		config.width = 1800;
		config.height = 1600;
		config.fullscreen = false;
		config.backgroundFPS = -1;
		config.foregroundFPS = 60;
		new LwjglApplication(new TestGDX(), config);
	}
}
