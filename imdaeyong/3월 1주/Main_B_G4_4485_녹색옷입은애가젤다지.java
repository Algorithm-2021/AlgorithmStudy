package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * @author 김대용
 * 메모리 17260kb
 * 시간 180ms
 * 푸는시간 3H
 *
 */
public class Main_B_G4_4485_녹색옷입은애가젤다지 {
	static int N, T = 0;
	static int[][] p; // 최소 비용 저장
	static int[][] map; // 잃는 코인 저장

	static StringBuilder sb = new StringBuilder();
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		while(N!=0) {
			T++;

			p = new int[N][N];
			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					p[r][c] = Integer.MAX_VALUE;
				}
			}

			bfs(T);

			N = Integer.parseInt(in.readLine());
		} 
		System.out.println(sb);
	}

	private static void bfs(int t) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		p[0][0] = map[0][0];
		pq.add(new Node(0, 0, p[0][0]));
		
		while (!pq.isEmpty()) {
			Node pos = pq.poll();

			int r = pos.r;
			int c = pos.c;
			int coin = pos.coin;

			for (int[] dir : dirs) {
				int nr = r + dir[0];
				int nc = c + dir[1];

				if (nr < 0 || nc < 0 || nr > N - 1 || nc > N - 1) continue;

				if (p[nr][nc] > coin + map[nr][nc]) {
					p[nr][nc] = coin + map[nr][nc];
					pq.add(new Node(nr, nc, coin + map[nr][nc]));
				}
			}
		}
		System.out.println("Problem "+t+": "+ p[N-1][N-1]);
	}
	static class Node implements Comparable<Node> {
		int r;
		int c;
		int coin;

		public Node(int r, int c, int coin) {
			this.r = r;
			this.c = c;
			this.coin = coin;
		}

		public int compareTo(Node o) {
			return this.coin - o.coin;
		}
	}
}