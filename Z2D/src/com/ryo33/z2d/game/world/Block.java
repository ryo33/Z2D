package com.ryo33.z2d.game.world;

import java.io.Serializable;

public class Block implements Serializable {

	public static final int blockSize = 56;
	private byte[] chips;
	
	public Block(){
		this.chips = new byte[blockSize * blockSize];
	}

	public Block(byte[][] chips) {
		this.chips = new byte[blockSize * blockSize];
		for(int y = 0, l = blockSize, m = blockSize; y < l; y ++){
			for(int x = 0; x < m; x ++){ 
				this.chips[y * blockSize + x] = chips[y][x];
			}
		}
	}
	
	public byte getChip(int x, int y){
		return this.chips[y * blockSize + x];
	}
	
}
