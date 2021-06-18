/*
테스트 1 〉	통과 (0.30ms, 52.1MB)
테스트 2 〉	통과 (0.36ms, 52.8MB)
테스트 3 〉	통과 (0.31ms, 52.6MB)
테스트 4 〉	통과 (0.33ms, 52.5MB)
테스트 5 〉	통과 (0.54ms, 52.1MB)
테스트 6 〉	통과 (0.32ms, 52.5MB)
테스트 7 〉	통과 (0.33ms, 52.7MB)
테스트 8 〉	통과 (0.32ms, 52.4MB)
테스트 9 〉	통과 (0.32ms, 52.9MB)
테스트 10 〉	통과 (0.30ms, 52.5MB)
테스트 11 〉	통과 (0.33ms, 52.3MB)
테스트 12 〉	통과 (0.34ms, 52.5MB)
테스트 13 〉	통과 (0.27ms, 52MB)
테스트 14 〉	통과 (0.32ms, 52.9MB)
테스트 15 〉	통과 (0.06ms, 52.2MB)
테스트 16 〉	통과 (0.03ms, 53MB)
테스트 17 〉	통과 (0.30ms, 53.4MB)
테스트 18 〉	통과 (0.33ms, 52.7MB)
테스트 19 〉	통과 (0.31ms, 53.1MB)
테스트 20 〉	통과 (0.36ms, 53.9MB)
 * 20m
 * 해쉬셋
 * https://programmers.co.kr/learn/courses/30/lessons/12981
 */
package PROGRAMMERS;

import java.util.Arrays;
import java.util.HashSet;

public class Solution_P_L2_12981_영어끝말잇기_정세린 {

	static class Solution {
		public int[] solution(int n, String[] words) {
			int[] answer = new int[2];
			HashSet<String> set = new HashSet<>();
			set.add(words[0]);
			answer[1] = 1; // 첫번째 사람

			for (int i = 1; i < words.length; i++) {
				answer[1]++; // 턴수++
				char before = words[i - 1].charAt(words[i - 1].length() - 1); // 이전 스펠링
				if (words[i].charAt(0) != before) { // 끝말을 잇지 못함
					answer[0] = answer[0] = i % n + 1;
					break;
				} else {
					if (!set.add(words[i])) { // 나왔던 단어를 말함
						answer[0] = i % n + 1;
						break;
					}
				}
			}

			if (answer[0] == 0 && answer[1] == words.length) { // 게임이 제대로 끝남
				answer[0] = 0;
				answer[1] = 0;
			}
			else answer[1] = (int) Math.ceil(answer[1] / (n * 1.0));
			return answer;
		}

	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 3;
		String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
		int[] answer = solution.solution(n, words);
		System.out.println(Arrays.toString(answer));
	}

}
