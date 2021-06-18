/*
테스트 1 〉	통과 (6.48ms, 52MB)
테스트 2 〉	통과 (5.98ms, 51.9MB)
테스트 3 〉	통과 (0.25ms, 52.1MB)
테스트 4 〉	통과 (6.99ms, 52.2MB)
테스트 5 〉	통과 (0.78ms, 53MB)
테스트 6 〉	통과 (0.41ms, 53.2MB)
테스트 7 〉	통과 (0.27ms, 52.7MB)
테스트 8 〉	통과 (0.42ms, 52.7MB)
 * 15m
 * dfs
 * https://programmers.co.kr/learn/courses/30/lessons/43165?language=java
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L2_43165_타겟넘버_정세린 {

	static class Solution {
		int[] nums;
		int cnt = 0;
		int target;

		public int solution(int[] numbers, int target) {
			int answer = 0;
			nums = Arrays.copyOf(numbers, numbers.length);
			this.target = target;

			dfs(0, 0);

			answer = cnt;
			return answer;
		}

		void dfs(int cur, int sum) {
			if (cur == nums.length) {
				if (sum == target) cnt++;
				return;
			}
			dfs(cur + 1, sum + nums[cur]);
			dfs(cur + 1, sum - nums[cur]);
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] numbers = {1, 1, 1, 1, 1};
		int target = 3;
		int answer = solution.solution(numbers, target);
		System.out.println(answer);
	}

}
