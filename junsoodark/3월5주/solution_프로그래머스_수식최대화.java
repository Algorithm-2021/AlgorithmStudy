/*

테스트 1 〉	통과 (0.36ms, 51.9MB)
테스트 2 〉	통과 (0.36ms, 52.1MB)
테스트 3 〉	통과 (0.44ms, 51.6MB)
테스트 4 〉	통과 (0.52ms, 51.9MB)
테스트 5 〉	통과 (0.68ms, 52.5MB)
테스트 6 〉	통과 (0.64ms, 53.1MB)
테스트 7 〉	통과 (0.71ms, 52.4MB)
테스트 8 〉	통과 (0.78ms, 52.7MB)
테스트 9 〉	통과 (0.85ms, 53.3MB)
테스트 10 〉	통과 (0.98ms, 52.2MB)
테스트 11 〉	통과 (1.26ms, 54.6MB)
테스트 12 〉	통과 (1.11ms, 52.6MB)
테스트 13 〉	통과 (1.23ms, 52.6MB)
테스트 14 〉	통과 (3.25ms, 53.3MB)
테스트 15 〉	통과 (1.33ms, 53.2MB)
테스트 16 〉	통과 (0.40ms, 52.3MB)
테스트 17 〉	통과 (0.46ms, 52.5MB)
테스트 18 〉	통과 (0.49ms, 51.8MB)
테스트 19 〉	통과 (0.40ms, 52MB)
테스트 20 〉	통과 (0.50ms, 54.5MB)
테스트 21 〉	통과 (1.34ms, 52.3MB)
테스트 22 〉	통과 (1.32ms, 53.1MB)
테스트 23 〉	통과 (0.37ms, 52.6MB)
테스트 24 〉	통과 (1.40ms, 52.6MB)
테스트 25 〉	통과 (1.38ms, 52.9MB)
테스트 26 〉	통과 (0.36ms, 52.4MB)
테스트 27 〉	통과 (1.38ms, 53MB)
테스트 28 〉	통과 (1.46ms, 52.6MB)
테스트 29 〉	통과 (1.36ms, 52.6MB)
테스트 30 〉	통과 (1.40ms, 52.5MB)


time : 1 Hour 30 Minute

풀이
큐를 사용하여 각 연산문자마다 일괄적으로 처리한후 다시 큐에 넣어 다음 연산 문자 일괄 처리

*/

//출처 : https://programmers.co.kr/learn/courses/30/lessons/67257
package algo_3월5주;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class solution_프로그래머스_수식최대화 {
	static Queue<String> q = new LinkedList<>();
	static long max = 0;

	public static void main(String[] args) {
		String expression = "100-200*300-500+20";
		System.out.println(solution(expression));
	}

	public static long solution(String expression) {
		long answer = 0;
		for (int i = 0; i < 3; i++) {
			inQueue(expression);
			ArrayList<String> arrtmp = new ArrayList<>();
			// 곱셈 계산
			if (i == 0) {
				while (!q.isEmpty()) {
					String tmp = q.poll();
					String ex = "a";
					if (!q.isEmpty()) {
						ex = q.poll();
					}
					while (!q.isEmpty() && ex.equals("*")) {
						tmp = Long.toString(Long.parseLong(tmp) * Long.parseLong(q.poll()));
						if (!q.isEmpty()) {
							ex = q.poll();
							continue;
						}
						ex = "a";
						break;
					}
					arrtmp.add(tmp);
					if (!ex.equals("a")) {
						arrtmp.add(ex);
					}
				}
			}
			// 덧셈 계산
			if (i == 1) {
				while (!q.isEmpty()) {
					String tmp = q.poll();
					String ex = "a";
					if (!q.isEmpty()) {
						ex = q.poll();
					}
					while (!q.isEmpty() && ex.equals("+")) {
						tmp = Long.toString(Long.parseLong(tmp) + Long.parseLong(q.poll()));
						if (!q.isEmpty()) {
							ex = q.poll();
							continue;
						}
						ex = "a";
						break;
					}
					arrtmp.add(tmp);
					if (!ex.equals("a")) {
						arrtmp.add(ex);
					}
				}
			}
			// 뺄셈 계산
			if (i == 2) {
				while (!q.isEmpty()) {
					String tmp = q.poll();
					String ex = "a";
					if (!q.isEmpty()) {
						ex = q.poll();
					}
					while (!q.isEmpty() && ex.equals("-")) {
						tmp = Long.toString(Long.parseLong(tmp) - Long.parseLong(q.poll()));
						if (!q.isEmpty()) {
							ex = q.poll();
							continue;
						}
						ex = "a";
						break;
					}
					arrtmp.add(tmp);
					if (!ex.equals("a")) {
						arrtmp.add(ex);
					}
				}
			}
			for (int j = 0; j < 3; j++) {
				if (i == j) {
					continue;
				}
				for (String string : arrtmp) {
					q.add(string);
				}
				ArrayList<String> arrtmp2 = new ArrayList<>();
				// 곱셈 계산
				if (j == 0) {
					while (!q.isEmpty()) {
						String tmp = q.poll();
						String ex = "a";
						if (!q.isEmpty()) {
							ex = q.poll();
						}
						while (!q.isEmpty() && ex.equals("*")) {
							tmp = Long.toString(Long.parseLong(tmp) * Long.parseLong(q.poll()));
							if (!q.isEmpty()) {
								ex = q.poll();
								continue;
							}
							ex = "a";
							break;
						}
						arrtmp2.add(tmp);
						if (!ex.equals("a")) {
							arrtmp2.add(ex);
						}
					}
					for (String string : arrtmp2) {
						q.add(string);
					}
				}
				// 덧셈 계산
				if (j == 1) {
					while (!q.isEmpty()) {
						String tmp = q.poll();
						String ex = "a";
						if (!q.isEmpty()) {
							ex = q.poll();
						}
						while (!q.isEmpty() && ex.equals("+")) {
							tmp = Long.toString(Long.parseLong(tmp) + Long.parseLong(q.poll()));
							if (!q.isEmpty()) {
								ex = q.poll();
								continue;
							}
							ex = "a";
							break;
						}
						arrtmp2.add(tmp);
						if (!ex.equals("a")) {
							arrtmp2.add(ex);
						}
					}
					for (String string : arrtmp2) {
						q.add(string);
					}
				}
				// 뺄셈 계산
				if (j == 2) {
					while (!q.isEmpty()) {
						String tmp = q.poll();
						String ex = "a";
						if (!q.isEmpty()) {
							ex = q.poll();
						}
						while (!q.isEmpty() && ex.equals("-")) {
							tmp = Long.toString(Long.parseLong(tmp) - Long.parseLong(q.poll()));
							if (!q.isEmpty()) {
								ex = q.poll();
								continue;
							}
							ex = "a";
							break;
						}
						arrtmp2.add(tmp);
						if (!ex.equals("a")) {
							arrtmp2.add(ex);
						}
					}
					for (String string : arrtmp2) {
						q.add(string);
					}
				}
				for (int k = 0; k < 3; k++) {
					if (i == k || j == k) {
						continue;
					}
					// 곱셈 계산
					if (k == 0) {
						answer = Long.parseLong(q.poll());
						while (!q.isEmpty()) {
							q.poll();
							answer *= Long.parseLong(q.poll());
						}
						answer = Math.abs(answer);
						if (max < answer) {
							max = answer;
						}
					}
					// 덧셈 계산
					if (k == 1) {
						answer = Long.parseLong(q.poll());
						while (!q.isEmpty()) {
							q.poll();
							answer += Long.parseLong(q.poll());
						}
						answer = Math.abs(answer);
						if (max < answer) {
							max = answer;
						}
					}
					// 뺄셈 계산
					if (k == 2) {
						answer = Long.parseLong(q.poll());
						while (!q.isEmpty()) {
							q.poll();
							answer -= Long.parseLong(q.poll());
						}
						answer = Math.abs(answer);
						if (max < answer) {
							max = answer;
						}
					}
				}
			}
		}
		return max;
	}

	public static void inQueue(String expression) {
		int len = expression.length();
		int in = 0;
		for (int i = 0; i < len; i++) {
			char c = expression.charAt(i);
			if (c == '-' || c == '+' || c == '*') {
				q.add(Integer.toString(in));
				in = 0;
				q.add(Character.toString(c));
			} else {
				in = in * 10 + (c - '0');
			}
		}
		q.add(Integer.toString(in));
	}
}
