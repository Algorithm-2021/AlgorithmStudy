/*
 * memory : 251332 KB
 * time : 1096 ms
 * 
 * solve time : 1 Hour
 * 
 * 풀이
 * 트리를 구성하면서 부모노드와 자식노드의 수, 손자노드의 수를 계산하면서 찾아야할 노드의 부모의 부모의 손자 노드의수에서 부모의 자식 노드의 수를 뺀값을 출력
 * 
 * 
 * 
 * 
 */

package algo_2월1주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_G4_9489_사촌 {
	static int N, K, res = 0;
	static cousin arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			boolean end = false;
			int tot = 0;
			String str[] = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			K = Integer.parseInt(str[1]);
			if (N == 0 && K == 0) {
				break;
			}
			arr = new cousin[N];
			str = br.readLine().split(" ");
			arr[0] = new cousin(-1, 0, 0);
			int curp = -1;
			int pre = Integer.parseInt(str[0]);
			if (pre == K) {
				res = 0;
			}
			for (int i = 1; i < N; i++) {
				int tmp = Integer.parseInt(str[i]);
				if (tmp == K) {
					res = i;
					end = true;
				}
				if (pre + 1 == tmp) {
					arr[i] = new cousin(curp, 0, 0);
					arr[curp].chlidren++;
					if (arr[curp].parent != -1) {
						arr[arr[curp].parent].grand++;
					}
					pre++;
				} else {
					curp++;
					arr[i] = new cousin(curp, 0, 0);
					arr[curp].chlidren++;
					if (arr[curp].parent != -1) {
						arr[arr[curp].parent].grand++;
					}
					pre = tmp;
				}
				if (end && (arr[res].parent == -1 || arr[arr[res].parent].parent == -1)) {
					break;
				}
			}
			if (arr[res].parent == -1 || arr[arr[res].parent].parent == -1) {
				sb.append('0').append('\n');
			} else {
				sb.append(arr[arr[arr[res].parent].parent].grand - arr[arr[res].parent].chlidren).append('\n');
			}
		}
		System.out.println(sb);
	}

	public static class cousin {
		int parent;
		int chlidren;
		int grand;

		public cousin(int parent, int chlidren, int grand) {
			this.parent = parent;
			this.chlidren = chlidren;
			this.grand = grand;
		}

	}
}
