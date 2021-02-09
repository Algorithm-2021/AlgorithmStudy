/*
 * 플로이드 와샬
 * 11824KB
 * 88ms
 * 1H
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_S1_1389_케빈베이컨의6단계법칙 {
	
	static int N;	// 친구의 수
	static int M; 	// 친구 관계의 수
	static int[][] distance;	
	static final int INF = 1000000;
	static int min = Integer.MAX_VALUE;
	static int minNum;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		distance = new int[N+1][N+1];	// 거리 저장
		for (int i = 0; i < distance.length; i++) {	// 거리 초기화
			Arrays.fill(distance[i], INF);
			distance[i][i] = 0;
		}
		
		// 친구 관계인 경우 1로
		for (int i = 0; i < M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			distance[a][b] = 1;
			distance[b][a] = 1;
		}
		
		floydWarshall();
		
		for (int n = 1; n <= N; n++) {
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				if (n == i) continue;
				sum += distance[n][i];	// n번 사람이 i로의 케빈베이컨 수
			}
			if (sum < min) {
				min = sum;
				minNum = n;
			}
		}
		System.out.println(minNum);
	} // end if main
	
	static void floydWarshall() {
		// k는 경유지
		for (int k = 1; k <= N; k++) {
			// i는 출발
			for (int i = 1; i <= N; i++) {
				// j는 도착
				for(int j = 1; j <= N; j++) {
					distance[i][j] = Integer.min(distance[i][k] + distance[k][j], distance[i][j]);
				}
			}	
		}
	} // end of floydWarshall

}
