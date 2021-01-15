package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//dp[i,j] -> i에서 j번까지 행렬곱셈 수의 가장 작은 값
//dp[i,i] -> 0
//dp[i,i+1] -> r[i]xc[i]xr[i+1]
//dp[i,j] -> dp[i,k]+dp[k+1,j] + r[i]xr[k+1]Xc[j] 중 최솟값

public class Main_B_G3_11049_행렬곱셈순서 {
static int N,r[],c[];
static long dp[][],min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 N = Integer.parseInt(br.readLine());
		 r = new int[N];
		 c = new int[N];
		 dp = new long[N][N];
		 for (int i = 0; i < N; i++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 r[i] = Integer.parseInt(st.nextToken());
			 c[i] = Integer.parseInt(st.nextToken());
		 }
		 //dp값 초기화
		 for (int i = 0; i < N-1; i++) {
			dp[i][i+1] = r[i]*c[i]*c[i+1];
		 }
		 //min 초기화
		 min = Long.MAX_VALUE;
		 System.out.println(dp(0,N-1));
	}
	
	static long dp(int n, int m) {
		//dp에 값이 있을 경우 계산완료
		if(dp[n][m]!=0) {
			return dp[n][m];
		}
		if(n==m) {
			return 0;
		}
		//dp에 값이 없을 경우 
		for (int k = n; k < m; k++) {
			min = Long.min(min, dp(n,k)+dp(k+1,m)+r[n]*c[k]*c[m]);
		}
		dp[n][m] = min;
		return dp[n][m];
	}

}
