package com.mygdx.game.maps;


import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.ui.Dialogue;
import com.mygdx.ui.PauseMenu;
import com.mygdx.ui.State;

/**
 * Simple test level
 * @see com.mygdx.game.maps.Level
 * @author Johok
 *
 */
public class TestLevel extends Level{
	private PauseMenu menu; 
	private Dialogue dia;

	/**
	 * @param mappath The string path of the map for this level
	 * @param camWidth Orthographic camera width 
	 * @param camHeight Orthographic camera height 
	 */
	public TestLevel(String mappath, int camWidth, int camHeight) {
		super(mappath, camWidth, camHeight);
		super.setObjectLayer("Statics_Object_Layer");
		super.setStartCoords(30, 30);
		menu = new PauseMenu(super.getCam());
		super.getManager().load("entities.txt", TextureAtlas.class);
		super.getManager().load("ui.txt", TextureAtlas.class);
		super.getManager().load("grass-tiles-2-small.png", Texture.class);
		}
	
	/**
	 * Renders all objects and entities in this level 
	 * @see com.mygdx.game.maps.Level#render(float, State);
	 */
	@Override
	public void render(float elapsed, State state) {
		
		super.getBatch().setColor(getRGB(), getRGB(), getRGB(), 1);
		
		switch(state) {
		case RUN:
			super.getRenderer().setView(super.getCam());
			super.getCam().update();
			super.getBatch().begin();
			super.tick(super.getTiledMapLayer("Background"));	
			super.getRenderer().renderTileLayer(super.getTiledMapLayer("Background"));
			super.getPlay().draw(elapsed, super.getBatch());
			super.getRenderer().renderTileLayer(super.getTiledMapLayer("Statics"));
			super.getBatch().end();
			break;
		
		case PAUSE:
			super.getRenderer().setView(super.getCam());
			super.getCam().update();
			super.getBatch().begin();
			super.tick(super.getTiledMapLayer("Background"));	
			super.getRenderer().renderTileLayer(super.getTiledMapLayer("Background"));
			super.getRenderer().renderTileLayer(super.getTiledMapLayer("Statics"));
			menu.tick();
			menu.render(elapsed, getBatch());
			super.getBatch().end();
			break;
		case EXIT:
			break;
		case SETTINGS:
			break;
		case DIALOGUE:
			super.getRenderer().setView(super.getCam());
			super.getCam().update();
			super.getBatch().begin();
			super.tick(super.getTiledMapLayer("Background"));	
			super.getRenderer().renderTileLayer(super.getTiledMapLayer("Background"));
			super.getRenderer().renderTileLayer(super.getTiledMapLayer("Statics"));
			super.getBatch().end();
			break;
		
		default:
			break;
	}
	
	}


//I'm watching you 0-0 
	public PauseMenu getPause() {
		return menu; 
	}
	public boolean isFinishedLoading() {
		return super.getManager().isFinished();
	}
	public AssetManager getManager() {
		return super.getManager();
	}
	public void garbage() {
		super.getManager().unload("entities.txt");
		super.getManager().unload("ui.txt");
		super.getManager().unload("grass-tiles-2-small.png");
	}

}
