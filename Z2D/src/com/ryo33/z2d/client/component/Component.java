package com.ryo33.z2d.client.component;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import com.ryo33.z2d.client.Updatable;
import com.ryo33.z2d.client.helper.Box;

public abstract class Component implements Updatable {

	public boolean isEnter;
	public Box box;
	
	public Component(Box box){
		this.box = box;
	}

	public Component(int x, int y, int width, int height){
		this.box = new Box(x, y, width, height);
	}
	
	public void place(int x, int y, int width, int height){
		box.x = x;
		box.y = y;
		box.width = width;
		box.height = height;
		box.removeOrigin();
	}
	
	public void redoLayout(){
		box.redoLayout();
	}

	@Override
	public int update() {
		return Nothing;
	}

	@Override
	public void eventMouseButton(int button, int action, int mods) {
		if(isEnter && button == GLFW_MOUSE_BUTTON_1 && action == GLFW_RELEASE){
			onClick();
		}
	}

	@Override
	public void eventCursorPos(double xpos, double ypos) {
		if(box.x < xpos && box.x + box.width > xpos && box.y < ypos && box.y + box.height > ypos){
			isEnter = true;
		}else{
			isEnter = false;
		}
	}
	
	public void onClick(){
		//Do nothing
	}

	@Override
	public void eventKey(int key, int scancode, int action, int mods) {
		//Do nothing
	}
	
	@Override
	public void eventWindowSize(){
		redoLayout();
	}

}
