package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameSave {
	private Preferences pref;
	public GameSave(String name) {
		pref = Gdx.app.getPreferences(name);
	}

}
