/*
 * memory : 41876 KB
 * time : 392 ms
 * 
 * solve time : 1 Hour 20 Minute
 * 
 * 풀이
 * 스택에 넣으며 내림차순 정렬이 되도록 스택에 넣고 빼며 K값과 현재 위치를 확인하고 마지막에 K=0이 되도록 하고 pos도 N이 되도록 스택을 관리한다.
 * 
 * 
 * 
 * 
 */

package algo_3월3주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B_G5_2812_크게만들기 {
	static double N, K;
	static int pos = 1;
	static char arr[];
	static StringBuilder result = new StringBuilder();
	static Stack<Character> s = new Stack<>();
	static Stack<Character> tmps = new Stack<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = br.readLine().toCharArray();
		s.push(arr[0]);
		while (K > 0 && pos < N) {
			while (!s.isEmpty() && s.peek() < arr[pos]) {
				s.pop();
				K--;
				if (K == 0) {
					break;
				}
			}
			s.push(arr[pos]);
			pos++;
		}
		while (K > 0) {
			s.pop();
			K--;
		}
		while (pos < N) {
			s.push(arr[pos]);
			pos++;
		}
		while (!s.isEmpty()) {
			tmps.push(s.pop());
		}
		while (!tmps.isEmpty()) {
			result.append(tmps.pop());
		}
		System.out.println(result);
	}

}
