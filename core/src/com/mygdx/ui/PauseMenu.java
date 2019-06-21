package com.mygdx.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class PauseMenu extends UI{
	
	private Rectangle buttonBounds,buttonBounds2;
	private Animation<TextureRegion> button,button2;
	private boolean touched = false;
	private OrthographicCamera cam; 
	public PauseMenu(OrthographicCamera cam) {
		super(cam);
		UI.multi.addProcessor(this);
		button = super.getAnimation("b", 1/100f);
		button2 = super.getAnimation("b", 1/100f);
		buttonBounds = new Rectangle(cam.position.x, cam.position.y,20,20);
		buttonBounds2 = new Rectangle(buttonBounds.x,buttonBounds.y+30,buttonBounds.getWidth(),buttonBounds.getHeight());
		this.cam = cam; 
	}
	public void tick() {
		buttonBounds.x = cam.position.x;
		buttonBounds.y = cam.position.y;
		buttonBounds2.x = buttonBounds.x; 
		buttonBounds2.y = buttonBounds.y + 30; 
	}

	
	public boolean button1Activate() {
		return touched && isMouseCollision(buttonBounds);
	}
	public boolean button2Activate() {
		return touched && isMouseCollision(buttonBounds2);
	}

	@Override
	public void render(float elapsedtime, Batch batch) {
		
		mousePosUpdate();
		if(isMouseCollision(buttonBounds)) {
			batch.draw(button.getKeyFrame(elapsedtime,true),buttonBounds.getX(),buttonBounds.getY(),buttonBounds.getWidth(),buttonBounds.getHeight());
		}else {
			batch.draw(button.getKeyFrame(0),buttonBounds.getX(),buttonBounds.getY(),buttonBounds.getWidth(),buttonBounds.getHeight());
		}
		if(isMouseCollision(buttonBounds2)) {
			batch.draw(button2.getKeyFrame(elapsedtime,true),buttonBounds2.getX(),buttonBounds2.getY(),buttonBounds2.getWidth(),buttonBounds2.getHeight());
		}else {
			batch.draw(button2.getKeyFrame(0),buttonBounds2.getX(),buttonBounds2.getY(),buttonBounds2.getWidth(),buttonBounds2.getHeight());
		}
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
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
