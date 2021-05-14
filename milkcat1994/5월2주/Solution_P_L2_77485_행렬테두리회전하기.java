import java.util.Arrays;

/*
 * --
 * 
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/77485
public class Solution_P_L2_77485_행렬테두리회전하기 {
	
	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer;
		
		int[][] board = new int[rows][columns];
		
		int ti = 1;
		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < columns; ++col) {
				board[row][col] = ti++;
			}
		}
		
		answer = new int[queries.length];
		for(int i=0; i<queries.length; ++i) {
			answer[i] = rotate(board, queries[i]);
		}
		return answer;
	}
	
	int rotate(int[][] board, int[] query) {
		int sr = query[0]-1;
		int sc = query[1]-1;
		int er = query[2]-1;
		int ec = query[3]-1;
		
		int origin = board[sr][ec];
		int temp;
		int min = origin;
		for(int c=ec-1; c>=sc; --c) {
			temp = board[sr][c];
			board[sr][c+1] = temp;
			min = Math.min(min, temp);
		}

		for(int r=sr+1; r<=er; ++r) {
			temp = board[r][sc];
			board[r-1][sc] = temp;
			min = Math.min(min, temp);
		}

		for(int c=sc+1; c<=ec; ++c) {
			temp = board[er][c];
			board[er][c-1] = temp;
			min = Math.min(min, temp);
		}
		
		for(int r=er-1; r>sr; --r) {
			temp = board[r][ec];
			board[r+1][ec] = temp;
			min = Math.min(min, temp);
		}
		
		board[sr+1][ec] = origin;
		return min;
	}


	public static void main(String[] args) {
		Solution_P_2 sol = new Solution_P_2();
		int rows = 6;
		int columns = 6;
		int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
		int[] answer = sol.solution(rows, columns, queries);
		System.out.println(Arrays.toString(answer));
	}
}