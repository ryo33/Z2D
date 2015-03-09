package com.ryo33.z2d.client.manager;

import com.ryo33.z2d.client.Updatable;

public class UStartingManager implements Updatable {
	
	public UMasterManager parent;
	
	public UStartingManager(UMasterManager parent) {
		this.parent = parent;
	}

	@Override
	public void update() {
		parent.state.setState(UMasterManager.title);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eventKey(int key, int scancode, int action, int mods) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eventMouseButton(int button, int action, int mods) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eventCursorPos(double xpos, double ypos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eventWindowSize() {
		// TODO Auto-generated method stub
		
	}

}
