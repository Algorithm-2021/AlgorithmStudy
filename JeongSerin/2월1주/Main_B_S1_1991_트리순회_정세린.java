/*
 * 11596KB
 * 80ms
 * 40m
 * 재귀로 풀기
 * DLR, LDR, DLR
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_S1_1991_트리순회_정세린 {
	static char[][] tree;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new char[26][3]; 	// ['부모'-26]:  [0]부모, [1]왼쪽자식, [2]오른쪽자식
		for (int i = 0; i < tree.length; i++) Arrays.fill(tree[i], '.');
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char parents = st.nextToken().charAt(0);	// 부모노드
			tree[parents - 65][0] = parents;
			for (int j = 1; j <= 2; j++) {
				tree[parents - 65][j] = st.nextToken().charAt(0);
			}
		}
		preorder('A' - 65);
		System.out.println();
		inorder('A' - 65);
		System.out.println();
		postorder('A' - 65);
		System.out.println();
	}
	// 전위순회
	static void preorder(int node) {	// DLR
		if (node < 0 || node >= 26) return;
		if (tree[node][0] == '.') return;
		
		System.out.print(tree[node][0]);	// D
		
		int left = tree[node][1] - 65;
		preorder(left);	// L
		
		int right = tree[node][2] - 65;
		preorder(right);	// R
		
	}
	// 중위순회
	static void inorder(int node) {	// LDR
		if (node < 0 || node >= 26) return;
		if (tree[node][0] == '.') return;
		
		int left = tree[node][1] - 65;
		inorder(left);	// L

		System.out.print(tree[node][0]);	// D
		
		int right = tree[node][2] - 65;
		inorder(right);	// R
	}
	// 후위순회
	static void postorder(int node) {	// LRD
		if (node < 0 || node >= 26) return;
		if (tree[node][0] == '.') return;
		
		int left = tree[node][1] - 65;
		postorder(left);	// L
		
		int right = tree[node][2] - 65;
		postorder(right);	// R

		System.out.print(tree[node][0]);	// D
	}
	
}
