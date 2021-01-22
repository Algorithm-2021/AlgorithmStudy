/*
 * memory : 13252 KB
 * time : 128 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * dfs 탐색을 하며 시간별로 치즈를 녹이며 진행
 * 
 * tip
 * 우선순위큐를 사용하여 풀이
 * 시간에 따라 poll을 하며 시간이 같을경우 빈공간 먼저 진행
 * 
 */

package algo_1월3주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_2638_치즈 {
	static int di[] = { 0, 0, 1, -1 };
	static int dj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<point> pq = new PriorityQueue<point>(new Comparator<point>() {

			@Override
			public int compare(point o1, point o2) {
				if (o1.depth == o2.depth) {
					return o1.cheese - o2.cheese;
				}
				return o1.depth - o2.depth;
			}
		});
		int n, m, res = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int arr[][] = new int[n][m];
		boolean visited[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		point start = new point(0, 0, 0, 0);
		pq.offer(start);
		visited[0][0] = true;
		while (!pq.isEmpty()) {
			point tmp = pq.poll();
			res = tmp.depth;
			for (int i = 0; i < 4; i++) {
				int ni = tmp.i + di[i];
				int nj = tmp.j + dj[i];
				if (ni < 0 || ni >= n || nj < 0 || nj >= m || visited[ni][nj]) {
					continue;
				}
				if (arr[ni][nj] == 0) {
					point input = new point(ni, nj, tmp.depth, 0);
					pq.offer(input);
					visited[ni][nj] = true;
				} else {
					if (arr[ni][nj] == 1) {
						arr[ni][nj]++;
					} else {
						point input = new point(ni, nj, tmp.depth + 1, 1);
						pq.offer(input);
						visited[ni][nj] = true;
					}
				}
			}
		}
		System.out.println(res);
	}

	static class point {
		int i;
		int j;
		int depth;
		int cheese;

		public point(int i, int j, int depth, int cheese) {
			this.i = i;
			this.j = j;
			this.depth = depth;
			this.cheese = cheese;
		}

	}
}
