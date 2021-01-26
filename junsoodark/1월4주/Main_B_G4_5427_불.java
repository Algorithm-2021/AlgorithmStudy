/*
 * memory : 102468 KB
 * time : 708 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * 불과 상근이를 각각의 큐에 넣어서 bfs 탐색을 하며 퍼져나감
 * 
 * 
 */

package algo_1월4주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_5427_불 {
	static int N, R, C, answer;
	static char arr[][];
	static boolean visit[][];
	static Queue<Integer> q;
	static Queue<Integer> qs;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			answer = 1;
			q = new LinkedList<Integer>();
			qs = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			arr = new char[R][C];
			visit = new boolean[R][C];
			for (int j = 0; j < R; j++) {
				String str = br.readLine();
				for (int k = 0; k < C; k++) {
					arr[j][k] = str.charAt(k);
					if (arr[j][k] == '@') {
						qs.offer(j * 10000 + k);
						visit[j][k] = true;
					} else if (arr[j][k] == '*') {
						q.offer(j * 10000 + k);
					}
				}
			}
			bfs();
			if (answer > 0) {
				sb.append(answer).append('\n');
			} else {
				sb.append("IMPOSSIBLE").append('\n');
			}
		}
		System.out.println(sb);
	}

	static int di[] = { 0, 1, 0, -1 };
	static int dj[] = { 1, 0, -1, 0 };

	private static void bfs() {
		int t = qs.peek();
		int ti = t / 10000;
		int tj = t % 10000;
		if (ti == 0 || tj == 0 || ti == R - 1 || tj == C - 1) {
			return;
		}
		while (!qs.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int tmp = q.poll();
				int tmpi = tmp / 10000;
				int tmpj = tmp % 10000;
				for (int j = 0; j < 4; j++) {
					int ni = tmpi + di[j];
					int nj = tmpj + dj[j];
					if (ni < 0 || ni >= R || nj < 0 || nj >= C) {
						continue;
					}
					if (arr[ni][nj] == '*' || arr[ni][nj] == '#') {
						continue;
					}
					arr[ni][nj] = '*';
					q.offer(ni * 10000 + nj);
				}
			}
			size = qs.size();
			answer++;
			for (int i = 0; i < size; i++) {
				int tmp = qs.poll();
				int tmpi = tmp / 10000;
				int tmpj = tmp % 10000;
				for (int j = 0; j < 4; j++) {
					int ni = tmpi + di[j];
					int nj = tmpj + dj[j];
					if (ni < 0 || ni >= R || nj < 0 || nj >= C) {
						continue;
					}
					if (arr[ni][nj] == '*' || arr[ni][nj] == '#') {
						continue;
					}
					if (visit[ni][nj] == true) {
						continue;
					}
					if (ni == 0 || nj == 0 || ni == R - 1 || nj == C - 1) {
						return;
					}
					visit[ni][nj] = true;
					qs.offer(ni * 10000 + nj);
				}
			}
			if (qs.isEmpty()) {
				answer = -1;
			}
		}
	}
}
