/*
 * 30160KB
 * 252ms
 * bfs
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_4485_녹색옷입은애가젤다지_정세린 {
	static int[][] dh = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static class Point{
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // output
		int tc = 1;
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) break; // 0 입력시 테스트케이스 입력 종료
			int[][] map = new int[N][N];
			int[][] memo = new int[N][N];
			for (int i = 0; i < N; i++) Arrays.fill(memo[i], Integer.MAX_VALUE);
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // end of input
			
			memo[0][0] = map[0][0];
			Queue<Point> q = new LinkedList<Point>();
			q.offer(new Point(0, 0)); // 출발 위치
			while (!q.isEmpty()) {
				Point cur = q.poll();
				
				for (int d = 0; d < 4; d++) { // 사방탐색.
					int ni = cur.i + dh[d][0]; // 다음위치
					int nj = cur.j + dh[d][1];
					if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue; // 배열 범위체크
					int tmp = memo[cur.i][cur.j] + map[ni][nj]; // 현재까지 잃은 값 + 다음칸에서 잃을값
					if (memo[ni][nj] > tmp) { // 이전에 갔던 경우 비교
						memo[ni][nj] = tmp;
						q.offer(new Point(ni, nj));
					}
				}
			}
		
			sb.append("Problem " + tc++ + ": " + memo[N-1][N-1] + "\n");
		} // end of while
		
		System.out.println(sb.toString());
	}
	
}
