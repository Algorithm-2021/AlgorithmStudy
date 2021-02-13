import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -공주님을 구해라!-
 * 1. 방문 배열은 3차원으로 관리하여 아이템이 있을 때 와 없을 때로 나눈다.
 * 2. 아이템이 있다면 방문 여부만 판단
 * 3. 아이템이 없다면 방문 여부와 벽인지, 아이템을 먹을 수 있는지 확인
 * 4. 시간 초과시 실패 반환
 * 
 * 메모리 : 17384KB
 * 시간 : 188ms
 * 풀이 시간 : 1H 20M
 */

//출처 : https://www.acmicpc.net/problem/17836
public class Main_B_G5_17836_공주님을구해라 {
	static int ROW,COL,T, time;
	
	static int[][] board;
	static boolean[][][] isVisited;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(solve() ? time : "Fail");
	}
	
	static boolean solve() {
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(0,0, false));
		isVisited[0][0][0] = true;
		
		int cr,cc, nr,nc, size;
		boolean ci,ni;
		Point tp;
		while(!que.isEmpty()) {
			size = que.size();
			while(--size>=0) {
				tp = que.poll();
				cr=tp.r; cc=tp.c; ci=tp.item;

				if(isGoal(cr,cc)) return true;
				
				for(int d=0; d<4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d]; ni=ci;
					if(isOut(nr, nc) || isVisited[nr][nc][1]) continue;
					
					if(hasItem(ci)) {
						isVisited[nr][nc][1] = true;
						
						que.offer(new Point(nr,nc, ni));
					}
					else {
						if(isVisited[nr][nc][0] || isWall(nr,nc)) continue;
						isVisited[nr][nc][0] = true;
						
						if(board[nr][nc] == 2) ni = true;
						que.offer(new Point(nr,nc, ni));
					}
				}
			}
			
			time++;
			if(time>T) {
				return false;
			}
		}
		
		return false;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
	
	static boolean hasItem(boolean item) {
		if(item)
			return true;
		return false;
	}
	
	static boolean isWall(int r, int c) {
		if(board[r][c] == 1)
			return true;
		return false;
	}
	
	static boolean isItem(int r, int c) {
		if(board[r][c] == 2)
			return true;
		return false;
	}
	
	static boolean isGoal(int r, int c) {
		if(r==ROW-1 && c==COL-1)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		board = new int[ROW][COL];
		isVisited = new boolean[ROW][COL][2];
		for (int row = 0; row < ROW; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < COL; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static class Point {
		boolean item;
		int r,c;
		
		Point(int r, int c, boolean item){
			this.r = r;
			this.c = c;
			this.item = item;
		}
	}
}
