package com.mygdx.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenuUI extends UI{
	private Animation<TextureRegion> newGameButton, loadGameButton, settingsButton, exitGameButton; 
	private Rectangle newGameBounds, loadGameBounds, settingsBounds, exitGameBounds; 
	private boolean touched; 
	private AssetManager manager;
	private Texture background, sign; 
	private OrthographicCamera cam;
	
	public MainMenuUI(OrthographicCamera cam) {
		super(cam,false);
		this.cam = cam; 
		
		UI.multi.addProcessor(this);
		super.setTextureAtlas("buttons.txt");
		background = new Texture(Gdx.files.internal("MainMenuScreen.png"));
		sign = new Texture(Gdx.files.internal("Sign.png"));
	
		newGameButton = super.getAnimation("NewGame", 1/10f);
		loadGameButton = super.getAnimation("LoadGame", 1/10f);
		settingsButton = super.getAnimation("Settings", 1/10f);
		exitGameButton = super.getAnimation("ExitButton", 1/10f);

		newGameBounds = new Rectangle(235,300,300,60);
		loadGameBounds = new Rectangle(235,240,300,60); 
		settingsBounds = new Rectangle(235,180,300,60); 
		exitGameBounds = new Rectangle(234,120,300,60);

		manager = new AssetManager();
		manager.load("buttons.txt",TextureAtlas.class);
		manager.load("MainMenuScreen.png", Texture.class);
		manager.load("Sign.png",Texture.class);
	}
	public boolean isFinishedLoading() {
		return manager.isFinished();
	}
	public AssetManager getManager() {
		return manager; 
	}
	public OrthographicCamera getCam() {
		return this.cam;
	}
	

	@Override
	public void render(float elapsedtime, Batch batch) { 
		mousePosUpdate();
		
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.draw(sign,200,0,464,480);
		batch.draw(newGameButton.getKeyFrame(0), newGameBounds.getX(),newGameBounds.getY(),newGameBounds.getWidth(),newGameBounds.getHeight());
		batch.draw(loadGameButton.getKeyFrame(0), loadGameBounds.getX(),loadGameBounds.getY(),loadGameBounds.getWidth(),loadGameBounds.getHeight());
		batch.draw(settingsButton.getKeyFrame(0), settingsBounds.getX(),settingsBounds.getY(),settingsBounds.getWidth(),settingsBounds.getHeight());
		batch.draw(exitGameButton.getKeyFrame(0), exitGameBounds.getX(),exitGameBounds.getY(),exitGameBounds.getWidth(),exitGameBounds.getHeight());
	}
	public boolean newGame() {
		return touched && isMouseCollision(this.newGameBounds);
	
	}
	public boolean loadGame() {
		return touched && isMouseCollision(this.loadGameBounds);
	}
	public boolean settings() {
		return touched && isMouseCollision(this.settingsBounds);
	}
	public boolean exit() {
		return touched && isMouseCollision(this.exitGameBounds);
	}
	public void garbage() {
		manager.unload("buttons.txt");
		manager.unload("Sign.png");
		manager.unload("MainMenuScreen.png");
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touched = true; 
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touched = false; 
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}