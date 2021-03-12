/*
 * 47676KB
 * 644ms
 * 40m
 * union-find
 * 합칠때 병력도 같이 합침.
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_B_G5_15809_전국시대_정세린 {
	static int[] parents;
	static int[] A; // 국가 병력
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 국가의 수 (1~N)
		int M = Integer.parseInt(st.nextToken()); // 기록의 수
		
		A = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		makeSet(N + 1);
		
		for (int i = 0; i < M; i++) { // 기록
			st = new StringTokenizer(br.readLine());
			int O = Integer.parseInt(st.nextToken()); // 1: 동맹, 2: 전쟁
			int P = Integer.parseInt(st.nextToken()); // 국가1
			int Q = Integer.parseInt(st.nextToken()); // 국가2
			
			if (O == 1) { // 동맹일때
				unionSet(P, Q, O);
			}
			
			if (O == 2) { // 전쟁일때
				int sizeP = A[findSet(P)]; // P의 규모
				int sizeQ = A[findSet(Q)]; // Q의 규모
				if (sizeP > sizeQ) { // P가 승리국
					unionSet(P, Q, O);
				}else { // Q가 승리국 또는 무승부
					unionSet(Q, P, O);
				}
			}
		}
		int cnt = 0;
		ArrayList<Integer> arrA = new ArrayList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (parents[i] == -1 && A[i] > 0) { // 병력이 0이하면 패배한 국
				cnt++; // 남아있는 국가
				arrA.add(A[i]);
			}
		}
		Collections.sort(arrA); // 오름차순
		
		System.out.println(cnt);
		String str = arrA.toString().replace(", ", " ");
		System.out.println(str.substring(1, str.length() - 1));

	} // end of main
	
	static void makeSet(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1);
	}
	
	static int findSet(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	// 합치기(동맹 또는 진 나라가 들어감)
	static boolean unionSet(int win, int lose, int O) {
		int winRoot = findSet(win);
		int loseRoot = findSet(lose);
		if (winRoot == loseRoot) return false;
		// union
		parents[loseRoot] = winRoot;
		// 병력 합치기
		// 동맹이면 둘이 그냥 합침
		if (O == 1) {
			A[winRoot] = A[winRoot] + A[loseRoot]; // 병력 합치기
			A[loseRoot] = 0;
		}
			
		// 전쟁으로 합쳐지면 승리국 - 패배국 (둘이 같으면 둘다 병력이 0이됨)
		if (O == 2) {
			A[winRoot] = A[winRoot] - A[loseRoot];
			A[loseRoot] = 0;
		}
		return true;
	}

}
