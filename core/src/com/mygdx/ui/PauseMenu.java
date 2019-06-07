package com.mygdx.ui;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class PauseMenu extends UI{
	private BitmapFont font; 
	private Rectangle buttonBounds,buttonBounds2;
	private Animation<TextureRegion> button,button2;
	
	public PauseMenu(OrthographicCamera cam) {
		super(cam);
		button = super.getAnimation("b", 1/100f);
		button2 = super.getAnimation("b", 1/100f);
		buttonBounds = new Rectangle(0-50,10-70,50,70);
		buttonBounds2 = new Rectangle(buttonBounds.x,buttonBounds.y+100,buttonBounds.getWidth(),buttonBounds.getHeight());
		
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public void render(float elapsedtime, SpriteBatch batch) {
		
	}

}
