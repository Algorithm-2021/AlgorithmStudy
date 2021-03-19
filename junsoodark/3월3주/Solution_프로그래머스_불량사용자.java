/*
 * 테스트 1 〉	통과 (0.06ms, 53.2MB)
 * 테스트 2 〉	통과 (0.08ms, 53.8MB)
 * 테스트 3 〉	통과 (0.07ms, 51.4MB)
 * 테스트 4 〉	통과 (0.07ms, 52.2MB)
 * 테스트 5 〉	통과 (22.53ms, 53.5MB)
 * 테스트 6 〉	통과 (1.67ms, 53MB)
 * 테스트 7 〉	통과 (0.05ms, 53MB)
 * 테스트 8 〉	통과 (0.07ms, 52MB)
 * 테스트 9 〉	통과 (0.11ms, 52.5MB)
 * 테스트 10 〉	통과 (0.05ms, 51.8MB)
 * 테스트 11 〉	통과 (0.10ms, 52MB)
 * 
 * time : 0 Hour 40 Minute
 * 
 * 풀이
 * 각각의 문자열을 dfs 탐색을 통해 비교를 한후 선택이 완료되면 선택된 유저들을
 * 비트마스킹을 통해 hashset에 추가하여 중복제거를 한 후에 그 사이즈를 출력한다.
 * 
 * */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/64064
package algo_3월3주;

import java.util.HashSet;

public class Solution_프로그래머스_불량사용자 {
	static boolean visited[];
	static HashSet<Integer> hs = new HashSet<>();

	public static void main(String[] args) {
		String user_id[] = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		String banned_id[] = { "*rodo", "*rodo", "******" };
		System.out.println(solution(user_id, banned_id));
	}

	public static int solution(String[] user_id, String[] banned_id) {
		visited = new boolean[user_id.length];
		dfs(user_id, banned_id, 0, 0);
		int answer = hs.size();
		return answer;
	}

	private static void dfs(String[] user_id, String[] banned_id, int i, int bit) {
		if (i == banned_id.length) {
			hs.add(bit);
			return;
		}
		for (int j = 0; j < user_id.length; j++) {
			if (!visited[j] && isSame(banned_id[i], user_id[j])) {
				visited[j] = true;
				dfs(user_id, banned_id, i + 1, bit | 1 << j);
				visited[j] = false;
			}
		}
	}

	private static boolean isSame(String ban, String user) {
		if (user.length() != ban.length()) {
			return false;
		}
		for (int i = 0; i < user.length(); i++) {
			if (user.charAt(i) == ban.charAt(i) || ban.charAt(i) == '*') {
				continue;
			}
			return false;
		}
		return true;
	}

}
