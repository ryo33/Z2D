package com.ryo33.z2d.network;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TaskSocket {
	
	private SocketChannel channel;
	
	private ByteBuffer buffer;
	private int offset;
	
	public TaskSocket(SocketChannel channel){
		this.channel = channel;
	}
	
	public void send(Packet packet){
		
	}
	
	public Packet recieve(){
		//TODO
		return null;
	}
	
}
