/*
테스트 10 〉	실패 (시간 초과)
테스트 11 〉	실패 (시간 초과)
 * https://programmers.co.kr/learn/courses/30/lessons/77885
 */
package PROGRAMMERS;

import java.util.Arrays;

public class ING_Solution_P_L2_77885_2개이하로다른비트_정세린 {
	static class Solution {
		public long[] solution(long[] numbers) {
			int len = numbers.length;
			long[] answer = new long[len];

			for (int i = 0; i < len; i++) {
				long num = numbers[i];
				long next = num + 1;
				long cnt = 0;
				while (true) {
					cnt = cntOne(num ^ next);

					if (cnt <= 2) {
						answer[i] = next;
						break;
					}
					next++;
				}
			}
			return answer;
		}

		long cntOne(long n) {
			long cnt = 0;
			while (n > 0) {
				if (n % 2 == 1) cnt++;
				if (cnt > 2) return cnt;
				n = n >> 1;
			}
			return cnt;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		long[] numbers = {2, 7};
		long[] answer = solution.solution(numbers);
		System.out.println(Arrays.toString(answer));
	}

}
