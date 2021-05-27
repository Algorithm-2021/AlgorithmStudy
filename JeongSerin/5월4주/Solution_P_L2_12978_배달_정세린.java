/*
테스트 28 〉	통과 (0.51ms, 55.5MB)
테스트 29 〉	통과 (0.46ms, 53.5MB)
테스트 30 〉	통과 (0.50ms, 53.1MB)
테스트 31 〉	통과 (0.25ms, 52.3MB)
테스트 32 〉	통과 (0.29ms, 52.1MB)
 * 50m
 * 다익스트라
 * https://programmers.co.kr/learn/courses/30/lessons/12978?language=java
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L2_12978_배달_정세린 {

	static class Solution {
		final int INF = 1000000;
		int[] dist;
		int[][] adj;
		boolean[] visited;
		int N;

		public int solution(int N, int[][] road, int K) {
			int answer = 0;
			adj = new int[N + 1][N + 1]; // 음식점은 1번, 1~ N개의 장소 (N <= 50)
			dist = new int[N + 1]; // 거리
			visited = new boolean[N + 1];
			this.N = N;

			for (int i = 0; i <= N; i++) Arrays.fill(adj[i], INF);

			for (int[] info : road) { // 인접행렬
				if (adj[info[0]][info[1]] < info[2]) continue; // 최소길이 간선만 저장
				adj[info[0]][info[1]] = info[2];
				adj[info[1]][info[0]] = info[2]; // 양방향
			}

			dijkstra(1); // 출발지 1

			for (int i = 1; i <= N; i++)
				if (dist[i] <= K) answer++;
			return answer + 1; // 자신포함
		}

		void dijkstra(int start) {
			visited[start] = true;
			for (int i = 1; i <= N; i++) dist[i] = adj[1][i]; // 거리

			for (int i = 1; i <= N - 2; i++) {
				int cur = getSmallIdx();
				visited[cur] = true;
				for (int j = 1; j <= N; j++) {
					if (visited[j]) continue;
					if (dist[j] > dist[cur] + adj[cur][j])
						dist[j] = dist[cur] + adj[cur][j];
				}
			}
		} // end of dijkstra()

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

	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int N = 5;
		int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
		int K = 3;
		int answer = solution.solution(N, road, K);
		System.out.println(answer);
	}

}
