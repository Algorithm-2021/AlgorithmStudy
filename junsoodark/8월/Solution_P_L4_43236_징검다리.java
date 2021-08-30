/*
정확성 테스트

테스트 1 〉	통과 (0.42ms, 71.6MB)
테스트 2 〉	통과 (0.62ms, 71.1MB)
테스트 3 〉	통과 (0.45ms, 72.3MB)
테스트 4 〉	통과 (4.16ms, 72.9MB)
테스트 5 〉	통과 (4.08ms, 61.4MB)
테스트 6 〉	통과 (27.98ms, 79.8MB)
테스트 7 〉	통과 (37.60ms, 62.1MB)
테스트 8 〉	통과 (42.05ms, 78.4MB)
테스트 9 〉	통과 (0.35ms, 59.7MB)

time : 1 Hour 0 Minute

풀이
이분 탐색으로 돌 사이의 거리를 선택하여 해당 거리보다 가까운 경우를 제거하며
제거해야 할 갯수보다 작거나 같게 제거 됐을때의 거리중 최대값을 리턴한다.


*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/43236
package algo_8월;

import java.util.Arrays;

public class Solution_P_L4_43236_징검다리 {
	public static void main(String[] args) {
		int distance = 25;
		int rocks[] = { 2, 14, 11, 21, 17 };
		int n = 2;
		System.out.println(solution(distance, rocks, n));
	}

	public static int solution(int distance, int[] rocks, int n) {
		Arrays.sort(rocks);
		int left = 1;
		int right = distance;
		int mid = 0;
		int answer = 0;
		while (left <= right) {
			int cnt = 0;
			int pre = 0;
			mid = (left + right) / 2;
			for (int i = 0; i < rocks.length; i++) {
				if (rocks[i] - pre < mid) {
					cnt++;
				} else {
					pre = rocks[i];
				}
			}
			if (distance - pre < mid) {
				cnt++;
			}
			if (cnt <= n) {
				if (answer < mid) {
					answer = mid;
				}
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return answer;
	}

}
