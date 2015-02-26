package com.ryo33.z2d.util;

import org.lwjgl.util.vector.Vector3f;

public class Point {
	
	public static final Point p00 = new Point(0, 0);
	public static final Point p01 = new Point(0, 1);
	public static final Point p10 = new Point(1, 0);
	public static final Point p11 = new Point(1, 1);
	
	public float x, y, z;
	
	public Point(Point point){
		this.x = point.x;
		this.y = point.y;
		this.z = point.z;
	}
	
	public Point(){
		x = y = z = 0;
	}
	
	public Point(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point(float x, float y){
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	public Vector3f toVecter3f(){
		return new Vector3f(x, y, z);
	}
	
}
