/*
 * -등굣길-
 * 1. 가장 상단과 좌측은 가능성을 1로 채운다.
 * 2. 이 때 중간에 웅덩이가 있다면 1로 채우는 것을 멈춘다. -> 이동이 우측, 아래 로만 가능하기 때문
 * 3. 1,1 부터 위쪽과 좌측의 경우의 수를 합하며 내려간다.
 * 
 * 풀이 시간 : 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/42898
public class Solution_P_L3_42898_등굣길 {
	// m : col
	// n : row
	int MOD = 1000000007;
	public int solution(int m, int n, int[][] puddles) {
		int[][] board = new int[n][m];

		// 웅덩이 표시
		for(int[] arr : puddles) {
			board[arr[1]-1][arr[0]-1] = -1;
		}
		
		// 좌측 열 값 초기화
		for(int row=0; row<n; ++row) {
			if(board[row][0] == -1) break;
			board[row][0] = 1;
		}
		
		// 상단 행 값 초기화
		for(int col=0; col<m; ++col) {
			if(board[0][col] == -1) break;
			board[0][col] = 1;
		}

		// 개수 갱신
		for(int row=1; row<n; ++row) {
			for(int col=1; col<m; ++col) {
				if(board[row][col] == -1) continue;
				board[row][col] += board[row-1][col] == -1 ? 0 : board[row-1][col];
				board[row][col] += board[row][col-1] == -1 ? 0 : board[row][col-1];
				board[row][col] %= MOD;
			}
		}
		
		return board[n-1][m-1];
	}

	public static void main(String[] args) {
		Solution_P_L3_42898_등굣길 sol = new Solution_P_L3_42898_등굣길();
		int m = 4;
		int n = 3;
		int[][] puddles = {{2,2}};
		int answer = sol.solution(m, n, puddles);
		System.out.println(answer);
	}
}