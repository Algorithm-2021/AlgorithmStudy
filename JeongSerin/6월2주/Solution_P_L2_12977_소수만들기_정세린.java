/*
테스트 1 〉	통과 (0.21ms, 54.1MB)
테스트 2 〉	통과 (0.26ms, 52.2MB)
테스트 3 〉	통과 (0.07ms, 52.9MB)
테스트 4 〉	통과 (0.08ms, 52.2MB)
테스트 5 〉	통과 (0.28ms, 52.6MB)
테스트 6 〉	통과 (0.40ms, 52.7MB)
테스트 7 〉	통과 (0.15ms, 52.2MB)
테스트 8 〉	통과 (0.81ms, 52.7MB)
테스트 9 〉	통과 (0.13ms, 52.2MB)
테스트 10 〉	통과 (0.86ms, 52.9MB)
테스트 11 〉	통과 (0.06ms, 52.5MB)
테스트 12 〉	통과 (0.04ms, 52.3MB)
테스트 13 〉	통과 (0.05ms, 52.2MB)
테스트 14 〉	통과 (0.05ms, 52.3MB)
테스트 15 〉	통과 (0.04ms, 52.7MB)
테스트 16 〉	통과 (0.83ms, 52.3MB)
테스트 17 〉	통과 (1.13ms, 52.8MB)
테스트 18 〉	통과 (0.35ms, 52.5MB)
테스트 19 〉	통과 (0.32ms, 52.9MB)
테스트 20 〉	통과 (1.18ms, 52.1MB)
테스트 21 〉	통과 (1.22ms, 53.6MB)
테스트 22 〉	통과 (0.43ms, 52MB)
테스트 23 〉	통과 (0.04ms, 52.6MB)
테스트 24 〉	통과 (0.66ms, 52.9MB)
테스트 25 〉	통과 (0.62ms, 51.9MB)
테스트 26 〉	통과 (0.05ms, 51.7MB)
 * 30m
 * 에스토스테네스의체. 재귀로 조합하면 터짐.
 * https://programmers.co.kr/learn/courses/30/lessons/12977
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L2_12977_소수만들기_정세린 {
	static class Solution {
		boolean[] isPrime;
		int max = 0;

		public int solution(int[] nums) {
			int answer = 0;
			for (int n : nums) max = Integer.max(max, n);
			max = max * 3;

			isPrime = new boolean[max];
			Arrays.fill(isPrime, true);
			isPrimeNumber();

			for (int i = 0; i < nums.length - 2; i++) {
				for (int j = i + 1; j < nums.length - 1; j++) {
					for (int k = j + 1; k < nums.length; k++) {
						int sum = nums[i] + nums[j] + nums[k];
						if (isPrime[sum]) answer++;
					}
				}
			}

			return answer;
		}

		boolean isPrimeNumber() {
			for (int i = 2; i < max; i++) {
				if (!isPrime[i]) continue;
				for (int j = i + i; j < max; j += i) {
					isPrime[j] = false;
				}
			}

			return false;
		}

	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] nums = {1,2,7,6,4};
		int answer = solution.solution(nums);
		System.out.println(answer);
	}

}
