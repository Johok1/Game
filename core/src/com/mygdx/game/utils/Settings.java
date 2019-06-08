package com.mygdx.game.utils;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

public class Settings {
	public static Preferences pref = Gdx.app.getPreferences("settings");
	private static boolean saved = false; 
	public Settings() {
		if(!saved) {
			defaults();
		}
	}

	private void defaults() {
		pref.putInteger("MoveUp", Input.Keys.valueOf("W"));
		pref.putInteger("MoveDown", Input.Keys.valueOf("S"));
		pref.putInteger("MoveLeft", Input.Keys.valueOf("A"));
		pref.putInteger("MoveRight", Input.Keys.valueOf("D"));
		pref.putInteger("PauseScreen", Input.Keys.valueOf("Escape"));
		pref.flush();
	}
	public void save() {
		this.saved = true; 
	}
	public void setDefault() {
		this.saved = false;
	}
	public void setMoveUp(String key) {
		pref.putInteger("MoveUp", Input.Keys.valueOf(key));
		pref.flush();
	}
	public void setMoveDown(String key) {
		pref.putInteger("MoveDown", Input.Keys.valueOf(key));
		pref.flush();
	}
	public void setMoveLeft(String key) {
		pref.putInteger("MoveLeft", Input.Keys.valueOf(key));
		pref.flush();
	}
	public void setMoveRight(String key) {
		pref.putInteger("MoveRight", Input.Keys.valueOf(key));
		pref.flush();
	}
	public void setPauseScreen(String key) {
		pref.putInteger("PauseScreen", Input.Keys.valueOf(key));
		pref.flush();
	}
	
}
