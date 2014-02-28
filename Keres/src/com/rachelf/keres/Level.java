package com.rachelf.keres;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> Added image based level editing system
import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 3f81881... Revert "Initial commit"
>>>>>>> Added image based level editing system
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rachelf.keres.entities.*;

public class Level {
	
	private int width;
	private int height;
	private Array<Block> blocks;
<<<<<<< HEAD
	private TiledMap map;
	private Pixmap levelData;
	private Player player;
	
	public Level() {
		blocks = new Array<Block>();
		levelData = new Pixmap(Gdx.files.internal("levels/level1.png"));
		generateLevel();
		//loadDemoLevel();
	}
	
	public void generateLevel() {
        width = levelData.getWidth();
        height = levelData.getHeight();
        System.out.println("W: " + width + "H: " + height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int invertY = Math.abs(y - height) - 1;
		//		System.out.println("X: " + x + "Y: " + y);
			//	System.out.println(levelData.getPixel(x,  y));
				if (levelData.getPixel(x, y) == 255) {
					System.out.println("Row: " + x + "Col: " + invertY);
					blocks.add(new Block((new Vector2(x, invertY))));
				}
				
				if (levelData.getPixel(x, y) == 65535) {
					 player = new Player(new Vector2(x, invertY));
				}
			}
		}
=======
<<<<<<< HEAD
	private TiledMap map;
	private Pixmap levelData;
	private Player player;
	
	public Level() {
		blocks = new Array<Block>();
		levelData = new Pixmap(Gdx.files.internal("levels/level1.png"));
		generateLevel();
		//loadDemoLevel();
	}
	
	public void generateLevel() {
        width = levelData.getWidth();
        height = levelData.getHeight();
        System.out.println("W: " + width + "H: " + height);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int invertY = Math.abs(y - height) - 1;
		//		System.out.println("X: " + x + "Y: " + y);
			//	System.out.println(levelData.getPixel(x,  y));
				if (levelData.getPixel(x, y) == 255) {
					System.out.println("Row: " + x + "Col: " + invertY);
					blocks.add(new Block((new Vector2(x, invertY))));
				}
				
				if (levelData.getPixel(x, y) == 65535) {
					 player = new Player(new Vector2(x, invertY));
				}
			}
		}
=======
	
	public Level() {
		loadDemoLevel();
>>>>>>> parent of 3f81881... Revert "Initial commit"
>>>>>>> Added image based level editing system
	}
	
	public int getWidth() {
		return width;
	}
	
<<<<<<< HEAD
	
=======
<<<<<<< HEAD
	
=======
>>>>>>> parent of 3f81881... Revert "Initial commit"
>>>>>>> Added image based level editing system
	public int getHeight() {
		return height;
	}
	
	public void setHeight (int height) {
		this.height = height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public Array<Block> getBlocks() {
		return blocks;
	}
	
	public void setBlocks(Array<Block> blocks) {
		this.blocks = blocks;
	}
	
	//public Block get(int x, int y) {
	//	return blocks[x][y];
	//}
	
	private void loadDemoLevel() {
        width = 30;
        height = 7;
        blocks = new Array<Block>();

        for (int col = 0; col < width - 10; col++) {
               	blocks.add(new Block((new Vector2(col, 0))));
               	blocks.add(new Block((new Vector2(col, 6))));
                if (col > 2) {
                   	blocks.add(new Block((new Vector2(col, 1))));

                }
        }
        blocks.add(new Block(new Vector2(0, 1)));
        blocks.add(new Block(new Vector2(0, 2)));
        blocks.add(new Block(new Vector2(0, 3)));
        blocks.add(new Block(new Vector2(0, 4)));
        blocks.add(new Block(new Vector2(0, 5)));
        
        blocks.add(new Block(new Vector2(6, 3)));
        blocks.add(new Block(new Vector2(6, 4)));
        blocks.add(new Block(new Vector2(6, 5)));
        
        blocks.add(new Block(new Vector2(21, 3)));
        blocks.add(new Block(new Vector2(22, 4)));
        blocks.add(new Block(new Vector2(23, 5)));
        
        
        blocks.add(new Block(new Vector2(11, -2)));

	}
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> Added image based level editing system

	public Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 3f81881... Revert "Initial commit"
>>>>>>> Added image based level editing system
}
