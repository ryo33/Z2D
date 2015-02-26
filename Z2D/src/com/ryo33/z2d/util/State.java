package com.ryo33.z2d.util;

public class State {
	
	protected int state;
	protected String[] stateStrings;
	protected int length;
	
	public State(String... states){
		length = states.length;
		stateStrings = new String[length];
		for(int i = 0; i < length; i ++){
			stateStrings[i] = states[i];
		}
	}
	
	public void setState(int state){
		if(this.state != state){
			if(Debug.isDebug) System.out.println("set state to " + stateStrings[state]);
			this.state = state;
		}
	}
	
	public int getState(){
		return state;
	}
	
}
