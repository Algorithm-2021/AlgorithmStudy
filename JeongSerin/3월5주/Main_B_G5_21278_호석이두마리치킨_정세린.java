/*
 * 12996KB
 * 136ms
 * 1H
 * 플로이드와샬, 조합
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G5_21278_호석이두마리치킨_정세린 {
	static final int INF = 1000000;
	static int[][] dist;
	static int N, M;
	static int[] selected = new int[2];
	static int minDist = Integer.MAX_VALUE;
	static int minL, minS; // 최소 거리를 갖는 건물 L(큰수), S(작은수)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 건물의 개수 (1 ~ N번)
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		dist = new int[N + 1][N + 1]; 
		for (int i = 0; i <= N; i++) Arrays.fill(dist[i], INF);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dist[a][b] = 1;
			dist[b][a] = 1;
		} // end of input
		
		// 1. FloydWarshall
		for (int k = 1; k <= N; k++) { // 거쳐가는 노드
			for (int i = 1; i <= N; i++) { // 출발노드
				for (int j = 1; j <= N; j++) { // 도착노드
					if (dist[i][k] + dist[k][j] < dist[i][j]) 
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		// 2. 조합
		combination(1, N, 2);
		
		System.out.println(minS + " " + minL + " " + minDist);
	} // end of main

	static void combination (int idx, int n, int r) {
		if (r == 0) { 
			// 3. 최소거리 구하기
			getMinDist();
			return;
		}
		
		for (int i = idx; i <= n; i++) {
			selected[r - 1] = i;
			combination(i + 1, n, r - 1);
		}
	} // end of combination
	
	static void getMinDist() {
		int sum = 0;
		for (int i = 1; i <= N; i++) { // 건물 i에서 치킨집으로 가는데걸리는 시간
			if (i == selected[0] || i == selected[1]) continue; // 치킨건물이면 패스
			int distA = dist[i][selected[0]];
			int distB = dist[i][selected[1]];
			sum = sum + Integer.min(distA, distB) * 2; // 왕복거리
		}
		if (sum < minDist) {
			minDist = sum;
			minL = selected[0]; // 큰수
			minS = selected[1]; // 작은수
		} else if (sum == minDist) { // 최소거리합이 같으면
			if ((minS > selected[1]) || // 작은수가 작은것을 선택
				(minS == selected[1] && minL > selected[0])) { // 작은수가 같으면 큰수가 큰것을 선택
				minDist = sum;
				minL = selected[0]; // 큰수
				minS = selected[1]; // 작은수
			}
		}
	} // end of getMinDist
	
}
