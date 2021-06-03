/*
테스트 1 〉	통과 (0.29ms, 53MB)
테스트 2 〉	통과 (0.35ms, 52.4MB)
테스트 3 〉	통과 (0.35ms, 52.9MB)
테스트 4 〉	통과 (0.30ms, 53MB)
테스트 5 〉	통과 (0.31ms, 51.8MB)
테스트 6 〉	통과 (0.29ms, 54MB)
테스트 7 〉	통과 (0.32ms, 52.3MB)
테스트 8 〉	통과 (0.41ms, 52.8MB)
테스트 9 〉	통과 (0.37ms, 52.7MB)
테스트 10 〉	통과 (0.33ms, 52.9MB)
테스트 11 〉	통과 (0.29ms, 51.9MB)
 * 30m
 * https://programmers.co.kr/learn/courses/30/lessons/42586?language=java
 */
package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution_P_L2_42586_기능개발_정세린 {

	static class Solution {
		public int[] solution(int[] progresses, int[] speeds) {
			int[] answer = {};
			int len = progresses.length;

			ArrayList<Integer> release = new ArrayList<Integer>();
			int before = (int) Math.ceil((100.0 - progresses[0]) / (speeds[0] * 1.0)); // 처음 완료되는 작업 시간
			int cnt = 1;

			for (int i = 1; i < len; i++) {
				int d = (int) Math.ceil((100.0 - progresses[i]) / (speeds[i] * 1.0)); // 해당 일을 마치는데 걸리는 시간

				if (d <= before) cnt++; // 한번에 완료할 수 있는 작업의 수

				else { // 같이 완료할 수 없는 작업
					before = d;
					release.add(cnt);
					cnt = 1;
				}
			}
			if (cnt > 0) release.add(cnt);

			answer = new int[release.size()];
			for (int i = 0; i < answer.length; i++)
				answer[i] = release.get(i);

			return answer;
		}

	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
		int[] answer = solution.solution(progresses, speeds);
		System.out.println(Arrays.toString(answer));
	}

}
