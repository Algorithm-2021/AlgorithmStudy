/*
정확성 테스트

테스트 1 〉	통과 (2.13ms, 52MB)
테스트 2 〉	통과 (4.01ms, 53.4MB)
테스트 3 〉	통과 (3.33ms, 53.1MB)
테스트 4 〉	통과 (1.95ms, 53.4MB)
테스트 5 〉	통과 (0.09ms, 53.2MB)
테스트 6 〉	통과 (2.01ms, 52.1MB)
테스트 7 〉	통과 (4.66ms, 52.1MB)
테스트 8 〉	통과 (4.66ms, 52.6MB)
테스트 9 〉	통과 (6.20ms, 53.2MB)
테스트 10 〉	통과 (12.67ms, 58MB)
테스트 11 〉	통과 (2.53ms, 53.2MB)
테스트 12 〉	통과 (2.56ms, 52.7MB)
테스트 13 〉	통과 (2.61ms, 52.6MB)
테스트 14 〉	통과 (6.07ms, 52.9MB)
테스트 15 〉	통과 (2.57ms, 52.8MB)
테스트 16 〉	통과 (2.11ms, 52.2MB)
테스트 17 〉	통과 (11.88ms, 54.4MB)
테스트 18 〉	통과 (7.52ms, 53.6MB)
테스트 19 〉	통과 (7.56ms, 55.1MB)
테스트 20 〉	통과 (15.97ms, 57.6MB)
테스트 21 〉	통과 (12.79ms, 57.3MB)
테스트 22 〉	통과 (14.58ms, 56.7MB)
테스트 23 〉	통과 (12.46ms, 57.3MB)
테스트 24 〉	통과 (14.95ms, 56.1MB)
테스트 25 〉	통과 (12.17ms, 57.4MB)
테스트 26 〉	통과 (11.93ms, 57.9MB)
테스트 27 〉	통과 (12.15ms, 57MB)
테스트 28 〉	통과 (1.91ms, 51.9MB)

time : 0 Hour 40 Minute

풀이
문자열을 잘라서 값을 구하며 가장 작은 값을 출력한다.

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/60057
package algo_5월3주;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_P_L2_60057_문자열압축 {
	static int R, C, ans;
	static Queue<String> q;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		System.out.println(solution("abcabcabcabcdededededede"));
	}

	public static int solution(String s) {
		int size = s.length();
		q = new LinkedList<>();

		for (int i = 1; i <= size / 2; i++) {
			String tmp = "";
			for (int j = 0; j < size; j++) {
				if (j + i <= size) {
					String ss = s.substring(j, j + i);
					j = j + i - 1;
					q.add(ss);
				} else {
					String ss = s.substring(j, size);
					q.add(ss);
					j = size;
				}

			}
			int cnt = 0;
			String tmpS = "";
			while (!q.isEmpty()) {

				if (tmpS.equals("")) {
					tmpS = q.poll();
					cnt++;
				} else {
					if (tmpS.equals(q.peek())) {
						q.poll();
						cnt++;
					} else {
						if (cnt != 1) {
							tmp += String.valueOf(cnt);
							cnt = 1;
						}
						tmp += tmpS;
						tmpS = q.poll();
					}
				}
			}
			if (cnt > 1)
				tmp += String.valueOf(cnt);
			tmp += tmpS;

			min = Integer.min(min, tmp.length());
		}
		min = min == Integer.MAX_VALUE ? 1 : min;

		return min;
	}
}
