/*
정확성 테스트
테스트 1 〉	통과 (0.56ms, 51.8MB)
테스트 2 〉	통과 (1.27ms, 51.8MB)
테스트 3 〉	통과 (0.67ms, 52.4MB)
테스트 4 〉	통과 (0.58ms, 53.2MB)
테스트 5 〉	통과 (0.53ms, 52.1MB)
테스트 6 〉	통과 (0.02ms, 52.2MB)
테스트 7 〉	통과 (0.03ms, 52.3MB)
테스트 8 〉	통과 (0.06ms, 52.5MB)
테스트 9 〉	통과 (0.64ms, 52.8MB)
테스트 10 〉	통과 (0.87ms, 52.9MB)
테스트 11 〉	통과 (66.95ms, 84MB)
테스트 12 〉	통과 (0.39ms, 51.6MB)
테스트 13 〉	통과 (1.11ms, 52.5MB)
테스트 14 〉	통과 (1.42ms, 52.7MB)
테스트 15 〉	통과 (1.73ms, 52.6MB)
테스트 16 〉	통과 (1.94ms, 52.2MB)
테스트 17 〉	통과 (0.04ms, 52.8MB)
테스트 18 〉	통과 (3.63ms, 52.7MB)
테스트 19 〉	통과 (3.92ms, 53.5MB)
테스트 20 〉	통과 (3.42ms, 53.6MB)

time : 0 Hour 40 Minute

풀이
LRU 알고리즘에서 0인경우와 1인 경우의 조건 추가

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/17680
package algo_5월1주;

import java.util.HashMap;
import java.util.Map;

public class Solution_P_L2_17680_캐시 {
	public static void main(String[] args) {
		int cacheSize = 3;
		String cities[] = { "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" };
		System.out.println(solution(cacheSize, cities));
	}

	public static int solution(int cacheSize, String[] cities) {
		int answer = 0;
		if (cities.length == 0) {
			return 0;
		}
		if (cacheSize == 0) {
			return cities.length * 5;
		} else if (cacheSize == 1) {
			String pre = cities[0].toLowerCase();
			answer += 5;
			for (int i = 1; i < cities.length; i++) {
				if (pre.equals(cities[i].toLowerCase())) {
					answer += 1;
				} else {
					pre = cities[i].toLowerCase();
					answer += 5;
				}
			}
		} else {
			LRUCache cache = new LRUCache(cacheSize);
			for (int i = 0; i < cities.length; i++) {
				answer += cache.put(cities[i].toLowerCase(), 0);
			}
		}
		return answer;
	}

	public static class LRUCache {
		private Map<String, node> nodeMap;
		private int capacity;
		private node head;
		private node tail;

		private class node {
			private String key;
			private int value;
			private node prev;
			private node next;

			public node(String key, int value) {
				this.key = key;
				this.value = value;
				this.next = null;
				this.prev = null;
			}
		}

		public LRUCache(int capacity) {
			this.nodeMap = new HashMap<>();
			this.capacity = capacity;
			head = new node(" ", 0);
			tail = new node("  ", 0);
			head.next = tail;
			tail.prev = head;

		}

		// 시간 복잡도 O(1)
		private void remove(node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;

			nodeMap.remove(node.key);
		}

		// 시간 복잡도 O(1)
		private void insertToHead(node node) {
			this.head.next.prev = node;
			node.next = this.head.next;
			node.prev = this.head;
			this.head.next = node;

			nodeMap.put(node.key, node);
		}

		// 시간 복잡도 O(1)
		public int get(String key) {
			if (!nodeMap.containsKey(key))
				return -1;
			node getNode = nodeMap.get(key);
			remove(getNode);
			insertToHead(getNode);
			return getNode.value;

		}

		// 시간 복잡도 O(1)
		public int put(String key, int value) {
			int res = 5;
			node newNode = new node(key, value);
			if (nodeMap.containsKey(key)) {
				node oldNode = nodeMap.get(key);
				remove(oldNode);
				res = 1;
			} else {
				if (nodeMap.size() >= this.capacity) {
					node delNode = tail.prev;
					remove(delNode);
				}
			}
			insertToHead(newNode);
			return res;
		}

	}

}
