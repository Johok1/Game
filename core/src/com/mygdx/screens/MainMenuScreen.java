package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.ui.MainMenuUI;

public class MainMenuScreen implements Screen{
	
	private Game agame;
	private float elapsedtime;
	private MainMenuUI mainMenuUI; 
	private OrthographicCamera cam; 
	private SpriteBatch batch; 
	public MainMenuScreen(Game agame) {
		this.agame = agame; 
		elapsedtime = 0f; 
		cam = new OrthographicCamera(500,500);
		mainMenuUI = new MainMenuUI(cam);
		batch = new SpriteBatch();
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render(float delta) {
		elapsedtime += Gdx.graphics.getDeltaTime();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		mainMenuUI.render(elapsedtime, batch, null);
		batch.end();
		if(mainMenuUI.touched()) {
			agame.setScreen(new GameScreen(agame));
		}
		if(mainMenuUI.exit()) {
			Gdx.app.exit();
		}
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
