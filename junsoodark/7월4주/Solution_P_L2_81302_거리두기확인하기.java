/*
정확성 테스트

테스트 1 〉	통과 (0.31ms, 51.6MB)
테스트 2 〉	통과 (0.22ms, 52MB)
테스트 3 〉	통과 (0.20ms, 51.7MB)
테스트 4 〉	통과 (0.24ms, 52.7MB)
테스트 5 〉	통과 (0.25ms, 52MB)
테스트 6 〉	통과 (0.22ms, 51.9MB)
테스트 7 〉	통과 (0.20ms, 52.9MB)
테스트 8 〉	통과 (0.24ms, 51.7MB)
테스트 9 〉	통과 (0.21ms, 52.5MB)
테스트 10 〉	통과 (0.20ms, 52.8MB)
테스트 11 〉	통과 (0.22ms, 52.8MB)
테스트 12 〉	통과 (0.27ms, 53.1MB)
테스트 13 〉	통과 (0.22ms, 53.1MB)
테스트 14 〉	통과 (0.21ms, 53MB)
테스트 15 〉	통과 (0.18ms, 51.9MB)
테스트 16 〉	통과 (0.21ms, 52MB)
테스트 17 〉	통과 (0.21ms, 51.8MB)
테스트 18 〉	통과 (0.20ms, 51.8MB)
테스트 19 〉	통과 (0.24ms, 51.7MB)
테스트 20 〉	통과 (0.22ms, 52.8MB)
테스트 21 〉	통과 (0.22ms, 51.5MB)
테스트 22 〉	통과 (0.29ms, 52.6MB)
테스트 23 〉	통과 (0.05ms, 52MB)
테스트 24 〉	통과 (0.20ms, 52.9MB)
테스트 25 〉	통과 (0.06ms, 52.2MB)
테스트 26 〉	통과 (0.05ms, 52.2MB)
테스트 27 〉	통과 (0.20ms, 52MB)
테스트 28 〉	통과 (0.19ms, 52.5MB)
테스트 29 〉	통과 (0.22ms, 52.3MB)
테스트 30 〉	통과 (0.20ms, 53.6MB)

time : 1 Hour 0 Minute

풀이
bfs 탐색으로 풀이

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/81302
package algo_7월4주;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_P_L2_81302_거리두기확인하기 {
	public static void main(String[] args) {
		String places[][] = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };
		int result[] = solution(places);
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(String[][] places) {
		int[] answer = new int[5];
		for (int i = 0; i < 5; i++) {
			char map[][] = new char[5][5];
			for (int j = 0; j < map.length; j++) {
				map[j] = places[i][j].toCharArray();
				System.out.println(Arrays.toString(map[j]));
			}
			answer[i] = check(map);
		}
		return answer;
	}

	private static int check(char[][] map) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (map[i][j] == 'P') {
					if (bfs(i, j, map)) {
						return 0;
					}
				}
			}
		}
		return 1;
	}

	static int di[] = { 0, 0, -1, 1 };
	static int dj[] = { 1, -1, 0, 0 };

	private static boolean bfs(int i, int j, char[][] map) {
		boolean visited[][] = new boolean[5][5];
		Queue<Integer> q = new LinkedList<>();
		visited[i][j] = true;
		q.add(i * 10 + j);
		int cnt = 0;
		while (!q.isEmpty()) {
			if (cnt == 2) {
				break;
			}
			int size = q.size();
			for (int k = 0; k < size; k++) {
				int tmp = q.poll();
				int a = tmp / 10;
				int b = tmp % 10;
				for (int l = 0; l < 4; l++) {
					int ni = a + di[l];
					int nj = b + dj[l];
					if (ni < 0 || ni >= 5 || nj < 0 || nj >= 5 || visited[ni][nj] || map[ni][nj] == 'X') {
						continue;
					}
					if (map[ni][nj] == 'P') {
						return true;
					}
					visited[ni][nj] = true;
					q.add(ni * 10 + nj);
				}
			}
			cnt++;
		}
		return false;
	}

}
