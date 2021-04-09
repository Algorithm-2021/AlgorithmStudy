/*
 * memory : 11936 KB
 * time : 132 ms
 * 
 * solve time : 0 Hour 40 Minute
 * 
 * 풀이
 * dfs 방식으로 모든 조합을 짜보며 모든 문제를 풀수 있으며 최소인 인원수를 출력
 * 
 * 
 * 
 * 
 */

//출처 : https://www.acmicpc.net/problem/11578
package algo_4월1주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G5_11578_팀원모집 {
	static int M, N;
	static int min = Integer.MAX_VALUE;
	static boolean visit[];
	static int solve[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		solve = new int[M];
		visit = new boolean[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				solve[i] = solve[i] | 1 << Integer.parseInt(st.nextToken());
			}
		}
		int notSol = 0;
		for (int i = 0; i < M; i++) {
			notSol = notSol | solve[i];
		}
		if (notSol + 2 != 1 << N + 1) {
			System.out.println(-1);
			return;
		}
		dfs(0, 0);
		System.out.println(min);

	}

	private static void dfs(int bit, int select) {
		if (bit + 2 == 1 << N + 1) {
			if (min > select) {
				min = select;
			}
			return;
		}
		if (select == M) {
			return;
		}
		for (int i = 0; i < M; i++) {
			if (bit == 0) {
				int tmpBit = solve[i];
				visit[i] = true;
				dfs(tmpBit, 1);
				visit[i] = false;
			} else {
				if (visit[i] == true) {
					continue;
				}
				if ((bit ^ solve[i]) > 0) {
					visit[i] = true;
					int tmpBit = bit | solve[i];
					dfs(tmpBit, select + 1);
					visit[i] = false;
				}
			}
		}
	}
}
