/*
정확성 테스트

테스트 1 〉	통과 (0.21ms, 51.9MB)
테스트 2 〉	통과 (0.27ms, 52.5MB)
테스트 3 〉	통과 (0.44ms, 52.7MB)
테스트 4 〉	통과 (1.91ms, 52.6MB)
테스트 5 〉	통과 (0.21ms, 51.6MB)
테스트 6 〉	통과 (0.23ms, 52.2MB)
테스트 7 〉	통과 (0.41ms, 53.1MB)
테스트 8 〉	통과 (0.94ms, 52.3MB)
테스트 9 〉	통과 (0.58ms, 52.5MB)
테스트 10 〉	통과 (0.89ms, 52.4MB)
테스트 11 〉	통과 (1.42ms, 52.5MB)

time : 0 Hour 40 Minute

풀이
스텍 배열을 이용하여 시뮬레이션 적용

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/64061
package algo_6월2주;

import java.util.Stack;

public class Solution_P_L2_64061_크레인인형뽑기게임 {
	public static void main(String[] args) {
		int board[][] = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int moves[] = { 1, 5, 3, 5, 1, 2, 1, 4 };
		System.out.println(solution(board, moves));
	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;
		Stack<Integer> s[] = new Stack[board[0].length + 1];
		for (int i = 0; i < board[0].length + 1; i++) {
			s[i] = new Stack<>();
		}
		for (int i = board.length - 1; i >= 0; i--) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				s[j + 1].push(board[i][j]);
			}
		}
		Stack<Integer> sAns = new Stack<>();
		for (int i = 0; i < moves.length; i++) {
			int mov = moves[i];
			if (s[mov].size() == 0) {
				continue;
			}
			int in = s[mov].pop();
			if (sAns.size() == 0) {
				sAns.push(in);
				continue;
			}
			if (sAns.peek() == in) {
				answer += 2;
				sAns.pop();
			} else {
				sAns.push(in);
			}
		}
		return answer;
	}

}
