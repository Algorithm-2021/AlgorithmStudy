/*
 * memory : 96352 KB
 * time : 264 ms
 * 
 * solve time : 2 Hour
 * 
 * 풀이
 * 손님의 출발과 도착을 미리 계산하고 택시가 가장 빠른 손님을 만나면 계산한 값을 사용하여 처리
 * 
 * 
 * 
 * 
 */

package algo_1월모의고사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_19238_스타트택시 {
	static int N, M, F;
	static int map[][];
	static int di[] = { 0, 0, 1, -1 };
	static int dj[] = { 1, -1, 0, 0 };
	static boolean end = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		person p[] = new person[M];
		int si, sj;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (st.nextToken().equals("1")) {
					map[i][j] = 1;
				}

			}
		}

		st = new StringTokenizer(br.readLine());
		si = Integer.parseInt(st.nextToken());
		sj = Integer.parseInt(st.nextToken());
		boolean fuel = false;
		for (int i = 0; i < M; i++) {
			fuel = false;
			st = new StringTokenizer(br.readLine());
			if (end) {
				continue;
			}
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Queue<point> q = new LinkedList<>();
			boolean visited[][] = new boolean[N + 1][N + 1];
			visited[a][b] = true;
			point dot = new point(a, b);
			q.offer(dot);
			int cnt = 0;
			boolean brea = false;
			while (!q.isEmpty()) {
				cnt++;
				int size = q.size();
				for (int k = 0; k < size; k++) {

					point tmp = q.poll();
					for (int j = 0; j < 4; j++) {
						int ni = tmp.i + di[j];
						int nj = tmp.j + dj[j];
						if (ni == c && nj == d) {
							p[i] = new person(cnt, c, d);
							fuel = true;
							map[a][b] = i + 2;
							brea = true;
							break;
						}
						if (ni < 1 || ni > N || nj < 1 || nj > N || visited[ni][nj] || map[ni][nj] == 1) {
							continue;
						}
						point input = new point(ni, nj);
						visited[ni][nj] = true;
						q.offer(input);
					}
				}
				if (brea) {
					break;
				}
			}

			if (!fuel) {
				end = true;
			}
		}
		if (!fuel) {
			System.out.println(-1);
			end = true;
		}
		boolean pb[] = new boolean[M];
		if (!end) {
			for (int i = 0; i < M; i++) {
				Queue<point> q = new LinkedList<point>();
				PriorityQueue<point> pq = new PriorityQueue<>(new Comparator<point>() {

					@Override
					public int compare(point o1, point o2) {
						if (o1.i == o2.i) {
							return o1.j - o2.j;
						}
						return o1.i - o2.i;
					}
				});
				point start = new point(si, sj);
				q.offer(start);
				while (!q.isEmpty()) {
					boolean visited[][] = new boolean[N + 1][N + 1];
					int size = q.size();
					boolean b = false;
					for (int j = 0; j < size; j++) {
						point tmp = q.poll();
						if (map[tmp.i][tmp.j] > 1 && !pb[map[tmp.i][tmp.j] - 2]) {
							pq.offer(tmp);
						} else {
							for (int k = 0; k < 4; k++) {
								int ni = tmp.i + di[k];
								int nj = tmp.j + dj[k];
								if (ni < 1 || ni > N || nj < 1 || nj > N || visited[ni][nj] || map[ni][nj] == 1) {
									continue;
								}
								point input = new point(ni, nj);
								visited[ni][nj] = true;
								q.offer(input);
							}
						}
					}
					if (!pq.isEmpty()) {
						point output = pq.poll();
						si = p[map[output.i][output.j] - 2].i;
						sj = p[map[output.i][output.j] - 2].j;
						if (F < p[map[output.i][output.j] - 2].fuel) {
							System.out.println(-1);
							return;
						}
						F = F + p[map[output.i][output.j] - 2].fuel;
						pb[map[output.i][output.j] - 2] = true;
						break;
					}
					F--;
					if (F < 0) {
						System.out.println(-1);
						return;
					}
				}
			}
			System.out.println(F);
		}
	}

	static class person {
		int fuel;
		int i;
		int j;

		public person(int fuel, int i, int j) {
			this.fuel = fuel;
			this.i = i;
			this.j = j;
		}
	}

	static class point {
		int i;
		int j;

		public point(int i, int j) {
			this.i = i;
			this.j = j;
		}

	}
}
