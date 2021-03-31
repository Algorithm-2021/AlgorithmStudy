/*
테스트 1 〉	통과 (0.03ms, 52.6MB)
테스트 2 〉	통과 (0.07ms, 52.6MB)
테스트 3 〉	통과 (0.18ms, 52.7MB)
테스트 4 〉	통과 (0.03ms, 52.3MB)
테스트 5 〉	통과 (0.02ms, 51.5MB)
1H
dfs, 
 */
package PROGRAMMERS;

public class Solution_P_L2_43163_단어변환_정세린 {

	static class Solution {
		String[] wordsCopy;
		int min = Integer.MAX_VALUE; // 최소 단계
		boolean[] visited;
		
		public int solution(String begin, String target, String[] words) {
			int answer = 0;
			int len = words.length;
			wordsCopy = new String[len + 1];
			wordsCopy[len] = begin; // 맨마지막에 시작 문자열 넣기
			int beginIdx = len; // 시작 인덱스
			int targetIdx = -1;
			for (int i = 0; i < len; i++) {
				wordsCopy[i] = words[i];
				if (words[i].equals(target)) targetIdx = i;
			}
			
			if (targetIdx == -1) return 0; // 타겟 문자열이 words에없음

			visited = new boolean[len + 1];
			visited[len] = true;
			dfs(beginIdx, targetIdx, 0);
	
			if (min == Integer.MAX_VALUE) return 0;
			answer = min;
			return answer;
		} // end of solution()
	
		void dfs(int curIdx, int targetIdx, int cnt) {
			if (curIdx == targetIdx) { // 종료조건
				min = Integer.min(min, cnt);
				return;
			}
			
			for (int nextIdx = 0; nextIdx < wordsCopy.length; nextIdx++) {
				if (nextIdx == curIdx || visited[nextIdx]) continue;
				if (countDiff(wordsCopy[curIdx], wordsCopy[nextIdx]) == 1) {
					visited[nextIdx] = true;
					dfs(nextIdx, targetIdx, cnt + 1);
					visited[nextIdx] = false;
				}
			}
	
		} // end of dfs()
	
		int countDiff(String cur, String next) {
			int diffCnt = 0;
			for (int i = 0; i < cur.length(); i++) {
				if (cur.charAt(i) != next.charAt(i)) diffCnt++;
				if (diffCnt > 1) break;
			}
			return diffCnt;
		} // end of countDiff()
	
	} // end of Solution

	public static void main(String[] args) {
		Solution solution = new Solution();
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
		int answer = solution.solution(begin, target, words);
		
		System.out.println(answer);
	}

}
