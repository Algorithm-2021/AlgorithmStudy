/*
테스트 5 〉	통과 (40.01ms, 57.7MB)
테스트 6 〉	통과 (42.25ms, 59.6MB)
테스트 7 〉	통과 (46.04ms, 58MB)
테스트 8 〉	통과 (51.99ms, 56.5MB)
테스트 9 〉	통과 (0.04ms, 51.8MB)
 * 2H
 * 이분탐색
 */
package PROGRAMMERS;

public class Solution_P_L3_43238_입국심사_정세린 {
	static class Solution {
	
		public long solution(int n, int[] times) {
			long answer = Long.MAX_VALUE;
			long end = Long.MAX_VALUE;
			for (int t : times) end = Math.min(end, t);
			long start = end; // 가장빠른 심사관이 1명 처리
			end = end * n; // 가장 빠른 심사관 * n명을 모두 처리
			
			long cnt = 0;
			long mid = (start + end) / 2;
			while (start <= end) { // 답을 찾을 때 까지
				cnt = 0;
				mid = (start + end) / 2;
				for (int t : times) {
					cnt += mid / t;
					if (cnt > n) break; // n명을 넘어가면 더이상 체크할 필요 없음
				}
				if (cnt < n) start = mid + 1;  // n명 심사 불가시 더 높은 수 탐색
				else if (cnt >= n) { // n명이상 심사가 가능하다면 더 낮은 수들 탐색
					answer = Math.min(answer, mid); // sum == n인 최소시간을 찾기 위함
					end = mid - 1;
				}
			}
	
			return answer;
		} 
	} // end of Solution

	public static void main(String[] args) {
		Solution s = new Solution();
		int n = 6;
		int[] times = {7, 10};
		long ans = s.solution(n, times);
		System.out.println(ans);
	}

}
