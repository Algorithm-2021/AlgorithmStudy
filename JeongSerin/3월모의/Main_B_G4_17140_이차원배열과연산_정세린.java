/*
 * 15424KB
 * 172ms
 * 1H 40m
 * HashMap으로 숫자의 등장횟수 센 뒤 정렬
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_B_G4_17140_이차원배열과연산_정세린 {
	static int[][] arr = new int[100 + 1][100 + 1];
	static HashMap<Integer, Integer> map = new HashMap<>(); // <숫자, 등장횟수>
	static int col, row;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); 
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // arr[R][C] == K가 될때까지 몇번 연산?
		
		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // end of input
		col = 3;
		row = 3;
		
		int cnt = 0;
		while (true) {
			if (cnt > 100) {
				cnt = -1;
				break;
			}
			if (arr[R][C] == K) break;
			// R연산
			if (row >= col) calcR(); 
			// C연산
			else if (row < col) calcC();
//			for (int i = 1; i <= row; i++) {
//				for (int j = 1; j <= col; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("======");
			cnt++;
		}
		
		System.out.println(cnt);
	} // end of main

	static void calcR() {
		for (int i = 1; i <= 100; i++) {
			map.clear();
			for (int j = 1; j <= 100; j++) { // 등장횟수 세기
				int num = arr[i][j];
				if (num == 0) continue; // 0은 무시
				if (map.get(num) == null) map.put(num, 1);
				else map.put(num, map.get(num) + 1);
			}
			if (map.size() == 0) break;
			
			Integer[] keys = sortMap(); // 숫자셋
			Arrays.fill(arr[i], 0); // 빈칸은 0으로 채우기 위함
			for (int a = 2; a <= keys.length * 2; a+= 2) {
				if (a > 100) break;
				arr[i][a - 1] = keys[a/2 - 1];
				if (a + 1 > 100) break;
				arr[i][a] = map.get(keys[a/2 - 1]);
			}
			
			if (i == 1) col = keys.length * 2;
			else col = Integer.max(col, keys.length * 2); // 열길이 업데이트
		}
		
	} // R연산
	
	static void calcC() {
		for (int j = 1; j <= 100; j++) {
			map.clear();
			for (int i = 1; i <= 100; i++) { // 등장횟수 세기
				int num = arr[i][j];
				if (num == 0) continue; // 0은 무시
				if (map.get(num) == null) map.put(num, 1);
				else map.put(num, map.get(num) + 1); // 개수 1 증가
			}
			if (map.size() == 0) break;
			
			Integer[] keys = sortMap(); // 숫자셋
			for (int i = 0; i <= 100; i++) arr[i][j] = 0; // 열 초기화
			
			for (int a = 2; a <= keys.length * 2; a+= 2) {
				if (a > 100) break;
				arr[a - 1][j] = keys[a/2 - 1];
				if (a + 1 > 100) break;
				arr[a][j] = map.get(keys[a/2 - 1]);
			}
			
			if (j == 1) row = keys.length * 2;
			else row = Integer.max(row, keys.length * 2);
		}
	} // C연산
	
	// 키셋 정렬
	static Integer[] sortMap() {
		Integer[] keys = map.keySet().toArray(new Integer[map.size()]); // 숫자셋
		Arrays.sort(keys, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				int appearTmp = map.get(o1) - map.get(o2); // 등장횟수
				int tmp = o1 - o2; // 수
				if (appearTmp == 0) return tmp; // 2순위 정렬 수오름차순
				return appearTmp; // 1순위정렬 등장횟수
			}
		});
		return keys;
	}
	
}
