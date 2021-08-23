/*
정확성 테스트

테스트 1 〉	통과 (0.03ms, 67.8MB)
테스트 2 〉	통과 (0.03ms, 58.7MB)
테스트 3 〉	통과 (0.07ms, 60.2MB)
테스트 4 〉	통과 (0.29ms, 69.2MB)
테스트 5 〉	통과 (0.91ms, 59MB)
테스트 6 〉	통과 (2.46ms, 60.3MB)
테스트 7 〉	통과 (4.94ms, 60MB)
테스트 8 〉	통과 (8.46ms, 75.7MB)
테스트 9 〉	통과 (9.01ms, 77.8MB)
테스트 10 〉	통과 (9.67ms, 60.2MB)

time : 1 Hour 0 Minute

풀이
플로이드 와샬로 각 정점을 이어준후 자신을 제외하고 모든 정점과 연결이 되면 순위를 정할수 있다고 판단한후 카운트한다.


*/

// 출처 : https://programmers.co.kr/learn/courses/30/lessons/49191
package algo_8월;

public class Solution_P_L3_49191_순위 {
	public static void main(String[] args) {
		int n = 5;
		int results[][] = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
		System.out.println(solution(n, results));
	}

	public static int solution(int n, int[][] results) {
		int max = 1000000;
		int arr[][] = new int[n + 1][n + 1];
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr.length; j++) {
				arr[i][j] = max;
			}
		}
		for (int[] res : results) {
			arr[res[0]][res[1]] = 1;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr.length; j++) {
				for (int k = 1; k < arr.length; k++) {
					if (arr[j][k] > arr[j][i] + arr[i][k]) {
						arr[j][k] = arr[j][i] + arr[i][k];
					}
				}
			}
		}
		int answer = 0;
		for (int i = 1; i < arr.length; i++) {
			boolean find = false;
			for (int j = 1; j < arr.length; j++) {
				if (i == j) {
					continue;
				}
				if (arr[i][j] == max && arr[j][i] == max) {
					find = true;
					break;
				}
			}
			if (!find) {
				answer++;
			}
		}
		return answer;
	}

}
