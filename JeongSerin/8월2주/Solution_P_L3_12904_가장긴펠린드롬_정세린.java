/*
 정확성  테스트
테스트 1 〉	통과 (0.19ms, 69.2MB)
테스트 2 〉	통과 (0.23ms, 76.2MB)
테스트 3 〉	통과 (0.62ms, 60.2MB)
테스트 4 〉	통과 (0.82ms, 61.1MB)
테스트 5 〉	통과 (0.80ms, 71.4MB)
테스트 6 〉	통과 (0.60ms, 59.4MB)
테스트 7 〉	통과 (0.49ms, 70.9MB)
테스트 8 〉	통과 (0.81ms, 74.6MB)
테스트 9 〉	통과 (0.18ms, 71.5MB)
테스트 10 〉	통과 (0.14ms, 70.1MB)
테스트 11 〉	통과 (0.18ms, 74.8MB)
테스트 12 〉	통과 (0.18ms, 74.5MB)
테스트 13 〉	통과 (0.54ms, 60.3MB)
테스트 14 〉	통과 (1.12ms, 59.8MB)
테스트 15 〉	통과 (1.47ms, 58.1MB)
테스트 16 〉	통과 (1.82ms, 72.4MB)
테스트 17 〉	통과 (0.15ms, 69.8MB)
테스트 18 〉	통과 (0.20ms, 76.2MB)
테스트 19 〉	통과 (0.44ms, 71.7MB)
테스트 20 〉	통과 (0.66ms, 70.8MB)
테스트 21 〉	통과 (0.58ms, 71.7MB)
효율성  테스트
테스트 1 〉	통과 (56.45ms, 52.6MB)
테스트 2 〉	통과 (0.42ms, 51.8MB)
 * 30m
 * reverse, substringX , 문자 하나씩 비교가 더 빠름
 * https://programmers.co.kr/learn/courses/30/lessons/12904
 */
package PROGRAMMERS;

public class Solution_P_L3_12904_가장긴펠린드롬_정세린 {
	static class Solution {
		public int solution(String s) {
			int answer = 0;
			int sLen = s.length();

			for (int len = sLen; len >= 1; len--) {
				L: for (int i = 0; i <= sLen - 1; i++) {
					if (i + len > sLen) break;
					int start = i; // 시작인덱스
					int end = i + len - 1; // 마지막 인덱스

					for (int j = 0; j < len / 2; j++) {
						if (s.charAt(start + j) != s.charAt(end - j)) continue L;
					}
					
					return len;
				}
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int answer = solution.solution("abcdcba");
		System.out.println(answer);
	}

}
