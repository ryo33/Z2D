package com.ryo33.z2d.util;

public class Box {

	public int x, y, width, height;
	
	public Box(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Box(){
		
	}
	
	public float getAspect(){
		return (float)width / height;
	}
	
}
