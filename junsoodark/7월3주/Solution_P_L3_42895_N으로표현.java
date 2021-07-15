/*
정확성 테스트

테스트 1 〉	통과 (15.21ms, 52.8MB)
테스트 2 〉	통과 (12.16ms, 52.9MB)
테스트 3 〉	통과 (12.14ms, 52.8MB)
테스트 4 〉	통과 (33.96ms, 54.9MB)
테스트 5 〉	통과 (16.47ms, 53.6MB)
테스트 6 〉	통과 (13.22ms, 54.3MB)
테스트 7 〉	통과 (12.88ms, 53.5MB)
테스트 8 〉	통과 (23.69ms, 54.5MB)
테스트 9 〉	통과 (23.16ms, 53.5MB)

time : 1 Hour 0 Minute

풀이
ex) N = 5
1개 사용 5
2개 사용 55
3개 사용 555
...
으로 초기화 한 후
2개 사용 = 1개사용 (+-/*) 1개사용
3개 사용 = 1개사용 (+-/*) 2개사용 U 2개사용 (+-/*) 1개사용
으로 점점 늘려가고 중복 계산을 피하기 위해 hash를 사용.
계산해 가며 number과 같은 수를 찾아서 사용 갯수 리턴

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42895
package algo_7월3주;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution_P_L3_42895_N으로표현 {
	public static void main(String[] args) {
		int N = 5;
		int number = 12;
		System.out.println(solution(N, number));
	}

	public static int solution(int N, int number) {
		HashSet<Integer> hs = new HashSet<Integer>();
		ArrayList<Integer>[] ar = new ArrayList[9];
		String str = "";
		for (int i = 1; i < 9; i++) {
			str = str + N;
			ar[i] = new ArrayList<Integer>();
			int in = Integer.parseInt(str);
			if (number == in) {
				return i;
			}
			hs.add(in);
			ar[i].add(in);
		}
		for (int i = 2; i < 9; i++) {
			for (int j = 1; j < i; j++) {
				for (int a = 0; a < ar[i - j].size(); a++) {
					for (int b = 0; b < ar[j].size(); b++) {
						int arA = ar[i - j].get(a);
						int arB = ar[j].get(b);
						int sum = arA + arB;
						if (sum == number) {
							return i;
						}
						if (hs.add(sum)) {
							ar[i].add(sum);
						}
						int minus = arA - arB;
						if (minus > 0) {
							if (minus == number) {
								return i;
							}
							if (hs.add(minus)) {
								ar[i].add(minus);
							}
						}
						int multi = arA * arB;
						if (multi == number) {
							return i;
						}
						if (hs.add(multi)) {
							ar[i].add(multi);
						}
						if (arB > 0) {
							int div = arA / arB;
							if (div > 0) {
								if (div == number) {
									return i;
								}
								if (hs.add(div)) {
									ar[i].add(div);
								}
							}
						}
					}
				}
			}
		}
		return -1;
	}
}
