package com.ryo33.z2d.game.entity;

import java.io.Serializable;

import com.ryo33.z2d.util.Point;
import com.ryo33.z2d.util.Quad;

public class Entity implements Serializable {

	public int x, y;
	
	public Entity(){
		
	}
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(){
		
	}
	
	public boolean canCollide(){
		return false;
	}
	
	public Quad getCollisionQuad(){
		return null;
	}
	
	public void onCollide(Entity entity, Point hitAt){
		
	}
	
	public void render(){
		
	}
	
}
