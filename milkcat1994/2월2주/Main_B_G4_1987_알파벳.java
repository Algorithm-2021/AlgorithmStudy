import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -알파벳-
 * 1. dfs이용하여 밟고 지나간 알파벳을 비트연산으로 기억하고 있는다.
 * 
 * 메모리 : 15056KB
 * 시간 : 844ms
 * 풀이 시간 : 1H
 */

//출처 : https://www.acmicpc.net/problem/1987
public class Main_B_G4_1987_알파벳 {
	static int R,C, MAX;
	
	static int[][] board;
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		init();
		
		solve(0,0,1,1<<board[0][0]);
		
		System.out.println(MAX);
	}
	
	static void solve(int r, int c, int time, int flag) {
		int nr,nc;
		for(int d=0; d<4; ++d) {
			nr=r+drow[d]; nc=c+dcol[d];
			if(isOut(nr,nc)) continue;
			
			if(isDuplicate(nr,nc,flag)) {
				MAX = Math.max(MAX, time);
				continue;
			}
			solve(nr, nc, time+1, flag | (1<<board[nr][nc]));
		}
	}
	
	static boolean isOut(int r, int c) {
		if(r<0 || c<0 || r>=R || c>=C)
			return true;
		return false;
	}
	
	static boolean isDuplicate(int r, int c, int flag) {
		if((flag & (1<<board[r][c])) > 0)
			return true;
		return false;
	}
	
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		String str;
		for (int row = 0; row < R; ++row) {
			str = br.readLine();
			for (int col = 0; col < C; ++col) {
				board[row][col] = str.charAt(col)-'A';
			}
		}
		
		MAX=1;
	}
}
