package com.rachelf.keres;

import java.util.HashMap;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.rachelf.keres.entities.Block;
import com.rachelf.keres.entities.Player;
import com.rachelf.keres.entities.Player.State;

public class WorldController {
	
	enum Keys {
		LEFT, RIGHT, JUMP, FIRE
	}
	
	private static final long longJumpPress = 150l;
	private static final float acceleration = 20;
	private static final float gravity = -20f;
	private static final float maxJumpSpeed = 7f;
	private static final float damp = 0.90f;
	private static final float maxVel = 4f;
	
	private float width;
	private float height;

	private World world;
	private Player player;
	private Level level;
	private long jumpPressedTime;
	private boolean jumpingPressed;
	
	static HashMap<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	};
	
	public WorldController (World world) {
		this.world = world;
		this.player = world.getPlayer();
		this.level = world.getLevel();
		this.width = level.getWidth();
		this.height = level.getHeight();
	}
	
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
		
	}
	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}
	
	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}
	
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}
	
	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}
	
	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
		jumpingPressed = false;
	}
	
	public void update(float delta) {
		processInput();
		player.update(delta, checkCollisions());
	}
	
	public Array<Block> checkCollisions() {
		Rectangle playerBounds = player.getBounds();
		Array<Block> collisions = new Array<Block>();
		for (Block block : world.getBlocks()) {
			if (block.bounds.overlaps(playerBounds)) {
				collisions.add(block);
				block.setCollided(true);
				block.setOverlap(intersect(block.getBounds(), player.getBounds()));
			}
			else {
				block.setCollided(false);
			}
			
		}
		collisions.sort(); // sort blocks by area collided with player, largest first
		return collisions;
	}
	
	
	private boolean processInput() {
		
		if (keys.get(Keys.JUMP)) {
			if (!player.getState().equals(State.JUMPING)) {
				jumpingPressed = true;
				jumpPressedTime = System.currentTimeMillis();
				player.setState(State.JUMPING);
				player.getVelocity().y = maxJumpSpeed;
				player.setOnGround(false);
			}
			
			else {
				if (jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= longJumpPress)) {
					jumpingPressed = false;
				}
				
				else {
					if (jumpingPressed) {
						player.getVelocity().y = maxJumpSpeed;
					}
				}
			}
		}
		
		if (keys.get(Keys.LEFT)) {
			player.setFacingLeft(true);
			if (!player.getState().equals(State.JUMPING)) {
				player.setState(State.WALKING);
			}
			player.getAcceleration().x = -acceleration;
		}
		
		else if (keys.get(Keys.RIGHT)){
			player.setFacingLeft(false);
			if (!player.getState().equals(State.JUMPING)) {
				player.setState(State.WALKING);
			}
			player.getAcceleration().x = acceleration;
		}
		
		else {
			if (!player.getState().equals(State.JUMPING)) {
				player.setState(State.IDLE);
				player.getAcceleration().x = 0;
			}

		}
		return false;
	}
	
	static public float intersect(Rectangle rectangle1, Rectangle rectangle2) {
		Rectangle intersection = new Rectangle();
		
		intersection.x = Math.max(rectangle1.x, rectangle2.x);
		intersection.width = Math.min(rectangle1.x + rectangle1.width, rectangle2.x + rectangle2.width) - intersection.x;
		intersection.y = Math.max(rectangle1.y, rectangle2.y);
		intersection.height = Math.min(rectangle1.y + rectangle1.height, rectangle2.y + rectangle2.height) - intersection.y;
	  
		return (intersection.width > intersection.height)? intersection.width : intersection.height;
	}
}
