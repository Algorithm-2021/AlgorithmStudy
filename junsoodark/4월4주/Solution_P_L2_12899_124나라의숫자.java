/*
 * 124 나라의 숫자
 * 
 * time : 0 Hour 30 Minute
 * 
 * 풀이
 * 3진법으로 계산하되 3으로 나누어 떨어지는 부분은 앞에서 1을 땡겨와 4로 표현한다.
 * 
 * 
 * 
 * 
정확성 테스트

테스트 1 〉	통과 (0.04ms, 52.3MB)
테스트 2 〉	통과 (0.04ms, 54.1MB)
테스트 3 〉	통과 (0.05ms, 52.4MB)
테스트 4 〉	통과 (0.03ms, 52.1MB)
테스트 5 〉	통과 (0.05ms, 52.7MB)
테스트 6 〉	통과 (0.05ms, 53.2MB)
테스트 7 〉	통과 (0.04ms, 52.4MB)
테스트 8 〉	통과 (0.05ms, 52.7MB)
테스트 9 〉	통과 (0.05ms, 52.2MB)
테스트 10 〉	통과 (0.05ms, 53.2MB)
테스트 11 〉	통과 (0.04ms, 52.2MB)
테스트 12 〉	통과 (0.04ms, 52.4MB)
테스트 13 〉	통과 (0.04ms, 52.6MB)
테스트 14 〉	통과 (0.04ms, 52.7MB)

효율성 테스트

테스트 1 〉	통과 (0.04ms, 52.6MB)
테스트 2 〉	통과 (0.05ms, 52.9MB)
테스트 3 〉	통과 (0.04ms, 53MB)
테스트 4 〉	통과 (0.06ms, 52.3MB)
테스트 5 〉	통과 (0.05ms, 53.4MB)
테스트 6 〉	통과 (0.05ms, 52.9MB)

*/

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12899
package algo_4월4주;

public class Solution_P_L2_12899_124나라의숫자 {
	public static String solution(int n) {
		String answer = "";
		StringBuilder sb = new StringBuilder();
		while (n >= 3) {
			int mod = n % 3;
			if (mod == 0) {
				sb.append(4);
				n = n / 3;
				n = n - 1;
			} else {
				sb.append(mod);
				n = n / 3;
			}
		}
		if (n != 0) {
			sb.append(n);
		}
		answer = sb.reverse().toString();
		return answer;
	}

	public static void main(String[] args) {
		System.out.println(solution(10));
	}
}
