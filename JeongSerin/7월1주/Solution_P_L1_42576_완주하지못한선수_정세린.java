/*
테스트 1 〉	통과 (0.05ms, 53MB)
테스트 2 〉	통과 (0.05ms, 52.9MB)
테스트 3 〉	통과 (0.30ms, 52.8MB)
테스트 4 〉	통과 (0.78ms, 53.6MB)
테스트 5 〉	통과 (0.65ms, 53.9MB)
효율성  테스트
테스트 1 〉	통과 (28.88ms, 81.7MB)
테스트 2 〉	통과 (54.68ms, 91.1MB)
테스트 3 〉	통과 (63.36ms, 95.1MB)
테스트 4 〉	통과 (50.04ms, 96.8MB)
테스트 5 〉	통과 (84.86ms, 95.1MB)
 * 15m
 * HashMap || sort
 * https://programmers.co.kr/learn/courses/30/lessons/42576?language=java
 */
package PROGRAMMERS;

import java.util.HashMap;

public class Solution_P_L1_42576_완주하지못한선수_정세린 {
	static class Solution {
		public String solution(String[] participant, String[] completion) {
			String answer = "";
			HashMap<String, Integer> map = new HashMap<>();

			for (String str : completion) {
				if (map.get(str) == null)
					map.put(str, 1);
				else
					map.put(str, map.get(str) + 1);
			}

			for (String str : participant) {
				if (map.get(str) == null || map.get(str) == 0) {
					answer = str;
					break;
				}
				map.put(str, map.get(str) - 1);
			}

//	         Arrays.sort(participant);
//	         Arrays.sort(completion);

//	         answer = participant[participant.length - 1];
//	         for(int i = 0; i < completion.length; i++) {
//	             if (!participant[i].equals(completion[i])) {
//	                 answer = participant[i];
//	                 break;
//	             }
//	         }

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		String answer = solution.solution(participant, completion);
		System.out.println(answer);
	}

}
