/*
테스트 1 〉	통과 (0.07ms, 52.7MB)
테스트 2 〉	통과 (0.05ms, 52.5MB)
테스트 3 〉	통과 (0.07ms, 52.8MB)
테스트 4 〉	통과 (0.07ms, 53.2MB)
테스트 5 〉	통과 (0.05ms, 52.7MB)
테스트 6 〉	통과 (0.06ms, 53.9MB)
테스트 7 〉	통과 (0.06ms, 53.2MB)
테스트 8 〉	통과 (0.05ms, 52.3MB)
테스트 9 〉	통과 (0.06ms, 52.9MB)
테스트 10 〉	통과 (0.06ms, 52.7MB)
테스트 11 〉	통과 (0.05ms, 52.2MB)
테스트 12 〉	통과 (0.06ms, 52.8MB)
테스트 13 〉	통과 (0.04ms, 52MB)
테스트 14 〉	통과 (3.26ms, 53.4MB)
테스트 15 〉	통과 (3.92ms, 54MB)
테스트 16 〉	통과 (8.91ms, 54.9MB)
테스트 17 〉	통과 (12.23ms, 55.5MB)
테스트 18 〉	통과 (12.91ms, 55.9MB)
테스트 19 〉	통과 (6.39ms, 54.7MB)
테스트 20 〉	통과 (10.89ms, 55.2MB)
효율성  테스트
테스트 1 〉	통과 (3.94ms, 57MB)
테스트 2 〉	통과 (2.76ms, 57.7MB)
테스트 3 〉	통과 (357.09ms, 245MB)
테스트 4 〉	통과 (202.39ms, 133MB)
 * 20m
 * 해쉬맵
 * https://programmers.co.kr/learn/courses/30/lessons/42577
 */
package PROGRAMMERS;

import java.util.HashMap;

public class Solution_P_L2_42577_전화번호목록_정세린 {
	static class Solution {
		public boolean solution(String[] phone_book) {
			boolean answer = true;
			HashMap<String, Boolean> map = new HashMap<>();
			StringBuilder sb = new StringBuilder();

			for (String phone : phone_book) map.put(phone, true);

			for (String phone : phone_book) {
				sb.setLength(0);
				for (int i = 0; i < phone.length() - 1; i++) {
					sb.append(phone.charAt(i));
					if (map.get(sb.toString()) != null) return false;
				}
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] phone_book = {"119", "97674223", "1195524421"};
		boolean answer = solution.solution(phone_book);
		System.out.println(answer);
	}

}
