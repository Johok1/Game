package com.mygdx.game.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.ui.State;

public abstract class Entity {
	public static ArrayList<Rectangle> col; 
	public Rectangle bounds;
	private TextureAtlas atlas; 
	private float ANIMATION_RENDER_RATE = 1/15f;
	public int augmentedX,augmentedY; 

	/**
	 * Represents an Entity object whose frames are being pulled from the same atlas object. Several utilities for collision and animation frame rate. 
	 * @param x the location on the x axis 
	 * @param y the location on the y axis 
	 * @param width the width of the bounding box 
	 * @param height the height of the bounding box 
	 * @param isPlayer if this entity is a player than set to true 
	 * @param gameWidth the width of the game screen (implementation date TBD)
	 * @param gameHeight the height of the game screen (implementation date TBD) 
	 * 
	 * @author Johok 
	 */
	public Entity(float x, float y, int width, int height, boolean isSolid) {
		this.bounds = new Rectangle(x,y,width,height+10);
		col = new ArrayList<Rectangle>();
		if(!isSolid)
		col.add(this.bounds);	
	   }
	
	public abstract void draw(float delta, Batch batch);
	public abstract void dispose();
	public abstract void tick(); 
	
	public TextureAtlas getTextureAtlas() {
		return this.atlas;
	}
	public void setTextureAtlas(String path) {
		this.atlas = new TextureAtlas(Gdx.files.internal(path));
	}
	/**
	 * This method is used to get a given set of images based on a passed string ID, please check the assets folder for the txt 
	 * that has this information 
	 * @param identifier string id of the animation within the atlas
	 * @return the animation found in the atlas based on the string id
	 */
	public Animation<TextureRegion> getAnimation(String identifier) {
		return new Animation<TextureRegion>(ANIMATION_RENDER_RATE,this.atlas.findRegions(identifier));
	}
	
	/**
	 * This method is checking to see if the passed bounding rectangle is currently colliding 
	 * on the left side of any of the bounding rectangles created. 
	 * @param bound The bounding rectangle 
	 * @return True if the collision is occurring, false otherwise. 
	 */
	public boolean collideLeft(Rectangle bound) { 
		for(Rectangle box: col) {
			if((bound.x <= box.x+box.width && bound.x >= (box.x+box.width)*.9) && (bound.y+bound.height >= box.y && bound.y <= box.y+box.height)) {
				return true;
				}
			}		
		return false;
	}
	/**
	 * This method is checking to see if the passed bounding rectangle is currently colliding
	 * on the right side of any of the bounding rectangles created. 
	 * @param bound the bounding rectangle
	 * @return True if the collision is occurring, false otherwise
	 */
	public boolean collideRight(Rectangle bound) {
		for(Rectangle box: col) {
				if((bound.x+bound.width >= box.x&&  bound.x <= (box.x)) && (bound.y+bound.height >= box.y && bound.y <= box.y+box.height)) {
				return true;	
				}
			}
		return false; 
	}
	/**
	 * This method is checking to see if the passed bounding rectangle is currently colliding 
	 * on the bottom of any of the bounding rectangles created.  
	 * @param bound the bounding rectangle 
	 * @return True if the collision is occurring, false otherwise 
	 */
	public boolean collideUp(Rectangle bound) {
			for(Rectangle box: col) {
				if((bound.y+bound.height >= box.y && bound.y <= (box.y )) && (bound.x+bound.width >= box.x&&bound.x <= box.x+box.width)) {
				return true;
				}
		}
		return false;
	}
	/**
	 * This method is checking to see if the passed bounding rectangle is currently colliding
	 * on the top of any of the bounding rectangles created. 
	 * @param bound the bounding rectangle 
	 * @return True if the collision is occurring, false otherwise. 
	 */
	public boolean collideDown(Rectangle bound) {
		for(Rectangle box: col) {
				if((bound.y <= box.y+box.height && bound.y + bound.height >= (box.y+box.height)) && (bound.x+bound.width >= box.x &&bound.x<= box.x+box.width)) {
				return true;
				}
		}
		return false; 
	}
	

	/**
	 * The rate in which frames are rendered in a given animation 
	 * @param rr the render rate (default 1/15f) 
	 */
	public void setRenderRate(float rr) {
		this.ANIMATION_RENDER_RATE = rr;
	}
	/**
	 * The purpose of this method is to change the bounding rectangle inherent with every entity object. Realize that if your implemented algorithm to 
	 * make the bounding rectangle follow the player does not account for this augmentation your code will not work. 
	 * @param x the x offset
	 * @param y the y offset
	 */
	public void augmentBoundingRectangle(float x, float y) {
		this.bounds.setX(this.bounds.x+x);
		this.bounds.setY(this.bounds.y+y);
	}
	enum Inputs{
		A,S,W,D,NONE;
	}

}
