/*
 * 메모리 초과 틀림
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * dfs 탐색을 하며 방문해보기
 * 
 * tip
 * 없던 열쇠를 가지게 되면 방문했던 배열 초기화
 * 있던 열쇠를 가지고 열쇠를 방문하면 . 으로 처리
 * 
 */

package algo_1월3주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G1_1194_달이차오른다가자 {
	static int N, M;
	static char arr[][];
	static int di[] = { 0, 0, -1, 1 };
	static int dj[] = { 1, -1, 0, 0 };
	static int starti, startj;
	static int res = -1;

	public static void main(String[] args) throws Exception {
//		int a = 'a'; // 97
//		int A = 'A'; // 65
//		char aa = 97; // a
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		Queue<point> q = new LinkedList<point>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == '0') {
					starti = i;
					startj = j;
				}
			}
		}
		boolean visited[][] = new boolean[N][M];
		visited[starti][startj] = true;
		boolean key[] = new boolean[7];
		point start = new point(starti, startj, visited, key, 0);
		q.offer(start);
		while (!q.isEmpty()) {
			point tmp = q.poll();
			for (int i = 0; i < 4; i++) {
				int ni = tmp.i + di[i];
				int nj = tmp.j + dj[i];
				if (ni < 0 || ni >= N || nj < 0 || nj >= M || tmp.visited[ni][nj]) {
					continue;
				}
				if (arr[ni][nj] == '#') {
					continue;
				}
				if (arr[ni][nj] == '.' || arr[ni][nj] == '0') {
					boolean inputvisit[][] = new boolean[N][M];
					for (int j = 0; j < N; j++) {
						for (int k = 0; k < M; k++) {
							inputvisit[j][k] = tmp.visited[j][k];
						}
					}
					inputvisit[ni][nj] = true;
					boolean inputkey[] = tmp.key.clone();
					point input = new point(ni, nj, inputvisit, inputkey, tmp.cnt + 1);
					q.offer(input);
				} else if (arr[ni][nj] == '1') {
					if (res == -1) {
						res = tmp.cnt + 1;
					} else {
						if (res > (tmp.cnt + 1)) {
							res = tmp.cnt + 1;
						}
					}
				} else if (arr[ni][nj] >= 97 && arr[ni][nj] <= 102) {
					if (!tmp.key[arr[ni][nj] - 97]) {
						boolean inputvisit[][] = new boolean[N][M];
						inputvisit[ni][nj] = true;
						boolean inputkey[] = tmp.key.clone();
						inputkey[arr[ni][nj] - 97] = true;
						point input = new point(ni, nj, inputvisit, inputkey, tmp.cnt + 1);
						q.offer(input);
					} else {
						boolean inputvisit[][] = new boolean[N][M];
						for (int j = 0; j < N; j++) {
							for (int k = 0; k < M; k++) {
								inputvisit[j][k] = tmp.visited[j][k];
							}
						}
						inputvisit[ni][nj] = true;
						boolean inputkey[] = tmp.key.clone();
						point input = new point(ni, nj, inputvisit, inputkey, tmp.cnt + 1);
						q.offer(input);
					}
				} else if (arr[ni][nj] >= 65 && arr[ni][nj] <= 70) {
					if (tmp.key[arr[ni][nj] - 65]) {
						boolean inputvisit[][] = new boolean[N][M];
						for (int j = 0; j < N; j++) {
							for (int k = 0; k < M; k++) {
								inputvisit[j][k] = tmp.visited[j][k];
							}
						}
						inputvisit[ni][nj] = true;
						boolean inputkey[] = tmp.key.clone();
						point input = new point(ni, nj, inputvisit, inputkey, tmp.cnt + 1);
						q.offer(input);
					}
				}
			}
		}
		System.out.println(res);
	}

	static class point {
		int i;
		int j;
		boolean visited[][];
		boolean key[];
		int cnt;

		public point(int i, int j, boolean[][] visited, boolean[] key, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.visited = visited;
			this.key = key;
			this.cnt = cnt;
		}

	}
}
