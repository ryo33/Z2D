package com.ryo33.z2d.util;

import static org.lwjgl.util.glu.GLU.*;

public class Camera {
	
	float yaw;
	float pitch;
	float roll;
	float x, y, z;
	
	public Camera(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void move(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void lookAt(float x, float y, float z){
    	gluLookAt(this.x, this.y, this.z, x, y, z, 0, 1, 0);	
	}
	
}
