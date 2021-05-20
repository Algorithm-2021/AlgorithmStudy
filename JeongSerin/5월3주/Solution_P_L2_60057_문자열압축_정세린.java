/*
테스트 24 〉	통과 (1.87ms, 55.1MB)
테스트 25 〉	통과 (2.49ms, 53.8MB)
테스트 26 〉	통과 (2.06ms, 53.9MB)
테스트 27 〉	통과 (2.43ms, 53.8MB)
테스트 28 〉	통과 (0.05ms, 51.9MB)
 * 1H 10m
 * https://programmers.co.kr/learn/courses/30/lessons/60057
 */
package PROGRAMMERS;

public class Solution_P_L2_60057_문자열압축_정세린 {
	static class Solution {
		public int solution(String s) {
			int total = s.length();
			int answer = total; // 최소길이

			for (int len = 1; len < s.length(); len++) {
				int complen = total; // 압축된 문자열 길이
				for (int i = 0; i < s.length() - len; i += len) {
					String pattern = s.substring(i, i + len); // 패턴을 확인할 문자열

					int cnt = 1; // 패턴 수
					for (int j = i + len; j < s.length() - len + 1; j += len) {
						if (pattern.equals(s.substring(j, j + len))) cnt++; // 패턴 일치
						else break;
					}
					if (cnt == 1) continue; // 다음 패턴 확인
					i += (cnt - 1) * len;
					complen = complen - (cnt * len) + len + Integer.toString(cnt).length();
				}
				answer = Integer.min(answer, complen);
			}
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String s = "abcabcabcabcdededededede";
		int answer = solution.solution(s);
		System.out.println(answer);
	}

}
