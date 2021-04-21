/*
테스트 10 〉	통과 (55.78ms, 58.5MB)
테스트 11 〉	통과 (74.21ms, 57.6MB)
테스트 12 〉	통과 (1.72ms, 52.4MB)
테스트 13 〉	통과 (1.88ms, 52.9MB)
테스트 14 〉	통과 (2.30ms, 54.3MB)
 * 30m
 * 스택
 */
package PROGRAMMERS;

import java.util.Stack;

public class Solution_P_L2_76502_괄호회전하기_정세린 {
	static class Solution {
		public int solution(String s) {
			int answer = -1;
			int len = s.length();
			Stack<Character> stack = new Stack<>();
			int cnt = 0;
			
			L: for (int x = 0; x < len; x++) {
				String str = s.substring(x) + s.substring(0, x);
				stack.clear();
				
				for (int i = 0; i < len; i++) {
					char ch = str.charAt(i);
					if (ch == '(' || ch == '{' || ch == '[') stack.push(ch); // 여는괄호
					if (ch == ')' || ch == '}' || ch == ']') { // 닫는 괄호
						if (stack.isEmpty()) continue L; // 잘못된 괄호
						char top = stack.peek();
						switch (ch) {
						case ')':
							if (top == '(') stack.pop();
							else continue L;
							break;
						case '}':
							if (top == '{') stack.pop();
							else continue L;
							break;
						case ']':
							if (top == '[') stack.pop();
							else continue L;
							break;
						}
					}
				}
				if (stack.isEmpty()) cnt++;
			}

			answer = cnt;
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String s = "}]()[{";
		int answer = solution.solution(s);
		System.out.println(answer);
	}

}
