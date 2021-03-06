/*
테스트 8 〉	통과 (11.69ms, 54.2MB)
테스트 9 〉	통과 (0.15ms, 52.3MB)
테스트 10 〉	통과 (11.74ms, 52.8MB)
테스트 11 〉	통과 (1.63ms, 52MB)
테스트 12 〉	통과 (1.44ms, 52.3MB)
 * 40m
 * 순열, 소수확인
 * https://programmers.co.kr/learn/courses/30/lessons/42839?language=java
 */

package PROGRAMMERS;

import java.util.HashSet;

public class Solution_P_L1_42839_소수찾기_정세린 {

	static class Solution {
		String nums;
		boolean[] selected;
		StringBuilder sb = new StringBuilder(); // 만들어진 숫자
		HashSet<Integer> set = new HashSet<>();
	
		public int solution(String numbers) {
			int answer = 0;
			int len = numbers.length();
			nums = numbers;
			selected = new boolean[len];
	
			for (int i = 1; i <= len; i++) { // 1 ~ len 개
				sb.setLength(0);
				permutation(len, i);
			}
	
			answer = set.size();
			return answer;
		} // end of solution()
	
		void permutation(int n, int r) {
			if (r == 0) {
				// 소수 확인
				int selectedNum = Integer.parseInt(sb.toString());
				if (isPrime(selectedNum)) set.add(selectedNum);
				return;
			}
	
			for (int i = 0; i < n; i++) {
				char number = nums.charAt(i);
				if (selected[i]) continue;
				selected[i] = true;
				sb.append(number);
				permutation(n, r - 1);
				selected[i] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
		} // end of permutation()
	
		boolean isPrime(int n) {
			if (n <= 1) return false;
			for (int i = 2; i <= (int)Math.sqrt(n); i++) 
				if (n % i == 0) return false;
			return true;
		} // end of isPrime();
	
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String numbers = "011";
		int answer = solution.solution(numbers);
		System.out.println(answer);
	}

}
