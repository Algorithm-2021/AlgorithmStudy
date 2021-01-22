import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * -불-
 * 1. 처음 입력에서 Queue에 불을 가장 앞으로, 플레이어를 가장 뒤로 넣어 이동을 최적화 시킨다. (불이 있는 곳은 사람이 가지 못하기 때문)
 * 2. Queue가 빌때까지 반복하며, 시간별로 이동을 진행한다(time 이용)
 * 3. '불'은 '길'과 '사람'인 쪽으로만 이동이 가능하다.
 * 4. '사람'은 '길'만 이동 가능하며, 밖으로 나갈경우 탈출이 된 것이므로 BFS를 종료한다.
 * 5. '사람'이 탈출 하지 못할 경우 bfs()는 false를 탈출 할경우 걸린 time을 반환한다.
 * 
 * 메모리 : 115720KB
 * 시간 : 684ms
 * 풀이 시간 : 30M
 */

//출처 : https://www.acmicpc.net/problem/5427
public class Main_B_G4_5427_불 {
	static int TC, ROW, COL, time;
	
	static char[][] board;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static Deque<Point> deque;
	
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while(--TC>=0) {
			init();
			
			sb.append(bfs() ? time : "IMPOSSIBLE").append('\n');
		}
		
		System.out.print(sb.toString());
	}
	
	static boolean bfs() {
		int qs, cr,cc, nr,nc;
		time = 0;
		Point tp;
		
		while(!deque.isEmpty()) {
			time++;
			qs = deque.size();
			while(--qs>=0) {
				tp = deque.poll();
				cr=tp.r; cc=tp.c;
				
				// 불 이동
				if(tp.isFire){
					for(int d=0; d<4; ++d) {
						nr=cr+drow[d]; nc=cc+dcol[d];
						if(isOut(nr, nc)) continue;
						
						if(isLoad(nr,nc) || isPlayer(nr,nc)) {
							deque.offer(new Point(nr,nc,true));
							board[nr][nc] = '*';
						}
					}
				}
				// 사람 이동
				else {
					for(int d=0; d<4; ++d) {
						nr=cr+drow[d]; nc=cc+dcol[d];
						if(isOut(nr,nc))
							return true;
						
						if(isLoad(nr,nc)) {
							deque.offer(new Point(nr,nc,false));
							board[nr][nc] = '@';
						}
					}
				}
			}
		}
		
		return false;
	}
	
	static boolean isLoad(int r, int c) {
		if(board[r][c] == '.')
			return true;
		return false;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
	
	static boolean isPlayer(int r, int c) {
		if(board[r][c] == '@')
			return true;
		return false;
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		COL = Integer.parseInt(st.nextToken());
		ROW = Integer.parseInt(st.nextToken());

		String str;
		deque = new LinkedList<>();
		board = new char[ROW][COL];
		for (int row = 0; row < ROW; ++row) {
			str = br.readLine();
			for (int col = 0; col < COL; ++col) {
				board[row][col] = str.charAt(col);
				
				// '불'은 가장 앞에, '사람'은 뒤쪽으로 넣어주기 위함
				switch (board[row][col]) {
				case '*':
					deque.offerFirst(new Point(row, col, true));
					break;
				
				case '@':
					deque.offerLast(new Point(row, col, false));
					break;
				}
			}
		}
	}
	
	static class Point {
		int r,c;
		boolean isFire;
		
		Point(int r, int c, boolean isFire){
			this.r = r;
			this.c = c;
			this.isFire = isFire;
		}
	}
}
