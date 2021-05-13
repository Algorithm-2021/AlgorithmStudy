/*
테스트 11 〉	통과 (0.02ms, 52.2MB)
테스트 12 〉	통과 (0.02ms, 52.9MB)
테스트 13 〉	통과 (0.02ms, 52.1MB)
테스트 14 〉	통과 (0.01ms, 52.1MB)
테스트 15 〉	통과 (0.01ms, 52MB)
 * 20m
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L2_77484_로또최고순위와최저순위_정세린 {
	static class Solution {
		boolean[] lotto = new boolean[46]; // 1 ~ 45로또번호
		int[] seq = {6, 6, 5, 4, 3, 2, 1}; // seq[일치하는 숫자 수] == 순위

		public int[] solution(int[] lottos, int[] win_nums) {
			int[] answer = new int[2];
			int zeroCnt = 0; // 안보이는수
			int cnt = 0; // 일치하는 수의 개수

			for (int winNum : win_nums) lotto[winNum] = true;

			for (int num : lottos) {
				if (num == 0) zeroCnt++; // 안보이는 숫자
				else if (lotto[num]) cnt++; // 일치
			}

			answer[0] = seq[cnt + zeroCnt];
			answer[1] = seq[cnt];
			return answer;
		}
	} // end of Solution

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		int[] answer = solution.solution(lottos, win_nums);
		System.out.println(Arrays.toString(answer));
	}

}
