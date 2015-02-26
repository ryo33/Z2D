package com.ryo33.z2d.util;

public class Point {
	
	public float x, y;
	
	public Point(Point point){
		this.x = point.x;
		this.y = point.y;
	}
	
	public Point(){
		x = y = 0;
	}
	
	public Point(float x, float y){
		this.x = x;
		this.y = y;
	}
	
}
