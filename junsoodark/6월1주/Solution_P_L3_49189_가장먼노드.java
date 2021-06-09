/*
정확성 테스트

테스트 1 〉	통과 (0.21ms, 52.9MB)
테스트 2 〉	통과 (0.23ms, 52.6MB)
테스트 3 〉	통과 (0.33ms, 52.8MB)
테스트 4 〉	통과 (1.20ms, 53.1MB)
테스트 5 〉	통과 (5.38ms, 53.5MB)
테스트 6 〉	통과 (5.08ms, 57.7MB)
테스트 7 〉	통과 (34.52ms, 73.4MB)
테스트 8 〉	통과 (51.57ms, 78.3MB)
테스트 9 〉	통과 (58.86ms, 78.4MB)

time : 1 Hour 0 Minute

풀이
각 progresses 에 speeds를 더해 100을 넘을떄마다 큐에 추가하여 계산

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/49189
package algo_6월1주;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_P_L3_49189_가장먼노드 {
	public static void main(String[] args) {
		int n = 6;
		int edge[][] = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		System.out.println(solution(n, edge));
	}

	public static int solution(int n, int[][] edge) {
		int answer = 0;
		boolean visited[] = new boolean[n + 1];
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<Integer>());
		}
		int dist[] = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			dist[i] = 30000;
		}
		int len = edge.length;
		for (int i = 0; i < len; i++) {
			int a = edge[i][0];
			int b = edge[i][1];
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		visited[1] = true;
		dist[1] = 0;
		Queue<Integer> q = new LinkedList<>();
		for (int j : adj.get(1)) {
			q.offer(j);
			dist[j] = 1;
			visited[j] = true;
		}
		int dis = 1;
		while (!q.isEmpty()) {
			dis++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				for (int j : adj.get(q.poll())) {
					if (visited[j]) {
						continue;
					}
					dist[j] = dis;
					visited[j] = true;
					q.offer(j);
				}
			}
		}
		for (int i = 0; i < n + 1; i++) {
			if (dist[i] == dis - 1) {
				answer++;
			}
		}
		return answer;
	}

}
