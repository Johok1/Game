package com.mygdx.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Item {
	private TextureAtlas items;
	private String description;
	public Item(String desc) {
		this.description = desc; 
		items = new TextureAtlas(Gdx.files.internal("items.txt"));
	}
	public String getDesc() {
		return this.description; 
	}
	public Animation<TextureRegion> getItemAnimation(String key ) {
		return new Animation<TextureRegion>(1/15f,items.findRegions(key));
	}
}
