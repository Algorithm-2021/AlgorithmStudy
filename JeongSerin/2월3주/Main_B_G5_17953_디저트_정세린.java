/*
 * dp
 * 106848KB
 * 524ms
 * 어제먹은거, 오늘 먹은거 골라가면서 memo에 저장.
 */

package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G5_17953_디저트_정세린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 날짜 수 (1~100,000)
		int M = Integer.parseInt(st.nextToken()); // 디저트 종류(1~10)
		int[][] dessert = new int[M+1][N+1]; // m번째 디저트. n날의 만족도
		int[][] memo = new int[M+1][N+1]; // 첫번째 선택이 m일때, n일째의 합 저장
		int max = 0; // 최대값 저장
		
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				dessert[m][n] = Integer.parseInt(st.nextToken());
			}
		} // end of input
		
		for (int m = 1; m <= M; m++) // 1번째 날 m디저트를 먹었을때, 1번째날까지의 만족도 합
			memo[m][1] = dessert[m][1];
		
		for (int d = 1; d <= N; d++) { // d번째 날
			for (int m = 1; m <= M; m++) { // 전날 먹은거
				for (int nm = 1; nm <= M; nm++) { // 오늘 먹을거
					int score = dessert[nm][d]; // 오늘 먹을거의 점수
					if (m == nm) score = score / 2; // 전날 먹은거랑 같으면 반으로

					// d-1일에 m을 먹고 d일에 nm을 먹었을때와 비교.
					memo[nm][d] = Integer.max(memo[nm][d], memo[m][d - 1] + score); 
				}
			}
		}
		
		for (int i = 1; i <= M; i++) max = Integer.max(max, memo[i][N]);
		System.out.println(max);
	} // end of main

}
