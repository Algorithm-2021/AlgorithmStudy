/*
정확성 테스트

테스트 1 〉	통과 (0.02ms, 52.8MB)
테스트 2 〉	통과 (0.03ms, 52.8MB)
테스트 3 〉	통과 (16.86ms, 57.6MB)
테스트 4 〉	통과 (11.95ms, 57.1MB)
테스트 5 〉	통과 (17.37ms, 60.1MB)
테스트 6 〉	통과 (12.41ms, 60.9MB)
테스트 7 〉	통과 (8.56ms, 59.7MB)
테스트 8 〉	통과 (6.98ms, 58.3MB)
테스트 9 〉	통과 (9.93ms, 59MB)
테스트 10 〉	통과 (6.39ms, 59.1MB)
테스트 11 〉	통과 (6.35ms, 60MB)

time : 0 Hour 40 Minute

풀이
시뮬레이션
맵을 돌며 숫자를 회전시키고 가장 작은 숫자를 회전할 때마다 추출한다.
*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/77485
package algo_5월2주;

import java.util.Arrays;

public class Solution_P_L1_77485_행렬테두리회전하기 {
	public static void main(String[] args) {
		int rows = 6;
		int columns = 6;
		int queries[][] = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		int result[] = solution(rows, columns, queries);
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		int[][] map = new int[rows][columns];
		int cnt = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				map[i][j] = cnt;
				cnt++;
			}
		}
		for (int i = 0; i < queries.length; i++) {
			answer[i] = rotate(map, queries[i][0] - 1, queries[i][1] - 1, queries[i][2] - 1, queries[i][3] - 1);
		}
		return answer;
	}

	public static int rotate(int map[][], int a, int b, int c, int d) {
		int min = map[a][b];
		int tmp = map[a][b];
		int i = a;
		int j = b;
		while (j < d) {
			if (min > map[i][j + 1]) {
				min = map[i][j + 1];
			}
			int tmpp = map[i][j + 1];
			map[i][j + 1] = tmp;
			tmp = tmpp;
			j++;
		}
		while (i < c) {
			if (min > map[i + 1][j]) {
				min = map[i + 1][j];
			}
			int tmpp = map[i + 1][j];
			map[i + 1][j] = tmp;
			tmp = tmpp;
			i++;
		}
		while (j > b) {
			if (min > map[i][j - 1]) {
				min = map[i][j - 1];
			}
			int tmpp = map[i][j - 1];
			map[i][j - 1] = tmp;
			tmp = tmpp;
			j--;
		}
		while (i > a) {
			if (min > map[i - 1][j]) {
				min = map[i - 1][j];
			}
			int tmpp = map[i - 1][j];
			map[i - 1][j] = tmp;
			tmp = tmpp;
			i--;
		}
		return min;
	}
}
