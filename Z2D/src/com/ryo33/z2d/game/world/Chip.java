package com.ryo33.z2d.game.world;

import com.ryo33.z2d.util.Texture;

public abstract class Chip {
	public Chip() {
	}
	public abstract boolean canEnter();
	public abstract Texture getTexture();
}
