package com.ryo33.z2d.client.manager;

import java.net.InetAddress;

import com.ryo33.z2d.client.Client;
import com.ryo33.z2d.client.Updatable;

public class UGameManager implements Updatable {
	
	public UMasterManager parent;
	public Client client;

	public UGameManager(UMasterManager parent){
		this.parent = parent;
	}
	
	public void init(InetAddress address, int port){
		client = new Client(address, port);
	}

	@Override
	public void update() {
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
