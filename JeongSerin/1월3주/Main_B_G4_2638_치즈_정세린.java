/*
 * 15988KB
 * 176ms
 * 1H 30m
 * 1. dfs(0, 0)로 탐색 시작: 가장자리엔 치즈가 없다-조건
 *    - 0일경우 방문 처리 후 다음 위치 방문
 *    - 1일 경우 cnt[i][j]++ (해당 위치의 치즈가 닿는 공기의면적이 1증가)
 *    cnt[i][j] == 2이면 좌표정보 큐에 담음(동시에 녹아야하므로 나중에 한꺼번에 처리)
 * 	  visited[i][j] = false로 (다른 공간(0)에서 치즈로 접근할수 있도록)
 *    return; : 치즈를 방문한 뒤엔 공기가 있던 곳으로 돌아가기 위함. (0 -> 1 -> 0)
 * 2. 큐에 담긴 치즈 모두 녹임
 * 3. 모든 치즈가 녹을 때까지 반복하며 시간을 셈
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_2638_치즈_정세린 {
	static int N, M, cheese;	// N: 세로, M: 가로, cheese: 치즈 수
	static int[][] map;
	static int[][] cnt;	// 치즈에 닿는 공기 접촉 면 세기(치즈를 녹이기에 유효한 접촉면만)
	static boolean[][] visited;
	static int[][] dh = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int time = 0;
	static Queue<Integer> q;	// 같은 시간에 녹을 치즈를 임시로 담아둠
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		q = new LinkedList<Integer>();
		cnt = new int[N][M];	// 치즈가 공기와 닿는 면의 수 저장
		visited = new boolean[N][M];	// 방문 여부
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) cheese++;	// 초기 치즈의 수
			}
		}	// end of input
		
		while (cheese > 0) {
			// 초기화
			for(int i = 0; i < N;i++) {
				Arrays.fill(cnt[i], 0);
				Arrays.fill(visited[i], false);
			}

			dfs(0, 0);	// 녹을 수 있는 치즈 탐색
			melting();	//	치즈 녹이기
			time++;	// 시간 추가
		}
		
		System.out.println(time);
	}
	
	static void dfs(int i, int j) {
		visited[i][j] = true;
		if (map[i][j] == 1) {
			cnt[i][j]++;	// 공기와의 접촉면
			if (cnt[i][j] == 2) q.offer(i * 1000 + j);	// 접촉면이 2개가 되면 동일 시간에 녹아야할 치즈
			
			visited[i][j] = false;	// 공기 부분만 방문처리.
			return;	// 치즈를 만나면 공기 부분으로 돌아감
		}
		
		for (int d = 0; d < 4; d++) {
			int ni = i + dh[d][0];
			int nj = j + dh[d][1];
			if (ni < 0 || ni >= N || nj < 0 || nj >= M) continue;
			if (!visited[ni][nj]) dfs(ni, nj);	// 근처 탐색
		}
	}
	
	static void melting() {
		while (!q.isEmpty()) {
			int pos = q.poll();
			map[pos/1000][pos%1000] = 0;	// 치즈가 녹음
			cheese--;
		}
	}
	
}
