package algo_1월2주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G3_11066_파일합치기 {
	static int dp[][] = new int[501][501];
	static int T, N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			int testCase[] = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				testCase[j] = Integer.parseInt(st.nextToken());
			}

			System.out.println(solution(testCase));
		}
	}

	public static int MIN(int a, int b) {
		return a <= b ? a : b;
	}

	public static int sum(int[] a, int s, int e) {
		if (s == 0)
			return a[e];
		else
			return a[e] - a[s - 1];
	}

	private static int solution(int[] a) {
		int size = a.length;
		int DP[][] = new int[size][size];
		int s[] = new int[size];

		s[0] = a[0];
		for (int i = 1; i < size; i++)
			s[i] += s[i - 1] + a[i];
		for (int i = 0; i < size - 1; i++)
			DP[i][i + 1] = a[i] + a[i + 1];

		for (int gap = 2; gap < size; gap++) {
			for (int i = 0; i + gap < size; i++) {
				int j = i + gap;
				DP[i][j] = Integer.MAX_VALUE;

				for (int k = i; k < j; k++) {
					DP[i][j] = MIN(DP[i][k] + DP[k + 1][j] + sum(s, i, j), DP[i][j]);
				}
			}
		}
		return DP[0][a.length - 1];
	}
}
