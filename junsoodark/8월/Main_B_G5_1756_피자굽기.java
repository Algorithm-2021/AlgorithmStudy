/*

memory : 83700 KB
time : 508 ms


time : 0 Hour 40 Minute

풀이
1. 오븐의 크기를 바로 위의 크기보다 클 경우 바로 위의 크기와 같은 크기로 입력을 받는다.
2. 피자를 아래부터 채워나가며 시작 위치를 마지막 넣은 위치부터 시작한다.

복잡도 : O(d)


*/

// 출처 : https://www.acmicpc.net/problem/1756
package algo_8월;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G5_1756_피자굽기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		if (d < n) {
			System.out.println(0);
			return;
		}
		int oven[] = new int[d];
		int pizza[] = new int[n];
		st = new StringTokenizer(br.readLine());
		int max = 1000000000;
		for (int i = 0; i < d; i++) {
			int in = Integer.parseInt(st.nextToken());
			if (in >= max) {
				oven[i] = max;
			} else {
				max = in;
				oven[i] = max;
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			pizza[i] = Integer.parseInt(st.nextToken());
		}
		int pos = d - 1;
		for (int i = 0; i < n; i++) {
			for (; pos >= 0; pos--) {
				if (pizza[i] > oven[pos]) {
					continue;
				} else {
					break;
				}
			}
			pos--;
		}
		if (pos < 0) {
			System.out.println(0);
			return;
		}
		System.out.println(pos + 2);
	}
}
