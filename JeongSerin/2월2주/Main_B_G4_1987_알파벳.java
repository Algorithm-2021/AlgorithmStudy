/*
 * 12416KB
 * 964ms
 * 20m
 * dfs로 풀기
 * 방문관리 'A'-65 ~ 'Z'-65
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G4_1987_알파벳 {
	static char[][] map;
	static int[][] dh = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int R, C;
	static int max = 1;
	static boolean[] visited = new boolean[26]; // 'A'-65 ~ 'Z'-65
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		dfs(0, 0, 1);
		
		System.out.println(max);
	} // end of main
	
	static void dfs(int ipos, int jpos, int cnt) {
		char alpha = map[ipos][jpos];
		visited[alpha - 65] = true;
		max = Integer.max(max, cnt);
		
		for (int d = 0; d < 4; d++) {
			int ni = ipos + dh[d][0];
			int nj = jpos + dh[d][1];
			if (ni < 0 || ni >= R || nj < 0 || nj >=C) continue; // 배열 인덱스 초과, 방문여부 확인
			if (!visited[map[ni][nj] - 65]) {
				dfs(ni, nj, cnt+1);
			}
		}
		visited[alpha - 65] = false;
	} // end of dfs
	
}
