/*
테스트 12 〉	통과 (5.46ms, 53.1MB)
테스트 13 〉	통과 (3.87ms, 51.9MB)
테스트 14 〉	통과 (0.48ms, 52.7MB)
테스트 15 〉	통과 (11.21ms, 52.8MB)
테스트 16 〉	통과 (0.40ms, 52.4MB)
 * 효율성테스트
테스트 1 〉	통과 (131.27ms, 65.7MB)
테스트 2 〉	통과 (273.04ms, 86.8MB)
테스트 3 〉	통과 (1329.27ms, 123MB)
테스트 4 〉	통과 (116.46ms, 65MB)
테스트 5 〉	통과 (1244.78ms, 125MB)
 * 20m
 * pq를 사용하여 앞에 두 음식을 섞음.
 * pq 맨앞의 음식이 K이상이면 모든 음식이 K이상
 */
package PROGRAMMERS;

import java.util.PriorityQueue;

public class Solution_P_L2_42626_더맵게_정세린 {
	
	static class Solution {
		public int solution(int[] scoville, int K) {
			int answer = -1;
			int len = scoville.length;

			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int i = 0; i < len; i++) pq.offer(scoville[i]);

			int cnt = 0; // 섞은 횟수
			boolean makeK = false; // 모든 음식이 K이상인가
			while (!pq.isEmpty()) {
				int low1 = pq.poll(); // 스코빌 지수가 가장 낮은 음식
				if (low1 >= K) { // 스코빌 지수가 가장 낮은 음식이 K이상이면 OK
					makeK = true;
					break;
				}
				if (pq.isEmpty()) break; // 모든음식을 섞어도 K가 안될때
				
				int low2 = pq.poll(); // 스코빌 지수가 두번째로 낮은 음식
				int mix = low1 + (low2 * 2); // 가작 스코빌지수가 낮은 음식 두개를 섞음
				pq.offer(mix);

				cnt++;
			}

			if (makeK) answer = cnt;
			return answer;
		}
	} // end of Solution

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;
		int answer = solution.solution(scoville, K);
		System.out.println(answer);
	}

}
