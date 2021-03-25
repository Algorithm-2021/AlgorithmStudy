/*

테스트 1 〉	통과 (0.28ms, 53.5MB)
테스트 2 〉	통과 (0.39ms, 52.1MB)
테스트 3 〉	통과 (0.44ms, 53.1MB)
테스트 4 〉	통과 (0.48ms, 52.8MB)
테스트 5 〉	통과 (0.36ms, 51.7MB)
테스트 6 〉	통과 (5.62ms, 52.7MB)
테스트 7 〉	통과 (3.69ms, 52.5MB)
테스트 8 〉	통과 (1.06ms, 52.3MB)
테스트 9 〉	통과 (0.99ms, 52.4MB)
테스트 10 〉	통과 (2.38ms, 52.9MB)
테스트 11 〉	통과 (2.32ms, 52.4MB)
테스트 12 〉	통과 (3.21ms, 52.8MB)
테스트 13 〉	통과 (3.59ms, 53.4MB)
테스트 14 〉	통과 (0.49ms, 52.5MB)
테스트 15 〉	통과 (2.93ms, 53.3MB)
테스트 16 〉	통과 (0.42ms, 52.7MB)

효율성 테스트

테스트 1 〉	통과 (165.48ms, 69.7MB)
테스트 2 〉	통과 (256.87ms, 87.1MB)
테스트 3 〉	통과 (1296.89ms, 123MB)
테스트 4 〉	통과 (146.21ms, 66.6MB)
테스트 5 〉	통과 (1411.18ms, 123MB)

time : 0 Hour 40 Minute

풀이
최소 힙(Heap)을 사용한 우선순위큐를 사용하여 계산


*/

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42626

package algo_3월4주;

import java.util.PriorityQueue;

public class Solution_프로그래머스_더맵게 {

	public static int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < scoville.length; i++) {
			pq.add(scoville[i]);
		}
		while (true) {
			if (pq.size() == 1) {
				int a = pq.poll();
				if (a >= K) {
					break;
				}
				answer = -1;
				break;
			}
			int a = pq.poll();
			int b = pq.poll();
			if (a >= K) {
				break;
			}
			answer++;
			int tmp = a + (b * 2);
			pq.add(tmp);
		}
		return answer;
	}

}
