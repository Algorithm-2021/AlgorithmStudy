/*
정확성 테스트

테스트 1 〉	통과 (0.07ms, 52.3MB)
테스트 2 〉	통과 (0.08ms, 51.7MB)
테스트 3 〉	통과 (0.07ms, 52MB)
테스트 4 〉	통과 (0.07ms, 54.1MB)
테스트 5 〉	통과 (0.05ms, 51.8MB)
테스트 6 〉	통과 (0.04ms, 53MB)
테스트 7 〉	통과 (0.07ms, 52.2MB)
테스트 8 〉	통과 (0.05ms, 52.8MB)
테스트 9 〉	통과 (0.05ms, 52.9MB)
테스트 10 〉	통과 (0.04ms, 52.6MB)
테스트 11 〉	통과 (0.06ms, 52.8MB)
테스트 12 〉	통과 (0.09ms, 52.9MB)
테스트 13 〉	통과 (0.06ms, 52.4MB)
테스트 14 〉	통과 (0.04ms, 53.1MB)
테스트 15 〉	통과 (0.04ms, 52.9MB)
테스트 16 〉	통과 (0.04ms, 53MB)
테스트 17 〉	통과 (0.06ms, 53.9MB)
테스트 18 〉	통과 (0.09ms, 52.1MB)
테스트 19 〉	통과 (0.06ms, 52.2MB)
테스트 20 〉	통과 (0.07ms, 52.3MB)
테스트 21 〉	통과 (0.04ms, 52.4MB)
테스트 22 〉	통과 (0.05ms, 53.1MB)
테스트 23 〉	통과 (0.04ms, 52.8MB)
테스트 24 〉	통과 (0.05ms, 52.5MB)
테스트 25 〉	통과 (0.05ms, 52.4MB)
테스트 26 〉	통과 (0.08ms, 52MB)
테스트 27 〉	통과 (0.04ms, 52.1MB)
테스트 28 〉	통과 (0.05ms, 52.3MB)

time : 1 Hour 0 Minute

풀이
해쉬맵을 이용하여 옷의 갯수를 체크하여 각 숫자를 +1하여 모두 곱한후 1을 뺀 값을 출력

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42578
package algo_7월1주;

import java.util.HashMap;

public class Solution_P_L2_42578_위장 {
	public static void main(String[] args) {
		String[][] clothes = { { "yellowhat", "headgear" }, { "bluesunglasses", "eyewear" },
				{ "green_turban", "headgear" } };
		System.out.println(solution(clothes));
	}

	public static int solution(String[][] clothes) {
		HashMap<String, Integer> hm = new HashMap<>();
		for (int i = 0; i < clothes.length; i++) {
			if (hm.containsKey(clothes[i][1])) {
				hm.put(clothes[i][1], hm.get(clothes[i][1]) + 1);
			} else {
				hm.put(clothes[i][1], 1);
			}
		}
		int cnt = 1;
		for (int i : hm.values()) {
			cnt = cnt * (i + 1);
		}
		return cnt - 1;
	}

}
