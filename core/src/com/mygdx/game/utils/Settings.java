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
