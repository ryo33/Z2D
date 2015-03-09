package com.ryo33.z2d.client.component;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

import com.ryo33.z2d.client.Updatable;
import com.ryo33.z2d.client.helper.LayoutBox;

public abstract class Component extends LayoutBox implements Updatable {

	private boolean isEnter;
	private boolean isEnabled;
	
	public Component(LayoutBox box){
		super(box);
		isEnabled = true;
	}

	public Component(int x, int y, int width, int height){
		this(new LayoutBox(x, y, width, height));
	}

	@Override
	public void update() {
	}

	@Override
	public void eventMouseButton(int button, int action, int mods) {
		if(isEnter && button == GLFW_MOUSE_BUTTON_1 && action == GLFW_RELEASE){
			onClick();
		}
	}

	@Override
	public void eventCursorPos(double xpos, double ypos) {
		if(this.x < xpos && this.x + this.width > xpos && this.y < ypos && this.y + this.height > ypos){
			isEnter = true;
		}else{
			isEnter = false;
		}
	}
	
	@Override
	public void render(){
		
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
	}
	
	public boolean isEnter(){
		return isEnter;
	}
	
	public boolean isEnabled(){
		return isEnabled;
	}

	public void setEnabled(boolean value) {
		this.isEnabled = value;
	}

}
