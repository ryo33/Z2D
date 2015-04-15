package com.ryo33.z2d.client;

import java.io.File;
import java.net.InetAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.ryo33.z2d.network.Packet;
import com.ryo33.z2d.util.ConfigManager;

public class Client {
	
	private static final String directory = "client/";
	
	private ConfigManager configManager; 
	private NetworkManager networkManager;
	private Queue<Packet> packets;
	
	public Client(InetAddress address, int port){
		packets = new ConcurrentLinkedDeque<Packet>();
		networkManager = new NetworkManager(packets, address, port);
		initClient();
	}
	
	public void initClient(){
	}
	
	public void exitClient(){
		
	}
	
	public ConfigManager getConfigManager(String name){
		return new ConfigManager(new File(directory + networkManager.getName() + "/" + name + ConfigManager.extention));
	}

	public static String getDirectory(String name){
		return directory + name + "/";
	}

}
