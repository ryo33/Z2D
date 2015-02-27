package com.ryo33.z2d.game.world;

import java.io.Serializable;

import com.ryo33.z2d.game.entity.Entity;
import com.ryo33.z2d.game.entity.EntityManager;

public class World implements Serializable {
	private static final long serialVersionUID = 1991802016596121857L;

	private EntityManager entities;
	private BlockManager blocks;
	private WorldGenerator worldGenerator;
	
	public World(){
		worldGenerator = new WorldGenerator();
		blocks = new BlockManager(worldGenerator);
		entities = new EntityManager();
	}
	
	public byte getChip(float x, float y){
		return blocks.getBlock((int)Math.ceil(x / Block.blockSize), (int)Math.ceil(y / Block.blockSize)).getChip((int)Math.ceil(x) % Block.blockSize, (int)Math.ceil(y) % Block.blockSize);
	}
	
	public Entity getEntity(int id){
		return entities.get(id);
	}

}
