/*
테스트 22 〉	통과 (18.55ms, 53.2MB)
테스트 23 〉	통과 (14.54ms, 52.9MB)
테스트 24 〉	통과 (2.76ms, 52.8MB)
테스트 25 〉	통과 (2.53ms, 52.7MB)
테스트 26 〉	통과 (2.11ms, 51.9MB)
 * 30m
 * https://programmers.co.kr/learn/courses/30/lessons/72410
 */
package PROGRAMMERS;

public class Solution_P_L2_72412_순위검색_정세린 {
	static class Solution {
		public String solution(String new_id) {
			String answer = "";
			answer = new_id.toLowerCase(); // 1
			answer = answer.replaceAll("[^a-z 0-9 \\-\\_\\.]", ""); // 2
			answer = answer.replaceAll("(\\.)\\1+", "."); // 3
			if (answer.startsWith(".")) answer = answer.substring(1);
			if (answer.endsWith(".")) answer = answer.substring(0, answer.length() - 1); // 4
			if (answer.length() == 0) answer = "a"; // 5
			if (answer.length() >= 16) answer = answer.substring(0, 15);
			if (answer.endsWith(".")) answer = answer.substring(0, answer.length() - 1); // 6
			if (answer.length() <= 2) {
				int len = answer.length();
				while (len++ <= 2)
					answer += answer.charAt(answer.length() - 1);
			} // 7

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String new_id = "...!@BaT#*..y.abcdefghijklm";
		String answer = solution.solution(new_id);
		System.out.println(answer);
	}

}
