/*
 * memory : 11488 KB
 * time : 76 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * 트리를 배열 구조로 구현하고 순회를 하며 string builder를 3개 선언하여 탐색 방법에 따라 각각 탐색 순서 저장
 * 
 * 
 * 
 * 
 */

package algo_2월1주;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B_S1_1991_트리순회 {
	static int N;
	static node nod[];
	static StringBuilder sb1, sb2, sb3;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb1 = new StringBuilder();
		sb2 = new StringBuilder();
		sb3 = new StringBuilder();
		nod = new node[N];
//		'A';// 65
//		'.';//46
		for (int i = 0; i < N; i++) {
			char ch[] = br.readLine().toCharArray();
			int a = ch[0] - 'A';
			int b = ch[2] - 'A';
			int c = ch[4] - 'A';
			nod[a] = new node(b, c);
		}
		order(0);
		System.out.println(sb1);
		System.out.println(sb2);
		System.out.println(sb3);
	}

	public static class node {
		int left, right;

		public node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

	static void order(int root) {
		sb1.append((char) (65 + root));
		if (nod[root].left > 0) {
			order(nod[root].left);
		}
		sb2.append((char) (65 + root));
		if (nod[root].right > 0) {
			order(nod[root].right);
		}
		sb3.append((char) (65 + root));
	}

}
