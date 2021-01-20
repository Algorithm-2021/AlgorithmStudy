/*
 * 121676KB
 * 344ms
 * 2H
 * 1) 조합으로 벽위치 선정 후 벽세우기
 * 2) bfs로 바이러스 퍼트리기 
 * 3) 안전구역 횟수 세기
 * 4) 최대값 비교 저장
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

public class Main_B_G5_14502_연구소_정세린 {
	
	static int[] combArr = new int[3];	// 벽을 설치할 세곳. i*100 + j
	static ArrayList<Integer> space = new ArrayList<Integer>();	// 벽을 세울 수 있는 공간 저장
	static int N, M;
	static int[][] map, mapCopy;
	static int max = 0;
	static Queue<Integer> virus = new LinkedList<Integer>();
	static Queue<Integer> virusCopy;
	static int[][] dh = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 세로
		M = Integer.parseInt(st.nextToken());	// 가로
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {	// 벽을 세울 수 있는 곳
					space.add(i*100 + j);
				}
				if (map[i][j] == 2) {	// 바이러스가 있는 위치
					virus.offer(i*100 + j);
				}
			}
		}	// end of input
		// 1. 안전구역 3개로 이루어진, 크기가 3인 조합 만들기
		combination(0, space.size(), 3);
		
		System.out.println(max);
	}
	
	static void combination(int idx, int n, int r) {
	    if (r == 0) {
	    	// 기존의 맵과 바이러스 정보를 복사하여 저장
	    	virusCopy = new LinkedList<Integer>();
	    	virusCopy.addAll(virus);
	    	mapCopy = new int[N][M];
	    	for (int i = 0; i < N; i++) {
	    		mapCopy[i] = Arrays.copyOf(map[i], map[i].length);
	    	}
	    	// 2. 바이러스를 퍼트림
	    	spreadVirus(mapCopy, virusCopy);
	    	return;
	    }
	    
	    for (int i = idx; i < n; i++) {
	    	combArr[r - 1] = space.get(i);
	    	combination(i + 1, n, r - 1);
	    }
	}
	
	static void spreadVirus(int[][] mapCopy, Queue<Integer> virusCopy) {
		// 3. 안전구역에 벽세우기
		for (int c = 0; c < 3; c++) {
			int i = combArr[c] / 100;
			int j = combArr[c] % 100;
			mapCopy[i][j] = 1;
		}
		// 4. bfs 바이러스 퍼트리기
		int cur;
		while (!virusCopy.isEmpty()) {
			cur = virusCopy.poll();
			int i = cur / 100;
			int j = cur % 100;
			for (int d = 0; d < 4; d++) {
				int ni = i + dh[d][0];
				int nj = j + dh[d][1];
				if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
				if (mapCopy[ni][nj] == 0) {	// 바이러스가 퍼질 수 있음.
					mapCopy[ni][nj] = 2;
					virusCopy.offer(ni*100 + nj);
				}
			}
		}
		
		// 5. 안전구역 넓이 계산
		int size = safeZone(mapCopy);
		max = (max > size)? max : size;	// 최대 넓이
	}
	
	static int safeZone(int[][] mapCopy) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j< M; j++) {
				if (mapCopy[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

}
