/*
 * 15668KB
 * 164ms
 * 1H
 * bfs사용.
 * 순간이동은 0초가 걸리기 떄문에 순간이동 우선으로 처리해야함
 * => pq사용.
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_13549_숨바꼭질3_정세린 {
	static int MAX = 100000; // N, K의 최댓값
	static class Point {
		int pos, time;

		public Point(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
		int K = Integer.parseInt(st.nextToken()); // 동생의 위치
		int[] time = new int[MAX + 1]; // 0 ~ MAX까지 좌표
		Arrays.fill(time, Integer.MAX_VALUE); // 최소 시간을 구하기 위해 최대값을 초기화
		Queue<Point> pq  = new PriorityQueue<Point>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.time - o2.time; // 순간이동은 0초가 걸리기 때문에 time이 더 적을것.
			}
		});
		
		pq.offer(new Point(N, 0));
		time[N] = 0;
		
		while(!pq.isEmpty()) {
			Point cur = pq.poll();
			if (cur.pos == K) {
				System.out.println(cur.time);
				return;
			}
			
			// 1. 앞으로 한칸 : 1초
			int go = cur.pos + 1;
			if (go <= MAX && time[go] > cur.time + 1) {
				time[go] = cur.time + 1;
				pq.offer(new Point(go, time[go]));
			}
			// 2. 뒤로 한칸 : 1초
			int back = cur.pos - 1;
			if (back >= 0 && time[back] > cur.time + 1) {
				time[back] = cur.time + 1;
				pq.offer(new Point(back, time[back]));
			}
			
			// 3. 순간이동 *2 : 0초
			int warp = cur.pos * 2;
			if (warp <= MAX && time[warp] > cur.time + 0) {
				time[warp] = cur.time + 0; 
				pq.offer(new Point(warp, time[warp]));
			}
		} // end of while
		
		System.out.println(time[K]);
	} // end of main

}
