package com.ryo33.z2d.util;

public class PointPair {
	
	public Point a, b;
	
	public PointPair(Point a, Point b){
		this.a = a;
		this.b = b;
	}
	
	public PointPair(){
		this.a = new Point();
		this.b = new Point();
	}
	
	public PointPair(float x1, float y1, float x2, float y2){
		this.a = new Point(x1, y1);
		this.b = new Point(x2, y2);
	}
	
}
