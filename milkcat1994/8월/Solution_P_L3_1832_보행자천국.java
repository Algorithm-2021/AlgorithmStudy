/*
 * -보행자 천국-
 * 1. 이전 좌표의 값에 따라 현재 위치에서의 자동차의 방향 갯수를 구한다.
 * 
 * 테스트 1 〉	통과 (127.73ms, 80.9MB)
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/1832
public class Solution_P_L3_1832_보행자천국 {
	int MOD = 20170805;
	public int solution(int m, int n, int[][] cityMap) {
		// 마지막 배열 >> 0 : 하단 이동, 1 : 우측 이동
		int[][][] arr = new int[m][n][2];
		
		arr[0][0][0] = arr[0][0][1] = 1;

		// row, col이 0인부분 미리 채우기
		for(int row = 1; row<m; ++row) {
			if(cityMap[row][0] == 1) break;
			arr[row][0][0] = arr[row-1][0][0];
		}
		
		for(int col = 1; col<n; ++col) {
			if(cityMap[0][col] == 1) break;
			arr[0][col][1] = arr[0][col-1][1];
		}
		
		int flag;
		for (int row = 1; row < m; ++row) {
			for (int col = 1; col < n; ++col) {
				flag = cityMap[row][col];
				if(flag != 1) {
					arr[row][col][0] += cityMap[row-1][col] == 2 ? arr[row-1][col][0] : arr[row-1][col][0] + arr[row-1][col][1];
					arr[row][col][0] %= MOD;
					
					arr[row][col][1] += cityMap[row][col-1] == 2 ? arr[row][col-1][1] : arr[row][col-1][0] + arr[row][col-1][1];
					arr[row][col][1] %= MOD;
				}
			}
		}
		return (arr[m-1][n-1][0] + arr[m-1][n-1][1]) % MOD;
	}


	public static void main(String[] args) {
		Solution_P_L3_1832_보행자천국 sol = new Solution_P_L3_1832_보행자천국();
		int m = 3;
		int n = 6;
		int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
		int answer = sol.solution(m, n, cityMap);
		System.out.println(answer);
	}
}