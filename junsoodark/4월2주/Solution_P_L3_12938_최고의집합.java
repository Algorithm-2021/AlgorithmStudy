/*
정확성 테스트
테스트 1 〉	통과 (0.09ms, 52.4MB)
테스트 2 〉	통과 (0.15ms, 53.3MB)
테스트 3 〉	통과 (0.13ms, 54.7MB)
테스트 4 〉	통과 (0.12ms, 53.4MB)
테스트 5 〉	통과 (0.05ms, 52.6MB)
테스트 6 〉	통과 (0.04ms, 52.6MB)
테스트 7 〉	통과 (0.03ms, 53.1MB)
테스트 8 〉	통과 (0.04ms, 54MB)
테스트 9 〉	통과 (0.09ms, 53.2MB)
테스트 10 〉	통과 (0.14ms, 52.9MB)
테스트 11 〉	통과 (0.12ms, 52.9MB)
테스트 12 〉	통과 (0.07ms, 53.5MB)
테스트 13 〉	통과 (0.16ms, 54MB)
테스트 14 〉	통과 (0.02ms, 53.6MB)

효율성 테스트
테스트 1 〉	통과 (0.17ms, 53.7MB)
테스트 2 〉	통과 (0.16ms, 53.9MB)
테스트 3 〉	통과 (0.15ms, 53.8MB)
테스트 4 〉	통과 (0.20ms, 53.6MB)
테스트 5 〉	통과 (0.27ms, 53.6MB)
테스트 6 〉	통과 (0.02ms, 52MB)

time : 0 Hour 10 Minute

풀이
숫자의 합인 s를 최대한 균등하게 배분해주면 곱이 가장 크게 된다.
s를 n으로 나눈 int 값에서 나머지 숫자의 갯수만큼 뒤에서 나눈 숫자 +1만큼 채운후 나머지는 나눈 숫자만큼을 채운다.

*/

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12938
package algo_4월2주;

public class Solution_P_L3_12938_최고의집합 {
	public int[] solution(int n, int s) {
		int[] answer = {};
		int tmp = s / n;
		if (tmp < 1) {
			answer = new int[1];
			answer[0] = -1;
			return answer;
		}
		answer = new int[n];

		int cnt = s - (tmp * n);
		for (int i = 0; i < cnt; i++) {
			answer[n - 1 - i] = tmp + 1;
		}
		for (int i = 0; i < n - cnt; i++) {
			answer[i] = tmp;
		}
		return answer;
	}
}
