package com.mygdx.game.maps;

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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.Player;
import com.mygdx.ui.State;
import com.mygdx.ui.UI;
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
	private MapLayer objectLayer; 
	private MapObjects objects;
	public Array<RectangleMapObject> boundingObjects; 

	
/** 
 * @param mappath A string representing the tmx file of the map for this given level (loading is buggy) 
 * @param camWidth The width of the orthographic camera
 * @param camHeight the height of the orthographic camera 
 */
	public Level(String mappath, int camWidth, int camHeight) {
		this.camWidth = camWidth; 
		this.camHeight = camHeight; 
		camera = new OrthographicCamera();
		camera.setToOrtho(false, camWidth, camHeight);
		play = new Player(this.x,this.y,8,20,true,3);
		map = new TmxMapLoader().load(mappath);
		renderer = new OrthogonalTiledMapRenderer(map);
		camera.position.x = renderer.getViewBounds().x;
		boundingObjects = new Array<RectangleMapObject>();
		
	}
	
	private void addCollisions() {
		for(MapObject object: objects) {
			RectangleMapObject rectObject = (RectangleMapObject) object;
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
	
	/**
	 * All tick methods should be called here, also holds the orthographic camera object 
	 */
	public void tick(TiledMapTileLayer background) {
		play.tick();
		load();

		//dis shit is broken fix plz 
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
	public abstract void load();
	
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
		return (TiledMapTileLayer) map.getLayers().get(layer);
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
	
	public void renderPlayer(float elapsed, Batch batch, State state) {
		play.draw(elapsed, batch, state);
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

}
