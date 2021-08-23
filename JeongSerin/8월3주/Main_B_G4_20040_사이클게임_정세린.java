/*
 * 15m 
 * 139672KB
 * 524ms
 * union find
 * https://www.acmicpc.net/problem/20040
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G4_20040_사이클게임_정세린 {
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 점의 수 0 ~ n-1
		int M = Integer.parseInt(st.nextToken()); // 진행 턴수
		
		makeSet(N);
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (!unionSet(a, b)) {
				System.out.println(i);
				return;
			}
		}
		
		System.out.println(0);
	}
	
	static void makeSet(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1);
	}
	
	static int findSet(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean unionSet(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if (rootA == rootB) return false;
		parents[rootB] = rootA;
		
		return true;
	}
}
