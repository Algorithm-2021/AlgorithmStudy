/*
정확성 테스트

테스트 1 〉	통과 (0.06ms, 52.1MB)
테스트 2 〉	통과 (0.04ms, 53.9MB)
테스트 3 〉	통과 (0.07ms, 52.4MB)
테스트 4 〉	통과 (0.04ms, 52.6MB)
테스트 5 〉	통과 (0.14ms, 52.7MB)
테스트 6 〉	통과 (0.04ms, 52.7MB)
테스트 7 〉	통과 (0.05ms, 51.9MB)
테스트 8 〉	통과 (0.06ms, 52.3MB)
테스트 9 〉	통과 (0.03ms, 52.5MB)
테스트 10 〉	통과 (0.08ms, 52.1MB)
테스트 11 〉	통과 (0.09ms, 52.7MB)
테스트 12 〉	통과 (0.72ms, 51.6MB)
테스트 13 〉	통과 (0.39ms, 52.7MB)
테스트 14 〉	통과 (8.83ms, 53.3MB)
테스트 15 〉	통과 (12.90ms, 53.6MB)
테스트 16 〉	통과 (0.19ms, 53.1MB)
테스트 17 〉	통과 (0.44ms, 53MB)
테스트 18 〉	통과 (6.86ms, 52.8MB)
테스트 19 〉	통과 (8.16ms, 52.8MB)
테스트 20 〉	통과 (9.52ms, 52.8MB)
테스트 21 〉	통과 (15.94ms, 53.5MB)
테스트 22 〉	통과 (8.32ms, 52.5MB)
테스트 23 〉	통과 (14.30ms, 53.1MB)
테스트 24 〉	통과 (15.47ms, 53.2MB)
테스트 25 〉	통과 (19.32ms, 53MB)
테스트 26 〉	통과 (18.58ms, 54.1MB)
테스트 27 〉	통과 (17.21ms, 55.7MB)
테스트 28 〉	통과 (18.95ms, 55.5MB)
테스트 29 〉	통과 (12.66ms, 53.6MB)
테스트 30 〉	통과 (22.51ms, 57.5MB)
테스트 31 〉	통과 (1.32ms, 52.3MB)
테스트 32 〉	통과 (2.80ms, 53.4MB)

time : 0 Hour 30 Minute

풀이
마을의 갯수만큼 반복하며 1번마을부터 1개씩 가지 뻗어가며 거리 계산

*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12978?language=java
package algo_5월4주;

public class Solution_P_L2_12978_배달 {
	public static void main(String[] args) {
		int N = 5;
		int road[][] = { { 1, 2, 1 }, { 2, 3, 3 }, { 5, 2, 2 }, { 1, 4, 2 }, { 5, 3, 1 }, { 5, 4, 2 } };
		int K = 3;
		System.out.println(solution(N, road, K));
	}

	static int len[];

	public static int solution(int N, int[][] road, int K) {
		len = new int[N];
		for (int i = 0; i < N; i++) {
			len[i] = -1;
		}
		len[0] = 0;
		for (int i = 0; i < N; i++) {
			makeRoad(road);
		}
		int answer = 1;
		for (int i = 1; i < N; i++) {
			if (len[i] > 0 && len[i] <= K) {
				answer++;
			}
		}

		return answer;
	}

	public static void makeRoad(int[][] road) {
		for (int i = 0; i < road.length; i++) {
			for (int j = 0; j < 3; j++) {
				int start = road[i][0] - 1;
				int end = road[i][1] - 1;
				int length = road[i][2];
				if (len[start] == 0) {
					if (len[end] == -1 || len[end] > length)
						len[end] = length;
				} else if (len[end] == 0) {
					if (len[start] == -1 || len[start] > length)
						len[start] = length;
				} else if (len[start] > 0 && len[end] > 0) {
					if (len[start] > len[end]) {
						int lentmp = len[end] + length;
						if (len[start] > lentmp) {
							len[start] = lentmp;
						}
					} else if (len[start] < len[end]) {
						int lentmp = len[start] + length;
						if (len[end] > lentmp) {
							len[end] = lentmp;
						}
					}
				} else if (len[start] > 0) {
					len[end] = len[start] + length;
				} else if (len[end] > 0) {
					len[start] = len[end] + length;
				}
			}
		}
	}

}
