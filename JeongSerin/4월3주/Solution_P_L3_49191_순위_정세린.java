/*
테스트 6 〉	통과 (2.99ms, 52.6MB)
테스트 7 〉	통과 (7.46ms, 54.9MB)
테스트 8 〉	통과 (9.11ms, 56.9MB)
테스트 9 〉	통과 (9.61ms, 54.8MB)
테스트 10 〉	통과 (10.94ms, 54.8MB)
 * 1H 10m
 * 그래프
 */
package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution_P_L3_49191_순위_정세린 {

	static class Solution {
		ArrayList<ArrayList<Integer>> abj = new ArrayList<>();
		int[] cnt;
		boolean[] visited;
	
		public int solution(int n, int[][] results) {
			int answer = 0;
			cnt = new int[n + 1]; // 방문 가능한 노드 개수(앞순위 선수 수)
			visited = new boolean[n + 1];
	
			for (int i = 0; i <= n; i++) abj.add(new ArrayList<Integer>());
			for (int i = 0; i < results.length; i++) {
				abj.get(results[i][1]).add(results[i][0]);
			} // 인접리스트
	
			for (int i = 1; i <= n; i++) {
				Arrays.fill(visited, false); // 방문 초기화
				dfs(i, i);
			}
	
			Arrays.sort(cnt); // 순위정렬
			
			for (int i = 1; i <= n; i++) {
				if (i == cnt[i]) { // (순위 == 앞사람수)면 순위가 정해진사람임 
					answer ++;
					if (i < n && cnt[i] == cnt[i + 1]) answer--; // 뒷사람의 cnt가 같으면 순위가 정해질 수 없기 때문에 다시 -1
				}
			}
	
			return answer;
		}
	
		void dfs(int i, int cur) { // 자신을 포함해서 앞순위 사람이 몇명인지 셈
			visited[cur] = true;
			cnt[i]++;
	
			for (int j : abj.get(cur)) {
				if (visited[j]) continue;
				dfs(i, j);
			}
		}
	} // end of Solution

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 7;
		int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}, {5, 6}, {6, 7}};
		int answer = solution.solution(n, results);
		System.out.println(answer);
	}

}
