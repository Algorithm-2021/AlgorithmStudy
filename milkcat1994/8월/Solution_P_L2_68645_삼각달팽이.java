import java.util.Arrays;

/*
 * -삼각 달팽이-
 * 1. 2차원 배열을 통해 삼각형 미리 입력하는 방식 사용
 * 
 * 풀이 시간 : 25m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/68645
public class Solution_P_L2_68645_삼각달팽이 {
	int[] drow = {1,0,-1};
	int[] dcol = {0,1,-1};
	
	public int[] solution(int n) {
		int lastNum = (n*(n+1))/2;
		
		int[] answer = new int[lastNum];
		int[][] board = new int[n][n];
		
		int cr=0;
		int cc=0;
		int d=0;
		
		int nr,nc;
		for(int num=1; num<=lastNum; ++num) {
			board[cr][cc] = num;
			
			nr = cr+drow[d];
			nc = cc+dcol[d];
			if(isOut(nr,nc,n) || board[nr][nc] != 0) {
				d = (d+1)%3;
				nr = cr+drow[d];
				nc = cc+dcol[d];
			}
			
			cr=nr; cc=nc;
		}
		
		int idx=0;
		for (int row = 0; row < n; ++row) {
			for (int col = 0; col < n; ++col) {
				if(board[row][col] == 0) break;
				answer[idx++] = board[row][col];
			}
		}
		return answer;
	}
	
	public boolean isOut(int r, int c, int n) {
		if(r<0 || c<0 || r<c || r>=n || c>=n)
			return true;
		return false;
	}

	public static void main(String[] args) {
		Solution_P_L2_68645_삼각달팽이 sol = new Solution_P_L2_68645_삼각달팽이();
		int n = 5;
		int[] answer = sol.solution(n);
		System.out.println(Arrays.toString(answer));
	}
}