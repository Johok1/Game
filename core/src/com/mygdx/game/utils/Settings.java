package com.mygdx.game.utils;

import com.badlogic.gdx.Preferences;

public class Settings {
	public static Preferences pref;
	private static boolean saved = false; 
	public Settings() {
		if(!saved) {
			defaults();
		}
	}
	private void defaults() {
		pref.putString("MoveUp", "w");
		pref.putString("MoveDown", "s");
		pref.putString("MoveLeft", "a");
		pref.putString("MoveRight", "d");
		pref.putString("PauseScreen", "escape");
	}
	public void save(boolean save) {
		this.saved = save; 
	}
	public void setMoveUp(char key) {
		
	}
	public void setMoveDown(char key) {
		
	}
	public void setMoveLeft(char key) {
		
	}
	public void setMoveRight(char key) {
		
	}
	public void setPauseScreen(char key) {
		
	}
	
}
