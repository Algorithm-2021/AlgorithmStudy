/*
정확성  테스트
테스트 1 〉	통과 (0.04ms, 53.1MB)
테스트 2 〉	통과 (0.03ms, 53.6MB)
테스트 3 〉	통과 (0.05ms, 52.5MB)
테스트 4 〉	통과 (0.03ms, 53.4MB)
테스트 5 〉	통과 (0.06ms, 52.4MB)
테스트 6 〉	통과 (0.04ms, 52.8MB)
테스트 7 〉	통과 (0.04ms, 52.8MB)
테스트 8 〉	통과 (0.06ms, 52MB)
테스트 9 〉	통과 (0.04ms, 53.8MB)
테스트 10 〉	통과 (0.03ms, 52.9MB)
효율성  테스트
테스트 1 〉	통과 (0.83ms, 52.2MB)
테스트 2 〉	통과 (0.37ms, 51.8MB)
테스트 3 〉	통과 (0.41ms, 52MB)
테스트 4 〉	통과 (0.58ms, 51.7MB)
테스트 5 〉	통과 (0.55ms, 53.1MB)
테스트 6 〉	통과 (0.67ms, 52.1MB)
테스트 7 〉	통과 (0.41ms, 52.7MB)
테스트 8 〉	통과 (0.61ms, 52.1MB)
테스트 9 〉	통과 (0.39ms, 52.1MB)
테스트 10 〉	통과 (0.60ms, 52.3MB)
 * 40m
 * https://programmers.co.kr/learn/courses/30/lessons/42898
 * DP
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L3_42898_등굣길_정세린 {
	static class Solution {
		public int solution(int m, int n, int[][] puddles) {
			int answer = 0;
			int[][] way = new int[m + 1][n + 1];
			for (int i = 1; i < way.length; i++) {
				Arrays.fill(way[i], 1);
				way[i][0] = 0;
			}
			for (int[] puddle : puddles) way[puddle[0]][puddle[1]] = 0;
			way[m][n] = -1;

			for (int i = 1; i <= m; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == 1 && j == 1) continue;
					if (way[i][j] == 0) continue; // 웅덩이 패스
					way[i][j] = (way[i - 1][j] + way[i][j - 1]) % 1000000007;
				}
			}

			// for (int i = 0; i < way.length; i++) System.out.println(Arrays.toString(way[i]));
			if (way[m][n] == -1) return 0; // 도착할 수 없는 경우
			answer = way[m][n] % 1000000007;
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int m = 4;
		int n = 3;
		int[][] puddles = {{2, 2}};
		int answer = solution.solution(m, n, puddles);
		System.out.println(answer);
	}

}
