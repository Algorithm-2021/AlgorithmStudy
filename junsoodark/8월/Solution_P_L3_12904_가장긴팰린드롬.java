/*
정확성 테스트

테스트 1 〉	통과 (0.02ms, 61.8MB)
테스트 2 〉	통과 (0.02ms, 70.9MB)
테스트 3 〉	통과 (0.55ms, 59.4MB)
테스트 4 〉	통과 (0.90ms, 73.8MB)
테스트 5 〉	통과 (0.49ms, 68.6MB)
테스트 6 〉	통과 (0.46ms, 69.2MB)
테스트 7 〉	통과 (0.53ms, 70MB)
테스트 8 〉	통과 (0.54ms, 75.2MB)
테스트 9 〉	통과 (0.02ms, 57.9MB)
테스트 10 〉	통과 (0.03ms, 58.8MB)
테스트 11 〉	통과 (0.03ms, 58.9MB)
테스트 12 〉	통과 (0.03ms, 69.1MB)
테스트 13 〉	통과 (0.32ms, 73.9MB)
테스트 14 〉	통과 (1.01ms, 71.4MB)
테스트 15 〉	통과 (1.36ms, 70.3MB)
테스트 16 〉	통과 (1.65ms, 73.1MB)
테스트 17 〉	통과 (0.02ms, 70MB)
테스트 18 〉	통과 (0.03ms, 71.9MB)
테스트 19 〉	통과 (0.40ms, 68.7MB)
테스트 20 〉	통과 (0.72ms, 62.1MB)
테스트 21 〉	통과 (0.51ms, 58.8MB)

효율성 테스트

테스트 1 〉	통과 (60.07ms, 52.5MB)
테스트 2 〉	통과 (0.11ms, 53.9MB)

time : 0 Hour 30 Minute

풀이
가장 긴 길이부터 시작하여 s의 부분문자열의 팰린드롬을 체크하여 가장 긴 팰린드롬의 길이를 찾는다.


*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12904
package algo_8월;

public class Solution_P_L3_12904_가장긴팰린드롬 {

	public static void main(String[] args) {
		String s = "abcdcba";
		System.out.println(solution(s));
	}

	public static int solution(String s) {
		for (int i = s.length(); i > 0; i--) {
			for (int j = 0; j + i <= s.length(); j++) {
				boolean find = true;
				for (int k = 0; k < i / 2; k++) {
					if (s.charAt(j + k) != s.charAt(j + i - k - 1)) {
						find = false;
						break;
					}
				}
				if (find) {
					return i;
				}
			}
		}
		return 1;
	}
}
