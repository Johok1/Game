package com.mygdx.game.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;

public class Settings {
	
	public static Preferences pref = Gdx.app.getPreferences("settings");
	public Settings() {
		if(!pref.getBoolean("Save")) {
			defaults();
		}
	}

	private void defaults() {
		pref.putBoolean("Save", false);
		pref.putInteger("MoveUp", Input.Keys.valueOf("W"));
		pref.putInteger("MoveDown", Input.Keys.valueOf("S"));
		pref.putInteger("MoveLeft", Input.Keys.valueOf("A"));
		pref.putInteger("MoveRight", Input.Keys.valueOf("D"));
		pref.putInteger("PauseScreen", Input.Keys.valueOf("Escape"));
		pref.flush();
	}
	
	public void save() {
		pref.putBoolean("Save", true);
		pref.flush();     
	}
	
	public void setDefault() {
		defaults();

	}
	public void setTime(float time) {
		pref.putFloat("Time", time);
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
