package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.ui.State;

public class Fire extends Entity{
	private float x,y; 
	private int width,height; 
	private Animation<TextureRegion> fire; 
	public Fire(float x, float y, int width, int height, boolean isSolid) {
		super(x, y, width, height, isSolid);
		this.x = x; 
		this.y = y; 
		this.width = width;
		this.height = height; 
		setTextureAtlas("fire/firedata.txt");
		fire = new Animation<TextureRegion>(1/15f,getTextureAtlas().findRegions("fire"));
	}

	@Override
	public void draw(float delta, Batch batch, State state) {
		batch.draw(fire.getKeyFrame(delta,true),x,y,width,height);
	}

	@Override
	public void dispose() {
		super.getTextureAtlas().dispose();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
