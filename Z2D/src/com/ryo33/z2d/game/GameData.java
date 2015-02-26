package com.ryo33.z2d.game;

import java.io.Serializable;

import com.ryo33.z2d.game.world.World;

public class GameData implements Serializable {

	private static final long serialVersionUID = -2549480358972335603L;
	
	public World world;
	
	public GameData(){
		this.world = new World();
	}

}
