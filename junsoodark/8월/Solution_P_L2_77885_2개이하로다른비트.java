/*
정확성 테스트

테스트 1 〉	통과 (0.10ms, 58.8MB)
테스트 2 〉	통과 (3.87ms, 84.7MB)
테스트 3 〉	통과 (0.02ms, 69MB)
테스트 4 〉	통과 (0.07ms, 62.7MB)
테스트 5 〉	통과 (0.08ms, 72.3MB)
테스트 6 〉	통과 (0.06ms, 70.6MB)
테스트 7 〉	통과 (3.89ms, 139MB)
테스트 8 〉	통과 (3.91ms, 129MB)
테스트 9 〉	통과 (3.54ms, 120MB)
테스트 10 〉	통과 (7.23ms, 109MB)
테스트 11 〉	통과 (6.19ms, 124MB)

time : 0 Hour 40 Minute

풀이
비트의 가장 오른쪽부터 0을 찾아 1로 바꾸고 바꾼자리의 오른쪽을 0으로 바꾼다.
오른쪽이 없을경우 0으로 바꾸는 과정을 생략

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/77885
package algo_8월;

import java.util.Arrays;

public class Solution_P_L2_77885_2개이하로다른비트 {
	public static void main(String[] args) {
		long numbers[] = { 1001, 337, 0, 1, 333, 673, 343, 221, 898, 997, 121, 1015, 665, 779, 891, 421, 222, 256, 512,
				128, 100 };
		System.out.println(Arrays.toString(solution(numbers)));
	}

	public static long[] solution(long[] numbers) {
		long answer[] = new long[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			long tmp = 1;
			while (true) {
				// 가장 먼저 나오는 0을 찾음
				if ((numbers[i] & tmp) > 0) {
					tmp = tmp << 1;
				} else {
					// 0을 1로 바꾸고 전의 위치를 0으로 바꿈 (없는 자리면 패스)
					answer[i] = (numbers[i] | tmp) ^ (tmp >> 1);
					break;
				}
			}
		}
		return answer;
	}

}
