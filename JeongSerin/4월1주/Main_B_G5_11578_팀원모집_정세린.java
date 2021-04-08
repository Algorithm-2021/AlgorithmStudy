/*
 * 11612KB
 * 84ms
 * 40m
 * 조합
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G5_11578_팀원모집_정세린 {
	static int N, M, R;
	static boolean[] solved;
	static int[] selected;
	static boolean done = false;
	static ArrayList<ArrayList<Integer>> qList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		solved = new boolean[N + 1];
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			qList.add(new ArrayList<>());
			int O = Integer.parseInt(st.nextToken()); // m번 사람이 풀수있는 문제의 개수
			for (int i = 0; i < O; i++) {
				int q = Integer.parseInt(st.nextToken());
				qList.get(m).add(q);
				solved[q] = true;
			}
			if (qList.get(m).size() == N) { // 한사람이 모든 문제를 풀 수 있다면
				System.out.println(1);
				return;
			}
		} // end of input
		
		for (int i = 1; i <= N; i++) { // 문전체를 절대 풀 수 없음
			if (!solved[i]) {
				System.out.println(-1);
				return;
			}
		}
		
		selected = new int[M];
		for (R = 2; R <= M; R++) { // R명 선택
			combination(0, M, R);
			if (done) { // 문제 다 풀기 가능
				System.out.println(R);
				return;
			}
		}
		
		System.out.println(-1);
	} // end of main
	
	static void combination(int idx, int n, int r) {
		if (done) return;
		if (r == 0) {
			if (checkSol()) done = true;
			return;
		}
		
		for (int i = idx; i < n; i++) {
			selected[r - 1] = i;
			combination(i + 1, n, r - 1);
		}
		
	} // end of combination
	
	static boolean checkSol() {
		Arrays.fill(solved, false);
		
		for (int i = 0; i < R; i++) {
			for (int q : qList.get(selected[i])) {
				solved[q] = true; // 선택된 사람 문제 풀기
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (!solved[i]) {
				return false; // 하나라도 못푼 문제가 있음
			}
		}
		return true;
	} // end of checkSol

}
