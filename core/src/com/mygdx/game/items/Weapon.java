package com.mygdx.game.items;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Weapon extends Item {
	private String desc, key;
	private int dmg; 
	public Weapon(String desc, int dmg, String key) {
		super(desc);
	this.desc = desc;
	this.key = key; 
	this.dmg = dmg; 
	}
	public int getDmg() {
		return this.dmg;
	}
	public Animation<TextureRegion> getAnimation(){
		return super.getItemAnimation(this.key);
	}
}
