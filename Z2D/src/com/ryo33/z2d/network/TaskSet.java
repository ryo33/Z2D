package com.ryo33.z2d.network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskSet {

	private List<Task> tasks;

	public TaskSet() {
		tasks = new ArrayList<Task>();
	}

	public Iterator<Task> iterator() {
		return tasks.iterator();
	}

	public void add(Task task) {
		tasks.add(task);
	}

	public void add(TaskSet... packets) {
		for (TaskSet packet : packets) {
			tasks.addAll(packet.tasks);
		}
	}

}
