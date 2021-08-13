/*
정확성 테스트

테스트 1 〉	통과 (0.02ms, 60.3MB)
테스트 2 〉	통과 (0.02ms, 59.7MB)
테스트 3 〉	통과 (0.02ms, 60.7MB)
테스트 4 〉	통과 (0.02ms, 73.4MB)
테스트 5 〉	통과 (0.02ms, 73.2MB)
테스트 6 〉	통과 (0.02ms, 75.6MB)
테스트 7 〉	통과 (0.02ms, 70.3MB)
테스트 8 〉	통과 (0.02ms, 59.9MB)
테스트 9 〉	통과 (0.01ms, 74.1MB)
테스트 10 〉	통과 (0.02ms, 70MB)
테스트 11 〉	통과 (0.01ms, 61.9MB)
테스트 12 〉	통과 (0.03ms, 72.7MB)
테스트 13 〉	통과 (0.03ms, 72.1MB)
테스트 14 〉	통과 (0.02ms, 59.9MB)
테스트 15 〉	통과 (0.02ms, 71.5MB)
테스트 16 〉	통과 (0.03ms, 59.3MB)
테스트 17 〉	통과 (0.02ms, 58.7MB)
테스트 18 〉	통과 (0.02ms, 59MB)
테스트 19 〉	통과 (0.03ms, 69.7MB)
테스트 20 〉	통과 (0.01ms, 59.6MB)
테스트 21 〉	통과 (0.03ms, 71.3MB)
테스트 22 〉	통과 (0.02ms, 60.4MB)
테스트 23 〉	통과 (0.03ms, 71.8MB)
테스트 24 〉	통과 (0.03ms, 73.7MB)
테스트 25 〉	통과 (0.02ms, 60.3MB)
테스트 26 〉	통과 (0.03ms, 73.6MB)
테스트 27 〉	통과 (0.03ms, 71.4MB)
테스트 28 〉	통과 (0.02ms, 60MB)
테스트 29 〉	통과 (0.02ms, 75.9MB)
테스트 30 〉	통과 (0.02ms, 60.1MB)
테스트 31 〉	통과 (0.02ms, 60.9MB)
테스트 32 〉	통과 (0.02ms, 71MB)
테스트 33 〉	통과 (0.03ms, 71.8MB)
테스트 34 〉	통과 (0.02ms, 60MB)

time : 0 Hour 30 Minute

풀이
a와 b를 작은수를 a 에 오게 한후
각 숫자를 홀수이면 절반으로 나눈후 +1 짝수이면 절반으로 나누며
a가 홀수 b 가 a+1이
될때까지 반복하여 반복 횟수를 구한다.

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12985
package algo_8월;

public class Solution_P_L2_12985_예상대진표 {
	public static void main(String[] args) {
		int n = 8;
		int a = 4;
		int b = 7;
		System.out.println(solution(n, a, b));
	}

	public static int solution(int n, int a, int b) {
		if (a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		int result = 1;
		while (!((a % 2) == 1 && (a + 1) == b)) {
			if ((a % 2) == 1) {
				a = (a / 2) + 1;
				if ((b % 2) == 1) {
					b = (b / 2) + 1;
				} else {
					b = b / 2;
				}
			} else {
				a = a / 2;
				if ((b % 2) == 1) {
					b = (b / 2) + 1;
				} else {
					b = b / 2;
				}
			}
			result++;
		}

		return result;
	}
}

/*
class Solution
{
    public int solution(int n, int a, int b)
    {
        return Integer.toBinaryString((a-1)^(b-1)).length();
    }
}

*/
