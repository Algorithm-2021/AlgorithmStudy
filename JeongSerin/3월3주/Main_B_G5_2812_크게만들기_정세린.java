/*
 * 64892KB
 * 648ms
 * 40m
 * 스택사용
 */
package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B_G5_2812_크게만들기_정세린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // N자리 숫자
		int K = Integer.parseInt(st.nextToken()); // 지우는 숫자의 개수
		String number = br.readLine();
		
		// 1. 스택사용
		Stack<Character> s = new Stack<>();
		s.add(number.charAt(0)); // 첫번째 숫자 넣기
		int index = 1; // 첫번째 자리부터 비교 시작
		
		int delete = 0; // 숫자 삭제 횟수
		L:while(delete < K) {
			// 스택 꼭대기의 수가 더 작으면 꺼냄
			while (!s.isEmpty() && s.peek() < number.charAt(index)) {
				s.pop();
				delete++;
				if (delete >= K) break L;
			}
			
			// 다음수를 푸시
			s.push(number.charAt(index));
			index++; // 다음 숫자 비교를 위한 index
			if (index >= N) break L;
		}
		
		// 지운 숫자가 K보다 작을경우 (같은 숫자가 여러개면 그럴수있음...)
		if (delete < K) {
			while(!s.isEmpty() && delete < K) {
				s.pop(); // 숫자를 지움
				delete++;
			}
		}
		
		// 아직 스택에 푸시되지 않은 숫자들 넣어둠
		while (index < N) {
			s.push(number.charAt(index));
			index++;
		}
		
		// 스택에 있는 숫자 나열
		String ans = s.toString();
		ans = ans.substring(1, ans.length() - 1).replace(", ", "");
		System.out.println(ans);
	} // end of main

}
