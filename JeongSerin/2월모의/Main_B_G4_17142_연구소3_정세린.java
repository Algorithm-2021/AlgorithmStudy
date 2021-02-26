/*
 * 45696KB
 * 252ms
 * 1H 30m
 * 1. 조합으로 퍼트릴 바이러스 선택
 * 2. 큐로 시간 계산
 * 3. 비활성 바이러스는 표시해두고 시간 계산 시 스킵 해야함
 *    (마지막에 비활성 바이러스만 남아있을때 더 퍼트리지 않아도 모두 감염된 상태이기 때문)
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_17142_연구소3_정세린 {
	static int N, M;
	static ArrayList<Point> virus = new ArrayList<Point>(); // 비활성 바이러스 위치정보
	static int[] selectedV; // 선택된 활성 바이러스
	static int[][] map;
	static int[][] mapCopy;
	static boolean[][] visited;
	static int minTime = Integer.MAX_VALUE; // 퍼트리는데 걸린 최소 시간
	static int[][] dh = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static class Point {
		int i, j, time;
		boolean none = false; // 비활성바이러스의 경우

		public Point(int i, int j, int time) { // 활성 바이러스의 위치
			this.i = i;
			this.j = j;
			this.time = time;
		}
		
		public Point(int i, int j, int time, boolean none) { // 비활성 바이러스 생성자
			this.i = i;
			this.j = j;
			this.time = time;
			this.none = none;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		mapCopy = new int[N][N];
		visited = new boolean[N][N];
		M = Integer.parseInt(st.nextToken());
		selectedV = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 비활성 바이러스 위치 기록
				if (map[i][j] == 2) virus.add(new Point(i, j, 0));				
			}
		} // end of input
		
		// 이미 다 감염된 상태의 경우 0을 출력 후 return
		boolean allVirus = true;;
		for (int i = 0; i < N; i++) { // 바이러스가 번지지 않은 곳 있나 체크
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					allVirus = false;
				}
			}
		}
		if (allVirus) {
			System.out.println(0);
			return;
		}
		
		// 1. 조합으로 활성화할 바이러스 M개 선택
		combination(0, virus.size(), M);
		
		// 어떤 선택을 해도 전체에 바이러스를 감염시킬 수 없음
		if (minTime == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(minTime);
	} // end of main
	
	static void combination(int idx, int n, int r) {
		if (r == 0) { // 조합 만들기 성공
			// 맵을 복사해서 사용
			for (int i = 0; i < map.length; i++) {
				mapCopy[i] = Arrays.copyOf(map[i], map[i].length);
				// 방문 초기화
				Arrays.fill(visited[i], false);
			}
			// 2. 바이러스를 퍼트림
			int time = spreadVirus();
			if (time > 0) minTime = Integer.min(minTime, time);
			
			return;
		}
		
		for (int i = idx; i < n; i++) {
			selectedV[r - 1] = i;
			combination(i + 1, n, r - 1);
		}
		
	} // end of combination
	
	static int spreadVirus() {
		Queue<Point> q = new LinkedList<Point>();
		for (int i = 0; i < selectedV.length; i++) { // 초기 활성 바이러스
			q.offer(virus.get(selectedV[i]));
			visited[virus.get(selectedV[i]).i][virus.get(selectedV[i]).j] = true;
			mapCopy[virus.get(selectedV[i]).i][virus.get(selectedV[i]).j] = -1; // 바이러스
		}
		
		int spreadTime = 0;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if (!cur.none) { // 비활성 바이러스가 마지막에 남은경우 시간계산을 안하기 위함
				spreadTime = Integer.max(spreadTime, cur.time);
			}
			
			for (int d = 0; d < 4; d++) {
				int ni = cur.i + dh[d][0];
				int nj = cur.j + dh[d][1];
				
				if (ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj]) continue; // 범위 초과 또는 이미 방문한곳
				if (mapCopy[ni][nj] == 1) continue; // 벽은 지나갈 수 없음
				
				// 비활성 바이러스의 경우 시간에서 제하기 위함
				if (mapCopy[ni][nj] == 2) q.offer(new Point(ni, nj, cur.time + 1, true));
				// 그냥 빈칸이면 상관X
				else q.offer(new Point(ni, nj, cur.time + 1));
				
				visited[ni][nj] = true;
				mapCopy[ni][nj] = -1;
			}
			
		}
		
		for (int i = 0; i < N; i++) { // 바이러스가 번지지 않은 곳 있나 체크
			for (int j = 0; j < N; j++) {
				if (mapCopy[i][j] == 0) {
					spreadTime = -1;
				}
			}
		}
		
		return spreadTime;
	}

}
