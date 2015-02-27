package com.ryo33.z2d.game.registry;

import java.util.ArrayList;
import java.util.List;

import com.ryo33.z2d.game.world.Chip;

public class ChipRegistry {
	
	public List<Regist> regists;
	
	public ChipRegistry(){
		regists = new ArrayList<ChipRegistry.Regist>();
	}
	
	public void registry(Chip chip){
		regists.add(new Regist(chip));
	}
	
	public int size(){
		return regists.size();
	}
	
	public Chip get(byte num){
		return regists.get(num).get();
	}
	
	public byte get(Chip chip){
		for(int i = 0, len = regists.size(); i < len; i ++){
			if(regists.get(i).isSame(chip)){
				return (byte)i;
			}
		}
		return 0;
	}
	
	private class Regist{
		private Chip chip;
		public Regist(Chip chip) {
			this.chip = chip;
		}
		public Chip get(){
			return chip;
		}
		public boolean isSame(Chip chip){
			if(this.chip == chip){
				return true;
			}
			return false;
		}
	}
	
}
