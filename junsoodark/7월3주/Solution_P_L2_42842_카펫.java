/*
정확성 테스트

테스트 1 〉	통과 (0.01ms, 53.2MB)
테스트 2 〉	통과 (0.02ms, 53.1MB)
테스트 3 〉	통과 (0.03ms, 52.9MB)
테스트 4 〉	통과 (0.03ms, 52.2MB)
테스트 5 〉	통과 (0.03ms, 52.5MB)
테스트 6 〉	통과 (0.02ms, 52.2MB)
테스트 7 〉	통과 (0.03ms, 54.2MB)
테스트 8 〉	통과 (0.03ms, 52.7MB)
테스트 9 〉	통과 (0.04ms, 53MB)
테스트 10 〉	통과 (0.05ms, 53MB)
테스트 11 〉	통과 (0.01ms, 52.2MB)
테스트 12 〉	통과 (0.03ms, 52.3MB)
테스트 13 〉	통과 (0.02ms, 51.9MB)

time : 0 Hour 10 Minute

풀이
brown과 yellow 를 합친숫자를 결과 값으로 하는 두 숫자의 곱중
두 숫자에서 2씩 빼서 곱한숫자가 yellow 와 같은 숫자를 출력

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42842
package algo_7월3주;

import java.util.Arrays;

public class Solution_P_L2_42842_카펫 {
	public static void main(String[] args) {
		int brown = 24;
		int yellow = 24;
		System.out.println(Arrays.toString(solution(brown, yellow)));
	}

	public static int[] solution(int brown, int yellow) {
		int sum = brown + yellow;
		int r = 3;
		int c = 1;
		while (true) {
			if (sum % r != 0) {
				r++;
				continue;
			} else {
				c = sum / r;
				if ((r - 2) * (c - 2) == yellow) {
					break;
				}
				r++;
			}
		}
		if (r < c) {
			int tmp = r;
			r = c;
			c = tmp;
		}
		int[] answer = { r, c };
		return answer;
	}
}
