package algo_1월2주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G3_11049_행렬곱셈순서 {
	static int N;
	static int testCase[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		testCase = new int[N + 1][2];

		for (int j = 1; j < N + 1; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			testCase[j][0] = Integer.parseInt(st.nextToken());
			testCase[j][1] = Integer.parseInt(st.nextToken());
		}
		System.out.println(solution());
	}

	public static int mul(int s, int m, int e) {
		return testCase[s][0] * testCase[m][1] * testCase[e][1];
	}

	private static int solution() {
		int DP[][] = new int[N + 1][N + 1];

		for (int i = 1; i < N; i++) {
			DP[i][i + 1] = testCase[i][0] * testCase[i][1] * testCase[i + 1][1];
		}

		for (int gap = 2; gap < N; gap++) {
			for (int i = 1; i + gap < N + 1; i++) {
				int j = i + gap;
				DP[i][j] = Integer.MAX_VALUE;

				for (int k = i; k < j; k++) {
					DP[i][j] = Math.min(DP[i][k] + DP[k + 1][j] + mul(i, k, j), DP[i][j]);
				}
			}
		}
		return DP[1][N];
	}
}
