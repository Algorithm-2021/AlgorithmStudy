
package algo_1월2주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G1_1328_고층빌딩 {
	static final int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		long[][][] DP = new long[101][101][101];
		DP[1][1][1] = 1;
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= L; j++) {
				for (int k = 1; k <= R; k++) {
					DP[i][j][k] += DP[i - 1][j][k] * (i - 2);
					DP[i][j][k] += DP[i - 1][j - 1][k];
					DP[i][j][k] += DP[i - 1][j][k - 1];
					DP[i][j][k] %= MOD;
				}
			}
		}

		// 정답출력
		System.out.println(DP[N][L][R]);
	}
}
