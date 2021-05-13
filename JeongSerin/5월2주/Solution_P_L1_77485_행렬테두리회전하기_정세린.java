/*
테스트 7 〉	통과 (35.91ms, 58.7MB)
테스트 8 〉	통과 (20.36ms, 57.4MB)
테스트 9 〉	통과 (39.27ms, 62.3MB)
테스트 10 〉	통과 (28.84ms, 58.3MB)
테스트 11 〉	통과 (28.71ms, 58.5MB)
 * 40m
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L1_77485_행렬테두리회전하기_정세린 {
	static class Solution {
		public int[] solution(int rows, int columns, int[][] queries) {
			int[] answer = new int[queries.length];
			int[][] board = new int[rows + 1][columns + 1];
			int number = 1;
			for (int i = 1; i <= rows; i++) {
				for (int j = 1; j <= columns; j++) {
					board[i][j] = number++;
				}
			}
	
			for (int i = 0; i < queries.length; i++) {
				// 회전
				int curi = queries[i][0];
				int curj = queries[i][1];
				int tmp = 0;
				int min = board[curi][curj];
				int ni = 0, nj = 0;
	
				while (true) {
					if (tmp != 0) {
						curi = ni;
						curj = nj;
						min = Integer.min(min, tmp);
					}
					// 다음 좌표
					ni = curi;
					nj = curj;
					if (curi == queries[i][0] && curj < queries[i][3]) nj++;
					else if (curi < queries[i][2] && curj == queries[i][3]) ni++;
					else if (curi == queries[i][2] && curj > queries[i][1]) nj--;
					else ni--;
	
					int tmp2 = board[ni][nj];
					if (tmp == 0) tmp = board[curi][curj];
					board[ni][nj] = tmp;
					tmp = tmp2;
					if (ni == queries[i][0] && nj == queries[i][1]) break;
				}
				answer[i] = min;
			}
			return answer;
		}
	
	} // end of Solution

	public static void main(String[] args) {
		Solution solution = new Solution();
		int rows = 6;
		int columns = 6;
		int[][] queries = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		int[] answer = solution.solution(rows, columns, queries);
		System.out.println(Arrays.toString(answer));
	}

}
