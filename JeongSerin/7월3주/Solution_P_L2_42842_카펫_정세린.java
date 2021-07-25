/*
테스트 1 〉	통과 (0.03ms, 53.2MB)
테스트 2 〉	통과 (0.05ms, 52.8MB)
테스트 3 〉	통과 (0.03ms, 53.9MB)
테스트 4 〉	통과 (0.03ms, 53MB)
테스트 5 〉	통과 (0.03ms, 52.6MB)
테스트 6 〉	통과 (0.03ms, 52.7MB)
테스트 7 〉	통과 (0.03ms, 53.1MB)
테스트 8 〉	통과 (0.03ms, 52.2MB)
테스트 9 〉	통과 (0.03ms, 51.9MB)
테스트 10 〉	통과 (0.02ms, 52.1MB)
테스트 11 〉	통과 (0.04ms, 52.2MB)
테스트 12 〉	통과 (0.04ms, 52.6MB)
테스트 13 〉	통과 (0.04ms, 52.9MB)
 * 20m
 * https://programmers.co.kr/learn/courses/30/lessons/42842
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L2_42842_카펫_정세린 {
	static class Solution {

		public int[] solution(int brown, int yellow) {
			int[] answer = new int[2];

			int a = 1;
			int b = (brown + 4) / 2;
			int c = brown + yellow;

			answer[0] = ((b) + (int) (Math.sqrt(b * b - 4 * a * c))) / (2 * a);
			answer[1] = ((b) - (int) (Math.sqrt(b * b - 4 * a * c))) / (2 * a);

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int brown = 10;
		int yellow = 2;
		int[] answer = solution.solution(brown, yellow);
		System.out.println(Arrays.toString(answer));
	}

}
