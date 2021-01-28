/*
 * 118824KB
 * 816ms
 * 1H
 * bfs
 * 1. 상근이 bfs로 이동
 * 2. 불이 bfs로 이동
 * 3. 상근이 탈출했는지 확인
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_5427_불_정세린 {
	
	static int w, h;
	static int[][] dh = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static char[][] map;
	static class Point{
		int i;
		int j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st; 
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			Queue<Point> man = new LinkedList<Point>();		// 상근이 bfs를 위한 큐
			Queue<Point> fire = new LinkedList<Point>();	// 불 bfs를 위한 큐
			int time = 1;	// 탈출하는데 걸리는 시간
			
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '@') man.add(new Point(i, j));	// 초기 상근이의 위치를 담음
					if (map[i][j] == '*') fire.add(new Point(i, j));	// 초기 불의 위치를 담음
				}
			}	// end of input
			
			boolean escape = false;	// 탈출 여부
			L:while(!man.isEmpty()) {	// 상민이가 살아있는동안
				// 상근이가 이동함 bfs
				int sizem = man.size();
				while(sizem-- > 0) {
					Point curs = man.poll();	// 상근이의 현재 위치
					if (map[curs.i][curs.j] == '*') continue;	// 불에 탄 상근이 pass
					if (escape = isOut(curs.i, curs.j)) break L;	// 탈출에 성공함
					
					for (int d = 0; d < 4; d++) {
						int ni = curs.i + dh[d][0];
						int nj = curs.j + dh[d][1];
						if (ni < 0 || ni >= h || nj < 0 || nj >= w) continue;
						if (map[ni][nj] == '.') {	// 상근이가 갈 수 있는 빈공간이라면
							man.offer(new Point(ni, nj));	// 상근이의 다음 위치를 push
							map[ni][nj] = '@';	// 상근이가 이동함
						}
					}
				}
				
				// 불이 번짐
				int sizef = fire.size();
				while (sizef-- > 0) {
					Point curf = fire.poll();
				
					for (int d = 0; d < 4; d++) {
						int ni = curf.i + dh[d][0];
						int nj = curf.j + dh[d][1];
						if (ni < 0 || ni >= h || nj < 0 || nj >= w) continue;
						if (map[ni][nj] == '.' || map[ni][nj] == '@') {	// 불이 번질 수 있다면
							fire.offer(new Point(ni, nj));
							map[ni][nj] = '*';	// 불이 옮겨붙음
						}
					}
				}
				
				time++;	// 시간 추가
			}
			
			if (escape) System.out.println(time);
			else System.out.println("IMPOSSIBLE");
		}
	}
	
	static boolean isOut(int i, int j) {	// 탈출 했는가?
		if (i == 0 || i == h-1 || j == 0 || j == w-1) 
			return true;	// 맵의 가장자리에 도달하면 탈출완료
		return false;
	}

}
