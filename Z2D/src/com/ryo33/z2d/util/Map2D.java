package com.ryo33.z2d.util;

import java.util.HashMap;
import java.util.Map;

public class Map2D<K1, K2, V> {
	private Map<K1, Map<K2, V>> map;

	public Map2D() {
		map = new HashMap<K1, Map<K2, V>>();
	}

	public void put(K1 a, K2 b, V c) {
		Map<K2, V> subMap = map.get(a);
		if (subMap == null) {
			map.put(a, subMap = new HashMap<K2, V>());
		}
		subMap.put(b, c);
	}
	
	public V get(K1 a, K2 b){
		Map<K2, V> subMap = map.get(a);
		if (subMap == null) {
			return null;
		}
		return subMap.get(b);
	}
	
}
