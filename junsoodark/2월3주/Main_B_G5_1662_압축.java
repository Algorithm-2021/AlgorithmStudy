/*
 * memory : 11512 KB
 * time : 76 ms
 * 
 * solve time : 1 Hour 30 Minute
 * 
 * 풀이
 * 스택 구조를 사용하여 문자열의 뒤부터 넣으며 계산
 * 
 * 
 * 
 * 
 */

package algo_2월3주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_B_G5_1662_압축 {
	static int res = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char arr[] = br.readLine().toCharArray();
		Stack<Integer> s = new Stack<>();
		for (int i = arr.length - 1; i >= 0; i--) {
			if (s.empty()) {
				if (arr[i] == ')') {
					s.push(-1);
				} else {
					s.push(1);
				}
			} else {
				if (arr[i] == ')') {
					s.push(-1);
				} else if (arr[i] == '(') {
					int tmp = s.pop();
					if (tmp == -1 || tmp == 0) {
						i--;
					} else {
						i--;
						if (arr[i] == '0') {
							s.pop();
							continue;
						}
						tmp = tmp * (arr[i] - '0');
						s.pop();
						if (s.empty()) {
							s.push(tmp);
							continue;
						} else {
							int t = s.pop();
							if (t == -1) {
								s.push(-1);
								s.push(tmp);
							} else {
								tmp = tmp + t;
								s.push(tmp);
							}
						}
					}
				} else {
					int tmp = s.pop();
					if (tmp == -1) {
						s.push(-1);
						s.push(1);
					} else {
						s.push(tmp + 1);
					}
				}
			}
		}
		if (s.empty()) {
			System.out.println(0);
		} else {
			System.out.println(s.pop());
		}
	}
}
