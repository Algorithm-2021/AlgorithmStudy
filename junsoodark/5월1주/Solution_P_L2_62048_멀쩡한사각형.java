/*
정확성 테스트
테스트 1 〉	통과 (0.02ms, 52.8MB)
테스트 2 〉	통과 (0.02ms, 51.9MB)
테스트 3 〉	통과 (0.02ms, 55MB)
테스트 4 〉	통과 (0.02ms, 53.4MB)
테스트 5 〉	통과 (0.03ms, 52.2MB)
테스트 6 〉	통과 (0.02ms, 52.5MB)
테스트 7 〉	통과 (0.02ms, 53.1MB)
테스트 8 〉	통과 (0.02ms, 53.8MB)
테스트 9 〉	통과 (0.02ms, 51.9MB)
테스트 10 〉	통과 (0.02ms, 52.1MB)
테스트 11 〉	통과 (0.02ms, 55.8MB)
테스트 12 〉	통과 (0.04ms, 52.1MB)
테스트 13 〉	통과 (0.03ms, 52.8MB)
테스트 14 〉	통과 (0.04ms, 53.8MB)
테스트 15 〉	통과 (0.04ms, 52.4MB)
테스트 16 〉	통과 (0.03ms, 52.7MB)
테스트 17 〉	통과 (0.04ms, 52.2MB)
테스트 18 〉	통과 (0.02ms, 51.9MB)

time : 0 Hour 20 Minute

풀이
전체 사각형의 갯수에서 w와 h만큼 뺀후 w와 h의 최대 공약수 만큼 더한다.

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/62048
package algo_5월1주;

public class Solution_P_L2_62048_멀쩡한사각형 {
	public static void main(String[] args) {
		int w = 8;
		int h = 12;
		System.out.println(solution(w, h));
	}

	public static long solution(int w, int h) {
		int big, small;
		int mok, nmg;
		int maxD;
		if (w >= h) {
			big = w;
			small = h;
		} else {
			big = h;
			small = w;
		}
		while (true) {
			mok = big / small;
			nmg = big - mok * small;
			if (nmg == 0) {
				maxD = small;
				break;
			} else {
				big = small;
				small = nmg;
			}
		}
		long answer = (long) h * (long) w - h - w + maxD;
		return answer;
	}
}
