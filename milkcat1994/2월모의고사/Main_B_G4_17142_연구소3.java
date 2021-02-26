package s_20210226;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -연구소3-
 * 
 * 메모리 : 42864KB
 * 시간 : 288ms
 * 풀이 시간 : 50M
 */

//출처 : https://www.acmicpc.net/problem/17142
public class Main_B_G4_17142_연구소3 {
	static int N,M, minRes;
	static int totalVirus, targetVirusNum;
	
	static int[][] board;
	static boolean[][] isVisited;
	
	static Stack<Virus> onVirus;
	static List<Virus> virus;
	
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		init();
		
		pickVirus(0,0);
		
		System.out.println(minRes == Integer.MAX_VALUE ? -1 : minRes);
	}
	
	static void pickVirus(int idx, int cnt) {
		if(cnt==M) {
			bfs();
			
			initVisited();
			return;
		}
		
		for(int i=idx; i<totalVirus; ++i) {
			onVirus.push(virus.get(i));
			pickVirus(i+1, cnt+1);
			onVirus.pop();
		}
	}
	
	static void bfs() {
		Queue<Virus> que = new LinkedList<>();
		for(Virus tv : onVirus) {
			que.offer(tv);
			isVisited[tv.r][tv.c]=true;
		}
		
		int curVirusNum=virus.size();
		int cr,cc, nr,nc, time=0, size;
		Virus tv;
		
		if(curVirusNum == targetVirusNum) {
			minRes = Integer.min(minRes, time);
			return;
		}
		
		while(!que.isEmpty()) {
			size = que.size();
			while(--size>=0) {
				tv = que.poll();
				cr=tv.r; cc=tv.c;
				for(int d=0; d<4; ++d) {
					nr=cr+drow[d]; nc=cc+dcol[d];
					if(isOut(nr, nc) || isVisited[nr][nc] || isWall(nr, nc)) continue;
					
					if(board[nr][nc]==0) curVirusNum++;
					
					if(curVirusNum == targetVirusNum) {
						minRes = Integer.min(minRes, time+1);
						return;
					}
					isVisited[nr][nc]=true;
					que.offer(new Virus(nr, nc));
					
				}
			}
			time++;
		}
		
		if(curVirusNum == targetVirusNum)
			minRes = Integer.min(minRes, time);
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=N || c>=N)
			return true;
		return false;
	}
	
	static boolean isWall(int r, int c) {
		if(board[r][c]==1)
			return true;
		return false;
	}
	
	static void initVisited() {
		isVisited = new boolean[N][N];
	}
	
	static void init() throws Exception {
		minRes = Integer.MAX_VALUE;
		onVirus = new Stack<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		isVisited = new boolean[N][N];
		virus = new ArrayList<>();
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
				switch (board[row][col]) {
				// blank
				case 0:
					targetVirusNum++;
					break;
				// Virus
				case 2:
					virus.add(new Virus(row, col));
					totalVirus++;
					targetVirusNum++;
					break;
				default:
					// Do Nothing
					break;
				}
			}
		}
	}
	
	static class Virus {
		int r,c;
		Virus(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
}
