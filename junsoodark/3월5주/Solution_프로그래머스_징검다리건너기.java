/*

테스트 1 〉	통과 (0.78ms, 52MB)
테스트 2 〉	통과 (0.93ms, 53.1MB)
테스트 3 〉	통과 (0.96ms, 52.9MB)
테스트 4 〉	통과 (0.89ms, 51.9MB)
테스트 5 〉	통과 (1.05ms, 53.2MB)
테스트 6 〉	통과 (1.71ms, 52.8MB)
테스트 7 〉	통과 (2.91ms, 52.7MB)
테스트 8 〉	통과 (4.13ms, 52.9MB)
테스트 9 〉	통과 (4.76ms, 52.1MB)
테스트 10 〉	통과 (0.96ms, 51.7MB)
테스트 11 〉	통과 (0.99ms, 52.4MB)
테스트 12 〉	통과 (0.94ms, 52.5MB)
테스트 13 〉	통과 (1.07ms, 52.6MB)
테스트 14 〉	통과 (1.87ms, 53.3MB)
테스트 15 〉	통과 (3.89ms, 52.4MB)
테스트 16 〉	통과 (8.17ms, 52.7MB)
테스트 17 〉	통과 (3.95ms, 52.7MB)
테스트 18 〉	통과 (0.59ms, 52.1MB)
테스트 19 〉	통과 (0.97ms, 52.3MB)
테스트 20 〉	통과 (1.00ms, 51.7MB)
테스트 21 〉	통과 (1.89ms, 52.4MB)
테스트 22 〉	통과 (4.09ms, 53.4MB)
테스트 23 〉	통과 (3.86ms, 52.7MB)
테스트 24 〉	통과 (3.81ms, 52.8MB)
테스트 25 〉	통과 (0.98ms, 52.9MB)

효율성 테스트

테스트 1 〉	통과 (197.01ms, 71.9MB)
테스트 2 〉	통과 (154.26ms, 69.6MB)
테스트 3 〉	통과 (137.72ms, 69.1MB)
테스트 4 〉	통과 (53.45ms, 70MB)
테스트 5 〉	통과 (66.63ms, 70.2MB)
테스트 6 〉	통과 (68.15ms, 70.4MB)
테스트 7 〉	통과 (176.59ms, 70.5MB)
테스트 8 〉	통과 (159.13ms, 72MB)
테스트 9 〉	통과 (140.53ms, 70.7MB)
테스트 10 〉	통과 (151.58ms, 69.5MB)
테스트 11 〉	통과 (166.09ms, 69.9MB)
테스트 12 〉	통과 (167.64ms, 70.2MB)
테스트 13 〉	통과 (502.72ms, 69.2MB)
테스트 14 〉	통과 (555.59ms, 70MB)

time : 1 Hour 0 Minute

풀이
우선순위 큐를 사용하여 숫자가 작은 돌을 꺼내면서 건널수 없는 경우일 때의 돌의 건널 수 있는 횟수를 출력

*/

//출처 : https://programmers.co.kr/learn/courses/30/lessons/64062
package algo_3월4주;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_프로그래머스_징검다리건너기 {
	public static void main(String[] args) {
		int s[] = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		int k = 3;
		System.out.println(solution(s, k));
	}

	public static int solution(int[] stones, int k) {
		int answer = 0;
		boolean visited[] = new boolean[stones.length];
		PriorityQueue<stone> pq = new PriorityQueue<>(new Comparator<stone>() {

			@Override
			public int compare(stone o1, stone o2) {
				if (o1.num > o2.num) {
					return 1;
				}
				return -1;
			}
		});
		for (int i = 0; i < stones.length; i++) {
			pq.add(new stone(stones[i], i));
		}
		while (!pq.isEmpty()) {
			int min = pq.peek().num;
			while (!pq.isEmpty() && min == pq.peek().num) {
				stone s = pq.poll();
				visited[s.pos] = true;
				int cnt = 1;
				int pos = s.pos + 1;
				while (pos < stones.length) {
					if (visited[pos]) {
						cnt++;
						pos++;
					} else {
						break;
					}
				}
				pos = s.pos - 1;
				while (pos >= 0) {
					if (visited[pos]) {
						cnt++;
						pos--;
					} else {
						break;
					}
				}
				if (cnt >= k) {
					answer = s.num;
					return answer;
				}
			}
		}
		return answer;
	}

	public static class stone {
		int num;
		int pos;

		public stone(int num, int pos) {
			this.num = num;
			this.pos = pos;
		}

	}
}
