/*
 * memory : 33408 KB
 * time : 452 ms
 * 
 * solve time : 1 Hour 0 Minute
 * 
 * 풀이
 * 유니온 파인드를 이용하여 동맹 속국 관계를 정의하며 추가적으로 가장 낮은 숫자의 국가에 병력의 결과값을 따로 저장한다.
 * -1은 자기 자신을 가리키며 -2는 멸망, 양의 정수는 해당 숫자의 국가와 동맹 관계를 나타낸다.
 * 
 * 
 * 
 * 
 */

package algo_3월2주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_G5_15809_전국시대 {
	static int arr[], soldier[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		soldier = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = -1;
			soldier[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) {
				union(b, c);
			} else {
				war(b, c);
			}
		}
		for (int i = 1; i < N + 1; i++) {
			if (arr[i] == -1) {
				pq.offer(soldier[i]);
			}
		}
		int size = pq.size();
		System.out.println(size);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(pq.poll()).append(' ');
		}
		System.out.println(sb);

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
		if (roota < rootb) {
			arr[rootb] = roota;
			soldier[roota] += soldier[rootb];
		} else {
			arr[roota] = rootb;
			soldier[rootb] += soldier[roota];
		}
	}

	public static void war(int a, int b) {
		int roota = findset(a);
		int rootb = findset(b);
		if (soldier[roota] > soldier[rootb]) {
			soldier[roota] -= soldier[rootb];
			arr[rootb] = roota;
		} else if (soldier[roota] < soldier[rootb]) {
			soldier[rootb] -= soldier[roota];
			arr[roota] = rootb;
		} else {
			arr[roota] = -2;
			arr[rootb] = -2;
		}
	}

}
