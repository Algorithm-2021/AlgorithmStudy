/*
테스트 12 〉	통과 (0.08ms, 53.8MB)
테스트 13 〉	통과 (0.10ms, 52.6MB)
테스트 14 〉	통과 (0.11ms, 53.7MB)
테스트 15 〉	통과 (0.12ms, 52.2MB)
테스트 16 〉	통과 (0.06ms, 52.7MB)
 * 20m
 */
package PROGRAMMERS;

public class Solution_P_L2_12951_JadenCase문자열만들기_정세린 {
	static class Solution {
		public String solution(String s) {
			String answer = "";
			char[] chArr = s.toCharArray();
	
			boolean space = true; // 첫글자는 대문자
			for (int i = 0; i < s.length(); i++) {
				if (chArr[i] == ' ') {
					space = true;
					continue;
				}
				if (space && Character.isLowerCase(chArr[i])) chArr[i] -= 32;
				else if (!space && Character.isUpperCase(chArr[i])) chArr[i] += 32;
				space = false;
			}
	
			answer = String.valueOf(chArr);
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String s = "3people unFollowed me";
		String ans = solution.solution(s);
		System.out.println(ans);
		char ch = 'a';
		System.out.println(s + Character.(ch-32));
	}

}
