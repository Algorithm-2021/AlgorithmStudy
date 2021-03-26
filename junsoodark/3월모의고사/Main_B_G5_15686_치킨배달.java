/*
 * memory : 220392 KB
 * time : 396 ms
 * 
 * solve time : 0 Hour 40 Minute
 * 
 * 풀이
 * 조합을 이용하여 치킨집을 선택하는 방법마다 치킨 거리를 계산하여 최소값을 출력한다.
 * 
 * 
 * 
 * 
 */

package algo_3월모의고사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_15686_치킨배달 {
	static int N, M, min = Integer.MAX_VALUE, arr[][], tmp;
	static boolean visited[][];
	static Queue<point> q;
	static int di[] = { 0, 0, 1, -1 }, dj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<point> chicken = new ArrayList<>();
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					chicken.add(new point(i, j));
				}
			}
		}
		int pick[] = new int[chicken.size()];
		for (int i = 0; i < M; i++) {
			pick[pick.length - 1 - i] = 1;
		}
		do {
			visited = new boolean[N][N];
			q = new LinkedList<>();
			int cnt = 0;
			for (point p : chicken) {
				if (pick[cnt] == 1) {
					visited[p.i][p.j] = true;
					q.offer(p);
				}
				cnt++;
			}
			tmp = 0;
			bfs();
			if (min > tmp) {
				min = tmp;
			}
		} while (nextPermutation(pick));
		System.out.println(min);
	}

	private static void bfs() {
		int dis = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size > 0) {
				point out = q.poll();
				for (int i = 0; i < 4; i++) {
					int ni = out.i + di[i];
					int nj = out.j + dj[i];
					if (ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj]) {
						continue;
					}
					visited[ni][nj] = true;
					if (arr[ni][nj] == 1) {
						tmp += dis;
					}
					q.offer(new point(ni, nj));
				}
				size--;
			}
			dis++;
		}
	}

	public static class point {
		int i;
		int j;

		public point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static boolean nextPermutation(int p[]) {
		int n = p.length;
		int i = n - 1;
		while (i > 0 && p[i - 1] >= p[i]) {
			--i;
		}
		if (i == 0) {
			return false;
		}

		int j = n - 1;
		while (p[i - 1] >= p[j]) {
			--j;
		}
		int tmp = p[i - 1];
		p[i - 1] = p[j];
		p[j] = tmp;

		int k = n - 1;
		while (k > i) {
			tmp = p[k];
			p[k] = p[i];
			p[i] = tmp;
			k--;
			i++;
		}
		return true;
	}
}