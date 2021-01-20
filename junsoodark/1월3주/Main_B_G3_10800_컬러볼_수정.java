/*
 * memory : 86540 KB
 * time : 1008 ms
 * 
 * solve time : 3 Hour
 * 
 * 풀이
 * 공을 우선순위 큐에 넣어서 사이즈별 색별로 꺼내면서 배열에 각 사이즈별 누적합과 총 누적합을 계산
 * 누적합 - 같은 색의 누적합 = 결과값
 * 
 * tip
 * 공의 크기가 커질때만 누적합을 변경하고 같을때는 임시합에 저장
 * 색과 크기가 모두 같을 때에는 색별 누적합이 중복 계산 되기 때문에 같은 크기 같은 사이즈의 값을 따로 저장하여 중복 계산 제거 
 * 
 */

package algo_1월3주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_G3_10800_컬러볼_수정 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int s[] = new int[N + 1];
		int tots = 0;
		int tmps = 0;
		int cursize = 0;
		int curcolor = 0;
		int sameColor = 0;
		PriorityQueue<ball> pq1 = new PriorityQueue<ball>(new Comparator<ball>() {

			@Override
			public int compare(ball o1, ball o2) {
				if (o1.size == o2.size) {
					return o1.color - o2.color;
				}
				return o1.size - o2.size;
			}
		});
		PriorityQueue<ball> pq2 = new PriorityQueue<ball>(new Comparator<ball>() {

			@Override
			public int compare(ball o1, ball o2) {
				return o1.num - o2.num;
			}
		});

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ball ball = new ball(i, a, b);
			pq1.add(ball);
		}
		ball tBall = pq1.poll();
		tmps = tmps + tBall.size;
		cursize = tBall.size;
		s[tBall.color] += tBall.size;
		tBall.res = 0;
		curcolor = tBall.color;
		sameColor = tBall.size;
		pq2.offer(tBall);
		while (!pq1.isEmpty()) {
			ball tmpBall = pq1.poll();
			if (cursize < tmpBall.size) {
				curcolor = tmpBall.color;
				sameColor = tmpBall.size;
				cursize = tmpBall.size;
				tots = tmps;
				tmps += tmpBall.size;
				tmpBall.res = tots - s[tmpBall.color];
				s[tmpBall.color] += tmpBall.size;
				pq2.offer(tmpBall);
			} else {
				if (curcolor != tmpBall.color) {
					curcolor = tmpBall.color;
					sameColor = tmpBall.size;
					tmps += tmpBall.size;
					tmpBall.res = tots - s[tmpBall.color];
					s[tmpBall.color] += tmpBall.size;
					pq2.offer(tmpBall);
				} else {
					s[tmpBall.color] += tmpBall.size;
					sameColor += tmpBall.size;
					tmpBall.res = tots - s[tmpBall.color] + sameColor;
					tmps += tmpBall.size;
					pq2.offer(tmpBall);
				}
			}

		}
		while (!pq2.isEmpty()) {
			ball balltmp = pq2.poll();
			sb.append(balltmp.res).append('\n');
		}
		System.out.println(sb);
	}

	static class ball {
		int num;
		int color;
		int size;
		int res;

		ball(int num, int color, int size) {
			this.num = num;
			this.color = color;
			this.size = size;
		}
	}
}
