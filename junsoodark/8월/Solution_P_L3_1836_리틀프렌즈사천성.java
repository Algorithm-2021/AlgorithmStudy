/*
정확성 테스트

테스트 1 〉	통과 (21.87ms, 66.3MB)

time : 1 Hour 0 Minute

풀이
1. board 배열을 char 형식의 2차원 배열의 map으로 바꾸어준후
2. hashmap을 이용하여 각 글자의 좌표값을 저장한다.
3. map의 알파벳을 중복을 제거하여 arraylist에 저장한 후 오름차순 정렬한다.
4. arraylist의 순서대로 탐색하며 hashmap의 좌표값으로 현재 map 상황에서 제거할 수 있는지 판단하여 제거할 수 있으면 제거후 StringBuilder에 추가한다.
5. StringBuilder에 추가한 글자를 map과 arraylist 에서 제거한다.
6. arraylist를 한바퀴 돌았을때 한개도 제거되지 않으면 "IMPOSSIBLE"를 리턴한다.
7. 4,5,6번 과정을 arraylist의 사이즈가 0일때까지 반복한다.
8. StringBuilder를 String로 리턴한다.


*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/1836
package algo_8월;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Solution_P_L3_1836_리틀프렌즈사천성 {
	public static void main(String[] args) {
		int m = 3;
		int n = 3;
		String[] board = { "DBA", "C*A", "CDB" };
		System.out.println(solution(m, n, board));
	}

	public static String solution(int m, int n, String[] board) {
		StringBuilder sb = new StringBuilder();
		ArrayList<Character> arr = new ArrayList<>();
		HashMap<Character, Integer> hm = new HashMap<>();
		char map[][] = new char[m][n];
		for (int i = 0; i < m; i++) {
			map[i] = board[i].toCharArray();
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				char c = map[i][j];
				if (c == '*') {
					continue;
				} else if (c == '.') {
					continue;
				}
				if (hm.containsKey(c)) {
					hm.put(c, (hm.get(c) * 10000) + ((i * 100) + j));
				} else {
					arr.add(c);
					hm.put(c, (i * 100) + j);
				}
			}
		}
		Collections.sort(arr);
		while (arr.size() != 0) {
			int size = arr.size();
			for (int i = 0; i < arr.size(); i++) {
				char c = arr.get(i);
				int tmp = hm.get(c);
				int a = tmp / 10000;
				int b = tmp % 10000;
				int ai = a / 100;
				int aj = a % 100;
				int bi = b / 100;
				int bj = b % 100;
				if (findRoot(ai, aj, bi, bj, map)) {
					arr.remove(i);
					sb.append(c);
					map[ai][aj] = '.';
					map[bi][bj] = '.';
					break;
				}
			}
			if (size == arr.size()) {
				return "IMPOSSIBLE";
			}
		}
		return sb.toString();
	}

	private static boolean findRoot(int ai, int aj, int bi, int bj, char[][] map) {
		if (ai == bi && (aj + 1 == bj || bj + 1 == aj)) {
			return true;
		}
		if (aj == bj && (ai + 1 == bi || bi + 1 == ai)) {
			return true;
		}
		if (ai == bi) {
			// aj bj 대수 비교 필요?
			for (int i = aj + 1; i < bj; i++) {
				if (map[ai][i] != '.') {
					return false;
				}
			}
			return true;
		} else if (aj == bj) {
			// ai bi 대수 비교 필요?
			for (int i = ai + 1; i < bi; i++) {
				if (map[i][aj] != '.') {
					return false;
				}
			}
			return true;
		} else {
			boolean root1 = true;
			boolean root2 = true;
			boolean root3 = true;
			boolean root4 = true;
			if (aj > bj) {
				for (int i = aj - 1; i >= bj; i--) {
					if (map[ai][i] != '.') {
						root1 = false;
					}
				}
				for (int i = ai; i < bi; i++) {
					if (map[i][bj] != '.') {
						root2 = false;
					}
				}
				if (root1 && root2) {
					return true;
				}
				for (int i = aj; i > bj; i--) {
					if (map[bi][i] != '.') {
						root3 = false;
					}
				}
				for (int i = ai + 1; i <= bi; i++) {
					if (map[i][aj] != '.') {
						root4 = false;
					}
				}
				if (root3 && root4) {
					return true;
				}
				return false;
			} else {
				for (int i = aj + 1; i <= bj; i++) {
					if (map[ai][i] != '.') {
						root1 = false;
					}
				}
				for (int i = ai; i < bi; i++) {
					if (map[i][bj] != '.') {
						root2 = false;
					}
				}
				if (root1 && root2) {
					return true;
				}
				for (int i = aj; i < bj; i++) {
					if (map[bi][i] != '.') {
						root3 = false;
					}
				}
				for (int i = ai + 1; i <= bi; i++) {
					if (map[i][aj] != '.') {
						root4 = false;
					}
				}
				if (root3 && root4) {
					return true;
				}
				return false;
			}
		}
	}
}
