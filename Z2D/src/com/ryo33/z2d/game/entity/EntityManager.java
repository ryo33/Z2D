package com.ryo33.z2d.game.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EntityManager {

	private Map<Integer, Entity> entities;
	private List<Integer> freeIDs;
	private int counter;

	public EntityManager() {
		entities = new HashMap<Integer, Entity>();
		freeIDs = new ArrayList<Integer>();
	}

	public Entity get(int id) {
		return entities.get(id);
	}

	public int put(Entity entity) {
		int tmp;
		entities.put(tmp = getFreeID(), entity);
		return tmp;
	}

	public void remove(int id) {
		entities.remove(id);
		freeIDs.add(id);
	}

	public Iterator<Entity> iterator() {
		Iterator<Entry<Integer, Entity>> iterator = entities.entrySet().iterator();
		return new Iterator<Entity>() {

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Entity next() {
				Entry<Integer, Entity> entry = iterator.next();
				return entry == null ? null : entry.getValue();
			}
		};
	}

	private int getFreeID() {
		if (freeIDs.isEmpty()) {
			return counter++;
		}
		int tmp = freeIDs.get(0);
		freeIDs.remove(0);
		return tmp;
	}

}