/*
테스트 1 〉	통과 (0.20ms, 52.4MB)
테스트 2 〉	통과 (0.23ms, 52.3MB)
테스트 3 〉	통과 (0.25ms, 52.3MB)
테스트 4 〉	통과 (3.63ms, 52.8MB)
테스트 5 〉	통과 (0.25ms, 52.1MB)
테스트 6 〉	통과 (0.15ms, 52.8MB)
테스트 7 〉	통과 (0.48ms, 52.3MB)
테스트 8 〉	통과 (1.08ms, 52.7MB)
테스트 9 〉	통과 (1.04ms, 52.9MB)
테스트 10 〉	통과 (1.22ms, 52.1MB)
테스트 11 〉	통과 (1.44ms, 53.5MB)
 * 30m
 * Stack
 */
package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Stack;

public class Solution_P_L2_12977_크레인인형뽑기_정세린 {

	static class Solution {
		public int solution(int[][] board, int[] moves) {
			int answer = 0;
			int len = board.length;
			Stack<Integer> basket = new Stack<>();
			ArrayList<Stack<Integer>> board2 = new ArrayList<>();
			for (int i = 0; i < len + 1; i++) board2.add(new Stack<Integer>());

			for (int i = len - 1; i >= 0; i--) {
				for (int j = 0; j < len; j++) {
					int doll = board[i][j];
					if (doll == 0) continue;
					board2.get(j + 1).add(doll);
				}
			}

			for (int m : moves) {
				if (board2.get(m).isEmpty())
					continue; // 빈곳에 갔을때
				int pick = board2.get(m).pop();

				if (basket.isEmpty()) {
					basket.add(pick); // 바구니가 비면 그냥 인형 넣음
					continue;
				}
				// 바구니에 인형이 있을 때
				int peek = basket.peek();

				if (pick == peek) { // 꼭대기에 있는 인형과 같으면
					basket.pop();
					answer += 2;
				} else basket.add(pick);

			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};	
		int[] moves = {1,5,3,5,1,2,1,4};
		int answer = solution.solution(board, moves);
		System.out.println(answer);
	}

}
