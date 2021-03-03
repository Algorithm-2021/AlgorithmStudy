/*
 * memory : 17716 KB
 * time : 196 ms
 * 
 * solve time : 1 Hour 0 Minute
 * 
 * 풀이
 * bfs 탐색을 하며 경로의 합이 더 작은경우 방문했어도 방문하여 값을 갱신하고 이어서 탐색한다.
 * 
 * 
 * 
 * 
 */

package algo_3월1주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_G4_4485_녹색옷입은애가젤다지 {
	static int di[] = { 0, 0, 1, -1 }, dj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		int cnt = 0;
		int N;
		StringBuilder sb = new StringBuilder();
		PriorityQueue<point> q = new PriorityQueue<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			N = Integer.parseInt(br.readLine());
			cnt++;
			if (N == 0) {
				System.out.println(sb);
				break;
			}
			int arr[][] = new int[N][N];
			boolean visited[][] = new boolean[N][N];
			int res[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			q.offer(new point(0, 0, arr[0][0]));
			visited[0][0] = true;
			res[0][0] = arr[0][0];
			while (!q.isEmpty()) {
				point tmp = q.poll();
				for (int i = 0; i < 4; i++) {
					int ni = tmp.i + di[i];
					int nj = tmp.j + dj[i];
					if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
						continue;
					}
					if (visited[ni][nj] && res[ni][nj] <= (arr[ni][nj] + tmp.sum)) {
						continue;
					}
					visited[ni][nj] = true;
					res[ni][nj] = arr[ni][nj] + tmp.sum;
					q.offer(new point(ni, nj, res[ni][nj]));
				}
			}
			sb.append("Problem ").append(cnt).append(": ").append(res[N - 1][N - 1]).append('\n');
		}
	}

	static class point implements Comparable<point> {
		int i;
		int j;
		int sum;

		public point(int i, int j, int sum) {
			this.i = i;
			this.j = j;
			this.sum = sum;
		}

		@Override
		public int compareTo(point o) {
			// TODO Auto-generated method stub
			return this.sum > o.sum ? 1 : -1;
		}

	}
}
