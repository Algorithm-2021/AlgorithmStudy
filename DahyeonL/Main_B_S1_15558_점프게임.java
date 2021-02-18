//푼시간 : 5시간
//27356	224
package algo_study_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_S1_15558_점프게임 {
	static int N, k, answer;
	static long map[][];
	static boolean isvisit[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new long[2][N + 1];
		isvisit = new boolean[2][N + 1];
		String tempL = br.readLine();
		String tempR = br.readLine();
		for (int i = 1; i < N + 1; i++) {
			map[0][i] = tempL.charAt(i - 1) - '0';// 왼
			map[1][i] = tempR.charAt(i - 1) - '0';// 오
		}
		answer = 0;
		BFS();
		System.out.println(answer);

	}


	static void BFS() {
		Queue<node> q = new LinkedList<node>();
		node temp = new node(false, 1);
		q.offer(temp); // 첫노드를 넣는다
		int sec = 1; // 초 카운트
		A: while (!q.isEmpty()) {
			int size = q.size(); // 단계확인
			B: while (size > 0) {
				node node = q.poll();
				int loc = 0;
				if (!node.loc)
					loc = 0; // 왼쪽
				else
					loc = 1; // 오른쪽
				isvisit[loc][node.num] = true; // 방문처리
				if (N < node.num + 1 || N < node.num + k) { // 가능할때
					answer = 1;
					break A;
				} else {
					if (node.loc) { // 오른쪽일경우
						if (map[0][node.num + k] == 1) {
							if (!isvisit[0][node.num + k]) {
								q.offer(new node(false, node.num + k));
								isvisit[0][node.num + k] = true;
							}
						}
					} else {// 왼쪽일경우
						if (map[1][node.num + k] == 1) {
							if (!isvisit[1][node.num + k]) {
								q.offer(new node(true, node.num + k));
								isvisit[1][node.num + k] = true;
							}
						}
					}
					if (node.num - 1 > 0 && node.num - 1 > sec) {
						if(loc==1&&node.num==14) {
							
						}
						if (map[loc][node.num - 1] == 1) { // i-1칸
							if (!isvisit[loc][node.num - 1]) {
								q.offer(new node(node.loc, node.num - 1));
								isvisit[loc][node.num - 1] = true;
							}
						}
					}
					
					if (map[loc][node.num + 1] == 1) { // i+1칸
						if (!isvisit[loc][node.num + 1]) {
							q.offer(new node(node.loc, node.num + 1));
							isvisit[loc][node.num + 1] = true;
						}
					}
					
					
				}
				size--;
			} // 한번의 이동이 끝남
			map[0][sec] = 0;
			map[1][sec] = 0;
			sec++;
		}

	}

	public static class node {
		boolean loc;// false :left, true :right
		int num;

		public node(boolean loc, int num) {
			this.loc = loc; // left :0, right:1
			this.num = num;
		}
	}

}
