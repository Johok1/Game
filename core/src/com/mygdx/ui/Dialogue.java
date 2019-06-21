package com.mygdx.ui;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Dialogue extends UI{
	private Animation<TextureRegion> buttonAnimation;
	private Rectangle fontBounds,rightButton,leftButton;
	private boolean isPushed = false;
	private HashMap<Integer,TextureRegion> page;
	private OrthographicCamera cam; 
	private int num = 1;
	private BitmapFont font; 
	/**
	 * Make sure to set the dialogue using setDialogue() first
	 * @param cam
	 */
	public Dialogue(OrthographicCamera cam, float x, float y, int width, int height ) {
		super(cam);
		font = new BitmapFont();
		UI.multi.addProcessor(this);
		page = new HashMap<Integer,TextureRegion>();
		this.cam = cam;
		buttonAnimation = super.getAnimation("b", 1/100f);
		fontBounds = new Rectangle(x, y,width,height);
		leftButton = new Rectangle(fontBounds.x,fontBounds.y,width/8,height/8);
		rightButton = new Rectangle(fontBounds.x + fontBounds.width, fontBounds.y,width/8,height/8);
	}
	/**
	 * Insert a hashmap, the key is the page number and the information is the dialogue stored in that page 
	 * @param map
	 */
	public void setDialogue(HashMap<Integer,TextureRegion> map) {
		this.page.putAll(map); 
	}
	

	@Override
	public void render(float elapsedtime, Batch batch) {
		mousePosUpdate();
		if(isMouseCollision(rightButton)) {
			batch.draw(buttonAnimation.getKeyFrame(elapsedtime,true),rightButton.x,rightButton.y,rightButton.width,rightButton.height);
		}else {
			batch.draw(buttonAnimation.getKeyFrame(0),rightButton.x,rightButton.y,rightButton.width,rightButton.height);
		}
		if(isMouseCollision(leftButton)) {
			batch.draw(buttonAnimation.getKeyFrame(elapsedtime,true),leftButton.x,leftButton.y,leftButton.width,leftButton.height);
		}else {
			batch.draw(buttonAnimation.getKeyFrame(0),leftButton.x,leftButton.y,leftButton.width,leftButton.height);
		}
		
		batch.draw(page.get(num), fontBounds.x, fontBounds.y,fontBounds.width,fontBounds.height);
		if(rightButtonPushed()) {
			num++;
		}else if(leftButtonPushed()) {
			num--;
		}
		if(num > page.size()) {
			num = page.size();
		}else if(num <= 0) {
			num = 1;
		}
	}
	private boolean rightButtonPushed() {
		return isPushed && isMouseCollision(rightButton);
	}
	private boolean leftButtonPushed() {
		return isPushed && isMouseCollision(leftButton);
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
		isPushed = true; 
		
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		isPushed = false;
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
