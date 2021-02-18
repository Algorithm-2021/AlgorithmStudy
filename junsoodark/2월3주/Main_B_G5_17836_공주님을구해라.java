/*
 * memory : 13180 KB
 * time : 120 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * 0,0 부터 bfs 탐색 방법으로 N-1, M-1 까지의 거리와 열쇠를 거쳐가는 거리를 비교해 짧은 거리를 찾는다.
 * 
 * 
 * 
 * 
 */

package algo_2월3주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_17836_공주님을구해라 {
	static int N, M, T, res = 10001;
	static int arr[][];
	static int di[] = { 0, 0, 1, -1 }, dj[] = { 1, -1, 0, 0 };
	static boolean end = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if (arr[0][0] == 2) {
			res = N + M - 2;
			if (res <= T) {
				System.out.println(res);
			} else {
				System.out.println("Fail");
			}
			return;
		}
		arr[0][0] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		int dis = 0;
		while (!q.isEmpty()) {
			if (dis >= T) {
				break;
			}
			if (end) {
				break;
			}
			if (res <= dis) {
				break;
			}
			dis++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int tmp = q.poll();
				for (int j = 0; j < 4; j++) {
					int ni, nj;
					ni = tmp / 100 + di[j];
					nj = tmp % 100 + dj[j];
					if (ni < 0 || ni >= N || nj < 0 || nj >= M || arr[ni][nj] == 1) {
						continue;
					}
					if (arr[ni][nj] == 2) {
						res = dis + N + M - ni - nj - 2;
						arr[ni][nj] = 1;
					} else {
						if (ni == N - 1 && nj == M - 1) {
							res = dis;
							end = true;
							break;
						} else {
							arr[ni][nj] = 1;
							q.offer(ni * 100 + nj);
						}
					}
				}
				if (end) {
					break;
				}
			}
		}
		if (res > T) {
			System.out.println("Fail");
		} else {
			System.out.println(res);
		}
	}

}
