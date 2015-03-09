package com.ryo33.z2d.game.world;

import java.io.Serializable;

public class WorldGenerator implements Serializable {
	
	public WorldGenerator(){
		
	}
	
	public Block createBlock(int x, int y){
		byte[][] chips = new byte[Block.blockSize][Block.blockSize];
		return new Block(chips);
	}
	
}
