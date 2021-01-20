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
