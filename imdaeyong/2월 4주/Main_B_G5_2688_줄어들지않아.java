package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author 김대용
 * long으로 안바꿔서 음수 나왔음 ㅅㅂ
 * 메모리 11320kb
 * 시간 72ms
 * 푸는시간 2H
 *
 */
public class Main_B_G5_2688_줄어들지않아 {
	static long dp[][] = new long[65][10];
	static long result[] = new long[65];
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T= Integer.parseInt(in.readLine());
		
		//dp 64 & 10 구하기
		for(int i=0; i<10;i++) {
			dp[1][i]=1;
		}
		result[1] = 10;
		for(int i=2; i<=64; i++) {
			for(int j=0; j<10; j++) {
				for(int k=0; k<=j;k++) {
					dp[i][j] +=dp[i-1][k];
				}
				result[i] +=dp[i][j];
			}
		}
		
		for(int t=1; t<=T;t++) {
			int n = Integer.parseInt(in.readLine());
			sb.append(result[n]+"\n");
		}
		System.out.println(sb.toString());
	}
}
