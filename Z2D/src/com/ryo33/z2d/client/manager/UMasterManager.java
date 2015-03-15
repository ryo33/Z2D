package com.ryo33.z2d.client.manager;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

import java.net.InetAddress;

import com.ryo33.z2d.client.Updatable;
import com.ryo33.z2d.util.Camera;
import com.ryo33.z2d.util.State;

public class UMasterManager implements Updatable {

	float x;
	float y;
	Camera camera;

	public static final int starting = 0, title = 1, game = 2;
	public State state;
	private Updatable[] managers;

	public UMasterManager() {
		state = new State("starting", "title", "game");
		managers = new Updatable[] { new UStartingManager(this), new UTitleManager(this), new UGameManager(this) };
		camera = new Camera(0, 0, 1);
	}

	@Override
	public void update() {
		managers[state.getState()].update();
	}

	@Override
	public void render() {
		managers[state.getState()].render();
	}

	@Override
	public void eventKey(int key, int scancode, int action, int mods) {
		float speed = 0.1F;
		if (key == GLFW_KEY_D) {
			x += speed;
		}
		if (key == GLFW_KEY_A) {
			x -= speed;
		}
		managers[state.getState()].eventKey(key, scancode, action, mods);
	}

	@Override
	public void eventMouseButton(int button, int action, int mods) {
		managers[state.getState()].eventMouseButton(button, action, mods);
	}

	@Override
	public void eventCursorPos(double xpos, double ypos) {
		managers[state.getState()].eventCursorPos(xpos, ypos);
	}

	@Override
	public void eventWindowSize() {
		managers[state.getState()].eventWindowSize();
	}

	public void gameInit(InetAddress address, int port) {
		((UGameManager)managers[game]).init(address, port);
	}

}
