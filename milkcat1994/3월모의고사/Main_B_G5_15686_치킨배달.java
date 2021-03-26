package s_20210326;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -치킨 배달-
 * combination 범위 지정 실수
 * 
 * 메모리 : 15204KB
 * 시간 : 192ms
 * 풀이 시간 : 40M
 */

//출처 : https://www.acmicpc.net/problem/15686
public class Main_B_G5_15686_치킨배달 {
	static final int HOUSE = 1;
	static final int CHICKEN = 2;
	
	static List<Point> chicken;
	static List<Point> house;
	static int[][] memo;
	
	static int N,M;
	static int minRes = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		int[][] board = new int[N][N];
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
				switch (board[row][col]) {
				case CHICKEN:
					chicken.add(new Point(row, col));
					break;
					
				case HOUSE:
					house.add(new Point(row, col));
					break;
					
				case 0:
				default:
					// do nothing
					break;
				}
			}
		}
		
		memo = new int[house.size()][chicken.size()];
		for(int h=0; h<house.size(); ++h) {
			for(int c=0; c<chicken.size(); ++c) {
				memo[h][c] = house.get(h).getDist(chicken.get(c));
			}
		}
		
		combination(0, 0, 0);
		
		System.out.println(minRes);
	}
	
	static void combination(int idx, int selected, int cnt) {
		if(cnt == M) {
			minRes = Math.min(minRes, getChickenDist(selected));
			return;
		}
		
		for(int i=idx; i<chicken.size(); ++i) {
			if((selected & 1<<i) > 0) continue;
			
			combination(i+1, selected | 1<<i, cnt+1);
		}
	}

	static int getChickenDist(int selected) {
		int dist=0;
        
		for(int h=0; h<house.size(); ++h) {
			int min = Integer.MAX_VALUE;
			for(int c=0; c<chicken.size(); ++c) {
				if((selected & 1<<c) < 1) continue;
				min = Math.min(min, memo[h][c]);
			}
			dist+=min;
		}
		return dist;
	}
	
	static class Point {
		int r,c;
		
		Point(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		int getDist(Point p){
			return Math.abs(this.r-p.r) + Math.abs(this.c-p.c);
		}
	}
}
