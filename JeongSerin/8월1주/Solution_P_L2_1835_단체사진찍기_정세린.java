/*
테스트 1 〉	통과 (1014.84ms, 62.8MB)
 * 순열, 완탐
 * 40m
 * https://programmers.co.kr/learn/courses/30/lessons/1835
 */
package PROGRAMMERS;

import java.util.Arrays;
import java.util.HashMap;

public class Solution_P_L2_1835_단체사진찍기_정세린 {
	static class Solution {
		char[] friends = { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
		int cnt;
		boolean[] visited;
		HashMap<Character, Integer> map;
		String[] data;
		boolean b = true;

		public int solution(int n, String[] data) {
			int answer = 0;
			int len = friends.length;
			visited = new boolean[len];
			this.data = Arrays.copyOf(data, data.length);
			map = new HashMap<>(); // 프렌즈, 위치

			cnt = 0;
			permutation(len, len);
			answer = cnt;
			return answer;
		}

		void permutation(int n, int r) {
			if (r == 0) {
				if (check()) cnt++;
				return;
			}

			for (int i = 0; i < n; i++) {
				if (visited[i]) continue;
				visited[i] = true;
				map.put(friends[i], r - 1);
				permutation(n, r - 1);
				visited[i] = false;
			}
		}

		boolean check() {
			for (String str : data) {
				char ch1 = str.charAt(0);
				char ch2 = str.charAt(2);
				char op = str.charAt(3); // =, >, <
				int l = Math.abs(map.get(ch1) - map.get(ch2)) - 1;
				int far = str.charAt(4) - '0';

				switch (op) {
				case '=':
					if (l != far) return false;
					break;
				case '<':
					if (l >= far) return false;
					break;
				case '>':
					if (l <= far) return false;
					break;
				}
			}
			return true;
		}

	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 2;
		String[] data = { "N~F=0", "R~T>2" };
		int ans = solution.solution(n, data);
		System.out.println(ans);
	}

}
