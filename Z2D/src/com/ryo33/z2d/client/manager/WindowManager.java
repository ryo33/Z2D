package com.ryo33.z2d.client.manager;

public class WindowManager {
	
	public long window;
	public int width;
	public int height;

	public WindowManager(long window, int width, int height) {
		this.window = window;
		this.width = width;
		this.height = height;
	}
	
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public float getAspect(){
		return (float)width / height;
	}

	public void toggleFullScreen() {
		//TODO
	}

}
