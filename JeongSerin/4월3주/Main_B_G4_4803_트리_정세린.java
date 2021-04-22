/*
 * 100%에서 틀림 욜라짱남
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G4_4803_트리_정세린 {
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			// n ≤ 500     m ≤ n(n-1)/2
			int n = Integer.parseInt(st.nextToken()); // 정점의 수 1~n
			int m = Integer.parseInt(st.nextToken()); // 간선의 수
			if (n == 0 && m == 0) break; // 종료조건
			
			makeSet(n + 1);
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
//				if (a == b) continue;
				unionSet(a, b);
			}
			int cnt = 0;
			for (int i = 1; i <= n; i++) {
				if (parents[i] == -1) cnt++; 
			}
			
			sb.append("Case " + tc + ": ");
			switch (cnt) {
			case 0: // 트리가 없을 때
				sb.append("No trees.\n");
				break;
			case 1:
				sb.append("There is one tree.\n");
				break;
			default:
				sb.append("A forest of " + cnt + " trees.\n");
				break;
			}
			
			tc++;
		} // end of while
		
		System.out.println(sb.toString());
	} // end of main
	
	static void makeSet(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1); // 루트 노드 -1
	}
	
	static int findSet(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static void unionSet(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) { // 사이클이 생김 (트리가 아님)
			parents[aRoot] = -2; // 트리가 아닌것 -2로 표시
			return;
		}
		if (aRoot < bRoot) parents[bRoot] = aRoot;
		else parents[aRoot] = bRoot;
	}
	
}
