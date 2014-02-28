package com.rachelf.keres;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.rachelf.keres.entities.*;
import com.rachelf.keres.entities.Player.State;

public class WorldRenderer {
	public static final  float runningFrameDuration = 0.06f;
	private static final float cameraWidth = 10f;
	private static final float cameraHeight = 7f;
	
	private World world;
	private OrthographicCamera camera;
	
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	private Texture playerTexture;
	private Texture blockTexture;
	
	private TextureRegion playerIdleLeft;
	private TextureRegion playerIdleRight;
	private TextureRegion playerFrame;
	private TextureRegion playerJumpRight;
	private TextureRegion playerFallRight;
	private TextureRegion playerJumpLeft;
	private TextureRegion playerFallLeft;
	
	private SpriteBatch batch;
	private boolean debug = false;
	private int width;
	private int height;
	private float pixPerUnitX;
	private float pixPerUnitY;
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;
	private TiledMap map;
	private Player player;
	private OrthogonalTiledMapRenderer renderer;
	
	public WorldRenderer(World world) {
		this.world = world;
		player = world.getPlayer();
		camera = new OrthographicCamera(cameraWidth, cameraHeight);
		camera.setToOrtho(false, cameraWidth, cameraHeight);
		camera.position.set(cameraWidth/2f, cameraHeight/2f, 0);
		this.camera.update();
		this.debug = false;
		batch = new SpriteBatch();
		loadTextures();
<<<<<<< HEAD
	//	map = new TmxMapLoader().load("images/test.tmx");
=======
<<<<<<< HEAD
	//	map = new TmxMapLoader().load("images/test.tmx");
=======
	//	map = new TmxMapLoader().load("images/map1.tmx");
>>>>>>> parent of 3f81881... Revert "Initial commit"
>>>>>>> Added image based level editing system
	//	renderer = new OrthogonalTiledMapRenderer(map);
	}
	
	public void resize(int w, int h) {
		this.width = w;
		this.height = h;
		pixPerUnitX = (float) width / cameraWidth;
		pixPerUnitY = (float) height / cameraHeight;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
	private void loadTextures() {
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/Player.pack"));
		playerIdleLeft = atlas.findRegion("playerIdle");
		playerIdleRight = new TextureRegion(playerIdleLeft);
		playerIdleRight.flip(true, false);
		playerFallLeft = atlas.findRegion("falling");
		playerFallRight = new TextureRegion(playerFallLeft);
		playerFallRight.flip(true, false);
		playerJumpLeft = atlas.findRegion("jumping");
		playerJumpRight = new TextureRegion(playerJumpLeft);
		playerJumpRight.flip(true, false);
		
		TextureRegion[] walkLeftFrames = new TextureRegion[5];
		
		for (int i = 0; i < 5; i++) {
			walkLeftFrames[i] = atlas.findRegion("y_player" + (i));
		}
		
		walkLeftAnimation = new Animation(runningFrameDuration, walkLeftFrames);
		TextureRegion[] walkRightFrames = new TextureRegion[5];
		
		for (int i = 0; i < 5; i++) {
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		
		walkRightAnimation = new Animation(runningFrameDuration, walkRightFrames);

		blockTexture = new Texture(Gdx.files.internal("images/block.png"));
	}
	
	public void render() {
		moveCamera(player.getPosition().x, cameraHeight / 2);
	//	renderer.setView(camera);
	//	renderer.render();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		drawPlayer();
		drawBlocks();
		batch.end();
		if (debug)
			drawDebug();
	}
	
	private void moveCamera(float x, float y) {
		if ((player.getPosition().x > cameraWidth /2) || player.getPosition().x < cameraWidth / 2) {
			camera.position.set(x, cameraHeight/2, 0);
			if ((player.getPosition().y > cameraHeight / 2 || player.getPosition().y < 0)) {
				camera.position.set(x, player.getPosition().y, 0);
			}
			camera.update();
		}
	}
	
	private void drawBlocks() {
		for (Block block : world.getDrawableBlocks()) {  // change to get drawable blocks
			batch.draw(blockTexture, block.getPosition().x, block.getPosition().y, Block.size, Block.size);
		}
		
	}
	
	private void drawPlayer() {
		playerFrame = player.isFacingLeft() ? playerIdleLeft : playerIdleRight;
		
		if (player.getState().equals(State.WALKING)) {
			playerFrame = player.isFacingLeft() ? walkLeftAnimation.getKeyFrame(player.getStateTime(), true) : walkRightAnimation.getKeyFrame(player.getStateTime(), true);
		}
		
		if (player.getState().equals(State.JUMPING)) {
			if (player.getVelocity().y > 0)
				playerFrame = player.isFacingLeft() ? playerJumpLeft : playerJumpRight;
			else 
				playerFrame = player.isFacingLeft() ? playerFallLeft : playerFallRight;
		}
		
		batch.draw(playerFrame, player.getPosition().x, player.getPosition().y, Player.size, Player.size);
	//	this.camera.position.set(world.getPlayer().getPosition().x, world.getPlayer().getPosition().y, 0);

	}
	
	public void drawDebug() {
		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin(ShapeType.Line);
		
		for (Block block : world.getDrawableBlocks()) {
			Rectangle rect = block.getBounds();
			if (block.getCollided())
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			else 
				debugRenderer.setColor(new Color(0, 1, 0, 1));

			debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		}
		
		Player player = world.getPlayer();
		Rectangle rect = player.getBounds();
		debugRenderer.setColor(new Color(0, 0, 1, 1));
		debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
		debugRenderer.end();
	}

	public boolean getDebug() {
		return debug;
	}
}
