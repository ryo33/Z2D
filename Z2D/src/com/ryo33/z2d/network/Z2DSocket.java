package com.ryo33.z2d.network;

import java.util.Iterator;
import java.util.Queue;

public abstract class Z2DSocket {
	
	private Queue<Packet> packets;
	
	public Z2DSocket(Queue<Packet> packets){
		this.packets = packets;
	}
	
	public final Iterator<Packet> iterator(){
		return packets.iterator();
	}
	
	protected final void add(Packet packet){
		this.packets.add(packet);
	}
	
	public final boolean isEmpty(){
		return this.packets.isEmpty();
	}
	
	public final Packet poll(){
		return this.packets.poll();
	}
	
}
