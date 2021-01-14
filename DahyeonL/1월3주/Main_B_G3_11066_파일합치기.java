/*
 * -파일합치기-
 * 못품
 *
 * 메모리 : 
 * 시간 : 
 * 풀이 시간 : 6H ing
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//접근의 이해 비슷한 문제 있으면 풀기
//DP문제확인하기
public class Main_B_G3_11066_파일합치기 {
static int test,K,Kvalue[],sumDist[],dp[][],min;
	//dp[i][j] = i부터 j까지를 더하는  최소값
	//dp[i][i] = Kvalue[i]
	//dp[i][i+1] = Kvalue[i]+Kvalue[i+1]
	//dp[i][j] = dp[i][k]+dp[k][j]+i와 j 사이의 수를 더한값 = sumDist(i,j) -> 임의의 k에 관한 최소값
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		test = Integer.parseInt(br.readLine());
		for (int T = 0; T < test; T++) {
			min = Integer.MAX_VALUE;//min 초기화
			K = Integer.parseInt(br.readLine());
			Kvalue = new int[K];
			dp = new int[K][K];
			sumDist = new int[K];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				Kvalue[i] = Integer.parseInt(st.nextToken());
			}
			//sumDist 배열 만들기 i 까지의 밸류의 합 -> sumDist(i,j)를 만들기 위함
			sumDist[0] = Kvalue[0];
			for (int i = 1; i <K ; i++) {
				sumDist[i] = sumDist[i-1]+Kvalue[i];
			}
			//dp 값 넣기
			for (int i = 0; i < K; i++) {
				dp[i][i] = Kvalue[i];
				if(i!=K-1)	dp[i][i+1] = Kvalue[i]+Kvalue[i+1];
			}
			System.out.println(dp(0,K-1));
		}
	}
	static int sumDist(int i, int j) {
		if(i==0) {
			return sumDist[j];
		}else {
			return sumDist[j]-sumDist[i];
		}
	}
	
	static int dp(int n,int m) {
		if(dp[n][m]!=0) {
			return dp[n][m];
		}
		//how to solve it....
//		for (int j = 2; j < K; j++) {
//			for (int i = 0; i < K; i++) {
//				for (int k = n+1; k < m; k++) {
//					min = Integer.min(min, dp[n][k]+dp[k][m]+sumDist(n,m));
//				}
//			}
//		}
		dp[n][m]= min;
		return dp[n][m];
	}

}
