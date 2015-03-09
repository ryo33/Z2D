package com.ryo33.z2d.game.world;

import java.io.Serializable;

public class Blocks implements Serializable {

	Block[] blocks;

	public Blocks(Block[] blocks) {
		this.blocks = blocks;
	}

	public Blocks(Block a, Block b, Block c, Block d, Block e, Block f, Block g, Block h, Block i) {
		this.blocks = new Block[] {
		a, b, c, d, e, f, g, h, i
		};
	}

	public byte getChip(int x, int y) {
		switch (x / Block.blockSize) {
		case 0:
			switch (x / Block.blockSize) {
			case 0:
				return blocks[1].getChip(x % Block.blockSize, y % Block.blockSize);
			case 1:
				return blocks[8].getChip(x % Block.blockSize, y % Block.blockSize);
			case 2:
				return blocks[7].getChip(x % Block.blockSize, y % Block.blockSize);
			}
		case 1:
			switch (x / Block.blockSize) {
			case 0:
				return blocks[2].getChip(x % Block.blockSize, y % Block.blockSize);
			case 1:
				return blocks[0].getChip(x % Block.blockSize, y % Block.blockSize);
			case 2:
				return blocks[6].getChip(x % Block.blockSize, y % Block.blockSize);
			}
		case 2:
			switch (x / Block.blockSize) {
			case 0:
				return blocks[3].getChip(x % Block.blockSize, y % Block.blockSize);
			case 1:
				return blocks[4].getChip(x % Block.blockSize, y % Block.blockSize);
			case 2:
				return blocks[5].getChip(x % Block.blockSize, y % Block.blockSize);
			}
		}
		return 0;
	}

}
