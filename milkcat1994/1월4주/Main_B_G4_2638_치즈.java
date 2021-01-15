import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -치즈-
 * Deque를 이용하여 빈 공간이라면 가장 앞에 넣어 먼저 처리 되도록 한다.
 * Queue의 Size만큼 순회 시켜 time을 구한다.
 * 또한 이미 방문한 빈칸의 경우 주변에 대해 처리 할 필요가 없으므로 재방문 하지 않는다.
 * 
 * 메모리 : 15800KB
 * 시간 : 172ms
 * 풀이 시간 : 1H 30M
 */

//출처 : https://www.acmicpc.net/problem/2638
public class Main_B_G4_2638_치즈 {
	static int ROW, COL;
	static int[][] cheeze;
	// 빈곳 재방문 하지 않도록
	static boolean[][] blankIsVisited;
	//해당 자리가 치즈인지
	static boolean[][] isCheeze;
	
	static int[] drow = {0,0,-1,1};
	static int[] dcol = {-1,1,0,0};
	
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(bfs());
	}
	
	static int bfs() {
		// time은 -1로 초기화 하여 첫 초기화로 인한 시간 변동을 예외처리한다.
		int size, cr,cc, nr,nc, time=-1;
		
		Point tp;
		Deque<Point> dq = new LinkedList<>();
		
		// 처음 빈 좌표를 이용하여 bfs 시작
		dq.offer(new Point(0,0));
		blankIsVisited[0][0] = true;
		
		while(!dq.isEmpty()) {
			size = dq.size();
			while(--size >= 0) {
				tp = dq.poll();
				cr=tp.r; cc=tp.c;
				
				for(int d=0; d<4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					// 나가거나, 빈칸으로써 방문한 자리라면 패스
					if(isOut(nr, nc) || blankIsVisited[nr][nc]) continue;
					
					// Cheeze 라면 해당 주변 빈 칸 량을 더해준다.
					if(isCheeze[nr][nc]) {
						cheeze[nr][nc]++;
						// 빈칸 량이 2 이상이라면 해당 자리는 빈칸이 되어야 하므로 맨 뒤에 더해준다.
						// size는 더해 주지 않아 다음 time때 실행 된다.
						if(cheeze[nr][nc]>1) {
							dq.offerLast(new Point(nr,nc));
							blankIsVisited[nr][nc] = true;
						}
					}
					// Blank 라면 가장 앞에 넣어 먼저 처리한다.
					else {
						// 추가적으로 size를 조절해주어 동일한 time에 처리해준다.
						size++;
						dq.offerFirst(new Point(nr,nc));
						blankIsVisited[nr][nc] = true;
					}
				}
			}
			time++;
		}
		
		return time;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=ROW || c>=COL)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		ROW = Integer.parseInt(st.nextToken());
		COL = Integer.parseInt(st.nextToken());
		
		cheeze = new int[ROW][COL];
		blankIsVisited = new boolean[ROW][COL];
		isCheeze = new boolean[ROW][COL];
		for (int row = 0; row < ROW; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < COL; ++col) {
				// cheeze 라면 값 true
				if(Integer.parseInt(st.nextToken()) == 1){
					isCheeze[row][col] = true;
				}
			}
		}
	}
	
	static class Point {
		int r,c;
		
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
