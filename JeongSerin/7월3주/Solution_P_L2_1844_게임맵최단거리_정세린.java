/*

테스트 1 〉	통과 (0.41ms, 52.5MB)
테스트 2 〉	통과 (0.47ms, 54MB)
테스트 3 〉	통과 (0.37ms, 53.1MB)
테스트 4 〉	통과 (0.35ms, 52.9MB)
테스트 5 〉	통과 (0.40ms, 51.8MB)
테스트 6 〉	통과 (0.42ms, 52.6MB)
테스트 7 〉	통과 (0.46ms, 52.1MB)
테스트 8 〉	통과 (0.41ms, 52.5MB)
테스트 9 〉	통과 (0.42ms, 53MB)
테스트 10 〉	통과 (0.38ms, 53.9MB)
테스트 11 〉	통과 (0.39ms, 52.1MB)
테스트 12 〉	통과 (0.38ms, 52.2MB)
테스트 13 〉	통과 (0.41ms, 52.4MB)
테스트 14 〉	통과 (0.40ms, 51.8MB)
테스트 15 〉	통과 (0.41ms, 51.6MB)
테스트 16 〉	통과 (0.41ms, 52.4MB)
테스트 17 〉	통과 (0.39ms, 52.4MB)
테스트 18 〉	통과 (1.76ms, 52.8MB)
테스트 19 〉	통과 (0.32ms, 52MB)
테스트 20 〉	통과 (0.36ms, 52.5MB)
테스트 21 〉	통과 (0.29ms, 53.8MB)
효율성  테스트
테스트 1 〉	통과 (10.25ms, 54.8MB)
테스트 2 〉	통과 (3.25ms, 52.9MB)
테스트 3 〉	통과 (6.60ms, 53.1MB)
테스트 4 〉	통과 (6.30ms, 55.9MB)
 * 30m
 * bfs
 * https://programmers.co.kr/learn/courses/30/lessons/1844
 */
package PROGRAMMERS;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_P_L2_1844_게임맵최단거리_정세린 {
	static class Solution {
		boolean[][] visited;
		int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		class Point {
			int i, j;

			public Point(int i, int j) {
				this.i = i;
				this.j = j;
			}
		}

		public int solution(int[][] maps) { // 0: 벽. 1: 공간
			int answer = -1;
			int n = maps.length;
			int m = maps[0].length;
			visited = new boolean[n][m];
			Queue<Point> q = new LinkedList<>();
			int cnt = 1;

			q.offer(new Point(0, 0)); // 시작위치
			visited[0][0] = true;
			boolean clear = false;

			L: while (!q.isEmpty()) {
				int size = q.size();

				while (size-- > 0) {
					Point cur = q.poll();
					if (cur.i == n - 1 && cur.j == m - 1) {
						clear = true;
						break L;
					}

					for (int d = 0; d < 4; d++) {
						int ni = cur.i + dir[d][0];
						int nj = cur.j + dir[d][1];
						if (ni < 0 || ni >= n || nj < 0 || nj >= m) continue;
						if (visited[ni][nj] || maps[ni][nj] == 0) continue;
						q.offer(new Point(ni, nj));
						visited[ni][nj] = true;
					}
				}
				cnt++;
			}

			if (clear) return cnt;
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
		int answer = solution.solution(maps);
		System.out.println(answer);
	}

}
