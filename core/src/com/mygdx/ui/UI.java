package com.mygdx.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class UI implements InputProcessor{
	private TextureAtlas UI;  
	private OrthographicCamera cam; 
	private Vector3 mousePos; 
	public UI(OrthographicCamera cam) {
		Gdx.input.setInputProcessor(this);
		this.cam = cam;
		mousePos = new Vector3();
		cam.unproject(mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		UI = new TextureAtlas(Gdx.files.internal("ui.txt"));
	}
	
	public void mousePosUpdate() {
		cam.unproject(mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	
	/**
	 * Always call mousePosUpdate(); first !!!
	 * @param elapsedtime
	 * @param batch
	 */
   public abstract void render(float elapsedtime, Batch batch);
   
   public void dispose() {
		UI.dispose();
	}
   
   public Animation<TextureRegion> getAnimation(String id, float frameRate){
	   return new Animation<TextureRegion>(frameRate,this.UI.findRegions(id));
   }
   
   public Vector3 getMousePos() {
	   return this.mousePos;
   }
   
   public boolean isMouseCollision(Rectangle bound) {
	   if((mousePos.x >= bound.getX() && mousePos.x <= bound.getX() + bound.getWidth())&&
		(mousePos.y >= bound.getY() && mousePos.y <= bound.getY() + bound.getHeight())){
		   return true; 
	   }else {
		   return false; 
	   }
	   
   }

}
