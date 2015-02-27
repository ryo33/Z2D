package com.ryo33.z2d.network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Packet {

	private List<Task> tasks;
	
	public Packet(){
		tasks = new ArrayList<Task>();
	}
	
	public Iterator<Task> iterator(){
		return tasks.iterator();
	}
	
	public void add(Task task){
		tasks.add(task);
	}

}
