package com.ryo33.z2d.game.world;

import java.io.Serializable;

public class World implements Serializable {
	private static final long serialVersionUID = 1991802016596121857L;

	private BlockManager blocks;
	private WorldGenerator worldGenerator;
	
	public World(){
		worldGenerator = new WorldGenerator();
		blocks = new BlockManager(worldGenerator);
	}

}
