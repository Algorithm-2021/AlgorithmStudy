/*
 320320KB
 680ms
 1h 30m
*/
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_S2_15486_퇴사2_정세린 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// N+1일날 퇴사
		int[] T = new int[N];	// 일하는데 걸리는 시간
		int[] P = new int[N];	// 받는 돈
		long[] memo = new long[N+1];	// 금액을메모
		long dayMax = 0;	// 해당 날에 최대로 받을 수 있는 돈
		long max = 0;	// 퇴사 전까지 최대로 받을 수 있는 돈
		
		for (int n = 0; n < N; n++) {	// n일차 까지의 최댓값 memo에 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[n] = Integer.parseInt(st.nextToken());
			P[n] = Integer.parseInt(st.nextToken());	// end of input
			
			// n날까지 최대로 받을 수 있는 돈
			dayMax = memo[n] > dayMax? memo[n] : dayMax;
			
			if (n + T[n] > N) continue;	// 퇴사 전까지 마무리 할 수 없는 일은 pass
			// n날의 일 마무리시 마무리한 날(n + Tn)의 수당 합
			memo[n + T[n]] = dayMax + P[n] > memo[n + T[n]]? dayMax + P[n] : memo[n + T[n]];
			// 받을 수 있는 최대 금액 합
			max = memo[n + T[n]] > max ? memo[n + T[n]] : max;
		}
		
		System.out.println(max);
	}
	
}
