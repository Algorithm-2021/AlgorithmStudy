/*
정확성 테스트

테스트 1 〉	통과 (0.03ms, 52.2MB)
테스트 2 〉	통과 (0.05ms, 52.4MB)
테스트 3 〉	통과 (0.29ms, 52.7MB)
테스트 4 〉	통과 (0.77ms, 53.9MB)
테스트 5 〉	통과 (0.66ms, 55.1MB)

효율성 테스트

테스트 1 〉	통과 (29.50ms, 81.8MB)
테스트 2 〉	통과 (60.46ms, 88.8MB)
테스트 3 〉	통과 (85.77ms, 97.7MB)
테스트 4 〉	통과 (50.54ms, 95.8MB)
테스트 5 〉	통과 (84.94ms, 95.7MB)

time : 0 Hour 15 Minute

풀이
해쉬맵을 이용하여 각 이름의 갯수를 체크

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42576
package algo_7월1주;

import java.util.HashMap;

public class Solution_P_L1_42576_완주하지못한선수 {
	public static void main(String[] args) {
		String[] participant = { "leo", "kiki", "eden" };
		String[] completion = { "eden", "kiki" };
		System.out.println(solution(participant, completion));
	}

	public static String solution(String[] participant, String[] completion) {
		HashMap<String, Integer> hm = new HashMap<>();
		for (int i = 0; i < completion.length; i++) {
			if (hm.containsKey(completion[i])) {
				hm.put(completion[i], hm.get(completion[i]) + 1);
			} else {
				hm.put(completion[i], 1);
			}
		}
		for (int i = 0; i < participant.length; i++) {
			if (hm.containsKey(participant[i])) {
				if (hm.get(participant[i]) == 0) {
					return participant[i];
				} else {
					hm.put(participant[i], hm.get(participant[i]) - 1);
				}
			} else {
				return participant[i];
			}
		}
		String answer = "";
		return answer;
	}

}
