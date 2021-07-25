/*
테스트 1 〉	통과 (2.41ms, 52.1MB)
테스트 2 〉	통과 (0.76ms, 52.6MB)
테스트 3 〉	통과 (0.89ms, 52.2MB)
테스트 4 〉	통과 (0.49ms, 53MB)
테스트 5 〉	통과 (0.80ms, 52.5MB)
테스트 6 〉	통과 (1.17ms, 53MB)
 * 40m
 * https://programmers.co.kr/learn/courses/30/lessons/42628
 */
package PROGRAMMERS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_P_L3_42628_이중우선순위큐_정세린 {
	static class Solution {
		public int[] solution(String[] operations) {
			int[] answer = { 0, 0 };
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			PriorityQueue<Integer> rpq = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});

			for (String oper : operations) {
				StringTokenizer st = new StringTokenizer(oper);
				String op = st.nextToken();
				int num = Integer.parseInt(st.nextToken());

				// 삽입
				if (op.equals("I")) {
					pq.offer(num);
					rpq.offer(num);
				}
				// 삭제
				else if (op.equals("D")) {
					if (pq.isEmpty() || rpq.isEmpty()) continue;
					if (num == 1) { // 최댓값 삭제
						int max = rpq.poll();
						pq.remove(max);
					} else if (num == -1) { // 최솟값 삭제
						int min = pq.poll();
						rpq.remove(min);
					}
				}
			}

			if (!pq.isEmpty() && !rpq.isEmpty()) {
				answer[0] = rpq.poll();
				answer[1] = pq.poll();
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] operations = {"I 7","I 5","I -5","D -1"};
		int[] answer = solution.solution(operations);
		System.out.println(Arrays.toString(answer));
	}

}
