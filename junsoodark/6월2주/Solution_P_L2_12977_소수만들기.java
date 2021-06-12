/*
정확성 테스트

테스트 1 〉	통과 (0.56ms, 52.8MB)
테스트 2 〉	통과 (1.16ms, 54.3MB)
테스트 3 〉	통과 (0.32ms, 53.2MB)
테스트 4 〉	통과 (0.61ms, 52.6MB)
테스트 5 〉	통과 (0.58ms, 53.3MB)
테스트 6 〉	통과 (1.67ms, 51.3MB)
테스트 7 〉	통과 (0.16ms, 51.8MB)
테스트 8 〉	통과 (2.08ms, 53.6MB)
테스트 9 〉	통과 (0.65ms, 52.5MB)
테스트 10 〉	통과 (1.49ms, 52.7MB)
테스트 11 〉	통과 (0.36ms, 52.3MB)
테스트 12 〉	통과 (0.06ms, 53.1MB)
테스트 13 〉	통과 (0.09ms, 52.1MB)
테스트 14 〉	통과 (0.06ms, 54MB)
테스트 15 〉	통과 (0.05ms, 53.7MB)
테스트 16 〉	통과 (2.77ms, 52MB)
테스트 17 〉	통과 (4.48ms, 52.3MB)
테스트 18 〉	통과 (0.10ms, 52.5MB)
테스트 19 〉	통과 (0.05ms, 52.4MB)
테스트 20 〉	통과 (2.04ms, 51.9MB)
테스트 21 〉	통과 (3.38ms, 51.9MB)
테스트 22 〉	통과 (0.78ms, 52.8MB)
테스트 23 〉	통과 (0.03ms, 52.8MB)
테스트 24 〉	통과 (2.32ms, 54.1MB)
테스트 25 〉	통과 (2.29ms, 51.4MB)
테스트 26 〉	통과 (0.04ms, 52.8MB)

time : 0 Hour 30 Minute

풀이
재귀와 조합을 이용하여 풀이

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12977
package algo_6월2주;

public class Solution_P_L2_12977_소수만들기 {
	public static void main(String[] args) {
		int nums[] = { 1, 2, 7, 6, 4 };
		System.out.println(solution(nums));
	}

	static int answer = 0;

	public static int solution(int[] nums) {
		combination(nums, 0, 0, 0);
		return answer;
	}

	public static void combination(int[] nums, int n, int in, int sum) {
		if (sum == 3) {
			boolean find = false;
			for (int i = 2; i <= Math.sqrt(in) + 1; i++) {
				if ((in % i) == 0) {
					find = true;
					break;
				}
			}
			if (!find) {
				answer++;
			}
			return;
		}
		if (nums.length == n) {
			return;
		}
		combination(nums, n + 1, in, sum);
		in = in + nums[n];
		combination(nums, n + 1, in, sum + 1);
	}

}
