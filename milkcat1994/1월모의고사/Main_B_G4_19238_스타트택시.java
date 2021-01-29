package s_20210129;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -스타트택시-
 * 
 * 메모리 : 22596KB
 * 시간 : 212ms
 * 풀이 시간 : 50M
 */

//출처 : https://www.acmicpc.net/problem/19238
public class Main_B_G4_19238_스타트택시 {
	static int N,M,O;
	static int[][] board;
	static boolean[][] isVisited;
	static int dest = -1;
	static boolean isAble;
	
	static Point taxi;
	static Point[] end;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		init();
		
		int time = 0;
		isAble = true;
		while(isAble && time<M) {
			if(isAble) goStart();
			
			if(isAble) goEnd();
			
			if(isAble) time++;
		}
		System.out.println(time == M ? O : -1);
	}
	
	static void goStart() {
		initVisited();
		Queue<Point> que = new LinkedList<>();
		PriorityQueue<Point> pq = new PriorityQueue<>();
		
		que.offer(taxi);
		isVisited[taxi.r][taxi.c] = true;
		
		int cr,cc, nr,nc, size, time=0;
		Point tp;
		boolean isFind = false;
		
		while(!que.isEmpty()) {
			size = que.size();
			while(--size>=0) {
				tp = que.poll();
				cr=tp.r; cc=tp.c;
				
				// 찾음
				if(board[cr][cc] > 0) {
					pq.offer(new Point(cr,cc));
					isFind = true;
				}
				
				for(int d=0; d<4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					if(isOut(nr, nc) || isWall(nr,nc) || isVisited[nr][nc]) continue;
					
					isVisited[nr][nc] = true;
					que.offer(new Point(nr, nc));
				}
			}
			
			if(isFind)
				break;
			else
				time++;
		}
		
		//연료 빼주기
		O-=time;
		if(O<0) isAble=false;
		if(pq.isEmpty()) {
			isAble = false;
			return;
		}
		tp = pq.poll();
		dest = board[tp.r][tp.c];
		board[tp.r][tp.c] = 0;
		taxi.move(tp.r, tp.c);
	}
	
	static void initVisited() {
		for(int i=0; i<N; ++i)
			Arrays.fill(isVisited[i], false);
	}
	
	static void goEnd() {
		initVisited();
		Queue<Point> que = new LinkedList<>();
		
		int destR = end[dest].r;
		int destC = end[dest].c;
		
		que.offer(taxi);
		isVisited[taxi.r][taxi.c] = true; 
		
		int cr,cc, nr,nc, size, time=0;
		Point tp;
		boolean isFind = false;
		
		while(!que.isEmpty()) {
			size = que.size();
			while(--size>=0) {
				tp = que.poll();
				cr=tp.r; cc=tp.c;
				
				// 찾음
				if(cr == destR && cc == destC) {
					isFind = true;
					break;
				}
				
				for(int d=0; d<4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					if(isOut(nr, nc) || isWall(nr,nc) || isVisited[nr][nc]) continue;
					
					isVisited[nr][nc] = true;
					que.offer(new Point(nr, nc));
				}
			}
			
			if(isFind)
				break;
			else
				time++;
		}
		if(!isFind) {
			isAble = false;
			return;
		}
		O-=time;
		if(O<0) isAble=false;
		O+=time*2;
		
		taxi.move(destR, destC);
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	static boolean isWall(int r, int c) {
		if(board[r][c] == -1)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		O = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		isVisited = new boolean[N][N];
		int ti;
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				ti = Integer.parseInt(st.nextToken());
				if(ti == 1) {
					board[row][col] = -1;
				}
			}
		}

		int tr,tc;
		st = new StringTokenizer(br.readLine(), " ");
		tr = Integer.parseInt(st.nextToken())-1;
		tc = Integer.parseInt(st.nextToken())-1;
		taxi = new Point(tr, tc);
		
		end = new Point[M+1];
		for(int i=1; i<=M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			tr = Integer.parseInt(st.nextToken())-1;
			tc = Integer.parseInt(st.nextToken())-1;
			
			board[tr][tc] = i;
			
			tr = Integer.parseInt(st.nextToken())-1;
			tc = Integer.parseInt(st.nextToken())-1;
			end[i] = new Point(tr, tc);
		}
	}
	
	static class Point implements Comparable<Point> {
		int r,c;
		
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Point o) {
			if(o.r==this.r) {
				return this.c - o.c;
			}
			return this.r - o.r;
		}
		
		public void move(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
