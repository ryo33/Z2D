package com.ryo33.z2d.client.manager;

import com.ryo33.z2d.client.Updatable;
import com.ryo33.z2d.client.component.Button;
import com.ryo33.z2d.client.component.Component;
import com.ryo33.z2d.client.helper.Box;
import com.ryo33.z2d.client.helper.RenderHelper;
import com.ryo33.z2d.util.State;

public class UTitleManager implements Updatable {

	public Component[][] componentGroups;

	public static final int first = 0, joinGame = 1, createGame = 2;

	public State state;

	public UTitleManager() {
		componentGroups = new Component[][] { { new Button("Join Game", Box.doGridLayout(16, 9, 2, 1, 12, 1)) {
			@Override
			public void onClick() {
				state.setState(joinGame);
			}
		}, new Button("Create Game", Box.doGridLayout(16, 9, 2, 3, 12, 1)) {
			@Override
			public void onClick() {
				state.setState(createGame);
			}
		} }, { new Button("う○ち", Box.doGridLayout(16, 9, 2, 3, 12, 1)) {
			@Override
			public void onClick() {
				state.setState(createGame);
			}
		} }, {

		} };
		state = new State("first", "joinGame", "createGame");
		state.setState(first);
	}

	@Override
	public int update() {
		return Nothing;
	}

	@Override
	public void render() {
		RenderHelper.set2D();
		for (Component button : componentGroups[state.getState()]) {
			button.render();
		}
	}

	@Override
	public void eventKey(int key, int scancode, int action, int mods) {
		for (Component button : componentGroups[state.getState()]) {
			button.eventKey(key, scancode, action, mods);
		}
	}

	@Override
	public void eventMouseButton(int button, int action, int mods) {
		for (Component button2 : componentGroups[state.getState()]) {
			button2.eventMouseButton(button, action, mods);
		}
	}

	@Override
	public void eventCursorPos(double xpos, double ypos) {
		for (Component button : componentGroups[state.getState()]) {
			button.eventCursorPos(xpos, ypos);
		}
	}

	@Override
	public void eventWindowSize() {
		for (Component button : componentGroups[state.getState()]) {
			button.eventWindowSize();
		}
	}

}