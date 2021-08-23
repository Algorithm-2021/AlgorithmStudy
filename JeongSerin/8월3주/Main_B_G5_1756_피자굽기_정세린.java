/*
 * 40m
 * 93660KB
 * 592ms
 * 오븐 위의 지름보다 아래지름이 크면 윗지름으로 저장
 * 5 6 4 3 6 2 3 -> 5 5 4 3 3 2 2
 * 끝에서부터 탐색. 피자 담기
 * https://www.acmicpc.net/problem/1756
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_1756_피자굽기_정세린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken()); // 오븐깊이
		int N = Integer.parseInt(st.nextToken()); // 반죽수
		int[] oven = new int[D + 1];
		Queue<Integer> pizza = new LinkedList<Integer>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= D; i++) {
			oven[i] = Integer.parseInt(st.nextToken());
			if (i > 1 && oven[i - 1] < oven[i]) oven[i] = oven[i - 1]; // 아래가 위에 지름보다 커도 소용x. 위지름과 같은값으로 저장
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) pizza.offer(Integer.parseInt(st.nextToken()));
		// end of input
		
		int pos = D;
		for (int i = D; i >= 1; i--) {
			if (pizza.isEmpty()) break;
			if (pizza.peek() <= oven[i]) { // 아래에서부터 확인, 오븐지름이 피자 지름과 같거나 크면
				pizza.poll();
				pos = i; // 피자를 쌓은 위치
			}
		}
		
		if (pizza.isEmpty()) System.out.println(pos);
		else System.out.println(0);
	}

}
