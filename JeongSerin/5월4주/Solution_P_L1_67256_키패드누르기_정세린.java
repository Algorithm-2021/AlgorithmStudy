/*
테스트 16 〉	통과 (0.80ms, 52.7MB)
테스트 17 〉	통과 (1.21ms, 52.3MB)
테스트 18 〉	통과 (1.27ms, 53MB)
테스트 19 〉	통과 (1.21ms, 52.4MB)
테스트 20 〉	통과 (1.22ms, 53.7MB)
 * 30m
 * https://programmers.co.kr/learn/courses/30/lessons/67256
 */
package PROGRAMMERS;

public class Solution_P_L1_67256_키패드누르기_정세린 {

	static class Solution {
		StringBuilder sb = new StringBuilder();
		int li = 4;
		int lj = 1; // 왼손 시작 위치 *
		int ri = 4;
		int rj = 3; // 오른손 시작 위치 #
		int ni, nj;
	
		public String solution(int[] numbers, String hand) {
			String answer = "";
	
			for (int num : numbers) {
				ni = (int) Math.ceil(num / 3.0); // 다음 숫자의 위치
				nj = num % 3;
				if (nj == 0) nj = 3;
				if (num == 0) {
					ni = 4;
					nj = 2;
				}
	
				if (num == 1 || num == 4 || num == 7) left(); // 왼손
				else if (num == 3 || num == 6 || num == 9) right(); // 오른손
				else { // 가운데
					int distL = Math.abs(ni - li) + Math.abs(nj - lj);
					int distR = Math.abs(ni - ri) + Math.abs(nj - rj);
					if (distL < distR) left();
					else if (distL > distR) right();
					else {
						if (hand.equals("left")) left();
						if (hand.equals("right")) right();
					}
				}
			} // end of for
			answer = sb.toString();
			return answer;
		}
	
		void left() {
			li = ni;
			lj = nj;
			sb.append('L');
		}
	
		void right() {
			ri = ni;
			rj = nj;
			sb.append('R');
		}
	
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand = "right";
		String answer = solution.solution(numbers, hand);
		System.out.println(answer);
	}

}
