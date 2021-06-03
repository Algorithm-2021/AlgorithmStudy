/*
테스트 1 〉	통과 (0.09ms, 52.2MB)
테스트 2 〉	통과 (0.11ms, 53.4MB)
테스트 3 〉	통과 (0.28ms, 52.3MB)
테스트 4 〉	통과 (1.30ms, 52.7MB)
테스트 5 〉	통과 (8.17ms, 53.4MB)
테스트 6 〉	통과 (19.63ms, 55.1MB)
테스트 7 〉	통과 (170.92ms, 66.3MB)
테스트 8 〉	통과 (726.06ms, 77MB)
테스트 9 〉	통과 (486.95ms, 77.1MB)
 * 40m
 * dijkstra
 * https://programmers.co.kr/learn/courses/30/lessons/49189
 */
package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution_P_L3_49189_가장먼노드_정세린 {

	static class Solution {
		ArrayList<ArrayList<Integer>> adj;
		int[] dist;
		int max = 1;
		int N;
		boolean[] visited;
		final int INF = 1000000;

		public int solution(int n, int[][] edge) {
			int answer = 0;
			adj = new ArrayList<>();
			dist = new int[n + 1];
			N = n;
			visited = new boolean[n + 1];
			// 인접리스트 1 ~ n
			for (int i = 0; i <= n; i++) adj.add(new ArrayList<Integer>());
			for (int[] e : edge) {
				adj.get(e[0]).add(e[1]);
				adj.get(e[1]).add(e[0]);
			}

			dijkstra(1);

			for (int i = 1; i <= N; i++) {
				if (dist[i] == max) answer++;
			}

			return answer;
		}

		int getSmallIdx() {
			int min = INF;
			int idx = 1;

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && dist[i] < min) {
					min = dist[i];
					idx = i;
				}
			}

			return idx;
		} // end of getSmallIdx()

		void dijkstra(int start) {
			visited[start] = true;
			Arrays.fill(dist, INF);

			for (int i : adj.get(start)) dist[i] = 1;

			for (int i = 1; i <= N - 2; i++) {
				int cur = getSmallIdx();
				visited[cur] = true;

				for (int j : adj.get(cur)) {
					if (visited[j]) continue;
					if (dist[j] > dist[cur] + 1) {
						dist[j] = dist[cur] + 1;
						max = dist[j] > max ? dist[j] : max;
					}
				}
			}
		} // end of dijkstra()
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 6;
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		int answer = solution.solution(n, edge);
		System.out.println(answer);
	}

}
