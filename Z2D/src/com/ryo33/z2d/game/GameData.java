package com.ryo33.z2d.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.ryo33.z2d.game.world.World;
import com.ryo33.z2d.server.Server;

public class GameData implements Serializable {

	private static final String extention = ".z2d";
	private static final long serialVersionUID = -2549480358972335603L;

	private World world;
	private File file;

	public GameData(File file) {
		world = new World();
		this.file = file;
	}

	public void saveData() {
		try {
			new ObjectOutputStream(new FileOutputStream(file)).writeObject(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GameData getGameData(String name) {
		GameData data;
		try {
			data = (GameData) new ObjectInputStream(new FileInputStream(Server.getDirectory(name) + name + extention)).readObject();
		} catch (Exception e) {
			data = new GameData(new File(Server.getDirectory(name) + name + extention));
		}
		return data;
	}

}