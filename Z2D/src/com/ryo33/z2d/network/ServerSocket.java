package com.ryo33.z2d.network;

import java.util.Queue;

public class ServerSocket extends Z2DSocket {

	public ServerSocket(Queue<Packet> packets) {
		super(packets);
	}
	
}
