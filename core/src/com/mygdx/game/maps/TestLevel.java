package com.mygdx.game.maps;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.ui.State;
import com.mygdx.ui.UI;

/**
 * Simple test level
 * @see com.mygdx.game.maps.Level
 * @author Johok
 *
 */
public class TestLevel extends Level{
	/**
	 * @param mappath The string path of the map for this level
	 * @param camWidth Orthographic camera width 
	 * @param camHeight Orthographic camera height 
	 */
	public TestLevel(String mappath, int camWidth, int camHeight) {
		super(mappath, camWidth, camHeight);
		super.setObjectLayer("Statics_Object_Layer");
		super.setStartCoords(30, 30);
	}
	
	

	/**
	 *Put all logic that must be called periodically in here  
	 *
	 * @see com.mygdx.game.maps.Level#load()
	 */
	@Override
	public void load() {
		
	}
	/**
	 * Renders all objects and entities in this level 
	 * @see com.mygdx.game.maps.Level#render(float, State);
	 */
	@Override
	public void render(float elapsed, State state) {
		super.tick(super.getTiledMapLayer("Background"));
		super.getCam().update();
		super.getRenderer().setView(super.getCam());
		super.getBatch().begin();
		super.getRenderer().renderTileLayer(super.getTiledMapLayer("Background"));
		super.renderPlayer(elapsed, getBatch(), state);
		super.getRenderer().renderTileLayer(super.getTiledMapLayer("Statics"));
		super.getBatch().end();
	}
	

}
