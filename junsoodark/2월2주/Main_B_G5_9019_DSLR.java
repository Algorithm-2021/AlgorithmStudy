/*
 * memory : 305680 KB
 * time : 2860 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * DSLR을 bfs로 각각 4가지 방법으로 탐색하여 결과값을 찾아 경로를 출력한다.
 * 
 * 
 * 
 * 
 */

package algo_2월2주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_B_G5_9019_DSLR {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			boolean visited[] = new boolean[10000];
			String str[] = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			Queue<num> q = new LinkedList<num>();
			num input = new num(a);
			q.offer(input);
			visited[a] = true;
			while (true) {
				num tmp = q.poll();
				int tmpnum = tmp.n;
				int out;
				out = dd(tmpnum);
				if (out == b) {
					sb.append(tmp.str).append('D').append('\n');
					break;
				}
				if (!visited[out]) {
					visited[out] = true;
					q.offer(new num(out, tmp.str + "D"));
				}
				out = ss(tmpnum);
				if (out == b) {
					sb.append(tmp.str).append('S').append('\n');
					break;
				}
				if (!visited[out]) {
					visited[out] = true;
					q.offer(new num(out, tmp.str + "S"));
				}
				out = left(tmpnum);
				if (out == b) {
					sb.append(tmp.str).append('L').append('\n');
					break;
				}
				if (!visited[out]) {
					visited[out] = true;
					q.offer(new num(out, tmp.str + "L"));
				}
				out = right(tmpnum);
				if (out == b) {
					sb.append(tmp.str).append('R').append('\n');
					break;
				}
				if (!visited[out]) {
					visited[out] = true;
					q.offer(new num(out, tmp.str + "R"));
				}
			}
		}
		System.out.println(sb);
	}

	static class num {
		int n;
		String str;

		public num(int n) {
			this.n = n;
			this.str = new String();
		}

		public num(int n, String str) {
			super();
			this.n = n;
			this.str = str;
		}

	}

	static int dd(int in) {
		int out = in * 2;
		out = out % 10000;
		return out;
	}

	static int ss(int in) {
		int out = in - 1;
		if (out < 0) {
			out = 9999;
		}
		return out;
	}

	static int left(int in) {
		int d1, d2, d3, d4;
		d1 = in / 1000;
		d2 = (in - d1 * 1000) / 100;
		d3 = (in - d1 * 1000 - d2 * 100) / 10;
		d4 = (in - d1 * 1000 - d2 * 100 - d3 * 10);
		int out = d2 * 1000 + d3 * 100 + d4 * 10 + d1;
		return out;
	}

	static int right(int in) {
		int d1, d2, d3, d4;
		d1 = in / 1000;
		d2 = (in - d1 * 1000) / 100;
		d3 = (in - d1 * 1000 - d2 * 100) / 10;
		d4 = (in - d1 * 1000 - d2 * 100 - d3 * 10);
		int out = d4 * 1000 + d1 * 100 + d2 * 10 + d3;
		return out;
	}
}
