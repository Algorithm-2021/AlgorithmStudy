/*
정확성 테스트

테스트 1 〉	통과 (0.37ms, 51.7MB)
테스트 2 〉	통과 (5.59ms, 52.4MB)
테스트 3 〉	통과 (0.12ms, 51.8MB)
테스트 4 〉	통과 (6.93ms, 53.1MB)
테스트 5 〉	통과 (16.20ms, 56.3MB)
테스트 6 〉	통과 (0.11ms, 52.3MB)
테스트 7 〉	통과 (0.47ms, 52MB)
테스트 8 〉	통과 (19.65ms, 54.5MB)
테스트 9 〉	통과 (0.17ms, 52.1MB)
테스트 10 〉	통과 (10.33ms, 52.7MB)
테스트 11 〉	통과 (1.84ms, 52.7MB)
테스트 12 〉	통과 (1.84ms, 54MB)

time : 0 Hour 30 Minute

풀이
조합으로 숫자를 만들어 해시에 넣고 소수판단하여 갯수 세기

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42839
package algo_5월4주;

import java.util.HashSet;

public class Solution_P_L1_42839_소수찾기 {
	public static void main(String[] args) {
		String numbers = "011";
		System.out.println(solution(numbers));
	}

	static HashSet<Integer> set = new HashSet<>();
	static boolean visited[];
	static char[] number;
	static StringBuilder sb;

	public static int solution(String numbers) {
		number = numbers.toCharArray();
		visited = new boolean[number.length];
		for (int i = 1; i <= number.length; i++) {
			sb = new StringBuilder();
			permutation(number.length, i);
		}
		int answer = 0;
		for (int i : set) {
			boolean find = false;
			if (i <= 1) {
				continue;
			} else {
				for (int j = 2; j <= (int) Math.sqrt(i); j++) {
					if (i % j == 0) {
						find = true;
						break;
					}
				}
				if (!find) {
					answer++;
				}
			}
		}
		return answer;
	}

	public static void permutation(int n, int r) {
		if (r == 0) {
			int makeNum = Integer.parseInt(sb.toString());
			set.add(makeNum);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i])
				continue;
			char num = number[i];
			visited[i] = true;
			sb.append(num);
			permutation(n, r - 1);
			visited[i] = false;
			sb.deleteCharAt(sb.length() - 1);
		}
	}

}
