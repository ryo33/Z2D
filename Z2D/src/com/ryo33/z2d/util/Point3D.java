package com.ryo33.z2d.util;

import org.lwjgl.util.vector.Vector3f;

public class Point3D {
	
	public static final Point3D p00 = new Point3D(0, 0);
	public static final Point3D p01 = new Point3D(0, 1);
	public static final Point3D p10 = new Point3D(1, 0);
	public static final Point3D p11 = new Point3D(1, 1);
	
	public float x, y, z;
	
	public Point3D(Point3D point){
		this.x = point.x;
		this.y = point.y;
		this.z = point.z;
	}
	
	public Point3D(){
		x = y = z = 0;
	}
	
	public Point3D(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D(float x, float y){
		this.x = x;
		this.y = y;
		this.z = 0;
	}

	public Vector3f toVecter3f(){
		return new Vector3f(x, y, z);
	}
	
}
