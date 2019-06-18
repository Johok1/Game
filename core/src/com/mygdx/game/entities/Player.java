package com.mygdx.game.entities;

import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.items.Item;
import com.mygdx.ui.State;
/**
 * Represents the player which will be followed by the orthographic camera and be the object of control by the game user
 * 
 * @author Johok
 */
public class Player extends Entity  {

	private Animation<TextureRegion> walkUp, walkLeft, walkRight, walkDown; 
	private TextureRegion still; 
	private float x,y; 
	private int width, height,speed; 
	private Inputs input; 
	private float gold,morale;
	private HashSet<Companion> party; 
	private HashMap<String,Item> inventory;
	private int food;
	/**
	 * 
	 * @param x position on the x axis
	 * @param y position on the y axis 
	 * @param width width of the bounding box 
	 * @param height height of the bounding box 
	 * @param isSolid true if the entity should render collision, false otherwise.  
	 * @param speed the speed that the player will move at 
	 */
	public Player(float x, float y, int width, int height, boolean isSolid, int speed) {
		super(x, y, width, height, true );
		this.x = x;
		this.y =y; 
		this.width = width; 
		this.height = height; 
		this.speed = speed;
		super.setTextureAtlas("entities.txt");
		walkRight = super.getAnimation("walkright");
		walkLeft = super.getAnimation("walkleft");
		walkUp = super.getAnimation("walkup");
		walkDown = super.getAnimation("down");
		still = new TextureRegion(super.getTextureAtlas().findRegion("Still"));
		super.setRenderRate(1/5f);		
		super.augmentBoundingRectangle(10, 5);
		super.augmentedX =10; 
		super.augmentedY =5;
		input = Inputs.NONE;
		gold = 500; 
		food = 10; 
		morale = 10; 
		party = new HashSet<Companion>();
		inventory = new HashMap<String,Item>();
	}
	/**
	 * dispose resources
	 * @see com.mygdx.game.entities.Entity#dispose()
	 */
	@Override
	public void dispose() {
		super.getTextureAtlas().dispose();
	}
	
	/**
	 * Logic to be called periodically 
	 */
	@Override 
	public void tick() {	
		super.bounds.x = this.x + super.augmentedX;
		super.bounds.y = this.y + super.augmentedY;
		if(Gdx.input.isKeyPressed(Input.Keys.A) && !super.collideLeft(super.bounds)) {
			input = Inputs.A;
		}else if(Gdx.input.isKeyPressed(Input.Keys.D) && !super.collideRight(super.bounds)) {
			input = Inputs.D;
		}else if(Gdx.input.isKeyPressed(Input.Keys.W)&& !super.collideUp(super.bounds)) {
			input = Inputs.W;
		}else if(Gdx.input.isKeyPressed(Input.Keys.S)&& !super.collideDown(super.bounds)) {
			input = Inputs.S;
		}else {
			input = Inputs.NONE;
		}
	}

	/**
	 * Draws player animations given input conditions
	 *@param statetime the elapsed time of the game 
	 *@param batch the drawing batch of the game 
	 * @see com.mygdx.game.entities.Entity#draw(float, com.badlogic.gdx.graphics.g2d.Batch)
	 */
	@Override
	public void draw(float statetime, Batch batch) {
			switch(input) {
			case A:
				this.x -=speed;
				batch.draw(walkLeft.getKeyFrame(statetime,true),this.x,this.y);
				break;
			case D:
				this.x += speed;
				batch.draw(walkRight.getKeyFrame(statetime,true), this.x, this.y);
				break;
			case W:
				this.y += speed;
				batch.draw(walkUp.getKeyFrame(statetime, true), this.x,this.y);
				break;
			case S:
				this.y -= speed; 
				batch.draw(walkDown.getKeyFrame(statetime, true), this.x, this.y);
				break;
			default:
				batch.draw(still, this.x, this.y);
		}
	}
	
	// getters and setters 
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y; 
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y; 
	}
	public void setGold(float gold) {
		this.gold = gold; 
	}
	public void addGold(float gold) {
		this.gold += gold;
	}
	public float getGold() {
		return this.gold; 
	}
	public HashSet<Companion> getParty(){
		return this.party; 
	}
	public void addCompanion(Companion e) {
		this.party.add(e);
	}
	public HashMap<String,Item> getInventory(){
		return this.inventory;
	}
	public void addItem(String key, Item item) {
		this.inventory.put(key, item);
	}
	public float getMorale() {
		return this.morale;
	}
	/**
	 * Use a negative to decrement 
	 * @param value
	 */
	public void augmentMorale(float value) {
		this.morale += value; 
	}
	public int getFood() {
		return this.food;
	}
	/**
	 * Use a negative to decrement 
	 * @param value
	 */
	public void changeFood(int value) {
		this.food += value; 
	}
	
}


