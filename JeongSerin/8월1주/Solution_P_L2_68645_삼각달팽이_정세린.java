/*
테스트 1 〉	통과 (0.01ms, 60.6MB)
테스트 2 〉	통과 (0.02ms, 73MB)
테스트 3 〉	통과 (0.01ms, 74.6MB)
테스트 4 〉	통과 (0.54ms, 72.6MB)
테스트 5 〉	통과 (0.85ms, 74.8MB)
테스트 6 〉	통과 (0.74ms, 71.9MB)
테스트 7 〉	통과 (25.61ms, 122MB)
테스트 8 〉	통과 (31.26ms, 121MB)
테스트 9 〉	통과 (32.77ms, 137MB)
https://programmers.co.kr/learn/courses/30/lessons/68645
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L2_68645_삼각달팽이_정세린 {
	static class Solution {
		int[][] dh = { { 1, 0 }, { 0, 1 }, { -1, -1 } }; // 방향순서

		public int[] solution(int n) {
			int[][] snail = new int[n][n];
			int maxNum = (n + 1) * (n / 2);
			if (n % 2 == 1) maxNum += (n / 2 + 1);
			int[] answer = new int[maxNum];

			int ipos = 0;
			int jpos = 0;
			int dir = 0;
			for (int num = 1; num <= maxNum; num++) {
				snail[ipos][jpos] = num;
				int ni = ipos + dh[dir][0];
				int nj = jpos + dh[dir][1];
				if (ni < 0 || ni >= n || nj < 0 || nj >= n) dir = (dir + 1) % 3;
				else if (snail[ni][nj] != 0) dir = (dir + 1) % 3;
				ipos = ipos + dh[dir][0];
				jpos = jpos + dh[dir][1];
			}

			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					answer[cnt++] = snail[i][j];
				}
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] answer = solution.solution(6);
		System.out.println(Arrays.toString(answer));
	}

}
