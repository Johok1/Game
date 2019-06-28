package com.mygdx.game.maps;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.Player;
import com.mygdx.game.utils.Settings;
import com.mygdx.ui.State;
/**
 * An abstract object representing a map with several objects on it (such as entities both alive and static). This is 
 * the basic structure bringing all of the game elements unto one object. 
 * 
 * @author Johok
 */
public abstract class Level {
	private Player play;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera; 
	private TiledMap map; 
	private int camWidth = 0, camHeight = 0; 
	private float x =0,y =0;
	private String objectpath;
	private MapLayer objectLayer,animationLayer; 
	private MapObjects objects,animationMapObjects;
	public Array<RectangleMapObject> boundingObjects,animationObjects;
	private AssetManager manager; 
	private Settings settings; 
	private float RGB = 0f;
	private Time timeState;
/** 
 * @param mappath A string representing the tmx file of the map for this given level (loading is buggy) 
 * @param camWidth The width of the orthographic camera
 * @param camHeight the height of the orthographic camera 
 */
	public Level(String mappath, int camWidth, int camHeight, Player play,Time timeState) {
		this.camWidth = camWidth; 
		this.camHeight = camHeight;
		this.timeState = timeState;
		settings = new Settings();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, camWidth, camHeight);
		this.play = play; 
		map = new TmxMapLoader().load(mappath);
		renderer = new OrthogonalTiledMapRenderer(map);
		camera.position.x = renderer.getViewBounds().x;
		boundingObjects = new Array<RectangleMapObject>();
		manager = new AssetManager();
		
	}
	public OrthographicCamera getCamera() {
		return this.camera;
	}
	private void addCollisions() {
		for(MapObject object: objects) {
			RectangleMapObject rectObject = (RectangleMapObject) object;
			object.setName(object.getName());
			boundingObjects.add(rectObject);
		}
		for(RectangleMapObject object: boundingObjects) {
			Rectangle box = object.getRectangle();
			play.col.add(box);
		}
	}
	/**
	 * Set the bounding layer for bounding boxes on the environment 
	 * This must be set each time a new level is made!
	 * @param layer
	 */
	public void setObjectLayer(String layer) {
		this.objectpath = layer; 
		objectLayer = getMapLayer(objectpath);
		objects = objectLayer.getObjects();
		addCollisions();
	}
	public void setAnimationLayer(String layer) {
		animationLayer = getMapLayer(layer);
		animationMapObjects = animationLayer.getObjects();
		for(MapObject object:animationMapObjects) {
		RectangleMapObject rectObject = (RectangleMapObject) object;
		animationObjects.add(rectObject);
		}
	}
	
	public Rectangle getRectangle(String name) {
		Rectangle bound = null; 
		for(RectangleMapObject object: boundingObjects) {
			if(name.equals(object.getName())){
				bound = object.getRectangle();
			}
		}
		return bound; 
	}
	
	/**
	 * All tick methods should be called here, also holds the orthographic camera object 
	 */
	public synchronized void tick(TiledMapTileLayer background) {
		play.tick();
		
	
		switch(timeState) {
		case MidNight:
			
			RGB = 0.1f;
			break;
		case Morning:
			RGB = 0.35f;
			break;
		case Noon:
			RGB = 0.5f;
			break;
		case Evening:
			RGB = 0.35f;
			break;
		case Night:
			RGB = 0.2f;
			break;
		}
		
		if(play.getX() + play.getWidth() < camWidth/2 ) {
			camera.position.x = camWidth/2;
		}else if(play.getX()+play.getWidth()>(background.getWidth()*background.getTileWidth())-camWidth/2){
			camera.position.x = (background.getWidth()*background.getTileWidth())-camWidth/2;
		}else {
			camera.position.x = play.getX() + play.getWidth();
		}if(play.getY() + play.getHeight() <= camHeight/2) { 
			camera.position.y = camHeight/2;
		}else if(play.getY()+play.getHeight() > (background.getHeight() * background.getTileHeight()) - camHeight/2) {
			camera.position.y = (background.getHeight() * background.getTileHeight()) - camHeight/2;
		}else{
			camera.position.y = play.getY()+play.getHeight();
		}
		
	}
	public Time getTime() {
		return this.timeState;
		
		
	}
	public abstract void render(float elapsed, State state);

	public OrthographicCamera getCam() {
		return camera; 
	}
	
	public Player getPlay() {
		return play;
	}
	/**
	 * Handle's rendering, call before starting batch. 
	 */
	public OrthogonalTiledMapRenderer getRenderer() {
		return renderer; 
	}
	/**
	 * Calls a layer 
	 * @param layer String ID 
	 * @return a TiledMapTileLayer
	 */
	public TiledMapTileLayer getTiledMapLayer(String layer) {
		TiledMapTileLayer tileLayer = (TiledMapTileLayer) map.getLayers().get(layer);
		return tileLayer; 
	}
	
	/**
	 * Calls a layer
	 * @param layer
	 * @return a MapLayer
	 */
	public MapLayer getMapLayer(String layer) {
		return map.getLayers().get(layer);
	}
	
	public Batch getBatch() {
		return renderer.getBatch();
	}
	
	/**
	 * Sets the starting coordinates of the player 
	 * @param x the coordinate on the x axis 
	 * @param y the coordinate on the y axis 
	 */
	public void setStartCoords(float x, float y) {
		play.setX(x);
		play.setY(y);
	}
	
	public void renderPlayer(float elapsed, Batch batch) {
		play.draw(elapsed, batch);
	}
	
	public void dispose() {
		renderer.dispose();
		play.dispose();
		map.dispose();
	}
	
	public int getCamWidth() {
		return camWidth;
	}
	public int getCamHeight() {
		return camHeight; 
	}
	public TiledMap getMap() {
		return map; 
	}
	public float getRGB() {
		return this.RGB;
	}
	public Settings getSettings() {
		return this.settings; 
	}
	public AssetManager getManager() {
		return manager;
	
	}

}
