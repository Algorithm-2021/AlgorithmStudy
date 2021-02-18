/*
 * memory : 12396 KB
 * time : 840 ms
 * 
 * solve time : 30 min
 * 
 * 풀이
 * dfs 방법으로 재귀를 사용하며 방문했던 알파벳을 기억하며 진행한다.
 * 
 * 
 * 
 * 
 */

package algo_2월3주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G4_1987_알파벳 {
	static int R, C;
	static boolean visited[] = new boolean[26];
	static int res = 1;
	static char arr[][];
	static int di[] = { 0, 0, 1, -1 }, dj[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		for (int i = 0; i < R; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		visited[arr[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		System.out.println(res);
	}

	private static void dfs(int a, int b, int c) {
		int ni, nj;
		for (int i = 0; i < 4; i++) {
			ni = a + di[i];
			nj = b + dj[i];
			if (ni < 0 || ni >= R || nj < 0 || nj >= C || visited[arr[ni][nj] - 'A']) {
				continue;
			}
			visited[arr[ni][nj] - 'A'] = true;
			if (c + 1 > res) {
				res = c + 1;
			}
			dfs(ni, nj, c + 1);
			visited[arr[ni][nj] - 'A'] = false;
		}
	}
}
