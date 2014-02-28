package com.rachelf.keres.screens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rachelf.keres.Keres;
import com.rachelf.keres.TweenAccessors.SpriteTween;

public class SplashScreen implements Screen {

	private SpriteBatch batch;
	private Sprite splash;
	private Keres myGame;
	private TweenManager manager;
	
	public SplashScreen(Keres game)
	{
		myGame = game;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.update(delta);
		batch.begin();
		splash.draw(batch);
		batch.end();
		
		if (manager.getRunningTweensCount() == 0)
			myGame.setScreen(myGame.getMenuScreen());
		if (Gdx.input.justTouched())
			myGame.setScreen(myGame.getMenuScreen());
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		manager = new TweenManager();
		
		Texture splashTexture = new Texture("images/keres.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		splash.setColor(0, 0, 0, 0); // initially invisible
		
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		Tween.to(splash, SpriteTween.ALPHA, 2f).target(1).ease(TweenEquations.easeInQuad).start(manager);
		Tween.to(splash, SpriteTween.ALPHA, 2f).target(0).delay(2).ease(TweenEquations.easeInOutQuad).start(manager);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		splash.getTexture().dispose();
	}

}
