import java.util.Stack;

/*
 * -괄호 회전하기-
 * 
 * 테스트 1 〉	통과 (14.25ms, 53.6MB)
 * 테스트 2 〉	통과 (7.08ms, 53MB)
 * 테스트 3 〉	통과 (7.83ms, 52.1MB)
 * 테스트 4 〉	통과 (11.38ms, 53.3MB)
 * 테스트 5 〉	통과 (24.49ms, 54.8MB)
 * 테스트 6 〉	통과 (13.27ms, 52.2MB)
 * 테스트 7 〉	통과 (17.04ms, 52.2MB)
 * 테스트 8 〉	통과 (21.19ms, 53.1MB)
 * 테스트 9 〉	통과 (30.31ms, 54.2MB)
 * 테스트 10 〉	통과 (49.48ms, 53.8MB)
 * 테스트 11 〉	통과 (51.74ms, 54.2MB)
 * 테스트 12 〉	통과 (0.15ms, 53MB)
 * 테스트 13 〉	통과 (0.26ms, 52.8MB)
 * 테스트 14 〉	통과 (0.26ms, 53.5MB)
 * 
 * 20M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/76502
public class Solution_P_L2_76502_괄호회전하기 {
	public int solution(String s) {
		int answer = 0;
		int length = s.length();
		
		Stack<Character> stack;
		char tc;
		F:for(int left=0; left<length; ++left) {
			stack = new Stack<>();
			for(int idx=0; idx<length; ++idx) {
				tc = s.charAt((idx+left)%length);
				switch (tc) {
				case '(':
				case '{':
				case '[':
					stack.push(tc);
					break;

				case ')':
					if(stack.isEmpty() || stack.pop() != '(') continue F;
					break;
				case '}':
					if(stack.isEmpty() || stack.pop() != '{') continue F;
					break;
				case ']':
					if(stack.isEmpty() || stack.pop() != '[') continue F;
					break;
					
				default:
					// do Nothing
					break;
				}
			}
			if(stack.isEmpty())
				answer++;
		}
		return answer;
	}


	public static void main(String[] args) {
		Solution_P_L2_76502_괄호회전하기 sol = new Solution_P_L2_76502_괄호회전하기();
		String s = "[](){}";
		int answer = sol.solution(s);
		System.out.println(answer);
	}
}