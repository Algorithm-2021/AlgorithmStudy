/*
 * 시간초과
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ING_Main_B_G5_9019_DSLR_정세린 {
	static int min;
	static int A, B;
	static boolean[] visited = new boolean[10001];
	
	static class Temp{ // 연산 중간의 숫자, 현재까지의 연산과정을 저장
		int num;
		String op;
		
		public Temp(int num, String op) {
			this.num = num;
			this.op = op;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		L:for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			Queue<Temp> q = new LinkedList<Temp>();
			q.clear();
			Arrays.fill(visited, false);
			q.offer(new Temp(A, "")); // 처음 숫자와 연산과정
			visited[A] = true;
			
			while (!q.isEmpty()) {
				Temp cur = q.poll();
				if (cur.num == B) {
					System.out.println(cur.op);
					continue L; // 다음 테스트 케이스
				}
				
				// 1. D: * 2 % 10000
				int resD = (cur.num * 2) % 10000;
				if (!visited[resD]) {
					visited[resD] = true;
					q.offer(new Temp(resD, cur.op + "D"));
				}
				
				// 2. S: -1, 음수면 9999
				int resS = cur.num - 1;
				if (resS <= 0) resS = 9999;
				if (!visited[resS]) {
					visited[resS] = true;
					q.offer(new Temp(resS, cur.op + "S"));
				}
				
				// 3. L: 2341
				int resL = Integer.parseInt(Integer.toString(cur.num).substring(1) + Integer.toString(cur.num).substring(0, 1));
				if (!visited[resL]) {
					visited[resL] = true;
					q.offer(new Temp(resL, cur.op + "L"));
				}
				
				// 4. R: 4123
				int resR = Integer.parseInt(Integer.toString(cur.num).substring(Integer.toString(cur.num).length() - 1) 
						+ Integer.toString(cur.num).substring(0, Integer.toString(cur.num).length() - 1));
				if (!visited[resR]) {
					visited[resR] = true;
					q.offer(new Temp(resR, cur.op + "R"));
				}
			} // end of while
			
		} // end of test case
	}
	
}
