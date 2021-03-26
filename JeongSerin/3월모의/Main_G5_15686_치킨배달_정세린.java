/*
 * 32340KB
 * 184ms
 * 1H 10m
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G5_15686_치킨배달_정세린 {
	static int N; // 맵의 크기 N*N (좌표는 1부터 시작)
	static int M; // 선택하는 치킨집 수
	static int min = Integer.MAX_VALUE; // 치킨거리합의 최소값
	static int map[][];
	static int mapCopy[][];
	static ArrayList<Point> house = new ArrayList<>(); // 집의 좌표 
	static ArrayList<Point> chicken = new ArrayList<>(); // 치킨집의 좌표
	static int[][] dh = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int[] selected;
	static class Point {
		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	} // end of Point class
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		mapCopy = new int[N+1][N+1];
		selected = new int[M];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) house.add(new Point(i, j)); // 집
				if (map[i][j] == 2) chicken.add(new Point(i, j)); // 치킨집
			}
		} // end of input
		
		// 1. 치킨집 중 M개 고르기
		combination(0, chicken.size(), M);
		
		System.out.println(min);
	} // end of main()

	static void combination(int idx, int n, int r) {
		if (r == 0) {
//			System.out.println(Arrays.toString(selected));
			// 2. 치킨거리 구하기
			getDistance();
			return;
		}
		
		for (int i = idx; i < n; i++) {
			selected[r - 1] = i;
			combination(i + 1, n, r - 1);
		}
		
	} // end of combination()
	
	static void getDistance() {
		for (int i = 0; i <= N; i++) mapCopy[i] = Arrays.copyOf(map[i], map[i].length);
		for (int i = 0; i < M; i++) {
			Point selectedChicken = chicken.get(selected[i]);
			mapCopy[selectedChicken.i][selectedChicken.j] = 3; // 선택된 치킨집은 3으로 표시
		}
		
		int sum = 0;
		for (int i = 0; i < house.size(); i++) {
			Point home = house.get(i);
			
			int minDist = Integer.MAX_VALUE;
			for (int c = 0; c < M; c++) { // 선택된 치킨집
				Point chick = chicken.get(selected[c]);
				int dist = Math.abs(home.i - chick.i) + Math.abs(home.j - chick.j);
				minDist = Integer.min(minDist, dist);
			}
			sum += minDist;
		} // end of for house
		
		min = Integer.min(min, sum);
	} // end of getDistance
	
}
