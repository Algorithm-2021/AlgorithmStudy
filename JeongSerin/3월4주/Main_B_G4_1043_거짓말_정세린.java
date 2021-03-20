/*
 * 11612KB
 * 80ms
 * 1H 20m
 * 1. 진실을 아는사람과 파티인사람A
 * 2. 1의 A와 파티인 사람B
 * 3. 2의 B와 파티인 사람C
 *        ...
 * -> 한 그룹으로 묶어가면서 해야함 -> union-find
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_G4_1043_거짓말_정세린 {
	static int[] parents;
	static int N, M, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 파티의 수
		parents = new int[N + 1];
		ArrayList<ArrayList<Integer>> party = new ArrayList<>();
		for (int i = 0; i < M; i++) { // 파티정보 입력을 위한 리스트
			party.add(new ArrayList<Integer>());
		}
		
		st = new StringTokenizer(br.readLine(), " "); 
		K = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
		if (K == 0) { // 아무도 진실을 모를 때
			System.out.println(M);
			return;
		}
		
		int knowPerson = 0; // 진실을 아는 사람 대표
		for (int i = 1; i <= K; i++) { // 진실을 아는 사람의 번호
			int num = Integer.parseInt(st.nextToken());
			if (i == 1) knowPerson = num; // 진실 대표
			unionSet(knowPerson, num); // 진실 그룹
		}
		
		for (int i = 0; i < M; i++) { // 파티 입력
			 st = new StringTokenizer(br.readLine(), " ");
			 int numP = Integer.parseInt(st.nextToken()); // 파티의 인원
			 
			 for (int j = 0; j < numP; j++) {
				 int num = Integer.parseInt(st.nextToken());
				 party.get(i).add(num);
				 if (j > 0) unionSet(party.get(i).get(j - 1), num); // 한 파티는 같은 그룹
			 }
		} // end of input
		
		int cnt = 0;
		L:for (int i = 0; i < M; i++) {
			for (int j = 0; j < party.get(i).size(); j++) {
				int num = party.get(i).get(j);
				// 진실을 아는 사람이 파티에 한명이라도 있다
				if (isGroup(knowPerson, num)) continue L; // 진실을 아는 사람과 같은 그룹인가
			}
			cnt++;
		}
		
		System.out.println(cnt);
	} // end of main
	
	static int findSet(int a) {
		if (parents[a] <= 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static void unionSet(int a, int b) { 
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return;
		parents[bRoot] = aRoot;
	}
	
	static boolean isGroup(int a, int b) { // 같은 그룹인가
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return true;
		return false;
	}
	
}
