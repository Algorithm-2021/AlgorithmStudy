import java.util.Arrays;

/*
 * -최고의 집합-
 * 1. 중복 허용하는 집합이므로 s/n이 n만큼 들어가며, s%n만큼 1씩 더해주면 된다.
 * 
 * 정확성  테스트
 * 테스트 1 〉	통과 (0.08ms, 53.1MB)
 * 테스트 2 〉	통과 (0.28ms, 53.2MB)
 * 테스트 3 〉	통과 (0.12ms, 53.5MB)
 * 테스트 4 〉	통과 (0.07ms, 53.5MB)
 * 테스트 5 〉	통과 (0.04ms, 52.7MB)
 * 테스트 6 〉	통과 (0.05ms, 52.3MB)
 * 테스트 7 〉	통과 (0.04ms, 52.4MB)
 * 테스트 8 〉	통과 (0.04ms, 52.7MB)
 * 테스트 9 〉	통과 (0.14ms, 53.3MB)
 * 테스트 10 〉	통과 (0.14ms, 53MB)
 * 테스트 11 〉	통과 (0.18ms, 54.1MB)
 * 테스트 12 〉	통과 (0.15ms, 52.1MB)
 * 테스트 13 〉	통과 (0.22ms, 53.5MB)
 * 테스트 14 〉	통과 (0.01ms, 52.9MB)
 * 
 * 효율성  테스트
 * 테스트 1 〉	통과 (0.16ms, 53.4MB)
 * 테스트 2 〉	통과 (0.15ms, 53MB)
 * 테스트 3 〉	통과 (0.26ms, 52.9MB)
 * 테스트 4 〉	통과 (0.21ms, 53.7MB)
 * 테스트 5 〉	통과 (0.35ms, 54.8MB)
 * 테스트 6 〉	통과 (0.01ms, 52.3MB)
 * 
 * 풀이 시간 : 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12938
public class Solution_P_L3_12938_최고의집합 {
	public int[] solution(int n, int s) {
		int[] answer = {};
		if(s < n)
			return new int[] {-1};
		
		answer = new int[n];
		Arrays.fill(answer, s/n);
		if(s%n != 0) {
			for(int idx=n-s%n; idx<n; ++idx)
				answer[idx]++;
		}
		return answer;
	}

	public static void main(String[] args) {
		Solution_P_L3_12938_최고의집합 sol = new Solution_P_L3_12938_최고의집합();
		int n=2;
		int s=9;
		int[] answer = sol.solution(n, s);
		System.out.println(Arrays.toString(answer));
	}
}