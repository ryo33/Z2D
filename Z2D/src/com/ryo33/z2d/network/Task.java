package com.ryo33.z2d.network;


public abstract class Task {
	
	public enum Server{
		
	}
	
	public enum Client{
		
	}
	
	public static final Server[] serverValues = Server.values();
	public static final Client[] clientValues = Client.values();
	
	private byte type;
	
	public Task(Server type){
		this.type = (byte) type.ordinal();
	}
	
	public Task(Client type){
		this.type = (byte) type.ordinal();
	}
	
	public Task(byte type){
		this.type = type;
	}
	
	public Server getTaskServer(){
		return serverValues[this.type];
	}
	
	public Client getTaskClient(){
		return clientValues[this.type];
	}
	
}
