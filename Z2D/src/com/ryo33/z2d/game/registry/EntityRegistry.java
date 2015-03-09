package com.ryo33.z2d.game.registry;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.ryo33.z2d.game.entity.Entity;

public class EntityRegistry implements Serializable {
	
	private Map<Class<Entity>, Info> entitys;
	
	public EntityRegistry(){
		entitys = new HashMap<Class<Entity>, EntityRegistry.Info>();
	}
	
	public void register(Class<Entity> entityClass, int updateFrequency){
		entitys.put(entityClass, new Info(updateFrequency));
	}
	
	public class Info{
		
		public int updateFrequency;
		
		public Info(int updateFrequency){
			this.updateFrequency = updateFrequency;
		}
		
	}
	
}
