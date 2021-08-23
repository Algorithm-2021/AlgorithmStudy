/*

memory : 133660 KB
time : 728 ms


time : 0 Hour 30 Minute

풀이
유니온 파인드로 연결해주다 부모가 같은 정점을 이어줄떄 횟수를 출력


*/

// 출처 : https://www.acmicpc.net/problem/20040
package algo_8월;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G4_20040_사이클게임 {
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = -1;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (!union(a, b)) {
				System.out.println(i + 1);
				return;
			}
		}
		System.out.println(0);
	}

	public static int findset(int a) {
		if (arr[a] == -1) {
			return a;
		}
		return arr[a] = findset(arr[a]);
	}

	public static boolean union(int a, int b) {
		int roota = findset(a);
		int rootb = findset(b);
		if (roota == rootb) {
			return false;
		} else {
			if (roota < rootb) {
				arr[rootb] = roota;
			} else {
				arr[roota] = rootb;
			}
		}
		return true;
	}

}
