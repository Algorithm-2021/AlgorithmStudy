/*
 113908KB
 1004ms
 3h
 1) 공 사이즈 오름차순 정렬
 2) 이전 공들까지의 전체합-이전 공들 중 같은 컬러 공의 합
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

public class Main_B_G3_10800_컬러볼_정세린 {
	
	public static class Ball {	// 컬러볼, {인덱스, 컬러, 사이즈}
		int idx;
		int color;
		int size;
		
		public Ball(int idx, int color, int size) {
			this.idx = idx;
			this.color = color;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// 공의 개수
		Queue<Ball> ball = new PriorityQueue<Ball>(new Comparator<Ball>() {	// 사이즈 오름차순
			@Override
			public int compare(Ball o1, Ball o2) {
				return o1.size - o2.size;
			}
		});
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ball.offer(new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}	// end of input
		
		int[] colorSum = new int[N + 1];	// 색에 따른 사이즈 합을 저장
		int totalSum = 0;	// 전체 사이즈 합을 저장
		int[] res = new int[N];		// 결과 저장
		Queue<Ball> tmp = new LinkedList<Ball>();	// 같은 사이즈의 공들 임시 저장
		
		while(ball.size() > 1) {
			Ball before = ball.poll();	// 앞의 공
			Ball cur = ball.peek();		// 다음공			
			
			if (before.size == cur.size) {	// 이전공과 사이즈가 같다면
				res[cur.idx] = res[before.idx] + colorSum[before.color] - colorSum[cur.color];	// 이전의 결과값 + 이전공 컬러의 합 - 현재 공 컬러의 합
				tmp.offer(before);	// 사이즈가 같은공은 임시로 저장됨
			}
			
			if (before.size < cur.size) {	// 더 큰 사이즈 등장!
				while(!tmp.isEmpty()) {		// 이전에 임시로 저장된 동일 사이즈의 공들을 합산
					Ball b = tmp.poll();
					totalSum += b.size;
					colorSum[b.color] += b.size;
				}
				
				totalSum = totalSum + before.size;
				colorSum[before.color] = colorSum[before.color] + before.size;
				res[cur.idx] = totalSum - colorSum[cur.color];	// 현재 결과 = 전체 합 + 이전 공들 중 같은 컬러의 공 사이즈합
			}
			
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(res[i] + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
}
