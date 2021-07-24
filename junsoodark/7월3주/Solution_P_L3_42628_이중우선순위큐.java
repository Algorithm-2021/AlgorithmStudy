/*
정확성 테스트

테스트 1 〉	통과 (0.56ms, 53MB)
테스트 2 〉	통과 (0.64ms, 51.8MB)
테스트 3 〉	통과 (0.68ms, 52.7MB)
테스트 4 〉	통과 (1.06ms, 51.6MB)
테스트 5 〉	통과 (0.46ms, 53.5MB)
테스트 6 〉	통과 (0.64ms, 52.3MB)

time : 0 Hour 30 Minute

풀이
큐를 최대힙과 최소힙 두개를 구현하여 풀이

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42628
package algo_7월3주;

import java.util.*;

public class Solution_P_L3_42628_이중우선순위큐 {
	public static void main(String[] args) {
		String[] operations = { "I 7", "I 5", "I -5", "D -1" };
		System.out.println(Arrays.toString(solution(operations)));
	}

	public static int[] solution(String[] operations) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		PriorityQueue<Integer> maxpq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < operations.length; i++) {
			String str = operations[i];
			if (str.charAt(0) == 'I') {
				StringTokenizer st = new StringTokenizer(str);
				st.nextToken();
				int in = Integer.parseInt(st.nextToken());
				pq.offer(in);
				maxpq.offer(in);
			} else {
				if (pq.size() != 0) {
					if (str.equals("D 1")) {
						int out = maxpq.poll();
						pq.remove(out);
					} else {
						int out = pq.poll();
						maxpq.remove(out);
					}
				}
			}
		}
		if (pq.size() == 0) {
			int[] answer = { 0, 0 };
			return answer;
		} else {
			int[] answer = { maxpq.poll(), pq.poll() };
			return answer;
		}
	}

}
