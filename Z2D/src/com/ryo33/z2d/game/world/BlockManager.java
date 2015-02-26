package com.ryo33.z2d.game.world;

import com.ryo33.z2d.util.HashMap2D;

public class BlockManager {
	
	private HashMap2D<Integer, Integer, Block> blocks;
	private WorldGenerator worldGenerator;
	
	public BlockManager(WorldGenerator worldGenerator){
		blocks = new HashMap2D<Integer, Integer, Block>();
		this.worldGenerator = worldGenerator;
	}
	
	public Block getBlock(int x, int y){
		Block block = blocks.get(x, y);
		if(block == null){
			block = worldGenerator.createBlock(x, y);
			blocks.put(x, y, block);
		}
		return block;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return 
	 * 7, 6, 5
	 * 8, 0, 4
	 * 1, 2, 3
	 */
	public Block[] getBlocks(int x, int y){
		return new Block[]{
			getBlock(x, y),
			getBlock(x - 1, y - 1),
			getBlock(x, y - 1),
			getBlock(x + 1, y - 1),
			getBlock(x + 1, y),
			getBlock(x + 1, y + 1),
			getBlock(x, y + 1),
			getBlock(x - 1, y + 1),
			getBlock(x - 1, y)
		};
	}
	
}
