package algo_1월모의고사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_S1_14888_연산자끼워넣기 {
	static int N, Max = Integer.MIN_VALUE, Min = Integer.MAX_VALUE;
	static int op[], arr[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		op = new int[4];
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		int cnt[] = new int[4];
		cal(cnt, 1, arr[0]);
		System.out.println(Max);
		System.out.println(Min);

	}

	private static void cal(int[] cnt, int num, int sum) {
		if (num == N) {
			if (Max < sum) {
				Max = sum;
			}
			if (Min > sum) {
				Min = sum;
			}
			return;
		}
		if (cnt[0] < op[0]) {
			cnt[0]++;
			cal(cnt, num + 1, sum + arr[num]);
			cnt[0]--;
		}
		if (cnt[1] < op[1]) {
			cnt[1]++;
			cal(cnt, num + 1, sum - arr[num]);
			cnt[1]--;
		}
		if (cnt[2] < op[2]) {
			cnt[2]++;
			cal(cnt, num + 1, sum * arr[num]);
			cnt[2]--;
		}
		if (cnt[3] < op[3]) {
			cnt[3]++;
			cal(cnt, num + 1, sum / arr[num]);
			cnt[3]--;
		}
	}
}
