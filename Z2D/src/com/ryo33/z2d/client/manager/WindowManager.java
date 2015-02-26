package com.ryo33.z2d.client.manager;

import com.ryo33.z2d.client.helper.LayoutBox;

public class WindowManager extends LayoutBox {
	
	public long window;

	public WindowManager(long window, int width, int height) {
		super(0, 0, width, height);
		this.window = window;
	}
	
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
	}

	public void toggleFullScreen() {
		//TODO
	}

}
