package com.rachelf.keres.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.rachelf.keres.Keres;
import com.rachelf.keres.World;
import com.rachelf.keres.WorldController;
import com.rachelf.keres.WorldRenderer;

public class GameScreen implements Screen, InputProcessor, GestureListener {

	private Keres myGame;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private WorldRenderer renderer;
	private WorldController controller;
	private Vector2 startLoc;
	private int touchX, touchY;
	
	private int width, height;
	
	public GameScreen(Keres game)
	{
		myGame = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		controller.update(delta);
		renderer.render();
	//	debugRenderer.render(world, camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world);
		//debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		controller = new WorldController(world);
		
		InputMultiplexer im = new InputMultiplexer();
		GestureDetector gest = new GestureDetector(this);
		im.addProcessor(gest);
		im.addProcessor(this);
		
		Gdx.input.setInputProcessor(im);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.D)
			renderer.setDebug(!renderer.getDebug());
		else if (keycode == Keys.C)
			System.out.println(world.getPlayer().getPosition());
		else if (keycode == Keys.LEFT)
			controller.leftPressed();
		else if (keycode == Keys.RIGHT)
			controller.rightPressed();
		else if (keycode == Keys.SPACE)
			controller.jumpPressed();
		return true;
		
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT)
			controller.leftReleased();
		else if (keycode == Keys.RIGHT)
			controller.rightReleased();
		else if (keycode == Keys.SPACE)
			controller.jumpReleased();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchX = screenX;
		touchY = screenY;
		
	//	if (screenX < width / 2 && screenY > height /2) {
	//		controller.leftPressed();
		//}
		
	//	if (screenX > width / 2 && screenY > height /2) {
	//		controller.rightPressed();
		//}
		
		if (screenX < width / 2) {
			startLoc = new Vector2(screenX, screenY);
			System.out.println(startLoc);
		}
	//	
		else if (screenX > height / 2) {
			controller.jumpPressed();
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
	//	if (screenX < width / 2 && screenY > height / 2) {
		//	if (touchX > width / 2) {
		//		controller.rightReleased();
		//	}
		//	controller.leftReleased();
		//}
		
		//if (screenX > width / 2 && screenY > height /2) {
		//	if (touchX < width / 2) {
		//		controller.leftReleased();
		//	}
		//	controller.rightReleased();
		//}
		
		if (screenX < width / 2) {
			controller.leftReleased();
			controller.rightReleased();
		}
		
		
		if (screenX > width / 2)
			controller.jumpReleased();
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		if (startLoc != null && screenX < width / 2) {
			if (screenX > startLoc.x) {
				controller.rightPressed();
				controller.leftReleased();
			}
			
			else if (screenX < startLoc.x) {
				controller.leftPressed();
				controller.rightReleased();
			}
		//	return true;
		}
		return true;
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


	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		//if (velocityY < 0) {
		//	controller.jumpPressed();
	//		return true;
	//	}
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

}
