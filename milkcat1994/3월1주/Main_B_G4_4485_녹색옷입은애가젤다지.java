import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -녹색 옷 입은 애가 젤다지?-
 * 1. 처음 접근은 dfs로 접근 하였으나 시간 초과 발생
 * 2. 다익스트라 알고리즘 이용하여 풀이
 * 
 * 메모리 : 20152KB
 * 시간 : 300ms
 * 풀이 시간 : 30M
 */

//출처 : https://www.acmicpc.net/problem/4485
public class Main_B_G4_4485_녹색옷입은애가젤다지 {
	static int N, TC;
	
	static int[][] board, dist;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		while(true) {
			TC++;
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			
			init();
			
			solve();
			
			sb.append("Problem "+TC+": "+dist[N-1][N-1]+"\n");
		}
		System.out.print(sb.toString());
	}
	
	static void solve() {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0,0, board[0][0]));
		
		Point tp;
		int cr,cc,cw, nr,nc,nw;
		while(!pq.isEmpty()) {
			tp = pq.poll();
			cr=tp.r; cc=tp.c; cw=tp.w;
			for(int d=0; d<4; ++d) {
				nr=cr+drow[d]; nc=cc+dcol[d];
				if(isOut(nr, nc)) continue;
				
				nw=cw+board[nr][nc];
				if(dist[nr][nc] > nw) {
					dist[nr][nc] = nw;
					pq.offer(new Point(nr,nc,nw));
				}
			}
		}
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		board = new int[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		dist = new int[N][N];
		for(int i=0; i<N; ++i)
			Arrays.fill(dist[i], Integer.MAX_VALUE);
	}
	
	static class Point implements Comparable<Point>{
		int r, c, w;
		
		Point(int r, int c, int w){
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Point o) {
			return this.w - o.w;
		}
	}
}
