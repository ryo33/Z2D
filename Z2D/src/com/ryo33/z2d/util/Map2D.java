package com.ryo33.z2d.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Map2D<K1, K2, V> implements Serializable {
	private Map<K1, Map<K2, V>> map;

	public Map2D() {
		map = new HashMap<K1, Map<K2, V>>();
	}

	public Iterator<Iterator<Item>> iterator() {
		Iterator<Entry<K1, Map<K2, V>>> iterator = map.entrySet().iterator();
		return new Iterator<Iterator<Item>>() {

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Iterator<Item> next() {
				Entry<K1, Map<K2, V>> entry = iterator.next();
				if (entry == null)
					return null;
				Iterator<Entry<K2, V>> iterator2 = entry.getValue().entrySet().iterator();
				return new Iterator<Item>() {

					@Override
					public boolean hasNext() {
						return iterator2.hasNext();
					}

					@Override
					public Item next() {
						Entry<K2, V> entry2 = iterator2.next();
						if (entry2 == null)
							return null;
						return new Item(entry.getKey(), entry2.getKey(), entry2.getValue());
					}

				};
			}
		};
	}

	public void put(K1 a, K2 b, V c) {
		Map<K2, V> subMap = map.get(a);
		if (subMap == null) {
			map.put(a, subMap = new HashMap<K2, V>());
		}
		subMap.put(b, c);
	}

	public V get(K1 a, K2 b) {
		Map<K2, V> subMap = map.get(a);
		if (subMap == null) {
			return null;
		}
		return subMap.get(b);
	}
	
	public void clear(){
		map.clear();
	}
	
	public class Item{
		K1 k1;
		K2 k2;
		V v;
		public Item(K1 k1, K2 k2, V v){
			this.k1 = k1;
			this.k2 = k2;
			this.v = v;
		}
		public K1 getKey1(){
			return k1;
		}
		public K2 getKey2(){
			return k2;
		}
		public V getValue(){
			return v;
		}
	}

}
