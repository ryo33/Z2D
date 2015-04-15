package com.ryo33.z2d.server;

import java.nio.channels.SocketChannel;

import com.ryo33.z2d.network.Packet;
import com.ryo33.z2d.network.TaskSocket;

public class Client {
	
	private TaskSocket socket;
	private boolean joined;

	public Client(SocketChannel socket){
		this.socket = new TaskSocket(socket);
		this.joined = false;
	}
	public Client(TaskSocket socket){
		this.socket = socket;
		this.joined = false;
	}
	
	public boolean isJoined(){
		return joined;
	}
	
	public void send(Packet packet){
		socket.send(packet);
	}
	
	public Packet recieve(){
		return socket.recieve();
	}
	
}
