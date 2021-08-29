/*
테스트 1 〉	통과 (120.62ms, 95.4MB)
 * 1H
 * DP
 * https://programmers.co.kr/learn/courses/30/lessons/1832
 */
package PROGRAMMERS;

public class Solution_P_L3_1832_보행자천국_정세린 {
	static class Solution {
		int MOD = 20170805;

		// 0: 이동가능, 1: 벽, 2: 방향전환 금지
		public int solution(int m, int n, int[][] cityMap) {
			int answer = 0;
			int[][][] map = new int[m][n][2]; // [][][0]: 위에서, [][][1]: 왼쪽에서

			for (int i = 0; i < m; i++) {
				if (cityMap[i][0] == 1) break;
				map[i][0][0] = 1; // 위에서 아래로
			}
			for (int j = 0; j < n; j++) {
				if (cityMap[0][j] == 1) break;
				map[0][j][1] = 1; // 왼쪽에서 오른쪽으로
			}

			for (int i = 1; i < m; i++) {
				for (int j = 1; j < n; j++) {
					if (cityMap[i][j] == 1) continue;
					int up = map[i - 1][j][0] + map[i - 1][j][1];
					int left = map[i][j - 1][0] + map[i][j - 1][1];

					if (cityMap[i - 1][j] == 2) up -= map[i - 1][j][1];
					if (cityMap[i][j - 1] == 2) left -= map[i][j - 1][0];

					map[i][j][0] = up % MOD;
					map[i][j][1] = left % MOD;
				}
			}

			answer = (map[m - 1][n - 1][0] + map[m - 1][n - 1][1]) % MOD;
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
		int answer = solution.solution(3, 6, cityMap);
		System.out.println(answer);
	}

}
