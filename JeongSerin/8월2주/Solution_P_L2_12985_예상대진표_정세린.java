/*
테스트 1 〉	통과 (0.02ms, 60.3MB)
테스트 2 〉	통과 (1.60ms, 70.6MB)
테스트 3 〉	통과 (0.25ms, 70.4MB)
테스트 4 〉	통과 (0.28ms, 71.9MB)
테스트 5 〉	통과 (0.23ms, 70.4MB)
테스트 6 〉	통과 (0.21ms, 74.3MB)
테스트 7 〉	통과 (0.22ms, 71.7MB)
테스트 8 〉	통과 (0.22ms, 72.8MB)
테스트 9 〉	통과 (0.24ms, 73MB)
테스트 10 〉	통과 (0.37ms, 61.6MB)
테스트 11 〉	통과 (0.27ms, 59.9MB)
테스트 12 〉	통과 (0.20ms, 71.2MB)
테스트 13 〉	통과 (0.26ms, 67.8MB)
테스트 14 〉	통과 (0.22ms, 59.4MB)
테스트 15 〉	통과 (0.29ms, 72.2MB)
테스트 16 〉	통과 (0.30ms, 70.3MB)
테스트 17 〉	통과 (0.22ms, 74.4MB)
테스트 18 〉	통과 (0.23ms, 71.7MB)
테스트 19 〉	통과 (0.21ms, 58.8MB)
테스트 20 〉	통과 (0.20ms, 60.7MB)
테스트 21 〉	통과 (0.22ms, 70.1MB)
테스트 22 〉	통과 (0.26ms, 72MB)
테스트 23 〉	통과 (0.27ms, 75.3MB)
테스트 24 〉	통과 (0.24ms, 61.2MB)
테스트 25 〉	통과 (0.03ms, 74.1MB)
테스트 26 〉	통과 (0.03ms, 69.1MB)
테스트 27 〉	통과 (0.20ms, 59.5MB)
테스트 28 〉	통과 (0.31ms, 71.4MB)
테스트 29 〉	통과 (0.34ms, 68.9MB)
테스트 30 〉	통과 (0.31ms, 60MB)
테스트 31 〉	통과 (0.20ms, 69.2MB)
테스트 32 〉	통과 (0.27ms, 70.3MB)
테스트 33 〉	통과 (0.24ms, 69.3MB)
테스트 34 〉	통과 (0.22ms, 59.9MB)
 * 10m
 * https://programmers.co.kr/learn/courses/30/lessons/12985
 */
package PROGRAMMERS;

public class Solution_P_L2_12985_예상대진표_정세린 {
	static class Solution {
		public int solution(int n, int a, int b) {
			int answer = 1;
			int num1 = Integer.min(a, b);
			int num2 = Integer.max(a, b);

			while (true) {
				if ((num2 - num1 == 1) && (num1 % 2 == 1)) break;
				num1 = (int) Math.ceil(num1 / 2.0);
				num2 = (int) Math.ceil(num2 / 2.0);
				answer++;
			}
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int answer = solution.solution(8, 4, 7);
		System.out.println(answer);
	}

}
