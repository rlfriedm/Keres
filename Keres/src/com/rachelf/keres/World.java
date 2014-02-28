package com.rachelf.keres;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rachelf.keres.entities.Block;
import com.rachelf.keres.entities.Player;

public class World {
	Array<Block> blocks = new Array<Block>();
	Player player;
	Level level;
	
	public World() {
		createWorld();
	}
	
	
	private void createWorld() {
<<<<<<< HEAD
		level = new Level();
		blocks = level.getBlocks();
		player = level.getPlayer();
=======
<<<<<<< HEAD
		level = new Level();
		blocks = level.getBlocks();
		player = level.getPlayer();
=======
		player = new Player(new Vector2(8, 2));
		level = new Level();
		blocks = level.getBlocks();
>>>>>>> parent of 3f81881... Revert "Initial commit"
>>>>>>> Added image based level editing system
	}


	public Array<Block> getBlocks() {
		return blocks;
	}
	
	public List<Block> getDrawableBlocks() {
		// optimize block drawing by drawing only those on the screen FIX THIS
		int x = (int) player.getPosition().x - level.getWidth();
		int y = (int) player.getPosition().y - level.getHeight();
		
		//if (x < 0)
		//	x = 0;
		//
		//if (y < 0)
		//	y = 0;
		
		int x2 = x + 2 * level.getWidth();
		int y2 = y + 2 * level.getHeight();
		
		if (x2 > level.getWidth()) {
			x2 = level.getWidth() - 1;
		}
		
		if (y2 > level.getHeight()) {
			y2 = level.getHeight() - 1;
		}
		
		List<Block> drawBlocks = new ArrayList<Block>();
		
		for (Block block : blocks) {
			if (block.position.x >= x && block.position.x <= x2) {
				if (block.position.y >= y && block.position.y <= y2) {
					drawBlocks.add(block);
				}
			}
		}
	//	System.out.println(drawBlocks.size());
	//	System.out.println(blocks.size);
		return drawBlocks;
		
		
	}

	public Player getPlayer() {
		return player;
	}
	
	public Level getLevel() {
		return level;
	}
}
