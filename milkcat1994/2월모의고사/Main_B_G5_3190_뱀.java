package s_20210226;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -뱀-
 * 
 * 메모리 : 15136KB
 * 시간 : 144ms
 * 풀이 시간 : 1H
 */

//출처 : https://www.acmicpc.net/problem/3190
public class Main_B_G5_3190_뱀 {
	static int N,K,L;
	
	static Deque<Point> snake;
	static Queue<Operation> operation;
	
	static boolean[][] apple;
	
	// up, right, down, left
	static int[] drow = {-1,0,1,0};
	static int[] dcol = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(solve());
	}
	
	static int solve() {
		int time=0;
		int nr,nc;
		int dir=1; //right
		
		int operationTime;
		Operation to;
		Point tp;
		while(!operation.isEmpty()) {
			to = operation.poll();
			operationTime = to.time;
			while(operationTime > time) {
				time++;
				tp = snake.peekFirst();
				
				nr=tp.r+drow[dir]; nc=tp.c+dcol[dir];
				if(isOut(nr, nc) || isConflict(nr, nc))
					return time;
				
				snake.offerFirst(new Point(nr,nc));
				// 다음칸이 사과가 아니라면
				if(!apple[nr][nc]) {
					snake.pollLast();
				}
				else {
					apple[nr][nc] = false;
				}
			}
			dir = changeDir(to, dir);
		}
		
		tp=snake.peekFirst();
		nr=tp.r+drow[dir]; nc=tp.c+dcol[dir];
		while(!isOut(nr, nc) && !isConflict(nr, nc)) {
			time++;
			snake.offerFirst(new Point(nr,nc));
			// 다음칸이 사과가 아니라면
			if(!apple[nr][nc]) {
				snake.pollLast();
			}
			else {
				apple[nr][nc] = false;
			}
			nr+=drow[dir]; nc+=dcol[dir];
		}
		return time+1;
	}
	
	static int changeDir(Operation op, int dir) {
		switch (op.direction) {
		//left
		case 'L':
			return (dir+4-1)%4;
		//right
		case 'D':
			return (dir+1)%4;
		default:
			// Do Nothing
			return -1;
		}
	}
	
	static boolean isConflict(int r, int c) {
		for(Point tp : snake) {
			if(r==tp.r && c==tp.c)
				return true;
		}
		return false;
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		apple = new boolean[N][N];
		int r,c;
		for(int i=0; i<K; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			apple[r][c] = true;
		}
		
		L = Integer.parseInt(br.readLine());
		operation = new LinkedList<>();
		int time;
		char direction;
		for(int i=0; i<L; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			time = Integer.parseInt(st.nextToken());
			direction = st.nextToken().charAt(0);
			operation.offer(new Operation(time, direction));
		}
		
		snake = new LinkedList<>();
		snake.offerFirst(new Point(0,0));
	}
	
	static class Point {
		int r,c;
		
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return r+" "+c;
		}
	}
	
	static class Operation {
		int time;
		char direction;
		
		public Operation(int time, char direction) {
			this.time = time;
			this.direction = direction;
		}
	}
}
