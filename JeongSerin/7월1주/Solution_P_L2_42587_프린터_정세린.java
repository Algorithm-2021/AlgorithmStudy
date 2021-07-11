/*
테스트 1 〉	통과 (1.51ms, 53.1MB)
테스트 2 〉	통과 (2.07ms, 52.2MB)
테스트 3 〉	통과 (1.16ms, 52.5MB)
테스트 4 〉	통과 (1.19ms, 51.8MB)
테스트 5 〉	통과 (1.21ms, 52.4MB)
테스트 6 〉	통과 (1.35ms, 52.6MB)
테스트 7 〉	통과 (1.34ms, 53MB)
테스트 8 〉	통과 (1.69ms, 53.1MB)
테스트 9 〉	통과 (1.19ms, 55.2MB)
테스트 10 〉	통과 (1.42ms, 53MB)
테스트 11 〉	통과 (1.58ms, 53MB)
테스트 12 〉	통과 (1.28ms, 52.4MB)
테스트 13 〉	통과 (1.62ms, 52.6MB)
테스트 14 〉	통과 (1.04ms, 52.4MB)
테스트 15 〉	통과 (1.23ms, 54.3MB)
테스트 16 〉	통과 (0.95ms, 53.6MB)
테스트 17 〉	통과 (1.69ms, 51.7MB)
테스트 18 〉	통과 (1.15ms, 52.9MB)
테스트 19 〉	통과 (1.66ms, 52.9MB)
테스트 20 〉	통과 (1.22ms, 52MB)
 * 15m
 * pq, q
 * https://programmers.co.kr/learn/courses/30/lessons/42587?language=java
 */
package PROGRAMMERS;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution_P_L2_42587_프린터_정세린 {
	static class Solution {
		class Job {
			int idx;
			int pri;

			public Job(int idx, int pri) {
				this.idx = idx;
				this.pri = pri;
			}
		}

		public int solution(int[] priorities, int location) {
			int answer = 0;
			PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					int sub = o2 - o1;
					return sub;
				}
			}); // 우선순위
			Queue<Job> q = new LinkedList<>(); // 작업 목록
			for (int i = 0; i < priorities.length; i++) {
				q.offer(new Job(i, priorities[i]));
				pq.offer(priorities[i]);
			}

			int seq = 1;
			while (!pq.isEmpty()) {
				int pri = pq.peek(); // 진행해야할 작업의 우선순위
				if (q.peek().pri == pri) { // 먼저 들어온 작업우선순위와 같다면
					if (q.peek().idx == location) return seq;
					else {
						seq++;
						pq.poll();
						q.poll();
					}
				} else { // 다르면 다음작업, 현재 작업은 맨뒤로
					q.offer(q.poll());
				}
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] priorities = {1, 1, 9, 1, 1, 1};
		int location = 0;
		int answer = solution.solution(priorities, location);
		System.out.println(answer);
	}

}
