/*
 * memory : 27380 KB
 * time : 336 ms
 * 
 * solve time : 1 Hour 0 Minute
 * 
 * 풀이
 * 치킨집의 조합마다 거리를 계산하여 최소값 계산
 * 
 * 
 * 
 * 
 */

package algo_3월5주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//출처 : https://www.acmicpc.net/problem/21278
public class Main_B_G5_21278_호석이두마리치킨 {
	static int N, M;
	static int sN = 101, bN = 101, min = Integer.MAX_VALUE;
	static boolean visit[];
	static boolean connect[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		connect = new boolean[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connect[a][b] = true;
			connect[b][a] = true;
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				visit = new boolean[N + 1];
				int tmp = count(i, j);
				if (min > tmp) {
					min = tmp;
					sN = i;
					bN = j;
				}
			}
		}
		System.out.println(sN + " " + bN + " " + min * 2);
	}

	private static int count(int i, int j) {
		Queue<Integer> q = new LinkedList<>();
		int result = 0;
		int cnt = 1;
		visit[i] = true;
		visit[j] = true;
		q.offer(i);
		q.offer(j);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				int tmp = q.poll();
				for (int l = 1; l < N + 1; l++) {
					if (connect[tmp][l] && !visit[l]) {
						result += cnt;
						visit[l] = true;
						q.offer(l);
					}
				}
			}
			if(min<result) {
				return result;
			}
			cnt++;
		}
		return result;
	}
}
