/*
정확성 테스트

테스트 1 〉	통과 (0.21ms, 51.8MB)
테스트 2 〉	통과 (0.17ms, 53.8MB)
테스트 3 〉	통과 (0.19ms, 53MB)
테스트 4 〉	통과 (0.18ms, 53.2MB)
테스트 5 〉	통과 (0.21ms, 53.2MB)
테스트 6 〉	통과 (0.18ms, 52.3MB)
테스트 7 〉	통과 (0.22ms, 52.8MB)
테스트 8 〉	통과 (0.19ms, 52MB)
테스트 9 〉	통과 (0.25ms, 52.1MB)
테스트 10 〉	통과 (0.20ms, 51.9MB)
테스트 11 〉	통과 (0.32ms, 54.3MB)
테스트 12 〉	통과 (0.18ms, 52.4MB)
테스트 13 〉	통과 (0.17ms, 53MB)
테스트 14 〉	통과 (0.18ms, 53.3MB)
테스트 15 〉	통과 (0.22ms, 52.5MB)
테스트 16 〉	통과 (0.16ms, 52.4MB)
테스트 17 〉	통과 (0.22ms, 52.6MB)
테스트 18 〉	통과 (0.16ms, 52.9MB)
테스트 19 〉	통과 (0.15ms, 53MB)
테스트 20 〉	통과 (0.15ms, 52.1MB)
테스트 21 〉	통과 (0.18ms, 52.4MB)

효율성 테스트

테스트 1 〉	통과 (8.21ms, 53.8MB)
테스트 2 〉	통과 (3.73ms, 53.8MB)
테스트 3 〉	통과 (4.89ms, 52.7MB)
테스트 4 〉	통과 (3.44ms, 53.7MB)

time : 0 Hour 40 Minute

풀이
bfs 탐색으로 풀이

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/1844
package algo_7월3주;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_P_L2_1844_게임맵최단거리 {

	public static void main(String[] args) {
		int maps[][] = { { 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1 },
				{ 0, 0, 0, 0, 1 } };
		System.out.println(solution(maps));
	}

	static int di[] = { 0, 0, -1, 1 };
	static int dj[] = { 1, -1, 0, 0 };

	public static int solution(int[][] maps) {
		int i = maps.length;
		int j = maps[0].length;
		boolean visited[][] = new boolean[i][j];
		visited[0][0] = true;
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		int answer = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			answer++;
			for (int k = 0; k < size; k++) {
				int tmp = q.poll();
				int a = tmp % 1000;
				int b = tmp / 1000;
				for (int l = 0; l < 4; l++) {
					int ni = a + di[l];
					int nj = b + dj[l];
					if (ni < 0 || ni >= i || nj < 0 || nj >= j || visited[ni][nj]) {
						continue;
					}
					if (maps[ni][nj] == 0) {
						continue;
					}
					if (ni == (i - 1) && nj == (j - 1)) {
						return answer;
					}
					visited[ni][nj] = true;
					q.offer((nj * 1000) + ni);
				}
			}
		}
		return -1;
	}

}
