/*
 * 13332KB
 * 124ms
 * 2H
 * bfs로 탐색.
 * 일반경로와 소드를 주운 후 걸리는 시간(공주위치 - 소드위치) 계산 후 비교
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_17863_공주님을구해라_정세린 {
	static int[][] dh = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	static class Point{	// 위치와 그람소지여부 저장
		int i, j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];	// (0, 0)에서 출발 (N-1, M-1)도착
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	// end of input
		
		int time = 0; // 공주를 구출하는데 걸리는 시간
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(0, 0)); // (0, 0)에서 출발
		visited[0][0] = true;
		boolean clear = false; // 그람을 얻지 않고 갔을 때 성공여부
		int gramTime = Integer.MAX_VALUE; // 그람을 주우고 걸리는 시간
		
		L:while(!q.isEmpty()) {
			int size = q.size();
			
			while (size-- > 0) {
				Point cur = q.poll();
				
				if (cur.i == N-1 && cur.j == M-1) {	// 공주 구출 성공
					clear = true;
					break L;
				}
				
				for (int d = 0; d < 4; d++) {
					int ni = cur.i + dh[d][0];
					int nj = cur.j + dh[d][1];
					if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
					if (visited[ni][nj]) continue;
					if (map[ni][nj] == 1) continue; // 벽을 만남
					
					if (map[ni][nj] == 2) // 다음 위치가 그람이면
						gramTime = time + 1 + (N-1) - ni + (M-1) - nj;
					
					q.offer(new Point(ni, nj));
					visited[ni][nj] = true;
				}
			}
			time++;
			if (time > T) {
				clear = false;
				break L;
			}
		}
		
		if (clear) System.out.println(Integer.min(time, gramTime));
		else if (gramTime <= T) System.out.println(gramTime);
		else System.out.println("Fail");
	}
}
