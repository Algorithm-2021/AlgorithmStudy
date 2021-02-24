/*
 * 11328KB
 * 76ms
 * 1H 30m
 * DP
 * memo[n][i]: n번째 자리에 i(0~9)가 올 경우의 수.
 * [1][0~9]: 0, 1, ..., 9로 각 경우의 수는 1가지.
 * [2][0]: 00 한가지. 1번째 자리에 0이 올경우 == [1][0]
 * [2][1]: 01, 11 두가지. 1번째 자리에 0또는 1이옴 == [1][0] + [1][1]
 * [2][2]: 02, 12, 22 세가지. 1번쨰 자리에 0,1,2가옴 == [1][0] + [1][1] + [1][2]
 * [3][2]: 002, 012, 112, 022, 122, 222 6가지 == [2][0] + [2][1] + [2][2]
 * 즉, memo[n][i] = memo[n-1][0] + memo[n-1][1] + ... + memo[n-1][i-1] + memo[n-1][i]임.
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_G5_2688_줄어들지않아_정세린 {
	static long[][] memo = new long[65][10]; // [n][0~9], n번째 자리에 0~9가 오는 경우의 수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 9; i++) memo[1][i] = 1; // 1번째 자리에 i숫자가 올 경우의 수는 1가지
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			for (int n = 2; n <= N; n++) {
				for (int i = 0; i <= 9; i++) {
					if (memo[n][i] != 0) continue; // 이미 채워짐
					
					for (int j = 0; j <= i; j++) {
						// memo[n][i] = memo[n-1][0] + memo[n-1][1] + ... + memo[n-1][i]
						memo[n][i] = memo[n][i] + memo[n - 1][j];
					}
				}
			} // end of for: memo
			
			// N자리의 줄어들지 않는 수는 n번째 자리에 0~9가 올때의 줄어들지않는 수의 합
			long cnt = 0;
			for (int i = 0; i <= 9 ; i++) cnt += memo[N][i];
			
			sb.append(cnt + "\n");
		}// end of test case
		
		System.out.println(sb.toString());
	} // end of main

}
