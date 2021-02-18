/*
 * 26504KB
 * 188ms
 * bfs
 * 방문관리 해줘야함
 * 4h
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_S1_15558_점프게임_정세린 {
	static int N, k; // 칸 수, 점프이동거리
	static char[][] map;
	static boolean[][] visited;
	static boolean clear = false;
	
	static class Point{
		int line, pos, time;

		public Point(int line, int pos, int time) {
			this.line = line;
			this.pos = pos;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new char[2][N];	// 0번줄, 1번 칸부터 시작
		visited = new boolean[2][N];
		
		for (int i = 0; i < 2; i++) {
			map[i] = br.readLine().toCharArray();
		} // end of input
		
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(0, 0, -1)); // 0번줄 1번칸부터 시작
		visited[0][0] = true;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			// 탈출 조건
			if (cur.time >= cur.pos || cur.pos < 0) continue;	// 길이 없는 곳
	
			if (cur.pos >= N - 1) { // 탈출 성공
				clear = true;
				break;
			}
			
			// 1. 한칸 앞으로
			if (!visited[cur.line][cur.pos + 1]
					&& map[cur.line][cur.pos + 1] == '1') {
				q.offer(new Point(cur.line, cur.pos + 1, cur.time + 1)); // 라인은 그대로, 한칸 앞으로
				visited[cur.line][cur.pos + 1] = true;
			}
			// 2. 한칸 뒤로
			if (cur.pos > 0 && !visited[cur.line][cur.pos-1]
					&& map[cur.line][cur.pos - 1] == '1') {
				q.offer(new Point(cur.line, cur.pos - 1, cur.time + 1));
				visited[cur.line][cur.pos - 1] = true;
			}
			// 3. 반대편줄로 이동 후 k칸 앞으로
			if (cur.pos + k <= N - 1 && !visited[(cur.line + 1) % 2][cur.pos + k]
					&& map[(cur.line + 1) % 2][cur.pos + k] == '1') {
				q.offer(new Point((cur.line + 1) % 2, cur.pos + k, cur.time + 1));
				visited[(cur.line + 1) % 2][cur.pos + k] = true;
			}
			else if (cur.pos + k > N - 1) {
				clear = true;
				break;
			}
		} // end of while
		
		if (clear) System.out.println(1);
		else System.out.println(0);
	} // end of main
	
}
