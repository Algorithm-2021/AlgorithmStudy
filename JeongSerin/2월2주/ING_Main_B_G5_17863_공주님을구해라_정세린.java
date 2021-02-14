/*
 * bfs
 * 중간에 틀림.. 왤까
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ING_Main_B_G5_17863_공주님을구해라_정세린 {
	static int[][] map;
	static boolean[][] visited;
	static int N, M, T;
	static int[][] dh = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	static class Point{	// 위치와 그람소지여부 저장
		int i, j;
		boolean gram = false;
		
		public Point(int i, int j, boolean gram) {
			super();
			this.i = i;
			this.j = j;
			this.gram = gram;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];	// (0, 0)에서 출발 (N-1, M-1)도착
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	// end of input
		
		int time = 0; // 공주를 구출하는데 걸리는 시간
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(0, 0, false)); // (0, 0)에서 출발
		visited[0][0] = true;
		boolean clear = false;
		int gramTime = 0;
		
		L:while(!q.isEmpty()) {
			int size = q.size();
			
			while(size-- > 0) {
				Point cur = q.poll();
				if (cur.i == N-1 && cur.j == M-1) {	// 공주 구출 성공
					clear = true;
					break L;
				}
				
				if (gramTime == 0 && cur.gram) {	// 그람 발견 후 소요시간
					gramTime = time + (N-1) - cur.i + (M-1) - cur.j;
				}
				
				for (int d = 0; d < 4; d++) {
					int ni = cur.i + dh[d][0];
					int nj = cur.j + dh[d][1];
					if (ni < 0 || ni >= N || nj < 0 || nj >= M || visited[ni][nj]) continue;
					if (map[ni][nj] == 1 && !cur.gram) continue; // 벽을 만났는데 그람이 없으면
					
					q.offer(new Point(ni, nj, (cur.gram || map[ni][nj] == 2)));	// 그람 이미 있음 || 그람 발견
					visited[ni][nj] = true;
				}
			}
			time++;
			if (time > T) {	// 시간초과
				clear = false;
				break L;
			}
		}
		
		if (gramTime <= T) clear = true;
		
		if (clear) System.out.println(Integer.min(time, gramTime));
		else System.out.println("Fail");
	}
}
