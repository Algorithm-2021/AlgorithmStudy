/*
 * memory : 109144 KB
 * time : 640 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * 스택에 탑을 넣으면서 자신보다 작은값이 나올때 빼면서 큰값이 나올때 큰값의 index를 출력하며 스텍에 자신을 저장
 * 
 * tip
 * 최대값을 저장하며 최대값 갱신시 스택 초기화
 * 
 * 
 */

package algo_1월4주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B_G5_2493_탑 {
	static int N, Max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<top> s = new Stack<top>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			top tmp = new top(i, Integer.parseInt(st.nextToken()));
			if (s.isEmpty()) {
				sb.append('0').append(' ');
				s.add(tmp);
				Max = tmp.height;
			} else {
				while (true) {
					if (Max < tmp.height) {
						s = new Stack<top>();
						Max = tmp.height;
						sb.append('0').append(' ');
						s.add(tmp);
						break;
					}
					top out = s.pop();
					if (out.height > tmp.height) {
						s.add(out);
						s.add(tmp);
						sb.append(out.num).append(' ');
						break;
					}

				}
			}
		}
		System.out.println(sb);
	}

	static class top {
		int num;
		int height;

		public top(int num, int height) {
			this.num = num;
			this.height = height;
		}

	}
}
