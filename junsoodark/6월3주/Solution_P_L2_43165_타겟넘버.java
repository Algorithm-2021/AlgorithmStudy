/*
정확성 테스트

테스트 1 〉	통과 (7.34ms, 52.6MB)
테스트 2 〉	통과 (11.66ms, 52.7MB)
테스트 3 〉	통과 (0.24ms, 52MB)
테스트 4 〉	통과 (0.68ms, 52.7MB)
테스트 5 〉	통과 (3.69ms, 52.7MB)
테스트 6 〉	통과 (0.38ms, 53MB)
테스트 7 〉	통과 (0.28ms, 53.1MB)
테스트 8 〉	통과 (0.65ms, 52.5MB)

time : 0 Hour 20 Minute

풀이
재귀를 이용하여 dfs 탐색

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/43165
package algo_6월3주;

public class Solution_P_L2_43165_타겟넘버 {

	public static void main(String[] args) {
		int numbers[] = { 1, 1, 1, 1, 1 };
		int target = 3;
		System.out.println(solution(numbers, target));
	}

	static int answer = 0;

	public static int solution(int[] numbers, int target) {
		dfs(numbers, 0, 0, target);
		return answer;
	}

	public static void dfs(int numbers[], int n, int sum, int target) {
		if (n == numbers.length) {
			if (sum == target) {
				answer++;
			}
			return;
		}
		sum = sum + numbers[n];
		dfs(numbers, n + 1, sum, target);
		sum = sum - (numbers[n] * 2);
		dfs(numbers, n + 1, sum, target);
	}
}
