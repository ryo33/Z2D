package com.ryo33.z2d.util;

import java.util.HashMap;

public class HashMap2D<A, B, C> {
	private HashMap<A, HashMap<B, C>> map;

	public HashMap2D() {
		map = new HashMap<A, HashMap<B, C>>();
	}

	public void put(A a, B b, C c) {
		HashMap<B, C> subMap = map.get(a);
		if (subMap == null) {
			map.put(a, subMap = new HashMap<B, C>());
		}
		subMap.put(b, c);
	}
	
	public C get(A a, B b){
		HashMap<B, C> subMap = map.get(a);
		if (subMap == null) {
			return null;
		}
		return subMap.get(b);
	}
	
}
