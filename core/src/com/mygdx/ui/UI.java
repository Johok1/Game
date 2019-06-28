package com.mygdx.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
	private boolean isCam;
	public static final InputMultiplexer multi = new InputMultiplexer();
	public UI(OrthographicCamera cam, boolean isCam) {
		this.isCam = isCam;
		Gdx.input.setInputProcessor(multi);
		this.cam = cam;
		mousePos = new Vector3();
		if(isCam)
			cam.unproject(mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		else
			mousePos.set(Gdx.input.getX(), -(Gdx.input.getY()-Gdx.graphics.getHeight()), 0);
	}
	

	public void mousePosUpdate() {
		if(isCam)
			cam.unproject(mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		else
			mousePos.set(Gdx.input.getX(), -(Gdx.input.getY()-Gdx.graphics.getHeight()), 0);
	}
	
	/**
	 * Always call mousePosUpdate(); first !!!
	 * @param elapsedtime
	 * @param batch
	 */
   public abstract void render(float elapsedtime, Batch batch);
   
 
   public void setTextureAtlas(String path) {
	   UI = new TextureAtlas(Gdx.files.internal(path));
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
