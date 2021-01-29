/*
 * 19300KB
 * 176ms
 * 7H
 * compare i,j오타없나잘보기....
 * 
 * 1. 출발지~도착지 거리 구해두기
 * 2. 출발~ 도착가는길이 막혀있으면 -1 출력
 * 3. pq로 현 위치에서 가장 가까운 승객 찾기
 * 4. 연료 계산 및 연료 부족한지 체크하기
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

public class Main_B_G4_19238_스타트택시_정세린 {
	static int N, M, fuel, starti, startj;
	static int[][] map;
	static int[][] destination;	// 도착지들 정보 [cusNum][좌표]   0: i, 1: j
	static int[] distance;	// 승객 도착까지의거리[cusNum]
	static boolean[][] visited;
	static final int WALL = -10000;
	
	static int[][] dh = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};	
	static Queue<Point> q = new LinkedList<Point>();
	static Queue<Point> pq = new PriorityQueue<Point>(new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			int tmp = o1.i - o2.i;
			if (tmp == 0) return o1.j - o2.j; 
			return tmp;
		}
	});
	static class Point {
		int i;
		int j;
		int dist;
		
		public Point(int i, int j, int dist) {
			this.i = i;
			this.j = j;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		destination = new int[M+1][2];
		distance = new int[M+1];
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
			map[posi][posj] = i;	// 승객 번호 맵에 표시
			destination[i][0] = endi;
			destination[i][1] = endj;
			distance[i] = getDist(posi, posj, endi, endj);	// 출발지~목적지까지 거리
			if (distance[i] == -1) {	// 길이 막혀있으면
				System.out.println(-1);
				return;
			}
		}	// end of input
		
		while (M > 0) {
			// 가장 가까운 승객 데려다 주기
			boolean near = nearPass();
			if (!near) break;
		}
		
		if (M > 0) System.out.println(-1);
		else System.out.println(fuel);
	}
	
	static boolean nearPass() {
		reset();
		q.offer(new Point(starti, startj, 0));
		visited[starti][startj] = true;
		
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				
				if (map[cur.i][cur.j] > 0) {	// 승객이라면
					pq.offer(cur);
				}
				for (int d = 0; d < 4; d++) {
					int ni = cur.i + dh[d][0];
					int nj = cur.j + dh[d][1];
					if (ni <= 0 || ni > N || nj <= 0 || nj > N) continue;
					if (!visited[ni][nj] && map[ni][nj] >= 0) {
						visited[ni][nj] = true;
						q.offer(new Point(ni, nj, cur.dist + 1));
					}
				}
			}
			if (pq.size() > 0) {
				Point near = pq.poll();
				int cusNum = map[near.i][near.j];
				map[near.i][near.j] = 0;
				
				fuel -= near.dist;	// 승객에게 가는데 드는 연료
				if (fuel <= 0) return false;	// 도착하면 승객을 태우고 가야하므로 0보다 커야함

				fuel -= distance[cusNum];	// 승객을 태워주는데 드는 연료
				if (fuel < 0) return false;	// 도착한 시점에서 연료를 채우므로 0이상이면 됨
				
				fuel += distance[cusNum]*2;	// 완료 휴 연료충전
				M--;	// 완료 후 승객 --
				
				starti = destination[cusNum][0];	// 도착지가 출발지가됨
				startj = destination[cusNum][1];
				
				return true;
			}
		}
		return false;
	}
	
	// 승객을 태우로 출발지에서 목적지까지 가는 거리
	static int getDist(int si, int sj, int ei, int ej) {
		q.offer(new Point(si, sj, 0));
		visited[si][sj] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if (cur.i == ei && cur.j == ej) {
				reset();
				return cur.dist;
			}
			
			for(int d = 0; d < 4; d++) {
				int ni = cur.i + dh[d][0];
				int nj = cur.j + dh[d][1];
				if (ni <= 0 || ni > N || nj <= 0 || nj > N) continue;
				if (!visited[ni][nj] && map[ni][nj] >= 0) {
					visited[ni][nj] = true;
					q.offer(new Point(ni, nj, cur.dist + 1));
				}
			}
		}
		reset();
		return -1;
	}
	
	// 초기화
	static void reset() {
		q.clear();
		pq.clear();
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
	}
	
}
