/*
 * memory : 11592 KB
 * time : 76 ms
 * 
 * solve time : 1 Hour 30 Minute
 * 
 * 풀이
 * 유니온 파인드를 이용하여 이야기를 아는 사람을 0번 배열에 포함시켜 놓고
 * 각각의 파티를 유니온 파인드를 실행한후 각 파티의 첫번째 사람을 확인하여
 * 0번과 다른 집합일때 +1 카운트를 하여 파티의 수를 센다.
 * 
 * 
 * 
 * 
 */

package algo_3월4주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G4_1043_거짓말 {
	static int N, M;
	static int arr[], party[];
	static int min = -1;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			arr[i] = -1;
		}
		party = new int[M];
		st = new StringTokenizer(br.readLine());
		int known = Integer.parseInt(st.nextToken());
		for (int i = 0; i < known; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			union(0, tmp);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if (num == 0) {
				party[i] = -1;
			} else {
				int tmpA = Integer.parseInt(st.nextToken());
				party[i] = tmpA;
				for (int j = 0; j < num - 1; j++) {
					int tmpB = Integer.parseInt(st.nextToken());
					union(tmpA, tmpB);
				}
			}
		}

		for (int i = 0; i < M; i++) {
			if (party[i] == -1) {
				answer++;
				continue;
			}
			int person = findset(party[i]);
			if (person != 0) {
				answer++;
			}
		}
		System.out.println(answer);
	}

	public static int findset(int a) {
		if (arr[a] == -1) {
			return a;
		}
		arr[a] = findset(arr[a]);
		return arr[a];
	}

	public static void union(int a, int b) {
		int roota = findset(a);
		int rootb = findset(b);
		if (roota == rootb) {
			return;
		} else if (roota < rootb) {
			arr[rootb] = roota;
		} else {
			arr[roota] = rootb;
		}

	}
}
