package cn.test.mycollection;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义实现HashMap的方法 HashMap：链表+数组
 * 
 * @author dell
 *
 */

public class MyHashMap<K,V> {
	MapNode[] table;
	int size;

	public MyHashMap() {
		table = new MapNode[16];
	}

	public void put(K key,V value) {
		MapNode node = new MapNode(key, value);
		node.hash = myHash(key.hashCode());
		if (table[node.hash] == null) {
			table[node.hash] = node;
			size++;
		} else {
			if (table[node.hash].k.equals(key)) {
				table[node.hash].v = value;
				return;
			}
			MapNode temp = table[node.hash];
			while (temp.next != null && (!temp.equals(key))) {
				temp = temp.next;
				if (temp.equals(key)) {
					this.set(key, value);
					return;
				}
			}
			temp.next = node;
			size++;
		}
	}

	public MapNode search(K key) {
		int hash = myHash(key.hashCode());
		if (table[hash] == null) {
			throw new RuntimeException("不存在key为" + key + "的键值对");
		}
		MapNode temp = table[hash];
		while (temp != null) {
			if (key.equals(temp.k)) {
				return temp;
			}
			temp=temp.next;
		}
		return null;
	}

	public V get(K key) {
		MapNode temp = this.search(key);
		if (temp != null) {
			return (V)temp.v;
		}
		return null;
	}

	public void set(K key,V value) {
        MapNode temp=this.search(key);
        if(temp!=null) {
        	temp.v=value;
        }
	}
	public void remove(K key) {
		int hash = myHash(key.hashCode());
		if (table[hash] == null) {
			throw new RuntimeException("不存在key为" + key + "的键值对");
		}
		if(table[hash].next==null&&table[hash].k.equals(key)) {
			table[hash]=null;
			size--;
			return;
		}
		MapNode node = table[hash];
		if(!(node.next.k.equals(key))){
			node=node.next;
			}
			node.next=node.next.next;
			size--;
	}
	private int myHash(int v) {
		return v & (table.length - 1);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				sb.append(table[i]);
				if (table[i].next != null) {
					MapNode temp = table[i].next;
					while (temp != null) {
						sb.append(temp);
						temp = temp.next;
					}
				}
			}
		}
		sb.append('}');
		return sb.toString();
	}

	public static void main(String[] args) {
		MyHashMap<Integer,String> map = new MyHashMap<>();
		map.put(1, "aaa");
		map.put(2, "bbb");
		map.put(1, "ccc");
		map.set(1, "aa");
		map.set(2, "666");
		map.put(6, "ttt");
		map.remove(6);
		System.out.println(map);
		System.out.println(map.size);
		// System.out.println(map.get(1));
		// System.out.println(map.get(5));

	}

}
