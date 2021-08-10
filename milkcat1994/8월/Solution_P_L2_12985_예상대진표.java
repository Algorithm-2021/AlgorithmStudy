/*
 * -예상 대진표-
 * 1. 트리구조에서 부모트리 번호를 찾는 방식 이용
 * 
 * 테스트 29 〉	통과 (0.03ms, 70.3MB)
 * 테스트 30 〉	통과 (0.02ms, 57.1MB)
 * 테스트 31 〉	통과 (0.04ms, 59.6MB)
 * 테스트 32 〉	통과 (0.04ms, 71.6MB)
 * 테스트 33 〉	통과 (0.03ms, 69.4MB)
 * 테스트 34 〉	통과 (0.03ms, 59.5MB)
 * 
 * 풀이 시간 : 10m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/12985
public class Solution_P_L2_12985_예상대진표 {
	public int solution(int n, int a, int b) {
		int answer = 0;
		do {
			answer++;
			a = (a+1)/2;
			b = (b+1)/2;
		} while(a != b);
		
		return answer;
	}

	public static void main(String[] args) {
		Solution_P_L2_12985_예상대진표 sol = new Solution_P_L2_12985_예상대진표();
		int N = 8;
		int A = 4;
		int B = 7;
		int answer = sol.solution(N, A, B);
		System.out.println(answer);
	}
}