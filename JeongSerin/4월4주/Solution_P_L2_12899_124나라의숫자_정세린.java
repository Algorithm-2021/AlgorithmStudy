/*
 * 2H
 * StringBuilder쓰기
 */
package PROGRAMMERS;

public class Solution_P_L2_12899_124나라의숫자_정세린 {
	static class Solution {
		int[] num = {4, 1, 2};

		public String solution(int n) {
			StringBuilder sb = new StringBuilder();

			int k = n;
			int remain = 0;

			while (k > 0) {
				remain = k % 3;
				k = k / 3;
				if (remain == 0) k--;
				sb.insert(0, num[remain]);
			}

			return sb.toString();
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String answer = solution.solution(12);
		System.out.println(answer);
	}

}
