/*
 * 시간초과
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
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) 
			isbreak[Integer.parseInt(st.nextToken())] = true; // end of input
		
		int curup = N; // 현위치, +누르면서 올라갈 것
		int curdown = N; // 현위치, -누르면서 내려갈 것
		int cnt = 0;
		
		while(true) { // 숫자 버튼 누르기
			// 1. 목표채널에서 하나씩 올라가면서 확인
			int up = makeChannel(curup++);
			if (up > 0) {
				cnt += up;
				break;
			}
			
			// 2. 목표채널에서 하나씩 내려가면서 확인
			if (curdown >= 0) { // 채널이 0보다 작을 수 없음
				int down = makeChannel(curdown--);
				if (down > 0) {
					cnt += down;
					break;
				}
			}
			cnt++;
		}
		
		int min = Integer.min(cnt, Math.abs(N - 100)); // 현재위치에서 단순 버튼 누르기
		
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
