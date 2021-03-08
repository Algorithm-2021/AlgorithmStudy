import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -카드짝 맞추기-
 * 1. 순열 이용하여 카드를 옮기는 순서를 정하였음.
 * 2. 순서는 카드를 옮기는 순서에 따라 가장 가까운 해당 카드까지 이동 -> 해당 카드 뒤집기 -> 해당 카드와 같은 카드로 이동 -> 뒤집기 -> 다음 카드 찾기
 * 3. 모든 카드를 다 뒤집었다면 가장 작은 이동 값을 갱신해준다.
 * 
 * 테스트 1 〉	통과 (1.70ms, 52.7MB)
 * 테스트 2 〉	통과 (1.66ms, 52.8MB)
 * 테스트 3 〉	통과 (1.80ms, 52MB)
 * 테스트 4 〉	통과 (1.59ms, 53MB)
 * 테스트 5 〉	통과 (7.31ms, 53.2MB)
 * 테스트 6 〉	통과 (4.25ms, 52.4MB)
 * 테스트 7 〉	통과 (4.45ms, 52.9MB)
 * 테스트 8 〉	통과 (4.27ms, 52.3MB)
 * 테스트 9 〉	통과 (8.39ms, 54.4MB)
 * 테스트 10 〉	통과 (8.98ms, 54.7MB)
 * 테스트 11 〉	통과 (8.39ms, 52.7MB)
 * 테스트 12 〉	통과 (7.27ms, 52.9MB)
 * 테스트 13 〉	통과 (24.09ms, 57.7MB)
 * 테스트 14 〉	통과 (25.90ms, 57.5MB)
 * 테스트 15 〉	통과 (23.27ms, 57.1MB)
 * 테스트 16 〉	통과 (21.36ms, 57.3MB)
 * 테스트 17 〉	통과 (0.87ms, 51.8MB)
 * 테스트 18 〉	통과 (4.03ms, 52MB)
 * 테스트 19 〉	통과 (1.46ms, 52.4MB)
 * 테스트 20 〉	통과 (1.15ms, 53.1MB)
 * 테스트 21 〉	통과 (3.54ms, 52.9MB)
 * 테스트 22 〉	통과 (26.94ms, 57.9MB)
 * 테스트 23 〉	통과 (25.94ms, 57.6MB)
 * 테스트 24 〉	통과 (4.20ms, 52.2MB)
 * 테스트 25 〉	통과 (23.58ms, 57.6MB)
 * 테스트 26 〉	통과 (4.02ms, 51.9MB)
 * 테스트 27 〉	통과 (3.65ms, 52.2MB)
 * 테스트 28 〉	통과 (1.99ms, 52.2MB)
 * 테스트 29 〉	통과 (4.42ms, 51.6MB)
 * 테스트 30 〉	통과 (1.76ms, 52.2MB)
 * 풀이 시간 : 1H
*/

//출처 : https://programmers.co.kr/learn/courses/30/lessons/72415
public class Solution_P_L3_72415_카드짝맞추기 {
	static final int BOARD_SIZE = 4;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static boolean[] isSelected;
	static int cardCnt, minRes;
	static List<Card>[] cardPosition;
	
	public int solution(int[][] board, int r, int c) {
		minRes = Integer.MAX_VALUE;
		cardCnt = countCard(board);
		initCardPositionList(board);
		
		isSelected = new boolean[cardCnt+1];
		
		permutation(board, 0, r, c, 0);
		
		return minRes;
	}
	
	public int countCard(int[][] board) {
		int cnt=0;
		for (int row = 0; row < BOARD_SIZE; ++row) {
			for (int col = 0; col < BOARD_SIZE; ++col) {
				if(board[row][col] != 0)
					cnt++;
			}
		}
		return cnt/2;
	}
	
	public void initCardPositionList(int[][] board) {
		cardPosition = new ArrayList[cardCnt+1];
		for(int i=1; i<=cardCnt; ++i) {
			cardPosition[i] = new ArrayList<>();
		}

		for (int row = 0; row < BOARD_SIZE; ++row) {
			for (int col = 0; col < BOARD_SIZE; ++col) {
				if(board[row][col] != 0) {
					cardPosition[board[row][col]].add(new Card(board[row][col], row, col));
				}
			}
		}
	}
	
	public void permutation(int[][] board, int moveCnt, int cursorR, int cursorC, int selectedCnt) {
		if(selectedCnt == cardCnt) {
			minRes = Math.min(minRes, moveCnt);
			return;
		}
		
		int[][] tempBoard = copyNewBoard(board);
		
		MoveInfo moveInfo1, moveInfo2;
		// idx == targetCardNum
		for(int idx = 1; idx<=cardCnt; ++idx) {
			if(isSelected[idx]) continue;
			isSelected[idx] = true;
			copyBoard(tempBoard, board);
			
			// move cursor from start card
			moveInfo1 = move(tempBoard, cursorR, cursorC, idx);
			
			// pold card
			pold(tempBoard, moveInfo1);

			// move cursor from end card
			moveInfo2 = move(tempBoard, moveInfo1.r, moveInfo1.c, idx);
			
			// pold card
			pold(tempBoard, moveInfo2);
			
			permutation(tempBoard, moveCnt+moveInfo1.time+moveInfo2.time+2, moveInfo2.r, moveInfo2.c, selectedCnt+1);
			
			isSelected[idx] = false;
		}
	}
	
	public int[][] copyNewBoard(int[][] board){
		int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
		for(int i=0; i<BOARD_SIZE; ++i)
			newBoard[i] = board[i].clone();
		return newBoard;
	}
	
	public void copyBoard(int[][] targetBoard, int[][] originBoard){
		for(int i=0; i<BOARD_SIZE; ++i)
			targetBoard[i] = originBoard[i].clone();
	}
	
	// pold targetCard
	static void pold(int[][] tempBoard, MoveInfo moveInfo) {
		tempBoard[moveInfo.r][moveInfo.c]=0;
	}
	
	// return moveInfo
	static MoveInfo move(int[][] board, int cursorR, int cursorC, int targetCardNum) {
		boolean[][] isVisited = new boolean[BOARD_SIZE][BOARD_SIZE];
		
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(cursorR, cursorC));
		isVisited[cursorR][cursorC]=true;
		
		int cr, cc, nr, nc, time=0, size;
		Point tp;
		while(!que.isEmpty()) {
			size = que.size();
			while(--size>=0) {
				tp = que.poll();
				cr=tp.r; cc=tp.c;
				if(isTarget(board, cr, cc, targetCardNum)) return new MoveInfo(cr, cc, time);
				
				// move beside
				for(int d=0; d<4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					if(isOut(nr, nc) || isVisited[nr][nc]) continue;
					isVisited[nr][nc] = true;
					que.offer(new Point(nr,nc));
				}
				
				// move using 'ctrl'
				for(int d=0; d<4; ++d) {
					nr=cr; nc=cc;
					do {
						nr+=drow[d]; nc+=dcol[d];
					}while(!isOut(nr,nc) && board[nr][nc]==0);
					
					if(isOut(nr,nc)) {
						nr-=drow[d]; nc-=dcol[d];
					}
					
					if(isVisited[nr][nc]) continue;
					isVisited[nr][nc] = true;
					que.offer(new Point(nr,nc));
				}
			}
			time++;
		}
		return null;
	}
	
	static boolean isTarget(int[][] board, int cr, int cc, int targetNum) {
		if(board[cr][cc] == targetNum)
			return true;
		return false;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=BOARD_SIZE || c>=BOARD_SIZE)
			return true;
		return false;
	}
	
	static class Card {
		int num,r,c;
		Card(int num, int r, int c){
			this.num = num;
			this.r = r;
			this.c = c;
		}
	}
	
	static class Point {
		int r,c;
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static class MoveInfo{
		int r,c, time;
		MoveInfo(int r, int c, int time){
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}


	public static void main(String[] args) {
		Solution_P_L3_72415_카드짝맞추기 sol = new Solution_P_L3_72415_카드짝맞추기();
		int[][] arr = {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
		int r=1, c=0;
		int answer = sol.solution(arr, r, c);
		System.out.println(answer);
	}
}