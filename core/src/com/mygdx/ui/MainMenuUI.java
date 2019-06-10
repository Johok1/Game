package com.mygdx.ui;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuUI extends UI{
	private BitmapFont font; 
	private Animation<TextureRegion> button,button2;
	private Rectangle buttonBounds,buttonBounds2;
	private boolean touched; 
	
	public MainMenuUI(OrthographicCamera cam) {
		super(cam);
		font = new BitmapFont(); 
		button = super.getAnimation("b", 1/100f);
		button2 = super.getAnimation("b", 1/100f);

		buttonBounds = new Rectangle(0-50,10-70,50,70);
		buttonBounds2 = new Rectangle(buttonBounds.x,buttonBounds.y+100,buttonBounds.getWidth(),buttonBounds.getHeight());
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touched = true; 
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touched = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) { 
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(float elapsedtime, Batch batch, State state) { 
		font.draw(batch, "Main Menu Screen", buttonBounds.getX()-(buttonBounds.getWidth()/2),buttonBounds.getY() +250);
		mousePosUpdate();
		if(isMouseCollision(buttonBounds)) {
			 batch.draw(button.getKeyFrame(elapsedtime, true),buttonBounds.getX(),buttonBounds.getY(),buttonBounds.getWidth(),buttonBounds.getHeight()); 
		}else {
			batch.draw(button.getKeyFrame(0),buttonBounds.getX(),buttonBounds.getY(),buttonBounds.getWidth(),buttonBounds.getHeight());
		}
		if(isMouseCollision(buttonBounds2)) {
			batch.draw(button2.getKeyFrame(elapsedtime, true),buttonBounds2.getX(), buttonBounds2.getY(),buttonBounds2.getWidth(),buttonBounds2.getHeight());
		}else {
			batch.draw(button2.getKeyFrame(0),buttonBounds2.getX(),buttonBounds2.getY(),buttonBounds2.getWidth(),buttonBounds2.getHeight());
		}
		
	}
	public boolean touched() {
		if(touched && isMouseCollision(buttonBounds)) {
			return true; 
		}else {
			return false; 
		}
	}
	public boolean exit() {
		if(touched && isMouseCollision(buttonBounds2)) {
			return true;
		}else {
			return false;
		}
	}

}
