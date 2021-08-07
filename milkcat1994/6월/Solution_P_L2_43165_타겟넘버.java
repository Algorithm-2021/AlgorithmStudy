/*
 * -타겟넘버-
 * dfs를 이용해  +, - 를 각각 진행한다.
 * 끝까지 진행하였을때 target 숫자와 동일하다면 결과값+1
 * 
 * 테스트 1 〉	통과 (6.01ms, 52MB)
 * 테스트 2 〉	통과 (15.58ms, 52.6MB)
 * 테스트 3 〉	통과 (0.21ms, 52.1MB)
 * 테스트 4 〉	통과 (4.59ms, 52.6MB)
 * 테스트 5 〉	통과 (1.62ms, 52.1MB)
 * 테스트 6 〉	통과 (0.28ms, 52.1MB)
 * 테스트 7 〉	통과 (0.20ms, 52MB)
 * 테스트 8 〉	통과 (0.61ms, 53.5MB)
 * 
 * 풀이 시간 : 9M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/43165
public class Solution_P_L2_43165_타겟넘버 {
	public int solution(int[] numbers, int target) {
		return dfs(numbers, 0, 0, target);
	}
	
	public int dfs(int[] numbers, int idx, int sum, int target) {
		if(idx >= numbers.length) {
			if(sum == target) {
				return 1;
			}
			return 0;
		}
		
		return dfs(numbers, idx+1, sum-numbers[idx], target) + dfs(numbers, idx+1, sum+numbers[idx], target);
	}


	public static void main(String[] args) {
		Solution_P_L2_43165_타겟넘버 sol = new Solution_P_L2_43165_타겟넘버();
		int[] numbers = {1,1,1,1,1};
		int target = 3;
		int answer = sol.solution(numbers, target);
		System.out.println(answer);
	}
}