package com.ryo33.z2d.client.manager;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.ryo33.z2d.Main;
import com.ryo33.z2d.client.Updatable;
import com.ryo33.z2d.client.component.Button;
import com.ryo33.z2d.client.component.Component;
import com.ryo33.z2d.client.helper.LayoutBox;
import com.ryo33.z2d.client.helper.RenderHelper;
import com.ryo33.z2d.server.Server;
import com.ryo33.z2d.util.State;

public class UTitleManager implements Updatable {

	public LayoutBox button1 = Main.windowManager.doGridLayout(new LayoutBox(), 16, 9, 2, 1, 12, 1);
	public LayoutBox button2 = Main.windowManager.doGridLayout(new LayoutBox(), 16, 9, 2, 3, 12, 1);

	public Server server;

	public Component[][] componentGroups;

	public static final int title = 0, joinGame = 1, createGame = 2;

	public State state = new State("title", "joinGame", "createGame");
	public UMasterManager parent;

	public UTitleManager(UMasterManager parent) {
		this.parent = parent;
		componentGroups = new Component[][] {
		{
				// title
		new Button("Join Game", button1.clone(new LayoutBox())) {
			@Override
			public void onClick() {
				state.setState(joinGame);
			}
		}, new Button("Create Game", button2.clone(new LayoutBox())) {
			@Override
			public void onClick() {
				state.setState(createGame);
			}
		}
		}, {
				// joinGame
		new Button("Join", button2.clone(new LayoutBox())) {
			@Override
			public void onClick() {
				// TODO
				try {
					((UMasterManager)parent).gameInit(InetAddress.getLocalHost(), 50033);
					parent.state.setState(UMasterManager.game);
					state.setState(title);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}, new Button("Back", button1.clone(new LayoutBox())) {
			@Override
			public void onClick() {
				state.setState(title);
			}
		}
		}, {
				// createGame
		new Button("Start", button2.clone(new LayoutBox())) {
			@Override
			public void onClick() {
				if (server == null || server.isRun() == false) {
					server = new Server("test", 50033);
					state.setState(title);
				}
			}
		}, new Button("Back", button1.clone(new LayoutBox())) {
			@Override
			public void onClick() {
				state.setState(title);
			}
		}
		}
		};
		for (Component[] components : componentGroups) {
			for (Component component : components) {
				Main.windowManager.add(component);
			}
		}
		state.setState(title);
	}

	@Override
	public void update() {
		componentGroups[2][0].setEnabled(server == null || !server.isRun());
	}

	@Override
	public void render() {
		RenderHelper.set2D();
		for (Component component : componentGroups[state.getState()]) {
			component.render();
		}
	}

	@Override
	public void eventKey(int key, int scancode, int action, int mods) {
		for (Component component : componentGroups[state.getState()]) {
			component.eventKey(key, scancode, action, mods);
		}
	}

	@Override
	public void eventMouseButton(int button, int action, int mods) {
		for (Component component2 : componentGroups[state.getState()]) {
			component2.eventMouseButton(button, action, mods);
		}
	}

	@Override
	public void eventCursorPos(double xpos, double ypos) {
		for (Component component : componentGroups[state.getState()]) {
			component.eventCursorPos(xpos, ypos);
		}
	}

	@Override
	public void eventWindowSize() {
		for (Component[] components : componentGroups) {
			for (Component component : components) {
				component.eventWindowSize();
			}
		}
	}

}