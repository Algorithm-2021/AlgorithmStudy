/*
정확성 테스트

테스트 1 〉	통과 (0.01ms, 52MB)
테스트 2 〉	통과 (0.02ms, 53.3MB)
테스트 3 〉	통과 (0.02ms, 51.8MB)
테스트 4 〉	통과 (0.05ms, 52.4MB)
테스트 5 〉	통과 (0.02ms, 52.9MB)
테스트 6 〉	통과 (0.02ms, 52.8MB)
테스트 7 〉	통과 (0.02ms, 51.7MB)
테스트 8 〉	통과 (0.03ms, 53.9MB)
테스트 9 〉	통과 (0.02ms, 53MB)
테스트 10 〉	통과 (0.01ms, 52.2MB)
테스트 11 〉	통과 (0.02ms, 52.1MB)
테스트 12 〉	통과 (0.02ms, 52.9MB)
테스트 13 〉	통과 (0.02ms, 52.3MB)
테스트 14 〉	통과 (0.02ms, 52.9MB)
테스트 15 〉	통과 (0.02ms, 53MB)

time : 0 Hour 20 Minute

풀이
0의 갯수와 일치하는 숫자의 갯수를 센후 순위를 출력한다.

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/77484
package algo_5월2주;

import java.util.Arrays;

public class Solution_P_L2_77484_로또최고순위와최저순위 {
	public static void main(String[] args) {
		int[] lottos = { 44, 1, 0, 0, 31, 25 };
		int[] win_nums = { 31, 10, 45, 1, 6, 19 };
		int[] result = solution(lottos, win_nums);
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];
		int cnt = 0;
		int cntZero = 0;
		for (int i = 0; i < 6; i++) {
			if (lottos[i] == 0) {
				cntZero++;
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				if (lottos[i] == win_nums[j]) {
					cnt++;
				}
			}
		}
		answer[0] = 7 - cnt - cntZero;
		if (answer[0] == 7) {
			answer[0] = 6;
		}
		answer[1] = answer[0] + cntZero;
		if (answer[1] == 7) {
			answer[1] = 6;
		}
		return answer;
	}

}
