/*
테스트 1 〉	통과 (116.39ms, 74MB)
테스트 2 〉	통과 (16.53ms, 52.5MB)
테스트 3 〉	통과 (11.86ms, 53MB)
테스트 4 〉	통과 (11.70ms, 53.5MB)
 * 1H
 * dfs, 완탐
 * 탐색할때 문자열 사전순서비교 후 minPath갱신
 */
package PROGRAMMERS;

import java.util.Arrays;

public class P_L3_43164_여행경로_정세린 {
	static class Solution {
		String[][] t; // 복사된 티켓
		String minpath = "/"; // 경로
		boolean[] visited; // 방문관리
		int N; // 티켓 수

		public String[] solution(String[][] tickets) {
			String[] answer = {};
			N = tickets.length;
			t = new String[N][2];
			for (int i = 0; i < N; i++) t[i] = Arrays.copyOf(tickets[i], N);
			visited = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				if (t[i][0].equals("ICN")) {
					dfs(i, 1, "ICN");
					Arrays.fill(visited, false);
				}
			}

			answer = minpath.split(",");

			return answer;
		} // end of solution

		public void dfs(int i, int cnt, String path) {
			visited[i] = true;
			if (cnt == N) {
				path = path + "," + t[i][1];
				if (minpath.equals("/")) {
					minpath = path;
				} else if (path.compareTo(minpath) < 0) {
					minpath = path;
				}
			}

			for (int k = 0; k < N; k++) {
				if (t[i][1].equals(t[k][0]) && !visited[k]) { // 현재 티켓의 도착지 == 출발지
					dfs(k, cnt + 1, path + "," + t[k][0]);
				}
			}
			visited[i] = false;
		} // end of dfs

	} // end of Solution

	public static void main(String[] args) {
		String[][] tickets = { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } };
		Solution s = new Solution();
		String[] answer = s.solution(tickets);
		System.out.println(Arrays.toString(answer));
	} // end of main

}
