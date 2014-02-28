package com.rachelf.keres.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block implements Comparable<Block> {
	public static final float size = 1f;
	
	public Vector2 position = new Vector2();
	public Rectangle bounds = new Rectangle();
	private boolean collided = false;
	private boolean tagged = false;
	private float overlap;
	
	public Block(Vector2 position) {
		this.position = position;
		this.bounds.width = size;
		this.bounds.height = size;
		this.bounds.setPosition(position);
	}
	
	public void setOverlap(float overlap) {
		this.overlap = overlap;
	}
	
	public double getOverlap() {
		return overlap;
	}
	
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return bounds;
	}

	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void setTagged(boolean tagged) {
		this.tagged = tagged;
	}
	public void setCollided(boolean collided) {
		this.collided = collided;
	}
	
	public boolean getCollided() {
		return collided;
	}
	
	public boolean getTagged() {
		return tagged;
	}

	@Override
	public int compareTo(Block other) {
		//System.out.println("Comparing: " + this.overlap + "to " + other.getOverlap());
		
		return  (other.getOverlap() > this.overlap)? 1 : -1; // if the area collided is >, then the block is higher in the order
	}
}
