/*
정확성  테스트
테스트 1 〉	통과 (0.84ms, 53.2MB)
테스트 2 〉	통과 (0.72ms, 51.8MB)
테스트 3 〉	통과 (0.70ms, 53.9MB)
테스트 4 〉	통과 (0.71ms, 53.1MB)
테스트 5 〉	통과 (0.68ms, 52.3MB)
테스트 6 〉	통과 (0.89ms, 52.5MB)
테스트 7 〉	통과 (0.75ms, 52.5MB)
테스트 8 〉	통과 (4.70ms, 52.4MB)
테스트 9 〉	통과 (5.16ms, 52.7MB)
테스트 10 〉	통과 (1.50ms, 53MB)
테스트 11 〉	통과 (0.70ms, 51.9MB)
테스트 12 〉	통과 (0.73ms, 52MB)
테스트 13 〉	통과 (0.52ms, 52.4MB)
효율성  테스트
테스트 1 〉	통과 (129.95ms, 67.8MB)
테스트 2 〉	통과 (105.98ms, 68MB)
 * 20m
 * https://programmers.co.kr/learn/courses/30/lessons/12927
 */
package PROGRAMMERS;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_P_L3_12927_야근지수_정세린 {
	static class Solution {
		public long solution(int n, int[] works) {
			long answer = 0;
			// 시간이 많이남은 일부터 처리하기 위함
			PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			for (int work : works) pq.offer(work);

			while (n > 0) {
				int w = pq.poll();
				if (w - 1 < 0) break; // 이후 모든일을 다 처리할 수 있음.
				pq.offer(w - 1);
				n--;
			}

			while (!pq.isEmpty()) {
				int rest = pq.poll();
				if (rest <= 0) break;
				answer += (long) rest * rest;
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 4;
		int[] works = {4, 3, 3};
		long answer = solution.solution(n, works);
		System.out.println(answer);
	}

}
