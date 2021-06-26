/*
정확성 테스트

테스트 1 〉	통과 (0.02ms, 52.7MB)
테스트 2 〉	통과 (20.90ms, 53MB)
테스트 3 〉	통과 (24.80ms, 54.3MB)
테스트 4 〉	통과 (22.30ms, 53.5MB)
테스트 5 〉	통과 (25.55ms, 54.2MB)
테스트 6 〉	통과 (24.87ms, 53.8MB)
테스트 7 〉	통과 (25.35ms, 53.1MB)
테스트 8 〉	통과 (24.42ms, 53.5MB)
테스트 9 〉	통과 (0.02ms, 51.5MB)
테스트 10 〉	통과 (0.22ms, 52.2MB)
테스트 11 〉	통과 (0.04ms, 52.5MB)
테스트 12 〉	통과 (0.02ms, 52.2MB)
테스트 13 〉	통과 (0.10ms, 53.2MB)

효율성 테스트

테스트 1 〉	통과 (73.47ms, 63.2MB)
테스트 2 〉	통과 (47.38ms, 59.1MB)
테스트 3 〉	통과 (59.17ms, 61.8MB)
테스트 4 〉	통과 (59.28ms, 61MB)
테스트 5 〉	통과 (58.70ms, 61.5MB)
테스트 6 〉	통과 (61.03ms, 60.6MB)
테스트 7 〉	통과 (60.55ms, 60.8MB)
테스트 8 〉	통과 (60.42ms, 60.9MB)

time : 0 Hour 10 Minute

풀이
스택을 이용하여 맨위에 있는것과 비교

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12973
package algo_6월4주;

import java.util.Stack;

public class Solution_P_L2_12973_짝지어제거하기 {
	public static void main(String[] args) {
		String s = "baabaa";
		System.out.println(solution(s));
	}

	static Stack<Character> st = new Stack<>();

	public static int solution(String s) {
		char arr[] = s.toCharArray();
		if ((s.length() % 2) != 0) {
			return 0;
		}
		int size = 1;
		st.push(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if ((arr.length - i) < size) {
				return 0;
			}
			if (!st.isEmpty() && st.peek() == arr[i]) {
				st.pop();
				size--;
			} else {
				st.push(arr[i]);
				size++;
			}
		}
		if (size == 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
