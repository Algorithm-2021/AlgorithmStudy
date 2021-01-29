/*
 * 틀림
 * 맵에 손님 번호 입력하고 시작 (1~M)
 * 벽은 WALL = -10000으로 바꾸고 시작
 * 방문관리 필요
 * dh = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};	// 1순위 행번호가 작은것, 2순위 열번호가 작은 것
 * 1. bfs 탐색 map[i][j] > 0 이면(손님) 손님 번호 반환 cusNum, 큐 비우고 현재 위치 add
 * 2. bfs 탐색 i, j 가 destination[cusNum] 에있는 좌표값과 동일 (목적지 도달)
 *    연료 더해주고 M-- 
 *    큐 비우고 현재 위치 add.
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ING_Main_B_G4_19238_스타트택시_정세린2 {
	static int N, M, fuel, starti, startj;
	static int[][] map;
	static int[][] dest;	// [cusNum][좌표]
	static boolean[][] visited;
	
	// 안됨 찾은 손님 size안에서 pq에 담아서 비교해서뺴기
	static int[][] dh = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};	// 1순위 행번호가 작은것, 2순위 열번호가 작은 것
	static final int WALL = -10000;
	static Queue<Point> q = new LinkedList<Point>();
	static Queue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			int tmp = o1.i - o1.j;
			if (tmp == 0) return o2.i - o2.j; 
			return tmp;
		}
	});
	static class Point {
		int i;
		int j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		dest = new int[M+1][2];
		visited = new boolean[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {	// 맵 정보
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) map[i][j] = WALL;	
			}
		}
		
		st = new StringTokenizer(br.readLine());
		starti = Integer.parseInt(st.nextToken());
		startj = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= M; i++) {	// 손님과 도착지 정보
			st = new StringTokenizer(br.readLine());
			int posi = Integer.parseInt(st.nextToken());
			int posj = Integer.parseInt(st.nextToken());
			int endi = Integer.parseInt(st.nextToken());
			int endj = Integer.parseInt(st.nextToken());
			map[posi][posj] = i;
			dest[i][0] = endi;
			dest[i][1] = endj;
		}	// end of input
		
		
		while (M > 0) {
			Point cust = findCus(starti, startj);
			if (cust == null) break;	// 접근 불가
			int custNum = map[cust.i][cust.j];
			starti = cust.i;
			startj = cust.j;
			map[starti][startj] = 0;
			
			for (int i = 0; i < visited.length; i++) Arrays.fill(visited[i], false);

			Point nearDest = findDest(starti, startj, custNum);
			if (nearDest == null) break;	// 접근 불가
			starti = nearDest.i;
			startj = nearDest.j;
			
			for (int i = 0; i < visited.length; i++) Arrays.fill(visited[i], false);
			
		}
		
		if (M == 0) System.out.println(fuel);
		else System.out.println(-1);
	}
	
	static Point findCus(int starti, int startj) {
		visited[starti][startj] = true;
		q.offer(new Point(starti, startj));	// 시작 위치
		
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				
				if (map[cur.i][cur.j] > 0) {
					pq.offer(cur);
				}
				
				for (int d = 0; d < 4; d++) {
					int ni = cur.i + dh[d][0];
					int nj = cur.j + dh[d][1];
					if (ni <=0 || ni > N || nj <= 0 || nj > N) continue;
					if (!visited[ni][nj] && map[ni][nj] >= 0) {
						if (fuel <= 0) return null;
						visited[ni][nj] = true;
						q.offer(new Point(ni, nj));
					}
				}
			}// end of while q size
			if (pq.size() > 0) {
				Point near = pq.poll();
				pq.clear();
				q.clear();
				return near;
			}
			fuel--;
		}	// end of while q
		
		return null;
	}
	
	static Point findDest(int starti, int startj, int cusNum) {
		visited[starti][startj] = true;
		q.offer(new Point(starti, startj));	// 시작 위치
		int toDest = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				
				if (cur.i == dest[cusNum][0] && cur.j == dest[cusNum][1]) {
					fuel = fuel + toDest * 2;
					q.clear();
					M--;
					return cur;
				}
				
				for (int d = 0; d < 4; d++) {
					int ni = cur.i + dh[d][0];
					int nj = cur.j + dh[d][1];
					if (ni <=0 || ni > N || nj <= 0 || nj > N) continue;
					if (!visited[ni][nj] && map[ni][nj] >= 0) {
						if (fuel <= 0) return null;
						visited[ni][nj] = true;
						q.offer(new Point(ni, nj));
					}
				}
			}// end of while q size
			fuel--;
			toDest++;
			
		}	// end of while q
		
		return null;
	}

}
