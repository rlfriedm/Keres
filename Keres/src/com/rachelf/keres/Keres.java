package com.rachelf.keres;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rachelf.keres.screens.GameScreen;
import com.rachelf.keres.screens.MainMenuScreen;
import com.rachelf.keres.screens.SplashScreen;


public class Keres extends Game {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private MainMenuScreen mainMenuScreen;
	private SplashScreen splash;
	private GameScreen gameScreen;

	@Override
	public void create() {		
		mainMenuScreen = new MainMenuScreen(this);  // create menu
		gameScreen = new GameScreen(this);  // create a new game screen
		splash = new SplashScreen(this);  // create a splash screen
		setScreen(splash);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {	
		super.render();
		camera.update();
		camera.apply(Gdx.gl10);
		
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		float aspectRatio = (float) width / (float) height;
		camera = new OrthographicCamera(2f * aspectRatio, 2f);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
	
	public MainMenuScreen getMenuScreen() {
		return mainMenuScreen;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
}
