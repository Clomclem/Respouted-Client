/*
 * This file is part of Spoutcraft.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org/>
 * Spoutcraft is licensed under the GNU Lesser General Public License.
 *
 * Spoutcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoutcraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.spoutcraft.api.util.map;

import gnu.trove.TIntCollection;
import gnu.trove.iterator.TLongIntIterator;
import gnu.trove.map.hash.TLongIntHashMap;
import gnu.trove.set.TLongSet;

/**
 * A simplistic map that supports a pair of integers for keys, using a trove long int hashmap in the backend.
 */
public class TIntPairIntHashMap {
	private TLongIntHashMap map;

	public TIntPairIntHashMap() {
		map = new TLongIntHashMap(100);
	}

	public TIntPairIntHashMap(int capacity) {
		map = new TLongIntHashMap(capacity);
	}

	public int put(int key1, int key2, int value) {
		long key = (((long)key1)<<32) | (((long)key2) & 0xFFFFFFFFL);
		return map.put(key, value);
	}

	public int get(int key1, int key2) {
		long key = (((long)key1)<<32) | (((long)key2) & 0xFFFFFFFFL);
		return map.get(key);
	}

	public boolean containsKey(int key1, int key2) {
		long key = (((long)key1)<<32) | (((long)key2) & 0xFFFFFFFFL);
		return map.containsKey(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsValue(int val) {
		return map.containsValue(val);
	}

	public boolean increment(int key1, int key2) {
		long key = (((long)key1)<<32) | (((long)key2) & 0xFFFFFFFFL);
		return map.increment(key);
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public TLongIntIterator iterator() {
		return map.iterator();
	}

	public TLongSet keySet() {
		return map.keySet();
	}

	public long[] keys() {
		return map.keys();
	}

	public int remove(int key1, int key2) {
		long key = (((long)key1)<<32) | (((long)key2) & 0xFFFFFFFFL);
		return map.remove(key);
	}

	public int size() {
		return map.size();
	}

	public TIntCollection valueCollection() {
		return map.valueCollection();
	}

	public int[] values() {
		return map.values();
	}
}
