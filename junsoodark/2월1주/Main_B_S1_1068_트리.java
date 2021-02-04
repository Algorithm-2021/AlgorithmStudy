package algo_2월1주;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B_S1_1068_트리 {
	static int N;
	static node nod[];
	static int res = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nod = new node[N];
		for (int i = 0; i < N; i++) {
			nod[i] = new node(-1, 0);
		}
		String line[] = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(line[i]);
			if (a == -1) {
				continue;
			} else {
				nod[i].parent = a;
				nod[a].childcnt++;
				nod[a].children[i] = true;
			}
		}
		int del = Integer.parseInt(br.readLine());
		int tmp = nod[del].parent;
		nod[tmp].children[del] = false;
		nod[tmp].childcnt--;
		for (int i = 0; i < N; i++) {
			if (del != i && nod[i].parent == -1) {
				findLeaf(i);
			}
		}
		System.out.println(res);
	}

	private static void findLeaf(int p) {
		int tmp = 0, cnt = -1;
		if (nod[p].childcnt == 0) {
			res++;
			return;
		}
		while (tmp < nod[p].childcnt) {
			cnt++;
			if (nod[p].children[cnt]) {
				findLeaf(cnt);
				tmp++;
			}
		}
	}

	public static class node {
		int parent;
		boolean children[];
		int childcnt;

		public node(int parent, int childcnt) {
			this.parent = parent;
			this.childcnt = childcnt;
			this.children = new boolean[50];

			for (int i = 0; i < 50; i++) {
				this.children[i] = false;
			}
		}

	}
}
