package com.ryo33.z2d.game.world;

import java.io.Serializable;

import com.ryo33.z2d.util.Texture;

public abstract class Chip implements Serializable {
	public Chip() {
	}
	public abstract boolean canEnter();
	public abstract Texture getTexture();
}
