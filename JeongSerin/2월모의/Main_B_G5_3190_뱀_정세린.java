/*
 * 12212KB
 * 84ms
 * 1h 22m
 * 사과의 위치는 2, 뱀은 1
 * 큐를 사용하여 뱀길이 관리
 * 방향전환은 dh, D(우로 90도): 인덱스+방향/ L: -방향
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_3190_뱀_정세린 {
	static int[][] dh = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static class Point { // 뱀의 위치좌표
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 맵 크기
		int[][] map = new int[N+1][N+1]; // 시작칸은 (1,1)
		int K = Integer.parseInt(br.readLine()); // 사과의 개수
		StringTokenizer st = null;
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2; // 사과의 위치
		}
		int L = Integer.parseInt(br.readLine()); // 방향전환 수
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(1, 1)); // 시작 위치
		map[1][1] = 1; // 뱀 표시
		int dir = 0; // 초기 방향
		int posi = 1; // 뱀 머리의 위치
		int posj = 1;
		int time = 0; // 시간
		boolean end = false; // 게임 오버 여부
		
		L:for (int l = 0; l < L; l++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 이동 (방향 전환 시간이 오기 전까지 이동하는 수)
			int move = Integer.parseInt(st.nextToken()) - time; // 움직이는 시간
			
			for (int i = 0; i < move; i++) { // move만큼 이동
				time++;
				posi = posi + dh[dir][0]; // 뱀머리 위치 변경
				posj = posj + dh[dir][1]; // 뱀머리 위치 변경
				
				if (posi <= 0 || posi > N || posj <= 0 || posj > N) {
					end = true; // 게임 오버
					break L; // 벽에 부딫힘
				}
				// 뱀의 몸통과 만남
				if (map[posi][posj] == 1) {
					end = true; // 게임 오버
					break L;
				}

				// 사과를 만남
				if (map[posi][posj] == 2)  // 사과를 먹으면
					q.offer(new Point(posi, posj)); // 길이만 늘어남.
				
				// 빈공간을 지남
				if (map[posi][posj] == 0) { // 빈공간을 지나면
					q.offer(new Point(posi, posj)); // 길이가 늘어남
					Point tail = q.poll(); // 뱀 꼬리 위치 비워줌
					map[tail.i][tail.j] = 0;
				}
				map[posi][posj] = 1; // 뱀 표시
				
			} // end of move
			
			// 방향전환
			char turn = st.nextToken().charAt(0); // L: 왼쪽으로 90도회전, D: 오른쪽으로
			if (turn == 'D')  // 오른쪽으로 90도
				dir = (dir + 1) % 4;
			if (turn == 'L') { // 왼쪽으로 90도 방향전환
				dir = dir - 1;
				if (dir == -1) dir = 3;
			}
		}
		
		if (!end) { // 게임이 오버된게 아니면 나머지 직선 이동
			while(!end) {
				time++;
				posi = posi + dh[dir][0];
				posj = posj + dh[dir][1];
				// 벽에 부딫힘
				if (posi <= 0 || posi > N || posj <= 0 || posj > N) end = true;
				// 몸통을 만남
				if (map[posi][posj] == 1) end = true;
			}
		}
		
		System.out.println(time);
	} // end of main

}
