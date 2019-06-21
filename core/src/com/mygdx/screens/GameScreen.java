package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.maps.TestLevel;
import com.mygdx.game.utils.Settings;
import com.mygdx.ui.State;

/**
 * Class that holds basic game logic and levels 
 * TODO: Abstract for various change variables (you need something that will determine 
 * when to change the state and what state to change it to).
 * @author Johok
 *
 */
public class GameScreen implements Screen {

	private float elapsedtime; 
	public TestLevel level; 
	private ShapeRenderer shape; 
	private State state;
	private Game agame;  
	public GameScreen(Game agame) {
		this.agame = agame;
		elapsedtime =0;
		level = new TestLevel("TestGreenMap.tmx",170,100);
		shape = new ShapeRenderer();
		state = State.RUN;
		
	}
	@Override
	public void show() {
	state = State.RUN;
	}
	

	@Override
	public void render(float delta) {
		if(level.isFinishedLoading())
		{
		level.getCam().update();
		switch(state) {	
		case EXIT:
			agame.setScreen(new MainMenuScreen(agame));
			break;
		case PAUSE:
			elapsedtime += Gdx.graphics.getDeltaTime();
			level.render(elapsedtime, state);
			if(Gdx.input.isKeyJustPressed(Settings.pref.getInteger("PauseScreen"))) {
				state = State.RUN;
				break;
			}else if(level.getPause().button1Activate()) {
				state = State.EXIT;
				break;
			}else if(level.getPause().button2Activate()) {
				state = State.SETTINGS;
			}
			break;
		case DIALOGUE:
			elapsedtime += Gdx.graphics.getDeltaTime();
			level.render(elapsedtime, state);
			if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
				state = State.RUN;
			}
		case RUN:
			elapsedtime += Gdx.graphics.getDeltaTime();
			level.render(elapsedtime, state);
			if(Gdx.input.isKeyJustPressed(Settings.pref.getInteger("PauseScreen"))) {
				state = State.PAUSE;
				break;
			}
			if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
				state = State.DIALOGUE;
				break;
			}
		case SETTINGS:
			elapsedtime += Gdx.graphics.getDeltaTime();
			level.render(elapsedtime, state);
			break;
		default:
			break;

		}
		}else {
			level.getManager().update();
			//loading screen here 
		}
		}
	public void debugRender() {
		
		shape.setAutoShapeType(true);
		shape.setProjectionMatrix(level.getCam().combined);
		shape.begin();
		shape.set(ShapeType.Filled);
		shape.setColor(Color.RED);
		shape.rect(level.getPlay().col.get(0).x, level.getPlay().col.get(0).y, level.getPlay().col.get(0).width, level.getPlay().col.get(0).height);
		for(Rectangle rect : level.getPlay().col) {
			shape.rect(rect.x, rect.y, rect.width, rect.height);
		}
		shape.setColor(Color.BLACK);
		shape.rect(level.getPlay().bounds.x, level.getPlay().bounds.y, level.getPlay().bounds.width, level.getPlay().bounds.height);
		shape.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
		state = State.PAUSE;
	}

	@Override
	public void resume() {
		state = State.RUN;
	}

	@Override
	public void hide() {
		state = State.PAUSE;
	}

	@Override
	public void dispose() {
		level.garbage();
		level.dispose();
	}

}
