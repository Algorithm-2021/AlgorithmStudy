/*
 * 67852KB
 * 256ms
 * 1D
 * 완탐
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G5_1107_리모컨_정세린 {
	static boolean[] isbreak; // 0 ~ 9 까지 고장 여부
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		isbreak = new boolean[10];
		StringTokenizer st = null;
		if (M > 0) st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) 
			isbreak[Integer.parseInt(st.nextToken())] = true; // end of input
		
		boolean remote = false;
		
		for (int i = 0; i < 10; i++) { // 누를수 있는 버튼이 없는 경우를 확인하기 위함
			if (!isbreak[i]) {
				remote = true;
				break;
			}
		}
		
		if (!remote) { // 누를 수 있는 버튼이 없는 경우
			System.out.println(Math.abs(N - 100));
			return;
		}
		
		// 0 ~
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= 1000000; i++) {
			int tmp = makeChannel(i);
			if (tmp < 0) continue;
			int cnt = Math.abs(i - N);
			if (cnt > min) break;
			cnt += makeChannel(i);
			min = Integer.min(min, cnt);
		}
		min = Integer.min(min, Math.abs(N - 100)); // 현재위치에서 단순 버튼 누르기와 비교
		System.out.println(min);
		
	} // end of main

	static int makeChannel(int num) { // num을 리모컨 숫자버튼을 눌러 만들 수 있는지
		int digitNum = Integer.toString(num).length(); // 몇개 자리인지?
		
		int cnt = 0;
		while(cnt++ < digitNum) {
			int tmp = num % 10; // 각 자리수
			if (isbreak[tmp]) return -1; // 고장난 버튼이면
			num = num / 10;
		}
		return digitNum; // 만들 수 있음
	}
	
}
