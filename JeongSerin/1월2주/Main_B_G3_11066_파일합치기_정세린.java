/*
 29564KB
 948ms
 2d
 DP
 분할정복
 memo[i][j]: i부터 j쪽까지 합치는데 드는 최소비용
*/
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G3_11066_파일합치기_정세린 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());	// test case
		
		for (int tc = 0; tc < T; tc++) {
			int K = Integer.parseInt(br.readLine());	// 파일의 수
			int[] file  = new int[K];	// 0번째 부터 시작.
			int[][] memo = new int[K][K];	// a ~ b쪽까지 비용최솟값.
			int[] sum = new int[K]; 	// n번까지의 합 
			
			for (int i = 0; i < memo.length; i++) {
				Arrays.fill(memo[i], Integer.MAX_VALUE);
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				file[i] = Integer.parseInt(st.nextToken());
				memo[i][i] = file[i];	// i쪽부터 i쪽까지
				if (i > 0) {
					memo[i-1][i] = file[i-1] + file[i];	// i-1 쪽부터 i쪽까지
					sum[i] = sum[i-1] + file[i];
				}
				else {
					sum[0] = file[0];
				}
			}	// end of input
			
			for (int x = 2; x < K; x++) {	// 좌상 -> 우하행렬 값 구하기  ex) [0][2], [1][3], ..., [0][3], ..
				for (int t = 0; t < K; t++) {
					int i = t;
					int j = t + x;
					if (j >= K) continue;	// idx 범위 초과
					
					for (int d = i; d < j; d++) {	// (i~d) + (d+1~j) 비용 최소 찾기
						int m = 0;
						if (i > 0) m = sum[i - 1];
						int tmp = memo[i][d] + memo[d+1][j] + sum[j] - m;
						if (d == i) tmp -= file[i];		// 병합된 페이지가 아닌 초기 단독페이지일 경우 합에서 뻄
						if (d+1 == j) tmp -= file[j];	// 병합된 페이지가 아닌 초기 단독페이지일 경우 합에서 뻄
						
						memo[i][j] = (memo[i][j] < tmp) ? memo[i][j] : tmp;
					}
				}
			}
			
			System.out.println(memo[0][K-1]);	// output
		}	// end of test case
		
	}
		
}
