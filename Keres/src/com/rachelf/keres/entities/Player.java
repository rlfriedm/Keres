package com.rachelf.keres.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rachelf.keres.entities.Player.State;

public class Player {

	public enum State {
		IDLE, WALKING, JUMPING, DYING
	}
	
	private static final long longJumpPress = 150l;
	private static final float acc = 20f;
	private static final float gravity = -20f;
	private static final float maxJumpSpeed = 7f;
	private static final float friction = 0.90f;
	private static final float maxVel = 4f;
	private static final float width = 100f;
	
	public static final float speed = 4;
	public static final float jumpVelocity = 1f;
	public static final float size = .5f;
	private float stateTime = 0;
	
	private boolean onGround = false;

	Vector2 position = new Vector2();
	Vector2 acceleration = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	State state = State.IDLE;
	boolean facingLeft = true;
	
	public Player(Vector2 position) {
		this.position = position;
		this.bounds.height = size;
		this.bounds.width = size;
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}
	public Rectangle getBounds() {
		return bounds;
	}

	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void setOnGround(boolean grounded) {
		onGround = grounded;
	}
	
	public boolean getOnGround() {
		return onGround;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
		bounds.x = position.x;
		bounds.y = position.y;
	}

	public void setState(State state) {
		// TODO Auto-generated method stub
		this.state = state;
	}

	public Vector2 getVelocity() {
		// TODO Auto-generated method stub
		return velocity;
	}

	public void setFacingLeft(boolean b) {
		// TODO Auto-generated method stub
		facingLeft = b;
	}
	
	public void update(float delta, Array<Block> collisions) {
		// NOTE: figure out why legs go into block, could be order of checking for collisions/updating position
		// position += velocity above collisions before changing the velocity
		acceleration.y = gravity;

		if (delta > .1f) {  // make sure time passed isn't great enough to cause clipping issues when window moved
			delta = .02f;
		}
		
		acceleration.scl(delta);
		velocity.add(acceleration);

		processCollisions(collisions);
		

		if (Math.abs(velocity.x) < .05)
			velocity.x = 0;
		else {
			velocity.x *= friction;
		}
		
		if (velocity.x > maxVel) 
			velocity.x = maxVel;
		if (velocity.x < -maxVel)
			velocity.x = -maxVel;
		
		
		position.add(velocity.cpy().scl(delta));
		bounds.x = position.x;
		bounds.y = position.y;
		stateTime += delta;

		setPosition(getPosition());
	}

	public Vector2 getAcceleration() {
		// TODO Auto-generated method stub
		return acceleration;
	}

	public State getState() {
		// TODO Auto-generated method stub
		return state;
	}

	public float getStateTime() {
		// TODO Auto-generated method stub
		return stateTime;
	}

	public void processCollisions(Array<Block> collidedBlocks) {
		for (Block block : collidedBlocks) {
			
			//System.out.println(count + " " + block.getOverlap());
			if (velocity.y <= 0 && distance(0, bounds.y, 0, block.bounds.y + block.bounds.height) < bounds.height / 2) {
				if ((distance(bounds.x + bounds.width, 0, block.bounds.x, 0) < .1 || distance(bounds.x, 0, block.bounds.x + block.bounds.width, 0) < .05) && !onGround && collidedBlocks.size > 1) {
					
					if (velocity.x > 0  && block.bounds.x > bounds.x) 
						position.x = block.bounds.x - bounds.width;
					
					if (velocity.x < 0 && block.bounds.x < bounds.x) 
						position.x = block.bounds.x + block.bounds.width;
					break;
				}
				velocity.y = 0;
				position.y = block.bounds.height + block.bounds.y;
				onGround = true;
				if (state.equals(State.JUMPING)) {
					setState(State.IDLE);
				}
			}
			
			else if (velocity.y > 0 && distance(0, bounds.y + bounds.height, 0, block.bounds.y) < bounds.height / 2) {

				if ((distance(bounds.x + bounds.width, 0, block.bounds.x, 0) < .1 || distance(bounds.x, 0, block.bounds.x + block.bounds.width, 0) < .05) && collidedBlocks.size > 1) {
		
					if (velocity.x > 0 && block.bounds.x > bounds.x) {
						position.x = block.bounds.x - bounds.width;
						System.out.println("going right");

					}
					
					if (velocity.x < 0 && block.bounds.x < bounds.x) {
						System.out.println("going left");
						position.x = block.bounds.x + block.bounds.width;
					}
					break;
				}

				position.y = block.bounds.y - bounds.height;
				velocity.y = 0;
			}
			
			else if (velocity.x > 0 && distance(bounds.x + bounds.width, 0, block.bounds.x, 0) < bounds.width / 2) {
				velocity.x = 0;
				position.x = block.bounds.x - bounds.width;
			}
			
			else if (velocity.x < 0 && distance(bounds.x, 0, block.bounds.x + block.bounds.width, 0) < bounds.width / 2) {
				velocity.x = 0;
				position.x = block.bounds.x + block.bounds.width;
	
			}
		}
	}
	
	public double distance(float x1, float y1, float x2, float y2) {
		return Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
	}
}
