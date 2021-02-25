package DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 김대용
 * 메모리 103404kb
 * 시간 532ms
 * 푸는시간 1H
 *
 */
public class Main_B_G5_17953_디저트 {
	static int dp[][] = new int[11][100001];
	static int dessert[][] = new int[11][100001];
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i=1; i<=M; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=1; j<=N; j++) {
				dessert[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				int max = 0;
				for(int k=1; k<=M;k++) {
					if(i>1 && j==k) { //전날 먹은것과 같은거라면 2로 나눠줌
						max = Math.max(dp[k][i-1]+dessert[j][i]/2,max);
					}else { //전날 먹은것과 다르면 그냥 dp
						max = Math.max(dp[k][i-1]+dessert[j][i],max);
					}
				}
				dp[j][i]=max;
			}
		}
		int result = 0;
		for(int i=1; i<=M;i++) {
			result = result > dp[i][N]? result:dp[i][N];
		}
		System.out.println(result);
	}
}
