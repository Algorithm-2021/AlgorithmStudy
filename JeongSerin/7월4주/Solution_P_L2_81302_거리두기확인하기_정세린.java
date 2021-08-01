/*
테스트 1 〉	통과 (0.05ms, 53MB)
테스트 2 〉	통과 (0.03ms, 52.3MB)
테스트 3 〉	통과 (0.03ms, 52MB)
테스트 4 〉	통과 (0.03ms, 52.6MB)
테스트 5 〉	통과 (0.02ms, 53.2MB)
테스트 6 〉	통과 (0.04ms, 52.3MB)
테스트 7 〉	통과 (0.02ms, 53.1MB)
테스트 8 〉	통과 (0.03ms, 52MB)
테스트 9 〉	통과 (0.03ms, 52.4MB)
테스트 10 〉	통과 (0.02ms, 52.3MB)
테스트 11 〉	통과 (0.02ms, 53.9MB)
테스트 12 〉	통과 (0.05ms, 52.6MB)
테스트 13 〉	통과 (0.02ms, 52.7MB)
테스트 14 〉	통과 (0.03ms, 52.3MB)
테스트 15 〉	통과 (0.03ms, 52.3MB)
테스트 16 〉	통과 (0.04ms, 53.9MB)
테스트 17 〉	통과 (0.03ms, 52.8MB)
테스트 18 〉	통과 (0.02ms, 52.2MB)
테스트 19 〉	통과 (0.02ms, 53.1MB)
테스트 20 〉	통과 (0.04ms, 52.2MB)
테스트 21 〉	통과 (0.02ms, 52MB)
테스트 22 〉	통과 (0.03ms, 53MB)
테스트 23 〉	통과 (0.02ms, 52.5MB)
테스트 24 〉	통과 (0.02ms, 52.1MB)
테스트 25 〉	통과 (0.02ms, 53.3MB)
테스트 26 〉	통과 (0.02ms, 52.3MB)
테스트 27 〉	통과 (0.04ms, 52.2MB)
테스트 28 〉	통과 (0.03ms, 52.4MB)
테스트 29 〉	통과 (0.03ms, 52MB)
테스트 30 〉	통과 (0.02ms, 51.7MB)
 * 40m
 * https://programmers.co.kr/learn/courses/30/lessons/81302
 */
package PROGRAMMERS;

import java.util.Arrays;

public class Solution_P_L2_81302_거리두기확인하기_정세린 {
	static class Solution {
		public int[] solution(String[][] places) {
			int[] answer = new int[5];

			L: for (int p = 0; p < 5; p++) {
				String[] place = places[p];
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (place[i].charAt(j) != 'P') continue;
						// 맨해튼거리가 1인경우
						if (i + 1 < 5 && place[i + 1].charAt(j) == 'P')
							continue L;
						if (j + 1 < 5 && place[i].charAt(j + 1) == 'P')
							continue L;
						// 맨해튼 거리가 2인경우
						if (i + 1 < 5 && j + 1 < 5 && place[i + 1].charAt(j + 1) == 'P') // 대각선 우하
							if (place[i + 1].charAt(j) != 'X' || place[i].charAt(j + 1) != 'X') continue L;

						if (i + 1 < 5 && j - 1 >= 0 && place[i + 1].charAt(j - 1) == 'P') // 대각선 좌하
							if (place[i + 1].charAt(j) != 'X' || place[i].charAt(j - 1) != 'X') continue L;

						if (i + 2 < 5 && place[i + 2].charAt(j) == 'P') // 두칸아래
							if (place[i + 1].charAt(j) != 'X') continue L;

						if (j + 2 < 5 && place[i].charAt(j + 2) == 'P') // 두칸 오른쪽
							if (place[i].charAt(j + 1) != 'X') continue L;
					}
				}

				answer[p] = 1;
			}

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
							 {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, 
							 {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, 
							 {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, 
							 {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		int[] answer = solution.solution(places);
		System.out.println(Arrays.toString(answer));
	}

}
