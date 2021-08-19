import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * -리틀 프렌즈 사천성-
 * 1. board에 존재하는 알파벳 LinkedList로 순차정렬
 * 2. 앞에서 부터 확인하며 지울 수 있는지 확인
 * 3. 지울 수 있는 여부는 4방향 모두 확인한다.
 * 4. 각 방향마다 반시계, 시계 방향으로 90도 회전한 방향도 확인해준다.
 * 5. 지울 수 있다면 지운 뒤 newBoard를 갱신, LinkedList에서 해당 알파벳 삭제 후 다시 앞부터 확인한다.
 * 
 * 정확성  테스트
 * 테스트 1 〉	통과 (102.51ms, 65.7MB)
 * 
 * 풀이 시간 : 1H
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/1836
public class Solution_P_L3_1836_리틀프렌즈사천성 {
	int[] drow = {-1,0,1,0};
	int[] dcol = {0,1,0,-1};
	
	int M,N;
	public String solution(int m, int n, String[] board) {
		M=m;
		N=n;
		
		boolean[] isChecked = new boolean['Z'-'A'+1];
		char[][] newBoard = new char[m][n];
		
		List<Tile> list = new LinkedList<>();
		char tc;
		for (int row = 0; row < m; ++row) {
			for(int col=0; col < n; ++col) {
				tc = board[row].charAt(col);
				newBoard[row][col] = tc;
				
				if('A' <= tc && tc <= 'Z' && !isChecked[tc-'A']) {
					list.add(new Tile(tc, row, col));
					isChecked[tc-'A'] = true;
				}
			}
		}
		
		Collections.sort(list, (o1, o2) -> o1.c - o2.c);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<list.size(); ++i) {
			
			if(deleteAble(list.get(i), newBoard)) {
				sb.append(list.remove(i).c);
				i=-1;
			}
		}
		
		return sb.toString().equals("") || !list.isEmpty() ? "IMPOSSIBLE" : sb.toString();
	}
	
	boolean deleteAble(Tile tile, char[][] board) {
		for(int d=0; d<4; ++d) {
			if(findAndChangeTile(tile, d, board)) return true;
		}
		return false;
	}
	
	boolean findAndChangeTile(Tile tile, int d, char[][] board) {
		char target = tile.c;
		// left, right로 90도 회전한 방향
		int[] sideD = {(d+3)%4, (d+1)%4};
		
		int cr = tile.row+drow[d];
		int cc = tile.col+dcol[d];
		int nr,nc, nd;
		
		while(!isOut(cr,cc)) {
			// 다음 위치 타겟인지 확인
			if(board[cr][cc] == target) {
				board[tile.row][tile.col] = '.';
				board[cr][cc] = '.';
				return true;
			}
			
			// 해당 자리 더이상 지나갈 수 없다면 멈추기
			if(!passAble(cr, cc, board))
				break;
			
			// 해당 자리에서 반시계, 시계 방향으로 회전한 방향으로 나아가기
			for(int i=0; i<sideD.length; ++i) {
				nd = sideD[i];
				nr = cr+drow[nd];
				nc = cc+dcol[nd];
				
				while(!isOut(nr,nc)) {
					// 해당 자리 타겟인지 확인
					if(board[nr][nc] == target) {
						board[tile.row][tile.col] = '.';
						board[nr][nc] = '.';
						return true;
					}
					
					// 더이상 지나갈 수 없다면 멈추기
					if(!passAble(nr, nc, board))
						break;
					
					nr += drow[nd];
					nc += dcol[nd];
				}
			}
			cr += drow[d];
			cc += dcol[d];
		}
		
		return false;
	}
	
	boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=M || c>=N)
			return true;
		return false;
	}
	
	boolean passAble(int r, int c, char[][] board) {
		if(board[r][c] != '.')
			return false;
		return true;
	}
	
	class Tile {
		char c;
		int row, col;
		
		Tile(char c, int row, int col){
			this.c = c;
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) {
		Solution_P_L3_1836_리틀프렌즈사천성 sol = new Solution_P_L3_1836_리틀프렌즈사천성();
		int m = 4;
		int n = 4;
		String[] board = {".ZI.", "M.**", "MZU.", ".IU."};
		for(int i=0; i<board.length; ++i)
			System.out.println(board[i]);
		String answer = sol.solution(m, n, board);
		System.out.println(answer);
	}
}