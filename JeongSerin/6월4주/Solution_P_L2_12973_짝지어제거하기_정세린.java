/*
테스트 1 〉	통과 (0.24ms, 52.9MB)
테스트 2 〉	통과 (22.96ms, 53.3MB)
테스트 3 〉	통과 (23.68ms, 54.1MB)
테스트 4 〉	통과 (25.08ms, 53.3MB)
테스트 5 〉	통과 (25.88ms, 53MB)
테스트 6 〉	통과 (25.88ms, 53.5MB)
테스트 7 〉	통과 (25.35ms, 53.9MB)
테스트 8 〉	통과 (32.78ms, 53.6MB)
테스트 9 〉	통과 (0.24ms, 52.1MB)
테스트 10 〉	통과 (0.35ms, 53.9MB)
테스트 11 〉	통과 (0.24ms, 52.8MB)
테스트 12 〉	통과 (0.17ms, 53MB)
테스트 13 〉	통과 (0.21ms, 52.3MB)
효율성  테스트
테스트 1 〉	통과 (68.01ms, 62.2MB)
테스트 2 〉	통과 (42.01ms, 57.6MB)
테스트 3 〉	통과 (48.60ms, 59MB)
테스트 4 〉	통과 (50.16ms, 59MB)
테스트 5 〉	통과 (49.83ms, 59.1MB)
테스트 6 〉	통과 (49.48ms, 58.5MB)
테스트 7 〉	통과 (49.44ms, 58.8MB)
테스트 8 〉	통과 (48.92ms, 59.5MB)
 * 15m
 * Stack
 * https://programmers.co.kr/learn/courses/30/lessons/12973
 */
package PROGRAMMERS;

import java.util.Stack;

public class Solution_P_L2_12973_짝지어제거하기_정세린 {
	static class Solution {
		public int solution(String s) {
			int answer = 1;
			Stack<Character> st = new Stack<>();

			for (int i = 0; i < s.length(); i++) {
				if (st.isEmpty()) st.add(s.charAt(i));
				else {
					if (st.peek() == s.charAt(i)) st.pop();
					else st.add(s.charAt(i));
				}
			}

			if (!st.isEmpty()) answer = 0;
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String s = "baabaa";
		int answer = solution.solution(s);
		System.out.println(answer);
	}

}
