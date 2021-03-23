/*
테스트 21 〉	통과 (2.25ms, 52.2MB)
테스트 22 〉	통과 (3.24ms, 53.8MB)
테스트 23 〉	통과 (3.56ms, 53.3MB)
테스트 24 〉	통과 (4.63ms, 52.3MB)
테스트 25 〉	통과 (0.94ms, 52.5MB)
효율성 테스트
테스트 10 〉	통과 (172.18ms, 70.1MB)
테스트 11 〉	통과 (151.66ms, 70.4MB)
테스트 12 〉	통과 (147.11ms, 70.4MB)
테스트 13 〉	통과 (1556.65ms, 69.4MB)
테스트 14 〉	통과 (1498.99ms, 69MB)
 * 1H 10m
 * 1. pq사용하여 숫자가 작은 징검다리 0으로 만들기
 * 2. 1에서 0으로 만든 징검다리 연속으로 0이 있는 개수가 k이상이면 더이상 못건넘
 */
package PROGRAMMERS;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_P_L2_64062_징검다리건너기_정세린 {

	static class Solution {
		class Point { // 징검다리
			int idx, num;

			public Point(int idx, int num) {
				this.idx = idx;
				this.num = num;
			}
		}

		public int solution(int[] stones, int k) { // k:최대로 건너뜃수있는 칸수
			int answer = 0; // 건널수 있는 니니즈 수
			int len = stones.length; // 징검다리 길이

			PriorityQueue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					return o1.num - o2.num;
				}
			});
			for (int i = 0; i < len; i++) // 징검다리의 숫자가 가장 작은것 부터 확인
				pq.offer(new Point(i, stones[i]));

			int zeroCnt = 0; // 연속으로 있는 0의 수
			while (!pq.isEmpty()) {
				int num = pq.peek().num; // 가장 작은 징검다리의 숫자
				
				L: while (!pq.isEmpty() && pq.peek().num == num) { // 징검다리숫자가 작은것들 0으로 만들기
					int idx = pq.poll().idx;
					stones[idx] = 0;

					// 0이 연속으로 k개인지 확인하는 과정
					zeroCnt = 1;
					// idx위치에서 앞방향으로 0의 개수
					int front = idx + k;
					if (front >= len) front = len - 1; // 범위
					for (int i = idx + 1; i <= front; i++) {
						if (stones[i] == 0) {
							zeroCnt++;
							if (zeroCnt >= k)
								break L;
						} else
							break;
					}
					
					// idx위치에서 뒷방향으로 0의개수 확인
					int back = idx - k;
					if (back < 0) back = 0;
					for (int i = idx - 1; i >= back; i--) {
						if (stones[i] == 0) {
							zeroCnt++;
							if (zeroCnt >= k)
								break L;
						} else
							break;
					}
				}
				if (zeroCnt >= k) { // 연속으로 있는 0의 수가 k이상이면 더이상 못건넘
					answer = num; // 징검다리에있는 숫자만큼 니니즈가 건넜기 때문에 징검다리가 0이된거임.
					break;
				}
			} // end of while

			return answer;
		}
	} // end of Solution

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		int answer = solution.solution(stones, k);
		System.out.println(answer);
	}

}
