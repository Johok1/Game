package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.maps.TestLevel;
import com.mygdx.game.utils.Settings;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MainMenuScreen;
/**
 * TODO: UI implementation 
 * TODO: Data Storage 
 * TODO: Sound Implementation 
 * TODO: Enemy AI (some sort of event system perhaps?)  
 * TODO: Refractor and Test Casing 
 * TODO: Title screen 
 * @author Johok
 */

public class TestGDX extends Game {
	private Settings pref;
	@Override
	public void create () {
		pref = new Settings();
		setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void render () {
		 Gdx.gl.glClearColor(0,0,0,0);
	     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}

