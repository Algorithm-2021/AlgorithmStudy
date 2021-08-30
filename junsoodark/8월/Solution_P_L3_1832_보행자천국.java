/*
정확성 테스트

테스트 1 〉	통과 (128.42ms, 99.3MB)

time : 1 Hour 10 Minute

풀이
오른쪽 아래쪽으로 DP로 누적해가면서 왼쪽에서 온것과 위쪽에서 온것을 따로 저장한다.


*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/1832
package algo_8월;

public class Solution_P_L3_1832_보행자천국 {
	static int MOD = 20170805;

	public static void main(String[] args) {
		int m = 3;
		int n = 3;
		int city_map[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		// {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
		// { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } }
		System.out.println(solution(m, n, city_map));
	}

	public static int solution(int m, int n, int[][] cityMap) {
		int dp[][][] = new int[m][n][2];
		for (int i = 0; i < dp.length; i++) {
			if (cityMap[i][0] == 1) {
				break;
			} else if (cityMap[i][0] == 0) {
				dp[i][0][0] = 1;
			}
		}
		for (int i = 0; i < dp[0].length; i++) {
			if (cityMap[0][i] == 1) {
				break;
			} else if (cityMap[0][i] == 0) {
				dp[0][i][1] = 1;
			}
		}
		for (int i = 1; i < cityMap.length; i++) {
			for (int j = 1; j < cityMap[0].length; j++) {
				if (cityMap[i][j] == 1) {
					continue;
				}
				if (cityMap[i - 1][j] == 1) {
					dp[i][j][1] = 0;
				} else if (cityMap[i - 1][j] == 2) {
					dp[i][j][1] = dp[i - 1][j][1];
				} else {
					dp[i][j][1] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
				}

				if (cityMap[i][j - 1] == 1) {
					dp[i][j][0] = 0;
				} else if (cityMap[i][j - 1] == 2) {
					dp[i][j][0] = dp[i][j - 1][0];
				} else {
					dp[i][j][0] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
				}
			}
		}
		return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
	}

}
