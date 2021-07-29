import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * -거리두기 확인하기-
 * 1. BFS 통해 거리가 2로 접근이 가능한 사람이 있는지 확인하는 방식
 * 
 * 풀이 시간 : 20m
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/81302
public class Solution_P_L2_81302_거리두기확인하기 {
	static int SIZE = 5;
	static char PERSON = 'P';
	static char WALL = 'X';
	static char BLANK = 'O';
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public int[] solution(String[][] places) {
		int[] answer = new int[places.length];
		
		for(int idx=0; idx<places.length; ++idx) {
			char[][] board = new char[SIZE][SIZE];
			
			// board 배열 초기화
			for(int i=0; i<SIZE; ++i) {
				board[i] = places[idx][i].toCharArray();
			}
			
			answer[idx] = solve(board);
		}
		
		return answer;
	}
	
	public int solve(char[][] board) {
		for(int row=0; row<SIZE; ++row) {
			for(int col=0; col<SIZE; ++col) {
				// 해당 자리가 사람이라면 bfs 진행
				// bfs가 false 반환되었다면 거리두기를 지키고 있지 않다는 것
				if(board[row][col] == PERSON && !bfs(board, row, col)) {
					return 0;
				}
			}
		}
		return 1;
	}
	
	public boolean bfs(char[][] board, int sr, int sc) {
		Queue<Point> que = new LinkedList<>();
		boolean[][] isVisited = new boolean[SIZE][SIZE];
		
		Point tp;
		int cr,cc,nr,nc, distance, size;
		que.offer(new Point(sr, sc));
		isVisited[sr][sc] = true;
		
		distance=1;
		while(!que.isEmpty() && distance <= 2) {
			size = que.size();
			while(--size >= 0) {
				tp = que.poll();
				
				cr=tp.r; cc=tp.c;
				for(int d=0; d<4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					if(isOut(nr,nc) || isVisited[nr][nc] || board[nr][nc] == WALL) continue;
					
					// 거리 2이하일때 사람을 만났다면 실패
					if(board[nr][nc] == PERSON)
						return false;
					
					que.offer(new Point(nr,nc));
					isVisited[nr][nc] = true;
				}
			}
			distance++;
		}
		
		return true;
	}
	
	class Point {
		int r,c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=SIZE || c>=SIZE)
			return true;
		return false;
	}

	public static void main(String[] args) {
		Solution_P_L2_81302_거리두기확인하기 sol = new Solution_P_L2_81302_거리두기확인하기();
		String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		int[] answer = sol.solution(places);
		System.out.println(Arrays.toString(answer));
	}
}