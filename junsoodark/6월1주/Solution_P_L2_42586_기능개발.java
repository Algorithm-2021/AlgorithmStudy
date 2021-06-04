/*
정확성 테스트

테스트 1 〉	통과 (0.18ms, 52.1MB)
테스트 2 〉	통과 (0.24ms, 52.2MB)
테스트 3 〉	통과 (0.28ms, 52.4MB)
테스트 4 〉	통과 (0.19ms, 52.1MB)
테스트 5 〉	통과 (0.14ms, 52.4MB)
테스트 6 〉	통과 (0.23ms, 52.3MB)
테스트 7 〉	통과 (0.27ms, 51.5MB)
테스트 8 〉	통과 (0.13ms, 52.2MB)
테스트 9 〉	통과 (0.24ms, 52.4MB)
테스트 10 〉	통과 (0.26ms, 52.4MB)
테스트 11 〉	통과 (0.14ms, 53.2MB)

time : 0 Hour 30 Minute

풀이
각 progresses 에 speeds를 더해 100을 넘을떄마다 큐에 추가하여 계산

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42586
package algo_6월1주;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_P_L2_42586_기능개발 {
	public static void main(String[] args) {
		int progresses[] = { 93, 30, 55 };
		int speeds[] = { 1, 30, 5 };
		System.out.println(Arrays.toString(solution(progresses, speeds)));
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> q = new LinkedList<Integer>();
		int startPoint = 0;
		int len = progresses.length;
		while (startPoint < len) {
			for (int i = startPoint; i < len; i++) {
				progresses[i] = progresses[i] + speeds[i];
			}
			if (progresses[startPoint] < 100) {
				continue;
			}
			int cnt = 0;
			for (int i = startPoint; i < len; i++) {
				if (progresses[i] >= 100) {
					cnt++;
					startPoint++;
					continue;
				}
				break;
			}
			if (cnt != 0) {
				q.offer(cnt);
			}
		}
		int[] answer = new int[q.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = q.poll();
		}
		return answer;
	}
}
