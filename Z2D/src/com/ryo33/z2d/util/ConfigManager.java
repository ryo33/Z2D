package com.ryo33.z2d.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.InitialContext;

import org.omg.CORBA.PUBLIC_MEMBER;

public class ConfigManager {

	public static final String extention = ".cfg";

	private static final String open = "{", close = "}";

	private File file;
	Map2D<String, String, Config> configs;
	private boolean canTrust;

	public ConfigManager(File file) {
		this.file = file;
		configs = new Map2D<String, String, ConfigManager.Config>();
		canTrust = true;
		read();
	}

	public void read() {
		String category = null;
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tmp;
			while ((tmp = reader.readLine()) != null) {
				tmp = tmp.trim();
				if (tmp.length() == 0) {
					continue;
				} else if (category == null) {
					if (tmp.length() >= 2 && tmp.charAt(tmp.length() - 1) == open.charAt(0)) {
						category = tmp.substring(0, tmp.length() - 1).trim();
						if (category.length() > 0) {
							continue;
						}
					}
					System.err.println(Error.IllegalFormatConfigFile);
					configs.clear();
					return;
				} else {
					if (tmp.equals(close)) {
						category = null;
						continue;
					}
					Config config = Config.get(tmp);
					if (config == null) {
						System.err.println(Error.IllegalFormatConfigFile);
						configs.clear();
						return;
					}
					configs.put(category, config.getName(), config);
				}
			}
			reader.close();
		} catch (Exception e) {
		}
	}

	public void write() {
		if (canTrust) {
			PrintWriter printWriter = null;
			try {
				printWriter = new PrintWriter(file);
				for (Iterator<Iterator<Map2D<String, String, Config>.Item>> iterator = configs.iterator(); iterator.hasNext();) {
					Iterator<Map2D<String, String, Config>.Item> iterator2 = iterator.next();
					if (iterator2.hasNext()) {
						Map2D<String, String, Config>.Item item = iterator2.next();
						printWriter.println(item.getKey1() + open);
						printWriter.println(item.getValue().format());
						while (iterator2.hasNext()) {
							printWriter.println(iterator2.next().getValue().format());
						}
						printWriter.println(close);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (printWriter != null)
					printWriter.close();
			}
		}
	}

	public Config getConfig(Type type, String category, String name, Object defaultValue) {
		Config config = configs.get(category, name);
		if (config == null || type != config.getType()) {
			config = new Config(type, name, defaultValue);
			configs.put(category, name, config);
		}
		return config;
	}

	public Config getConfig(String category, String name, int defaultValue) {
		return getConfig(Type.INT, category, name, defaultValue);
	}

	public Config getConfig(String category, String name, float defaultValue) {
		return getConfig(Type.FLOAT, category, name, defaultValue);
	}

	public Config getConfig(String category, String name, String defaultValue) {
		return getConfig(Type.STRING, category, name, defaultValue);
	}

	public Config getConfig(String category, String name, boolean defaultValue) {
		return getConfig(Type.BOOLEAN, category, name, defaultValue);
	}

	private enum Type {
		INT("I"), FLOAT("F"), STRING("S"), BOOLEAN("B");
		private final String name;
		private static Map<String, Type> types;

		private Type(String name) {
			this.name = name;
			init();
		}

		private void init() {
			if (Type.types == null) {
				Type.types = new HashMap<String, ConfigManager.Type>();
			}
			types.put(this.name, this);
		}

		private String getName() {
			return this.name;
		}

		private static Type get(String type) {
			return types.get(type);
		}
	}

	public static class Config {

		private static final String INDENT = "    ", TYPEANDNAME = ":", NAMEANDVALUE = "=";

		private Type type;
		private String name;
		private Object value;

		public Config(Type type, String name, Object value) {
			this.type = type;
			this.name = name;
			this.value = value;
		}

		public String format() {
			return INDENT + getType().getName() + TYPEANDNAME + getName() + NAMEANDVALUE + getValue();
		}
		
		public static Config get(String config) {
			String[] strs1 = config.trim().split(TYPEANDNAME);
			Type type;
			if (strs1.length == 2 && strs1[0].length() == 1 && (type = Type.get(strs1[0])) != null) {
				String[] strs2 = strs1[1].split(NAMEANDVALUE);
				String name, value;
				if (strs2.length == 2 && (name = strs2[0].trim()).length() > 0 && (value = strs2[1].trim()).length() > 0) {
					switch (type) {
					case INT:
						return new Config(type, name, new Integer(value));
					case FLOAT:
						return new Config(type, name, new Float(value));
					case BOOLEAN:
						return new Config(type, name, new Boolean(value));
					case STRING:
						return new Config(type, name, value);
					}
				}
			}
			return null;
		}

		public void set(Object value){
			this.value = value;
		}
		
		public Type getType() {
			return this.type;
		}

		public String getName() {
			return this.name;
		}

		public Object getValue() {
			return this.value;
		}

		public int getInt() {
			return (int) this.value;
		}

		public float getFloat() {
			return (float) this.value;
		}

		public String getString() {
			return (String) this.value;
		}

		public boolean getBoolean() {
			return (boolean) this.value;
		}

	}

}