/*
 * memory : 11512 KB
 * time : 80 ms
 * 
 * solve time : 0 Hour 15 Minute
 * 
 * 풀이
 * G를 나누는 자연수를 구한뒤 그 자연수와 몫을 합하여 짝수인지 판별하고 합하여 2로 나눈 값이 현재 몸무게가 된다.
 * 자연수와 몫이 같은 경우는 기억하던 몸무게가 0이 되므로 제외한다.
 * 
 * 
 * 
 * 
 */

// 출처 : https://www.acmicpc.net/problem/1484
package algo_5월1주;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B_G4_1484_다이어트 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int a = (int) Math.sqrt(G);
		StringBuilder sb = new StringBuilder();
		for (int i = a; i > 0; i--) {
			int b = G / i;
			if (G != b * i || (b + i) % 2 != 0 || b == i) {
				continue;
			}
			sb.append((b + i) / 2).append('\n');
		}
		if (sb.length() == 0) {
			System.out.println(-1);
		}
		System.out.println(sb);
	}
}
