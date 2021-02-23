/*
 * memory : 14224 KB
 * time : 140 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * bfs 탐색을 하는데 시간과 위치에 따라서 우선 순위를 두어 탐색
 * 
 * 
 * 
 * 
 */

package algo_2월4주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_G5_13549_숨바꼭질3 {
	static int N, K, Max = 100001;
	static boolean visited[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		visited = new boolean[100001];
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		PriorityQueue<point> pq = new PriorityQueue<>(new Comparator<point>() {

			@Override
			public int compare(point o1, point o2) {
				if (o1.time == o2.time) {
					return o1.pos - o2.pos;
				}
				return o1.time - o2.time;
			}
		});
		visited[N] = true;
		pq.offer(new point(N, 0));
		if (K <= N) {
			System.out.println(N - K);
			return;
		}
		while (!pq.isEmpty()) {
			point tmp = pq.poll();
			int tel = tmp.pos * 2;
			if (tel >= K) {
				if (tel == K) {
					System.out.println(tmp.time);
					return;
				} else if (tel < 100001 && Max > tel && !visited[tel]) {
					pq.offer(new point(tel, tmp.time));
					visited[tel] = true;
					Max = tel;
					pq.offer(tmp);
					continue;
				}
			} else {
				if (!visited[tel]) {
					pq.offer(new point(tel, tmp.time));
					visited[tel] = true;
					pq.offer(tmp);
					continue;
				}
			}
			if (tmp.pos < K && !visited[tmp.pos + 1]) {
				if ((tmp.pos + 1) == K) {
					System.out.println(tmp.time + 1);
					return;
				}
				pq.offer(new point(tmp.pos + 1, tmp.time + 1));
				visited[tmp.pos + 1] = true;
			}
			if (tmp.pos > 0 && !visited[tmp.pos - 1]) {
				if ((tmp.pos - 1) == K) {
					System.out.println(tmp.time + 1);
					return;
				}
				pq.offer(new point(tmp.pos - 1, tmp.time + 1));
				visited[tmp.pos - 1] = true;
			}
		}
	}

	static class point {
		int pos;
		int time;

		public point(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}

	}

}
