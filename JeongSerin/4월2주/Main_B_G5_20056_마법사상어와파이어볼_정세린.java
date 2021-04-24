/*
 * 30284KB
 * 388ms
 * 1H 50m
 * PriorityQueue사용(같은위치 파이어볼을 알기위함), Queue사용
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_20056_마법사상어와파이어볼_정세린 {
	static class FireBall {
		int r, c; // 행열
		int m, s, d; // 질량, 속력, 방향
		
		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		
	}
	static int N, M, K;
	static int[][] dh = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static Queue<FireBall> beforeMove = new LinkedList<FireBall>();
	static PriorityQueue<FireBall> moved = new PriorityQueue<FireBall>(new Comparator<FireBall>() {
		@Override
		public int compare(FireBall o1, FireBall o2) {
			int rTmp = o1.r - o2.r;
			int cTmp = o1.c - o2.c;
			if (rTmp == 0) return cTmp;
			return rTmp;
		};
	});


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 판의 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 이동명령 수
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			beforeMove.offer(new FireBall(r, c, m, s, d));
		} // end of input
		int k = K;
		while (k-- > 0) {
			move();
			divide();
		}
		
		int sumM = 0;
		while (!beforeMove.isEmpty()) 
			sumM += beforeMove.poll().m;
		
		System.out.println(sumM);
		
	} // end of main
	
	static void move() { // 불이 이동
		while (!beforeMove.isEmpty()) {
			FireBall fire = beforeMove.poll();
			int rMove = (fire.r + dh[fire.d][0] * fire.s) % N;
			int cMove = (fire.c + dh[fire.d][1] * fire.s) % N;
			fire.r = rMove < 1 ? N + rMove  : rMove;
			fire.c = cMove < 1 ? N + cMove  : cMove;
			moved.offer(fire);
		}
	} // end of move()
	
	static void divide() { // 같은칸에 있는 파볼이 나눠짐
		int[] allOddEven = {0, 2, 4, 6}; // 모두 짝수거나 홀수일때 방향
		int[] notAllOddEven = {1, 3, 5, 7}; 
		
		while (!moved.isEmpty()) {
			FireBall cur = moved.poll();
			int sumM = cur.m;
			int sumS = cur.s;
			int cnt = 1;
			int oddCnt = cur.d % 2; // 홀수방향 개수
			
			while (!moved.isEmpty() && 
					cur.r == moved.peek().r && cur.c == moved.peek().c) { // 같은 칸에 있는 파이어볼
				FireBall next = moved.poll();
				sumM += next.m;
				sumS += next.s;
				cnt++;
				oddCnt += next.d % 2;
			}
			
			if (cnt == 1) { // 같은칸에 있는 다른 파이어볼이 없음 
				beforeMove.add(cur);
				continue;
			}
			
			int nextM = sumM / 5;
			if (nextM <= 0) continue; // 질량이 0
			int nextS = sumS / cnt;
			if (oddCnt == 0 || oddCnt == cnt) { // 모두 홀수이거나 짝수이면
				for (int nextD : allOddEven) 
					beforeMove.add(new FireBall(cur.r, cur.c, nextM, nextS, nextD));
			}else {
				for (int nextD : notAllOddEven) 
					beforeMove.add(new FireBall(cur.r, cur.c, nextM, nextS, nextD));
			}
		} // end of while
		
	} // end of divide

}
