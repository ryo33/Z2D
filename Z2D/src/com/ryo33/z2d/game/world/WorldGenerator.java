package com.ryo33.z2d.game.world;

public class WorldGenerator {
	
	public WorldGenerator(){
		
	}
	
	public Block createBlock(int x, int y){
		byte[][] chips = new byte[Block.blockSize][Block.blockSize];
		return new Block(chips);
	}
	
}
